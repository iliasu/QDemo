package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = 1L;

	public AboutDialog()
	{
		setSize(new Dimension(300,200));
		setResizable(false);
		setLayout(new BorderLayout());
		setModal(true);
		
		JPanel aboutPanel = new JPanel();
		aboutPanel.setBackground(new Color(206, 217, 228));
		aboutPanel.setLayout(new BorderLayout());
		aboutPanel.add(new JLabel("QDemo Study Aid", SwingConstants.CENTER), BorderLayout.CENTER);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		JLabel l1 = new JLabel("© 2018 Elven Rings Ltd");
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel l2 = new JLabel("All rights reserved.");
		l2.setAlignmentX(Component.CENTER_ALIGNMENT);
		box.add(l1);
		box.add(l2);
		
		aboutPanel.add(box, BorderLayout.SOUTH);
		add(aboutPanel, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
			
		});
	}
}
