package com.elvenrings.qdemo.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.Border;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

/**
 * A <code>SwingRenderer</code> implementation that provides the following
 * functionality: <br>
 * 1. Renders all options associated with the question as Check Boxes.<br>
 * 2. Retrieves the input and fires a
 * <code>MultipleChoiceSwingSelectionEvent</code> to all registered listeners.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class MultipleChoiceSwingRenderer extends DefaultSwingRenderer
{
	private Set<Integer> chosenInput = null;
	private JCheckBox[] checkBoxes;
	QPanel mainPanel = new QPanel();

	/**
	 * Single Parameter constructor accepts a <code>MultipleChoiceQuestion</code> as
	 * the question to Render
	 * 
	 * @param question
	 *            <code>MultipleChoiceQuestion</code> to render
	 */
	public MultipleChoiceSwingRenderer(MultipleChoiceQuestion question)
	{
		this.question = question;
		this.submitButton = new JButton("Submit");
		this.submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Choice choice = MultipleChoiceSwingRenderer.this.question.getChoice();
				MultipleChoiceSwingRenderer.this.getInput();
				
				MultipleChoiceSwingSelectionEvent mce = new MultipleChoiceSwingSelectionEvent(
						MultipleChoiceSwingRenderer.this, MultipleChoiceSwingRenderer.this.chosenInput, choice);
				fireMultipleChoiceEvent(mce);
			}
		});
	}

	private void fireMultipleChoiceEvent(MultipleChoiceSwingSelectionEvent event)
	{
		for (SubmitSwingListener l : listeners)
		{
			l.multipleChoiceSwingEventOccurred(event);
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
		questionPanel.setBackground(Color.WHITE);
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
	public Set<Integer> getInput()
	{
		Set<Integer> answer = new HashSet<>();

		for (int i = 0; i < checkBoxes.length; i++)
		{
			if (checkBoxes[i].isSelected())
			{
				answer.add(i + 1);
			}
		}

		chosenInput = answer;
		return answer;

	}

	private Box layoutOptions()
	{
		List<String> optionList = question.getChoice().getOptionList();
		int optionCount = optionList.size();
		checkBoxes = new JCheckBox[optionCount];
	
		Box box = Box.createVerticalBox();
		box.setOpaque(true);
		box.setBackground(new Color(234,234,234));

		for (int i = 0; i < optionCount; i++)
		{
			checkBoxes[i] = new JCheckBox();
			checkBoxes[i].setText(optionList.get(i));
			box.add(Box.createVerticalGlue());
			box.add(checkBoxes[i]);
		}
		
		box.add(Box.createVerticalStrut(5));
		box.add(submitButton);
		return box;
	}
	
	
	public MultipleChoiceQuestion getQuestion()
	{
		return (MultipleChoiceQuestion) question;
	}
	
	public JCheckBox[] getCheckBoxes()
	{
		return checkBoxes;
	}
}
