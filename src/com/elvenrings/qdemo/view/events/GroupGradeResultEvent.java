package com.elvenrings.qdemo.view.events;

public class GroupGradeResultEvent
{
	private int correct;
	private int total;
	
	public GroupGradeResultEvent(int correct, int total)
	{
		this.correct = correct;
		this.total = total;
	}
	public int getCorrect()
	{
		return correct;
	}
	public int getTotal()
	{
		return total;
	}
}
