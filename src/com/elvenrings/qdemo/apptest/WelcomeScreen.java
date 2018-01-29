package com.elvenrings.qdemo.apptest;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JWindow;

public class WelcomeScreen extends JWindow
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public WelcomeScreen()
	{
		panel = new JPanel();
		// panel.setBackground(new Color(228, 228, 241,128));
		panel.setBackground(new Color(0, 128, 0, 128));
		add(this.panel, BorderLayout.CENTER);
	}

	public WelcomeScreen(JPanel panel)
	{
		this.panel = panel;
		setLayout(new BorderLayout());
		add(this.panel, BorderLayout.CENTER);
	}

	public JPanel getPanel()
	{
		return panel;
	}
}
