package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.QuizMediator;

public class QuizCompletedState implements QuizState
{

	@Override
	public void handle(QuizStateContext context)
	{
		QuizMediator quizMediator = context.getQuizMediator();
		quizMediator.quizEnded();
		context.setState(new QuizInitialState());
	}

}
