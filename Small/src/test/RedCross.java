package test;

import acm.graphics.*;
import java.awt.*;

public class RedCross extends GCompound{
	private static final int CROSSBAR_LENGTH = 60;
	private static final int CROSSBAR_WIDTH = 20;
	
	public RedCross(){
		//horizontal bar
		GRect hCrossBar = new GRect(CROSSBAR_LENGTH,CROSSBAR_WIDTH);
		//vertical bar
		GRect vCrossBar = new GRect(CROSSBAR_WIDTH,CROSSBAR_LENGTH);
		hCrossBar.setFilled(true);
		vCrossBar.setFilled(true);
//		add(hCrossBar,(getWidth()-hCrossBar.getWidth())/2,(getHeight()-hCrossBar.getHeight())/2);
//		System.out.println("width="+getWidth()+"height="+getHeight());
//		add(vCrossBar,(getWidth()-vCrossBar.getWidth())/2,(getHeight()-vCrossBar.getHeight())/2);
//		System.out.println("width="+getWidth()+"height="+getHeight());
//		add(hCrossBar, -CROSSBAR_LENGTH / 2+10, -CROSSBAR_WIDTH / 2+10);
//		add(vCrossBar, -CROSSBAR_WIDTH / 2+10, -CROSSBAR_LENGTH / 2+10);
		add(hCrossBar, -hCrossBar.getWidth() / 2, -hCrossBar.getHeight() / 2);
		add(vCrossBar, -vCrossBar.getWidth() / 2, -vCrossBar.getHeight()/ 2);
		setColor(Color.RED);
	}
}
