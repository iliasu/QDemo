package com.elvenrings.qdemo.view.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.FillBlankQuestion;
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
		QPanel panel = super.render();
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.BLACK);

		textField = new JTextField(15);

		layoutOptions(panel);
		Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border insideBorder = BorderFactory.createEtchedBorder();

		Border border = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
		panel.setBorder(border);
		return panel;
	}

	private void layoutOptions(QPanel panel)
	{
		GridBagConstraints gc = panel.getConstraints();
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.fill = GridBagConstraints.NONE;

		gc.gridy++;
		panel.add(textField, gc);
		gc.gridy++;
		gc.weighty = 10;
		panel.add(submitButton, gc);

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

}
