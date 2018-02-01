package com.elvenrings.qdemo.utils;

public class TrackerException extends Exception
{
	private static final long serialVersionUID = 1L;

	public TrackerException()
	{
		super("Boundary reached");
	}
	
	public TrackerException(String message)
	{
		super(message);
	}
}