package com.elvenrings.qdemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.elvenrings.qdemo.interfaces.Choice;

/**
 * A class representing the options available for a question accepting a text or
 * series of texts to fill in the blanks.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlank implements Choice
{
	private Pattern[] patterns;
	private List<String> emptyList = new ArrayList<>();

	/**
	 * Default constructor
	 */
	public FillBlank()
	{
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return An empty string list appropriate for a fill in the blank question
	 *         type. Not really used but enables this class to be used like other
	 *         classes implementing {@link com.elvenrings.qdemo.interfaces.Choice}
	 *         interface without the overhead of checking for null values.
	 */
	@Override
	public List<String> getOptionList()
	{
		return emptyList;
	}

	/**
	 * Accepts an array of regex values and sets these as the patterns against which
	 * answers will be matched.
	 * 
	 * @param regexList
	 *            An array of regex strings.
	 */
	public void setPatterns(String[] regexList)
	{
		int size = regexList.length;
		patterns = new Pattern[size];

		for (int i = 0; i < regexList.length; i++)
		{
			this.patterns[i] = Pattern.compile(regexList[i]);
		}
	}

	/**
	 * Matches the provided list of strings against the set regex patterns in this
	 * class.
	 * 
	 * @param answers
	 *            An array of strings representing answers
	 * @return true if ALL answers match their respective regex patterns; false
	 *         otherwise. Returns false also if the number of strings differ from
	 *         the number of patterns.
	 */
	public boolean matches(String[] answers)
	{
		if (answers.length != patterns.length)
		{
			return false;
		}
		for (int i = 0; i < patterns.length; i++)
		{
			Matcher matcher = patterns[i].matcher(answers[i]);
			if (!matcher.matches())
			{
				return false;
			}
		}

		return true;
	}
}
