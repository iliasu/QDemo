package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;

import javax.swing.JComboBox;

import com.elvenrings.qdemo.utils.TrackerException;
import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;
import com.elvenrings.qdemo.view.swing.listeners.QuestionFileLoadListener;

public class ComboPopulator implements QuestionFileLoadListener
{
	
	
	@Override
	public void XMLFileLoaded(QuestionFileLoadEvent event)
	{
		try
		{
			ApplicationContext context = ApplicationContext.getInstance();
			@SuppressWarnings({ "unchecked", "static-access" })
			JComboBox<DefaultCarousel> combobox = (JComboBox<DefaultCarousel>) context.get("cmbLoadedFiles");
			DefaultCarousel carousel = new DefaultCarousel(event.getFileName() + event.hashCode(), event.getReadQuestions(), null, null, true);
			
			combobox.addItem(carousel);
			
		} catch (TrackerException e)
		{
			e.printStackTrace();
		}
		
	}

}
