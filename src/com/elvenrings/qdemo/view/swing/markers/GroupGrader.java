package com.elvenrings.qdemo.view.swing.markers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.view.events.Event;
import com.elvenrings.qdemo.view.events.GroupGradeEvent;
import com.elvenrings.qdemo.view.events.GroupGradeResultEvent;
import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.listeners.GroupGraderListener;
import com.elvenrings.qdemo.view.swing.listeners.GroupGraderResultListener;

public class GroupGrader implements GroupGraderListener
{
	private FillBlankMarker fillBlankMarker = new  FillBlankMarker();
	private SingleChoiceMarker singleChoiceMarker = new SingleChoiceMarker();
	private MultipleChoiceMarker multiChoiceMarker = new MultipleChoiceMarker();
	
	private List<GroupGraderResultListener> listeners = new ArrayList<>();
	
	@Override
	public void gradingActivitySubmitted(GroupGradeEvent event)
	{
		
		Map<Question, Event> eventMap = event.getEventMap();
		int correct = 0;
		int total = 0;
		for(Event e  : eventMap.values())
		{
			total++;
			if(e instanceof FillBlankSwingEvent)
			{
				if(fillBlankMarker.isCorrect(e))
				{
					correct++;
				}
			}
			
			if(e instanceof SingleChoiceSwingSelectionEvent)
			{
				if(singleChoiceMarker.isCorrect(e))
				{
					correct++;
				}
			}
			
			if(e instanceof MultipleChoiceSwingSelectionEvent)
			{
				if(multiChoiceMarker.isCorrect(e))
				{
					correct++;
				}
			}
			
		}
		
		GroupGradeResultEvent result = new GroupGradeResultEvent(correct, total);
		fireGroupGradeResultEvent(result);
	}

	public void addGroupGraderResultListener(GroupGraderResultListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeGroupGraderResultListener(GroupGraderResultListener listener)
	{
		listeners.remove(listener);
	}
	
	private void fireGroupGradeResultEvent(GroupGradeResultEvent event)
	{
		for(GroupGraderResultListener listener : listeners)
		{
			listener.gradingActivityCompleted(event);
		}
	}
}
