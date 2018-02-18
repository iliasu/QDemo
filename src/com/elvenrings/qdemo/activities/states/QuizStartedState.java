package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.QuizMediator;

public class QuizStartedState implements QuizState
{

	@Override
	public void handle(QuizStateContext context)
	{
		QuizMediator quizMediator = context.getQuizMediator();
		quizMediator.quizStarted();
		context.setState(new QuizCompletedState());

	}

}
