package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.ExamMediator;

public class ExamCompletedState implements ExamState
{

	@Override
	public void handle(ExamStateContext context)
	{
		ExamMediator examMediator = context.getExamMediator();
		examMediator.examEnded();
		context.setState(new ExamInitialState());
	}

}
