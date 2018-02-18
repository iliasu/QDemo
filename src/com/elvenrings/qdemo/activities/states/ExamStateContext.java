package com.elvenrings.qdemo.activities.states;

import com.elvenrings.qdemo.activities.ExamMediator;

public interface ExamStateContext
{
	public ExamMediator getExamMediator();
	public void setState(ExamState state);
}
