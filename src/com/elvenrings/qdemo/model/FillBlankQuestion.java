package com.elvenrings.qdemo.model;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.interfaces.Questionable;

/**
 * A <code>Question</code> implementation class which accepts a string or series of strings as
 * possible answers for the question it poses. In more technical terms this means
 * that its encapsulated <code>Choice</code> member variable is of type <code>FillBlank</code>
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlankQuestion extends Question implements Questionable
{
	private FillBlank fillBlank;

	/**
	 * Default Constructor instantiates an object of this class with empty preamble
	 * and body parts and an empty <code>FillBlank Choice</code>.
	 */
	public FillBlankQuestion()
	{
		preamble = new Preamble();
		body = new Body();
		fillBlank = new FillBlank();
	}

	/** {@inheritDoc} */
	@Override
	public Preamble getPreamble()
	{
		return preamble;
	}

	/** {@inheritDoc} */
	@Override
	public Body getBody()
	{
		return body;
	}

	/** {@inheritDoc} */
	@Override
	public Choice getChoice()
	{
		return fillBlank;
	}

}
