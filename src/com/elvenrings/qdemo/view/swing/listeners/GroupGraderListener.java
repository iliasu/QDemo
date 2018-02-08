package com.elvenrings.qdemo.view.swing.listeners;

import java.util.EventListener;

import com.elvenrings.qdemo.view.events.GroupGradeEvent;

public interface GroupGraderListener extends EventListener
{
	public void gradingActivitySubmitted(GroupGradeEvent event);
}
