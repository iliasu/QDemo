package com.elvenrings.qdemo.view.swing.listeners;

import java.util.EventListener;

import com.elvenrings.qdemo.view.events.GroupGradeResultEvent;

public interface GroupGraderResultListener extends EventListener
{
	public void gradingActivityCompleted(GroupGradeResultEvent event);
}
