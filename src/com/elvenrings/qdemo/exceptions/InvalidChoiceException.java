package com.elvenrings.qdemo.exceptions;

public class InvalidChoiceException extends Exception
{
	private static final long serialVersionUID = 1L;

	public InvalidChoiceException(String message)
	{
		super(message);
	}
	
	public InvalidChoiceException()
	{
		super("Invalid Choice");
	}
}
