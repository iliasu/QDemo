package com.elvenrings.qdemo.utils;

public class Tally
{
	private int correct = 0;
	private int wrong = 0;
	private int total = 0;

	public void incrementCorrect(int count)
	{
		correct = correct + count;
		total = total + count;
	}

	public void incrementWrong(int count)
	{
		wrong = wrong + count;
		total = total + count;
	}

	public void incrementCorrect()
	{
		correct++;
		total++;
	}

	public void incrementWrong()
	{
		wrong++;
		total++;
	}

	public int getTotal()
	{
		return total;
	}

	public int getCorrect()
	{
		return correct;
	}

	public int getWrong()
	{
		return wrong;
	}
}
