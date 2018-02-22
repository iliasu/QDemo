package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.QuizMediator;

public class QuizForwardState implements QuizState
{

	@Override
	public void handle(QuizStateContext context)
	{
		QuizMediator quizMediator = context.getQuizMediator();
		quizMediator.quizNext();
		context.setState(new QuizForwardState());
	}

}
