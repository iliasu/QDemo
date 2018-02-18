package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.QuizMediator;

public interface QuizStateContext
{
	public QuizMediator getQuizMediator();
	public void setState(QuizState state);
}
