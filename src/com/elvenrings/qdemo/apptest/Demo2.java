package com.elvenrings.qdemo.apptest;

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class Demo2 extends JFrame
{
	private JDesktopPane desktopPane;
	private JInternalFrame internalFrame;
	
	public Demo2()
	{
		desktopPane = new JDesktopPane();
		internalFrame = new JInternalFrame("Internal Frame");
		internalFrame.setSize(400, 300);
		internalFrame.setResizable(false);
		internalFrame.setClosable(false);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.setVisible(true);
		desktopPane.add(internalFrame);
		add(desktopPane);
		
	}
	public static void main(String[] args)
	{
		Demo2 demo = new Demo2();
		demo.setSize(640, 500);
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demo.setBackground(Color.WHITE);
		
		demo.setVisible(true);
	}

}
