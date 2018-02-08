package com.elvenrings.qdemo.model;

import com.elvenrings.qdemo.interfaces.Choice;

/**
 * An abstract class representing the idea of a question. A
 * <code>Question</code> consists of a series of ordered <code>Preamble</code>s
 * and <code>Body</code> parts. This class cannot be instantiated and needs to
 * be overridden by a class that will typically implement
 * <code>Questionable</code> and thus will provide a mechanism for hooking a
 * <code>Choice</code> implementation on to the question.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public abstract class Question
{
	protected Preamble preamble;
	protected Body body;
	private Integer sequence1 = 0;
	private Integer sequence2 = 0;
	private Integer attempts = 0;

	protected Question()
	{
	}

	/**
	 * Adds a text <code>String</code> to the <code>Preamble</code> list.
	 * 
	 * @param text
	 *            Preamble text.
	 */
	public void addPreambleText(String text)
	{
		addText(preamble, ++sequence1, text);
	}

	/**
	 * Adds binary data to the <code>Preamble</code> list.
	 * 
	 * @param data
	 *            Preamble data.
	 */
	public void addPreambleData(byte[] data)
	{
		addData(preamble, ++sequence1, data);
	}

	/**
	 * Adds a text <code>String</code> to the <code>Body</code> list.
	 * 
	 * @param text
	 *            Body text.
	 */
	public void addBodyText(String text)
	{
		addText(body, ++sequence2, text);
	}

	/**
	 * Adds binary data to the <code>Body</code> list.
	 * 
	 * @param data
	 *            Body binary data.
	 */
	public void addBodyData(byte[] data)
	{
		addData(body, ++sequence2, data);
	}

	public Integer getAttempts()
	{
		return attempts;
	}

	public void setAttempts(Integer attempts)
	{
		this.attempts = attempts;
	}

	private void addText(Part fragment, Integer sequence, String text)
	{
		fragment.addText(sequence, text);
	}

	private void addData(Part fragment, Integer sequence, byte[] data)
	{
		Byte[] d = new Byte[data.length];
		for (int i = 0; i < data.length; i++)
		{
			d[i] = (Byte) data[i];
		}
		fragment.addData(sequence, d);
	}

	public abstract Choice getChoice();
}
