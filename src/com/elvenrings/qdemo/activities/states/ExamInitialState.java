package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.ExamMediator;

public class ExamInitialState implements ExamState
{

	@Override
	public void handle(final ExamStateContext context)
	{
		ExamMediator examMediator = context.getExamMediator();
		examMediator.init();
		
		context.setState(new ExamStartedState());
	}

}
