package com.elvenrings.qdemo.apptest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Demo3
{
	private static final long serialVersionUID = 1L;
	private WelcomeScreen welcomeScreen;

	public Demo3()
	{
		welcomeScreen = new WelcomeScreen();
		welcomeScreen.setSize(400, 300);
		Dimension screenSize = welcomeScreen.getToolkit().getScreenSize();
		Dimension windowSize = welcomeScreen.getSize();

		int posX = (screenSize.width - windowSize.width) / 2;
		int posY = (screenSize.height - windowSize.height) / 2;

		welcomeScreen.setLocation(posX, posY);
		welcomeScreen.setVisible(true);
		welcomeScreen.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e)
			{
				int clickCount = e.getClickCount();
				if (clickCount == 2)
				{
					welcomeScreen.dispose();
				}
			}

		});
	}

	public static void main(String[] args)
	{
		Demo3 demo = new Demo3();
		JPanel panel = demo.welcomeScreen.getPanel();
		JLabel label = new JLabel("<html>WELCOME TO <strong>QDEMO!<strong><html>");
		label.setOpaque(true);
		label.setBackground(new Color(0,128,0,128));
		panel.add(label);
		demo.welcomeScreen.repaint();
		demo.welcomeScreen.revalidate();

	}

}
