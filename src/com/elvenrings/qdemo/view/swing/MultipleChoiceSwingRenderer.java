package com.elvenrings.qdemo.view.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
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
		QPanel panel = super.render();
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.BLACK);
		List<String> optionList = question.getChoice().getOptionList();
		int optionCount = optionList.size();

		checkBoxes = new JCheckBox[optionCount];

		for (int i = 0; i < optionCount; i++)
		{
			checkBoxes[i] = new JCheckBox();
			checkBoxes[i].setText(optionList.get(i));
		}

		layoutOptions(panel);
		Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border insideBorder = BorderFactory.createEtchedBorder();

		Border border = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
		panel.setBorder(border);
		return panel;
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

	private void layoutOptions(QPanel panel)
	{
		GridBagConstraints gc = panel.getConstraints();
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.fill = GridBagConstraints.NONE;

		for (int i = 0; i < checkBoxes.length; i++)
		{
			gc.gridy++;
			panel.add(checkBoxes[i], gc);
		}
		gc.gridy++;
		panel.add(submitButton, gc);
	}
}
