package com.elvenrings.qdemo.view.swing.listeners;

import java.util.EventListener;

import com.elvenrings.qdemo.view.events.SingleGradeResultEvent;

public interface SingleGraderResultListener extends EventListener
{
	public void gradingActivityCompleted(SingleGradeResultEvent event);
}
