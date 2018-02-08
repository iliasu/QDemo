package com.elvenrings.qdemo.view.swing.listeners;

import java.util.EventListener;

import com.elvenrings.qdemo.view.events.SingleGradeResultEvent;

public interface SingleGraderListener extends EventListener
{
	public void gradingActivitySubmitted(SingleGradeResultEvent event);
}
