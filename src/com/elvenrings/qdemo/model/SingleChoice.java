package com.elvenrings.qdemo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.interfaces.Choice;

/**
 * A class representing the options available for a question for which only one
 * option is correct.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoice implements Choice
{
	private Map<Integer, String> map = new HashMap<>();
	private Integer correctChoice = -1;

	/**
	 * Default Constructor
	 */
	public SingleChoice()
	{
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return An empty <code>String List</code> with each string representing the
	 *         text for a single option
	 */
	public List<String> getOptionList()
	{
		List<String> list = new ArrayList<>();

		for (Integer key : map.keySet())
		{
			list.add(map.get(key));
		}

		return list;
	}

	/**
	 * Returns a map containing all the available options.
	 * 
	 * @return A map of all options and their positions. The position will be used
	 *         for ordering the options when they are displayed.
	 */
	public Map<Integer, String> getOptionMap()
	{
		return map;
	}

	/**
	 * Adds a new option to the option list. If another option is present at the
	 * specified location it overwrites it.
	 * 
	 * @param position
	 *            The position of the option in the option list.
	 * @param text
	 *            The textual string of the option.
	 */
	public void addOption(Integer position, String text)
	{
		map.put(position, text);
	}

	/**
	 * Sets the position of the option that is the correct answer.
	 * 
	 * @param correctPosition
	 *            The position of the correct answer.
	 * @throws InvalidChoiceException
	 *             Raised if the specified position is invalid.
	 */
	public void setCorrectChoice(Integer correctPosition) throws InvalidChoiceException
	{
		if (map.containsKey(correctPosition))
		{
			correctChoice = correctPosition;
		} else
		{
			throw new InvalidChoiceException(
					"Option " + correctPosition + " does not exist to be set as correct choice.");
		}
	}

	/**
	 * Returns the position of the correct answer.
	 * 
	 * @return The position of the correct answer.
	 * @throws InvalidChoiceException
	 *             Raised if the correct position is currently unset
	 */
	public int getCorrectChoice() throws InvalidChoiceException
	{
		if (correctChoice == -1)
		{
			throw new InvalidChoiceException("Correct choice is currently not set to a valid value: " + correctChoice);
		}
		return correctChoice;
	}

	/**
	 * String representation of this class. A '*' is appended as the last character
	 * of the correct option text.
	 * 
	 * @return A string representation of this class (Single Choice options).
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (Integer i : map.keySet())
		{
			sb.append(i + " - " + map.get(i));
			if (i.equals(correctChoice))
			{
				sb.append(" *");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
