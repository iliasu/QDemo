package com.elvenrings.qdemo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.interfaces.Choice;

/**
 * A class representing the options available for a question for which one or
 * more options are correct.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class MultipleChoice implements Choice
{
	private Map<Integer, String> map = new HashMap<>();
	private Set<Integer> correctSet = new HashSet<>();

	public MultipleChoice()
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
	 * Sets the positions of the options that are correct answers.
	 * 
	 * @param correctPositions
	 *            A <code>List</code> specifying the positions of the correct
	 *            answers.
	 * @throws InvalidChoiceException
	 *             Raised if the specified list is null or empty or if an invalid
	 *             option is included in the list. The correct positions should
	 *             always be considered unset if this option is raised.
	 */
	public void setCorrectChoices(List<Integer> correctPositions) throws InvalidChoiceException
	{
		correctSet.clear();
		if (correctPositions == null || correctPositions.isEmpty())
		{
			throw new InvalidChoiceException("Empty list provided cannot set correct options");
		}

		for (Integer c : correctPositions)
		{
			if (map.containsKey(c))
			{
				correctSet.add(c);

			} else
			{
				correctSet.clear();
				throw new InvalidChoiceException("Option " + c + " does not exist to set as correct choice.");
			}
		}

	}

	/**
	 * Returns a <code>Set</code> of positions of the correct answers.
	 * 
	 * @return The position of the correct answer.
	 * @throws InvalidChoiceException
	 *             Raised if no correct positions have been set.
	 */
	public Set<Integer> getCorrectChoices() throws InvalidChoiceException
	{
		if (correctSet == null || correctSet.isEmpty())
		{
			throw new InvalidChoiceException(
					"Correct choices currently not set to a valid value: " + choiceString(correctSet));
		}
		return correctSet;
	}

	/**
	 * String representation of this class. A '*' is appended as the last character
	 * of all correct option texts.
	 * 
	 * @return A string representation of this class (Multiple Choice options).
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (Integer i : map.keySet())
		{
			sb.append(i + " - " + map.get(i));
			if (correctSet.contains(i))
			{
				sb.append(" *");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private String choiceString(Set<Integer> list)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (Integer i : list)
		{
			builder.append(i + ", ");
		}

		return builder.substring(0, builder.length() - 2) + "]";

	}
}
