package com.elvenrings.qdemo.view.swing.markers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.event.EventListenerList;

import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.events.SingleGradeResultEvent;
import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.SingleGraderResultListener;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

public class SingleGrader implements SubmitSwingListener
{
	private FillBlankMarker fillBlankMarker = new FillBlankMarker();
	private SingleChoiceMarker singleChoiceMarker = new SingleChoiceMarker();
	private MultipleChoiceMarker multiChoiceMarker = new MultipleChoiceMarker();

	private EventListenerList listeners = new EventListenerList();
	private Map<Question, Integer> map = new HashMap<>();

	@Override
	public void fillBlankSwingEventOccurred(FillBlankSwingEvent event)
	{
		FillBlankQuestion question = (FillBlankQuestion) event.getRenderer().getQuestion();
		if (map.containsKey(question))
		{
			Integer count = map.get(question);
			count++;
			map.put(question, count);
		}
		else
		{
			map.put(question, 1);
		}
		boolean correct = fillBlankMarker.isCorrect(event);
		
		//Integer attempt = map.get(question);
		//Integer allowedAttempts = question.getAttempts();
		SingleGradeResultEvent e = new SingleGradeResultEvent(event.getRenderer(), question, map.get(question), correct);
		fireSingleGradeResultEvent(e);
		//System.out.println("Fill Blank Event: " + correct + "| attempt: " + attempt + "| threshold: " + allowedAttempts);

	}

	@Override
	public void singleChoiceSwingEventOccurred(SingleChoiceSwingSelectionEvent event)
	{
		SingleChoiceQuestion question = (SingleChoiceQuestion) event.getRenderer().getQuestion();
		if (map.containsKey(question))
		{
			Integer count = map.get(question);
			count++;
			map.put(question, count);
		}
		else
		{
			map.put(question, 1);
		}
		boolean correct = singleChoiceMarker.isCorrect(event);
		SingleGradeResultEvent e = new SingleGradeResultEvent(event.getRenderer(), question, map.get(question), correct);
		fireSingleGradeResultEvent(e);
		//System.out.println("Single Choice Event: "+ correct);
	}

	@Override
	public void multipleChoiceSwingEventOccurred(MultipleChoiceSwingSelectionEvent event)
	{
		MultipleChoiceQuestion question = (MultipleChoiceQuestion) event.getRenderer().getQuestion();
		
		if (map.containsKey(question))
		{
			Integer count = map.get(question);
			count++;
			map.put(question, count);
		}
		else
		{
			map.put(question, 1);
		}
		boolean correct = multiChoiceMarker.isCorrect(event);
		SingleGradeResultEvent e = new SingleGradeResultEvent(event.getRenderer(), question, map.get(question), correct);
		fireSingleGradeResultEvent(e);
		System.out.println("Multiple Choice Event: " +correct);
	}

	public void addSingleGraderResultListener(SingleGraderResultListener listener)
	{
		listeners.add(SingleGraderResultListener.class, listener);
	}
	
	public void removeSingleGraderResultListener(SingleGraderResultListener listener)
	{
		listeners.remove(SingleGraderResultListener.class, listener);
	}
	
	private void fireSingleGradeResultEvent(SingleGradeResultEvent event)
	{
		SingleGraderResultListener[] resultListeners = listeners.getListeners(SingleGraderResultListener.class);
		for(int i=0; i<resultListeners.length; i++)
		{
			resultListeners[i].gradingActivityCompleted(event);
		}
	}
}
