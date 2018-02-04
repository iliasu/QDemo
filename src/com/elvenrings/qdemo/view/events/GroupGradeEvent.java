package com.elvenrings.qdemo.view.events;

import java.util.Map;

import com.elvenrings.qdemo.model.Question;

public class GroupGradeEvent implements Event
{
	private Map<Question, Event> eventMap;
	
	public GroupGradeEvent(Map<Question, Event> eventMap)
	{
		this.eventMap = eventMap;
	}

	public Map<Question, Event> getEventMap()
	{
		return eventMap;
	}
	
}
