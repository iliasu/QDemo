package com.elvenrings.qdemo.view.events;

import com.elvenrings.qdemo.interfaces.Choice;

/**
 * An event that is generated when a user answers a Single-Choice type of
 * question.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoiceSelectionEvent implements Event
{
	protected Integer input;
	protected Choice choice;

	/**
	 * Constructor that accepts the received input, as well as the choices for the
	 * question.
	 * 
	 * @param input
	 *            Integer representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	protected SingleChoiceSelectionEvent(Integer input, Choice choice)
	{
		this.input = input;
		this.choice = choice;
	}

	/**
	 * Retrieves the user input as an Integer.
	 * 
	 * @return An Integer representing the user input.
	 */
	public Integer getInput()
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
