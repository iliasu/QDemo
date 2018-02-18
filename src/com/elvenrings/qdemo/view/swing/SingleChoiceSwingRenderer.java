package com.elvenrings.qdemo.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.Question;
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
	private JLabel colorStatusLabel = null;
	//QPanel mainPanel;

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
		SubmitSwingListener[] swingListeners = listeners.getListeners(SubmitSwingListener.class);
		for(int i=0; i< swingListeners.length; i++)
		{
			swingListeners[i].singleChoiceSwingEventOccurred(event);
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
		mainPanel = super.render();
		mainPanel.questionPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BorderLayout());
		
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setForeground(Color.WHITE);
		
		mainPanel.add(mainPanel.tabbedPane,BorderLayout.CENTER);
		
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
		box.setBackground(BOXCOLOR);

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
		//submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		Box box2 = Box.createHorizontalBox();
		setupColorStatusLabel();
		
		box2.add(submitButton);
		box2.add(Box.createHorizontalGlue());
		box2.add(colorStatusLabel);
		box2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		box.add(box2);
		
		//box.add(submitButton);
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
	
	@Override
	public Question getQuestion()
	{
		return (SingleChoiceQuestion) question;
	}
	
	
	public JRadioButton[] getRadioButtons()
	{
		return radioButtons;
	}
	
	@Override
	public JComponent[] getInputComponent()
	{
		return  radioButtons;
	}
}
