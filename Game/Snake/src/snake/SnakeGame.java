package snake;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
//add from local
public class SnakeGame {
        public static void main(String[] args) {
                SnakeFrame frame = new SnakeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }
}

// ----------记录状态的线程
class StatusRunnable implements Runnable {
        public StatusRunnable(Snake snake, JLabel statusLabel, JLabel scoreLabel) {
                this.statusLabel = statusLabel;
                this.scoreLabel = scoreLabel;
                this.snake = snake;
        }

        public void run() {
                String sta = "";
                String spe = "";
                while (true) {

                        switch (snake.status) {
                        case Snake.RUNNING:
                                sta = "Running";
                                break;
                        case Snake.PAUSED:
                                sta = "Paused";
                                break;
                        case Snake.GAMEOVER:
                                sta = "GameOver";
                                break;
                        }
                        statusLabel.setText(sta);
                        scoreLabel.setText("" + snake.score);
                        try {
                                Thread.sleep(100);
                        } catch (Exception e) {
                        }
                }
        }

        private JLabel scoreLabel;
        private JLabel statusLabel;
        private Snake snake;
}

// ----------蛇运动以及记录分数的线程
class SnakeRunnable implements Runnable {
        public SnakeRunnable(Snake snake, Component component) {
                this.snake = snake;
                this.component = component;
        }

        public void run() {
                while (true) {
                        try {
                                snake.move();
                                component.repaint();
                                Thread.sleep(snake.speed);
                        } catch (Exception e) {
                        }
                }
        }

        private Snake snake;
        private Component component;
}

class Snake {
        boolean isRun;// ---------是否运动中
        ArrayList<Node> body;// -----蛇体
        Node food;// --------食物
        int derection;// --------方向
        int score;
        int status;
        int speed;
        public static final int SLOW = 500;
        public static final int MID = 300;
        public static final int FAST = 100;
        public static final int RUNNING = 1;
        public static final int PAUSED = 2;
        public static final int GAMEOVER = 3;
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int RIGHT = 3;
        public static final int DOWN = 4;

        public Snake() {
                speed = Snake.SLOW;
                score = 0;
                isRun = false;
                status = Snake.PAUSED;
                derection = Snake.RIGHT;
                body = new ArrayList<Node>();
                body.add(new Node(60, 20));
                body.add(new Node(40, 20));
                body.add(new Node(20, 20));
                makeFood();
        }

        // ------------判断食物是否被蛇吃掉
        // -------如果食物在蛇运行方向的正前方，并且与蛇头接触，则被吃掉
        private boolean isEaten() {
                Node head = body.get(0);
                if (derection == Snake.RIGHT && (head.x + Node.W) == food.x
                                && head.y == food.y)
                        return true;
                if (derection == Snake.LEFT && (head.x - Node.W) == food.x
                                && head.y == food.y)
                        return true;
                if (derection == Snake.UP && head.x == food.x
                                && (head.y - Node.H) == food.y)
                        return true;
                if (derection == Snake.DOWN && head.x == food.x
                                && (head.y + Node.H) == food.y)
                        return true;
                else
                        return false;
        }

        // ----------是否碰撞
        private boolean isCollsion() {
                Node node = body.get(0);
                // ------------碰壁
                if (derection == Snake.RIGHT && node.x == 280)
                        return true;
                if (derection == Snake.UP && node.y == 0)
                        return true;
                if (derection == Snake.LEFT && node.x == 0)
                        return true;
                if (derection == Snake.DOWN && node.y == 380)
                        return true;
                // --------------蛇头碰到蛇身
                Node temp = null;
                int i = 0;
                for (i = 3; i < body.size(); i++) {
                        temp = body.get(i);
                        if (temp.x == node.x && temp.y == node.y)
                                break;
                }
                if (i < body.size())
                        return true;
                else
                        return false;
        }

        // -------在随机的地方产生食物
        public void makeFood() {
                Node node = new Node(0, 0);
                boolean isInBody = true;
                int x = 0, y = 0;
                int X = 0, Y = 0;
                int i = 0;
                while (isInBody) {
                        x = (int) (Math.random() * 15);
                        y = (int) (Math.random() * 20);
                        X = x * Node.W;
                        Y = y * Node.H;
                        for (i = 0; i < body.size(); i++) {
                                if (X == body.get(i).x && Y == body.get(i).y)
                                        break;
                        }
                        if (i < body.size())
                                isInBody = true;
                        else
                                isInBody = false;
                }
                food = new Node(X, Y);
        }

        // ---------改变运行方向
        public void changeDerection(int newDer) {
                if (derection % 2 != newDer % 2)// -------如果与原来方向相同或相反，则无法改变
                        derection = newDer;
        }

        public void move() {
                if (isEaten()) {// -----如果食物被吃掉
                        body.add(0, food);// --------把食物当成蛇头成为新的蛇体
                        score += 10;
                        makeFood();// --------产生食物
                } else if (isCollsion())// ---------如果碰壁或自身
                {
                        isRun = false;
                        status = Snake.GAMEOVER;// -----结束
                } else if (isRun) {// ----正常运行（不吃食物，不碰壁，不碰自身）
                        Node node = body.get(0);
                        int X = node.x;
                        int Y = node.y;
                        // ------------蛇头按运行方向前进一个单位
                        switch (derection) {
                        case 1:
                                X -= Node.W;
                                break;
                        case 2:
                                Y -= Node.H;
                                break;
                        case 3:
                                X += Node.W;
                                break;
                        case 4:
                                Y += Node.H;
                                break;
                        }
                        body.add(0, new Node(X, Y));
                        // ---------------去掉蛇尾
                        body.remove(body.size() - 1);
                }
        }
}

// ---------组成蛇身的单位，食物
class Node {
        public static final int W = 20;
        public static final int H = 20;
        int x;
        int y;

        public Node(int x, int y) {
                this.x = x;
                this.y = y;
        }
}

// ------画板
class SnakePanel extends JPanel {
        Snake snake;

        public SnakePanel(Snake snake) {
                this.snake = snake;
        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Node node = null;
                for (int i = 0; i < snake.body.size(); i++) {// ---红蓝间隔画蛇身
                        if (i % 2 == 0)
                                g.setColor(Color.blue);
                        else
                                g.setColor(Color.yellow);
                        node = snake.body.get(i);
                        g.fillRect(node.x, node.y, node.H, node.W);// *******************试用*********************
                }
                node = snake.food;
                g.setColor(Color.red);
                g.fillRect(node.x, node.y, node.H, node.W);
        }
}

class SnakeFrame extends JFrame {
        private JLabel statusLabel;
        private JLabel speedLabel;
        private JLabel scoreLabel;
        private JPanel snakePanel;
        private Snake snake;
        private JMenuBar bar;
        JMenu gameMenu;
        JMenu helpMenu;
        JMenu speedMenu;
        JMenuItem newItem;
        JMenuItem pauseItem;
        JMenuItem beginItem;
        JMenuItem helpItem;
        JMenuItem aboutItem;
        JMenuItem slowItem;
        JMenuItem midItem;
        JMenuItem fastItem;

        public SnakeFrame() {
                init();
                ActionListener l = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == pauseItem)
                                        snake.isRun = false;
                                if (e.getSource() == beginItem)
                                        snake.isRun = true;
                                if (e.getSource() == newItem) {
                                        newGame();
                                }
                                // ------------菜单控制运行速度
                                if (e.getSource() == slowItem) {
                                        snake.speed = Snake.SLOW;
                                        speedLabel.setText("Slow");
                                }
                                if (e.getSource() == midItem) {
                                        snake.speed = Snake.MID;
                                        speedLabel.setText("Mid");
                                }
                                if (e.getSource() == fastItem) {
                                        snake.speed = Snake.FAST;
                                        speedLabel.setText("Fast");
                                }
                        }
                };
                pauseItem.addActionListener(l);
                beginItem.addActionListener(l);
                newItem.addActionListener(l);
                aboutItem.addActionListener(l);
                slowItem.addActionListener(l);
                midItem.addActionListener(l);
                fastItem.addActionListener(l);
                addKeyListener(new KeyListener() {
                        public void keyPressed(KeyEvent e) {
                                switch (e.getKeyCode()) {
                                // ------------方向键改变蛇运行方向
                                case KeyEvent.VK_DOWN:// 
                                        snake.changeDerection(Snake.DOWN);
                                        break;
                                case KeyEvent.VK_UP:// 
                                        snake.changeDerection(Snake.UP);
                                        break;
                                case KeyEvent.VK_LEFT:// 
                                        snake.changeDerection(Snake.LEFT);
                                        break;
                                case KeyEvent.VK_RIGHT:// 
                                        snake.changeDerection(Snake.RIGHT);
                                        break;
                                // 空格键，游戏暂停或继续
                                case KeyEvent.VK_SPACE:// 
                                        if (snake.isRun == true) {
                                                snake.isRun = false;
                                                snake.status = Snake.PAUSED;
                                                break;
                                        }
                                        if (snake.isRun == false) {
                                                snake.isRun = true;
                                                snake.status = Snake.RUNNING;
                                                break;
                                        }
                                }
                        }

                        public void keyReleased(KeyEvent k) {
                        }

                        public void keyTyped(KeyEvent k) {
                        }
                });
        }

        private void init() {
                speedLabel = new JLabel();
                snake = new Snake();
                setSize(380, 460);
                setLayout(null);
                this.setResizable(false);
                bar = new JMenuBar();
                gameMenu = new JMenu("Game");
                newItem = new JMenuItem("New Game");
                gameMenu.add(newItem);
                pauseItem = new JMenuItem("Pause");
                gameMenu.add(pauseItem);
                beginItem = new JMenuItem("Continue");
                gameMenu.add(beginItem);
                helpMenu = new JMenu("Help");
                aboutItem = new JMenuItem("About");
                helpMenu.add(aboutItem);
                speedMenu = new JMenu("Speed");
                slowItem = new JMenuItem("Slow");
                fastItem = new JMenuItem("Fast");
                midItem = new JMenuItem("Middle");
                speedMenu.add(slowItem);
                speedMenu.add(midItem);
                speedMenu.add(fastItem);
                bar.add(gameMenu);
                bar.add(helpMenu);
                bar.add(speedMenu);
                setJMenuBar(bar);
                statusLabel = new JLabel();
                scoreLabel = new JLabel();
                snakePanel = new JPanel();
                snakePanel.setBounds(0, 0, 300, 400);
                snakePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
                add(snakePanel);
                statusLabel.setBounds(300, 25, 60, 20);
                add(statusLabel);
                scoreLabel.setBounds(300, 20, 60, 20);
                add(scoreLabel);
                JLabel temp = new JLabel("状态");
                temp.setBounds(310, 5, 60, 20);
                add(temp);
                temp = new JLabel("分数");
                temp.setBounds(310, 105, 60, 20);
                add(temp);
                temp = new JLabel("速度");
                temp.setBounds(310, 55, 60, 20);
                add(temp);
                speedLabel.setBounds(310, 75, 60, 20);
                add(speedLabel);
        }

        private void newGame() {
                this.remove(snakePanel);
                this.remove(statusLabel);
                this.remove(scoreLabel);
                speedLabel.setText("Slow");
                statusLabel = new JLabel();
                scoreLabel = new JLabel();
                snakePanel = new JPanel();
                snake = new Snake();
                snakePanel = new SnakePanel(snake);
                snakePanel.setBounds(0, 0, 300, 400);
                snakePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
                Runnable r1 = new SnakeRunnable(snake, snakePanel);
                Runnable r2 = new StatusRunnable(snake, statusLabel, scoreLabel);
                Thread t1 = new Thread(r1);
                Thread t2 = new Thread(r2);
                t1.start();
                t2.start();
                add(snakePanel);
                statusLabel.setBounds(310, 25, 60, 20);
                add(statusLabel);
                scoreLabel.setBounds(310, 125, 60, 20);
                add(scoreLabel);
        }

}