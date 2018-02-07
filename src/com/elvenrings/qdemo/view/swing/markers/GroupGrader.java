package com.elvenrings.qdemo.view.swing.markers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.view.events.Event;
import com.elvenrings.qdemo.view.events.GroupGradeEvent;
import com.elvenrings.qdemo.view.events.GroupGradeResultEvent;
import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;
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
		Map<SwingRenderer,Boolean> rendererMap = new HashMap<>();
		int correct = 0;
		int total = 0;
		for(Event e  : eventMap.values())
		{
			total++;
			if(e instanceof FillBlankSwingEvent)
			{
				FillBlankSwingRenderer renderer = ((FillBlankSwingEvent) e).getRenderer();
				if(fillBlankMarker.isCorrect(e))
				{
					rendererMap.put(renderer, true);
					correct++;
				}
				else
				{
					rendererMap.put(renderer, false);
				}
				
				
				
			}
			
			
			if(e instanceof SingleChoiceSwingSelectionEvent)
			{
				SingleChoiceSwingRenderer renderer = ((SingleChoiceSwingSelectionEvent) e).getRenderer();
				
				if(singleChoiceMarker.isCorrect(e))
				{
					rendererMap.put(renderer, true);
					correct++;
				}
				else
				{
					rendererMap.put(renderer, false);
				}
			}
			
			if(e instanceof MultipleChoiceSwingSelectionEvent)
			{
				MultipleChoiceSwingRenderer renderer = ((MultipleChoiceSwingSelectionEvent) e).getRenderer();
				if(multiChoiceMarker.isCorrect(e))
				{
					rendererMap.put(renderer, true);
					correct++;
				}
				else
				{
					rendererMap.put(renderer, false);
				}
			}
			
		}
		
		GroupGradeResultEvent result = new GroupGradeResultEvent(rendererMap, correct, total);
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