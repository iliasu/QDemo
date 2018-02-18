package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.QuizMediator;

public class QuizInitialState implements QuizState
{

	@Override
	public void handle(QuizStateContext context)
	{
		QuizMediator quizMediator = context.getQuizMediator();
		quizMediator.init();
		
		context.setState(new QuizStartedState());
	}

}
