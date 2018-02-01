package com.elvenrings.qdemo.app;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.elvenrings.qdemo.utils.TrackerException;
import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;
import com.elvenrings.qdemo.view.swing.listeners.QuestionFileLoadListener;

public class ComboPopulator implements QuestionFileLoadListener
{
	ApplicationContext context;
	
	ComboPopulator(ApplicationContext context)
	{
		this.context = context;
	}
	
	@Override
	public void XMLFileLoaded(QuestionFileLoadEvent event)
	{
		try
		{
			JPanel startPanel = new JPanel();
			JPanel endPanel = new JPanel();
			startPanel.add(new JLabel("Welcome"));
			endPanel.add(new JLabel("End"));
			ApplicationContext context = ApplicationContext.getInstance();
			@SuppressWarnings({ "static-access", "unchecked" })
			JComboBox<DefaultCarousel> combobox = (JComboBox<DefaultCarousel>) context.get("cmbLoadedFiles");
			DefaultCarousel carousel = new DefaultCarousel(event.getFileName() + event.hashCode(), event.getReadQuestions(), startPanel, endPanel, true);
			
			combobox.addItem(carousel);
			combobox.requestFocus();
			
		} catch (TrackerException e)
		{
			e.printStackTrace();
		}
		
	}

}
