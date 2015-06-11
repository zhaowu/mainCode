package name;

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
//public class NameSurfer extends ConsoleProgram implements NameSurferConstants {
	private JLabel label;
	private JTextField Name;
	private JButton getGraph;
	private JButton Clear;
	private NameSurferDataBase namesdb;
	private NameSurferGraph graph;
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods //
		setSize(1000,800);
		setUpInteractors();
		graph = new NameSurferGraph();
		add(graph);
		//for JButton
		addActionListeners();
		//read the file of names 
		namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if(e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}
		//for both JTextField and JButton
		else {
			if (Name.getText().equals("")){
				Name.setText("Give me a name");
			}
			else{
			String enteredName = Name.getText();
			NameSurferEntry rankings = namesdb.findEntry(enteredName);
			if(rankings != null) {
				graph.addEntry(rankings);
				graph.update();
			}
			}
		}
	}
	public void mouseClicked(MouseEvent e){
		if (e.getSource()==Name){
			Name.setText("");
		}
	}
	
	private void setUpInteractors(){
		label = new JLabel ("Name: ");
		Name = new JTextField(20);
		Name.addActionListener(this);
		Name.addMouseListener(this);
		getGraph = new JButton("Graph");
		Clear = new JButton ("Clear");
		add(label,SOUTH);
		add(Name,SOUTH);
		add(getGraph,SOUTH);
		add(Clear,SOUTH);

	}
}
