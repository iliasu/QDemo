package com.elvenrings.qdemo.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

/**
 * A <code>SwingRenderer</code> implementation that provides the following
 * functionality: <br>
 * 1. Renders the input prompt. <br>
 * 2. Retrieves the Swing input and fires a <code>FillBlankSwingEvent</code> to
 * all registered listeners.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlankSwingRenderer extends DefaultSwingRenderer
{
	private String[] input = null;
	private JTextField textField = null;
	private JLabel colorStatusLabel = null;
	//QPanel mainPanel;

	/**
	 * Single Parameter constructor accepts a <code>FillBlankQuestion</code> as the
	 * question to Render
	 * 
	 * @param question
	 *            <code>FillBlankQuestion</code> to render
	 */
	public FillBlankSwingRenderer(FillBlankQuestion question)
	{
		this.question = question;
		this.submitButton = new JButton("Submit");
		this.submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Choice choice = FillBlankSwingRenderer.this.question.getChoice();
				FillBlankSwingRenderer.this.getInput();

				FillBlankSwingEvent fbe = new FillBlankSwingEvent(FillBlankSwingRenderer.this,
						FillBlankSwingRenderer.this.input, choice);
				fireFillBlankEvent(fbe);
			}
		});
	}

	private void fireFillBlankEvent(FillBlankSwingEvent event)
	{
		for (SubmitSwingListener l : listeners)
		{
			l.fillBlankSwingEventOccurred(event);
		}
	}

	/**
	 * Adds a user prompt to the already rendered question.
	 * 
	 * @return A fully rendered Question on a QPanel.
	 */
	@Override
	public QPanel render()
	{
		mainPanel = super.render();
		//questionPanel.setBackground(Color.WHITE);
		
		mainPanel.questionPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new BorderLayout());
		
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setForeground(Color.BLACK);
		
		mainPanel.add(mainPanel.tabbedPane,BorderLayout.CENTER);
		
		Box box = layoutOptions();
		mainPanel.add(box,BorderLayout.SOUTH);
		
		
		Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border insideBorder = BorderFactory.createEtchedBorder();

		Border border = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
		
		mainPanel.setBorder(border);
		return mainPanel;
		
	}

	private Box layoutOptions()
	{
		Box box = Box.createVerticalBox();
		box.setOpaque(true);
		box.setBackground(BOXCOLOR);
		textField = new JTextField(10);
		textField.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(textField);
		box.add(Box.createVerticalStrut(5));
		//submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		Box box2 = Box.createHorizontalBox();
		setupColorStatusLabel();
		
		box2.add(submitButton);
		box2.add(Box.createHorizontalGlue());
		box2.add(colorStatusLabel);
		box2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		box.add(box2);
		messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(messageLabel);
		
		return box;

	}
	
	private void setupColorStatusLabel()
	{
		colorStatusLabel = new JLabel("");
		colorStatusLabel.setOpaque(true);
		colorStatusLabel.setPreferredSize(new Dimension(26,26));
		colorStatusLabel.setMaximumSize(new Dimension(26,26));
		colorStatusLabel.setBackground(BOXCOLOR);
	}

	public JLabel getColorStatusLabel()
	{
		return this.colorStatusLabel;
	}
	
	/**
	 * Retrieves user input. Separate tokens should be delimited by a comma.
	 * 
	 * @return <code>List</code> of Strings from the user input.
	 */
	public List<String> getInput()
	{
		List<String> temp = null;
		String text = textField.getText();
		temp = extractInput(text, ",");

		input = temp.toArray(new String[temp.size()]);

		return temp;

	}

	private List<String> extractInput(String line, String tokenizer)
	{
		String[] tokens = line.split(tokenizer);
		List<String> strTokens = new ArrayList<>();

		for (int i = 0; i < tokens.length; i++)
		{
			strTokens.add(tokens[i].trim());
		}

		return strTokens;
	}
	
	@Override
	public Question getQuestion()
	{
		return (FillBlankQuestion) question;
	}
	
	public JTextField getTextField()
	{
		return this.textField;
	}

	@Override
	public JComponent[] getInputComponent()
	{
		JComponent[] cs = {textField};
		return  cs;
	}

	
}
