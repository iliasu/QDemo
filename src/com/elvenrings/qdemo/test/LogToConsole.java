package com.elvenrings.qdemo.test;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;
import com.elvenrings.qdemo.view.swing.markers.FillBlankMarker;
import com.elvenrings.qdemo.view.swing.markers.MultipleChoiceMarker;
import com.elvenrings.qdemo.view.swing.markers.SingleChoiceMarker;

public class LogToConsole implements SubmitSwingListener
{
	private Map<Question, Integer> map = new HashMap<>();
	private Map<Question, Boolean> answers = new HashMap<>();

	private void showAnswers()
	{
		System.out.print("{");
		for(Question q : answers.keySet())
		{
			System.out.println("[q = " + q + ", " + answers.get(q) + "] ");
		}
		System.out.println("}");
	}
	@Override
	public void fillBlankSwingEventOccurred(FillBlankSwingEvent event)
	{
		FillBlankSwingRenderer renderer = event.getRenderer();
		FillBlankQuestion question = (FillBlankQuestion) renderer.getQuestion();
		
		StringJoiner sj = new StringJoiner(":");

		for (String str : event.getInput())
		{
			sj.add(str);
		}

		if (new FillBlankMarker().isCorrect(event))
		{
			answers.put(question, true);
			System.out.println("Correct!");
		} else
		{
			answers.put(question, false);
			System.out.println("Wrong!");
		}

		map.put(question, map.getOrDefault(question, 0) + 1);
		int userAttempts = map.get(question);
		if (userAttempts >= question.getAttempts())
		{
			renderer.getSubmitButton().setEnabled(false);
			renderer.getTextField().setEditable(false);
			return;
		}
		showAnswers();
	}

	@Override
	public void singleChoiceSwingEventOccurred(SingleChoiceSwingSelectionEvent event)
	{
		SingleChoiceSwingRenderer renderer = event.getRenderer();
		SingleChoiceQuestion question = (SingleChoiceQuestion) renderer.getQuestion();
		
		if (new SingleChoiceMarker().isCorrect(event))
		{
			answers.put(question, true);
			System.out.println("Correct!");
		} else
		{
			answers.put(question, false);
			System.out.println("Wrong!");
		}
		
		map.put(question, map.getOrDefault(question, 0) + 1);
		int userAttempts = map.get(question);
		if (userAttempts >= question.getAttempts())
		{
			
			renderer.getSubmitButton().setEnabled(false);
			for(JRadioButton button : renderer.getRadioButtons())
			{
				button.setEnabled(false);
			}
			return;
		}
		showAnswers();
	}

	@Override
	public void multipleChoiceSwingEventOccurred(MultipleChoiceSwingSelectionEvent event)
	{
		MultipleChoiceSwingRenderer renderer = event.getRenderer();
		MultipleChoiceQuestion question = (MultipleChoiceQuestion) renderer.getQuestion();
		
		if (new MultipleChoiceMarker().isCorrect(event))
		{
			answers.put(question, true);
			System.out.println("Correct!");
		} else
		{
			answers.put(question, false);
			System.out.println("Wrong!");
		}
		
		map.put(question, map.getOrDefault(question, 0) + 1);
		int userAttempts = map.get(question);
		if (userAttempts >= question.getAttempts())
		{
			renderer.getSubmitButton().setEnabled(false);
			for(JCheckBox button : renderer.getCheckBoxes())
			{
				button.setEnabled(false);
			}
			return;
		}
		
		showAnswers();
	}

}
