package com.elvenrings.qdemo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract class that represents a part of a question. <code>Part</code>s of
 * a <code>Question</code> include the <code>Preamble</code> and
 * <code>Body</code> classes.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public abstract class Part
{
	private Map<Integer, String> stringMap = new HashMap<>();
	private Map<Integer, Byte[]> byteMap = new HashMap<>();

	protected Part()
	{
	}

	/**
	 * Returns a list of strings that are part of this <code>Part</code> object.
	 * 
	 * @return A list of strings that are part of this <code>Part</code> object.
	 */
	public List<String> getText()
	{
		List<String> list = new ArrayList<>();
		for (Integer key : stringMap.keySet())
		{
			list.add(stringMap.get(key));
		}

		return list;
	}

	/**
	 * Returns a list of Byte arrays that are part of this <code>Part</code> object.
	 * 
	 * @return A list of Byte arrays that are part of this <code>Part</code> object.
	 */
	public List<Byte[]> getData()
	{
		List<Byte[]> list = new ArrayList<>();
		for (Integer key : byteMap.keySet())
		{
			list.add(byteMap.get(key));
		}

		return list;
	}

	/**
	 * Adds a string to this <code>Part</code> object.
	 * 
	 * @param position
	 *            Location of this string with respect to other strings and bytes
	 *            arrays. The value of this parameter affects the location of this
	 *            string when a <code>Question</code> is rendered.
	 * @param text
	 *            Text string to add
	 */
	public void addText(Integer position, String text)
	{
		stringMap.put(position, text);
	}

	/**
	 * Adds a Byte array to this <code>Part</code> object.
	 * 
	 * @param position
	 *            Location of this Byte array with respect to other strings and byte
	 *            arrays. The value of this parameter affects the location of this
	 *            byte array when a <code>Question</code> is rendered.
	 * @param data
	 *            Text string to add
	 */
	public void addData(Integer position, Byte[] data)
	{
		byteMap.put(position, data);
	}
}
