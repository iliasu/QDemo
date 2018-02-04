package com.elvenrings.qdemo.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

/**
 * A <code>SwingRenderer</code> implementation that provides the following
 * functionality: <br>
 * 1. Renders all options associated with the question as Radio Buttons. <br>
 * 2. Retrieves the input and fires a
 * <code>SingleChoiceConsoleSelectionEvent</code> to all registered listeners.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoiceSwingRenderer extends DefaultSwingRenderer
{
	private Integer chosenInput = -1;
	private JRadioButton[] radioButtons;
	QPanel mainPanel = new QPanel();

	/**
	 * Single Parameter constructor accepts a <code>SingleChoiceQuestion</code> as
	 * the question to Render
	 * 
	 * @param question
	 *            <code>SingleChoiceQuestion</code> to render
	 */
	public SingleChoiceSwingRenderer(SingleChoiceQuestion question)
	{
		this.question = question;
		this.submitButton = new JButton("Submit");
		this.submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Choice choice = SingleChoiceSwingRenderer.this.question.getChoice();
				SingleChoiceSwingRenderer.this.getInput();

				SingleChoiceSwingSelectionEvent sce = new SingleChoiceSwingSelectionEvent(
						SingleChoiceSwingRenderer.this, SingleChoiceSwingRenderer.this.chosenInput, choice);
				fireSingleChoiceEvent(sce);
			}
		});
	}

	private void fireSingleChoiceEvent(SingleChoiceSwingSelectionEvent event)
	{
		for (SubmitSwingListener l : listeners)
		{
			l.singleChoiceSwingEventOccurred(event);
		}
	}

	/**
	 * Adds a user prompt to the already rendered question and all options
	 * associated with the question.
	 * 
	 * @return A fully rendered Question.
	 */
	@Override
	public QPanel render()
	{
		QPanel questionPanel = super.render();
		//questionPanel.setBackground(Color.WHITE);
		questionPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new BorderLayout());
		
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setForeground(Color.BLACK);
		
		mainPanel.add(questionPanel,BorderLayout.CENTER);
		
		Box box = layoutOptions();
		mainPanel.add(box,BorderLayout.SOUTH);
		Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border insideBorder = BorderFactory.createEtchedBorder();

		Border border = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);

		mainPanel.setBorder(border);
		return mainPanel;
	}

	/**
	 * Accepts user input.
	 * 
	 * @return <code>Set</code> of Integers from the user input.
	 */
	public Integer getInput()
	{
		int answer = -1;

		for (int i = 0; i < radioButtons.length; i++)
		{
			if (radioButtons[i].isSelected())
			{
				answer = i + 1;
				break;
			}
		}

		chosenInput = answer;
		return answer;

	}

	private Box layoutOptions()
	{
		List<String> optionList = question.getChoice().getOptionList();
		int optionCount = optionList.size();
		radioButtons = new JRadioButton[optionCount];
		ButtonGroup group = new ButtonGroup();
	
		Box box = Box.createVerticalBox();
		box.setOpaque(true);
		box.setBackground(new Color(234,234,234));

		for (int i = 0; i < optionCount; i++)
		{
			radioButtons[i] = new JRadioButton();
			radioButtons[i].setText(optionList.get(i));
			group.add(radioButtons[i]);
			box.add(Box.createVerticalGlue());
			radioButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			box.add(radioButtons[i]);
		}
		box.add(Box.createVerticalStrut(5));
		submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(submitButton);
		messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(messageLabel);
		return box;
	}
	
	public SingleChoiceQuestion getQuestion()
	{
		return (SingleChoiceQuestion) question;
	}
	
	public JRadioButton[] getRadioButtons()
	{
		return radioButtons;
	}
}
