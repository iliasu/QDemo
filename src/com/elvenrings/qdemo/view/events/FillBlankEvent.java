package com.elvenrings.qdemo.view.events;

import com.elvenrings.qdemo.interfaces.Choice;

/**
 * An event that is generated when a user answers a Fill-In-The-Blank type of
 * question.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlankEvent implements Event
{
	protected String[] input;
	protected Choice choice;

	/**
	 * Constructor that accepts the received input, as well as the choices for the
	 * question.
	 * 
	 * @param input
	 *            Array of strings representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	protected FillBlankEvent(String[] input, Choice choice)
	{
		this.input = input;
		this.choice = choice;
	}

	/**
	 * Retrieves the user input as an array of Strings
	 * 
	 * @return An array of strings representing the user input.
	 */
	public String[] getInput()
	{
		return input;
	}

	/**
	 * Retrieves the options/choices presented to the user for a question.
	 * 
	 * @return The choice object that was presented to the user.
	 */
	public Choice getChoice()
	{
		return choice;
	}
}
