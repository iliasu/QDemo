package com.elvenrings.qdemo.utils;

public class Tracker
{
	int first;
	int last;
	int current;
	boolean init = false;

	public void init(int first, int last, int current) throws TrackerException
	{
		if (last < first)
		{
			throw new IllegalArgumentException("Parameter last: " + last + " should be >= parameter first: " + first);
		}
		if (current > last)
		{
			throw new IllegalArgumentException(
					"Parameter current: " + current + " should be <= parameter last: " + last);
		}
		if (current < first)
		{
			throw new IllegalArgumentException(
					"Parameter current: " + current + " should be >= parameter first: " + first);
		}

		init = true;
		this.first = first;
		this.last = last;
		this.current = current;
	}

	public void moveToLast() throws TrackerException
	{
		checkInit();
		current = last;
	}

	public void moveToFirst()
	{
		current = first;
	}

	public void next() throws TrackerException
	{
		checkInit();
		if (current < last)
		{
			current++;
		} else
		{
			throw new TrackerException("Upper boundary reached.");
		}
	}

	public void prev() throws TrackerException
	{
		checkInit();
		if (current > first)
		{
			current--;
		} else
		{
			throw new TrackerException("Lower boundary reached.");
		}
	}

	public boolean hasNext() throws TrackerException
	{
		checkInit();
		return (current == last) ? false : true;

	}

	public boolean hasPrev() throws TrackerException
	{
		checkInit();
		return (current == first) ? false : true;

	}

	private void checkInit() throws TrackerException
	{
		if (!init)
		{
			throw new TrackerException("Not initialized");
		}
	}
}