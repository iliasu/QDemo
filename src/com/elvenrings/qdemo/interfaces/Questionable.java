package com.elvenrings.qdemo.interfaces;

import com.elvenrings.qdemo.model.Body;
import com.elvenrings.qdemo.model.Preamble;

/**
 * <code>Question</code> classes that implement this interface can
 * rendered/displayed by a Renderer object. For now renderers supported are
 * Console and Swing Renderers. HTML and XML renderers are on the TODO list.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public interface Questionable
{
	/**
	 * Returns the Preamble
	 * 
	 * @return The preamble of the question.
	 */
	public Preamble getPreamble();

	/**
	 * Returns the Body
	 * 
	 * @return The Body of the question.
	 */
	public Body getBody();

	/**
	 * Returns the Choice object for the question.
	 * 
	 * @return The preamble of the question.
	 */
	public Choice getChoice();
}
