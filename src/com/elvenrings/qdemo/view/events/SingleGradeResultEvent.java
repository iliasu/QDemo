package com.elvenrings.qdemo.view.events;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.model.Question;

public class SingleGradeResultEvent
{
	private SwingRenderer renderer;
	private Question question;
	private Integer attempt;
	private boolean correct;
	
	public SingleGradeResultEvent(SwingRenderer renderer, Question question, Integer attempt, boolean correct)
	{
		this.renderer = renderer;
		this.question = question;
		this.attempt = attempt;
		this.correct = correct;
	}

	public boolean isCorrect()
	{
		return correct;
	}

	public void setCorrect(boolean correct)
	{
		this.correct = correct;
	}

	public SwingRenderer getRenderer()
	{
		return renderer;
	}

	public void setRenderer(SwingRenderer renderer)
	{
		this.renderer = renderer;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public Integer getAttempt()
	{
		return attempt;
	}

	public void setAttempt(Integer attempt)
	{
		this.attempt = attempt;
	}
	
}
