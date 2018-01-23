package com.elvenrings.qdemo.view.events;

import java.util.Set;

import com.elvenrings.qdemo.interfaces.Choice;

/**
 * An event that is generated when a user answers a Multiple-Choice type of
 * question.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class MultipleChoiceSelectionEvent implements Event
{

	protected Set<Integer> input;
	protected Choice choice;

	/**
	 * Constructor that accepts the received input, as well as the choices for the
	 * question.
	 * 
	 * @param input
	 *            Set of integers representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	protected MultipleChoiceSelectionEvent(Set<Integer> input, Choice choice)
	{
		this.input = input;
		this.choice = choice;
	}

	/**
	 * Retrieves the user input as a Set of Integers.
	 * 
	 * @return A Set of integers representing the user input.
	 */
	public Set<Integer> getInput()
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
