package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.ExamMediator;

public class ExamStartedState implements ExamState
{

	@Override
	public void handle(ExamStateContext context)
	{
		ExamMediator examMediator = context.getExamMediator();
		examMediator.examStarted();
		context.setState(new ExamCompletedState());
	}

}
