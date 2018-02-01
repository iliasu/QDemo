package com.elvenrings.qdemo.view.events.swing;

import com.elvenrings.qdemo.xml.ReadQuestions;

public class QuestionFileLoadEvent
{
	ReadQuestions readQuestions;
	String fileName;
	public QuestionFileLoadEvent(ReadQuestions readQuestions, String fileName)
	{
		this.readQuestions = readQuestions;
		this.fileName = fileName;
	}
	
	public ReadQuestions getReadQuestions()
	{
		return this.readQuestions;
	}
	
	public String getFileName()
	{
		return this.fileName;
	}
}
