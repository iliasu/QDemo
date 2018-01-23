package com.elvenrings.qdemo.interfaces;

/**
 * Implementation classes of this interface render <code>Question</code>s on the
 * console.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public interface ConsoleRenderer
{
	/**
	 * Returns a text <code>String</code> with a question rendered in it.
	 * 
	 * @return String with a question rendered in it.
	 */
	public abstract String render();

	/**
	 * Gets input from the commandline and fires an event for all registered
	 * listeners of the class.
	 */
	public abstract void getResponse();
}
