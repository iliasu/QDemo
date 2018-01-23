package com.elvenrings.qdemo.model;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.interfaces.Questionable;

/**
 * A <code>Question</code> implementation class which accepts a single correct
 * option out of a list options for the question it poses. In more technical
 * terms this means that its encapsulated <code>Choice</code> member variable is
 * of type <code>SingleChoice</code>
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoiceQuestion extends Question implements Questionable
{
	
	private SingleChoice singleChoice;
	
	/**
	 * Default Constructor instantiates an object of this class with empty preamble
	 * and body parts and an empty <code>SingleChoice</code>.
	 */
	public SingleChoiceQuestion()
	{
		preamble = new Preamble();
		body = new Body();
		singleChoice = new SingleChoice();
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
		return singleChoice;
	}

}
