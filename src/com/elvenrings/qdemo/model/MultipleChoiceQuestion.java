package com.elvenrings.qdemo.model;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.interfaces.Questionable;

/**
 * A <code>Question</code> implementation class which accepts multiple correct
 * options out of a list options for the question it poses. In more technical
 * terms this means that its encapsulated <code>Choice</code> member variable is
 * of type <code>MultipleChoice</code>
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class MultipleChoiceQuestion extends Question implements Questionable
{
	private MultipleChoice multiChoice;

	/**
	 * Default Constructor instantiates an object of this class with empty preamble
	 * and body parts and an empty <code>MultipleChoice</code>.
	 */
	public MultipleChoiceQuestion()
	{
		preamble = new Preamble();
		body = new Body();
		multiChoice = new MultipleChoice();
	}

	/** {@inheritDoc} */
	public Preamble getPreamble()
	{
		return preamble;
	}

	/** {@inheritDoc} */
	public Body getBody()
	{
		return body;
	}

	/** {@inheritDoc} */
	public Choice getChoice()
	{
		return multiChoice;
	}
}
