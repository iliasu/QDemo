package com.elvenrings.qdemo.view.swing.listeners;

import java.util.EventListener;

import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;

public interface QuestionFileLoadListener extends EventListener
{
	public void XMLFileLoaded(QuestionFileLoadEvent event);
}
