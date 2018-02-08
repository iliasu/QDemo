package com.elvenrings.qdemo.app;

import java.util.HashMap;
import java.util.Map;

import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;
import com.elvenrings.qdemo.view.swing.listeners.QuestionFileLoadListener;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class QuestionMap implements QuestionFileLoadListener
{
	private Map<String, ReadQuestions> readQuestions = new HashMap<>();

	@Override
	public void XMLFileLoaded(QuestionFileLoadEvent event)
	{
		ReadQuestions questions = event.getReadQuestions();
		String name = event.getFileName();

		Integer hash = questions.hashCode();
		String key = name + "-" + hash;
		readQuestions.put(key, questions);
		for (String q : readQuestions.keySet())
		{
			System.out.println(q);
		}
	}

}
