package com.elvenrings.qdemo.test;

import java.util.StringJoiner;

import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;
import com.elvenrings.qdemo.view.swing.markers.FillBlankMarker;
import com.elvenrings.qdemo.view.swing.markers.MultipleChoiceMarker;
import com.elvenrings.qdemo.view.swing.markers.SingleChoiceMarker;

public class LogToConsole implements SubmitSwingListener
{

	@Override
	public void fillBlankSwingEventOccurred(FillBlankSwingEvent event)
	{
		StringJoiner sj = new StringJoiner(":");

		for (String str : event.getInput())
		{
			sj.add(str);
		}

		if (new FillBlankMarker().isCorrect(event))
		{
			System.out.println("Correct!");
		} else
		{
			System.out.println("Wrong!");
		}

	}

	@Override
	public void singleChoiceSwingEventOccurred(SingleChoiceSwingSelectionEvent event)
	{
		if (new SingleChoiceMarker().isCorrect(event))
		{
			System.out.println("Correct!");
		} else
		{
			System.out.println("Wrong!");
		}
	}

	@Override
	public void multipleChoiceSwingEventOccurred(MultipleChoiceSwingSelectionEvent event)
	{
		if (new MultipleChoiceMarker().isCorrect(event))
		{
			System.out.println("Correct!");
		} else
		{
			System.out.println("Wrong!");
		}
	}

}
