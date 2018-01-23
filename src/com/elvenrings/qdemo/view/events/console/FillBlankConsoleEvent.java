package com.elvenrings.qdemo.view.events.console;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.view.console.FillBlankConsoleRenderer;
import com.elvenrings.qdemo.view.events.FillBlankEvent;

/**
 * An event that is generated when a user answers a Fill-In-The-Blank type of
 * question from the <b>console</b>.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlankConsoleEvent extends FillBlankEvent
{
	private FillBlankConsoleRenderer renderer;

	/**
	 * Saves a copy of the Renderer in the event object thus allowing event
	 * listeners an opportunity to interact with the renderer
	 * 
	 * @param renderer
	 *            <code>FillBlankConsoleRenderer</code> that rendered the question
	 *            for which this event is raised.
	 * @param input
	 *            Integer representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	public FillBlankConsoleEvent(FillBlankConsoleRenderer renderer, String[] input, Choice choice)
	{
		super(input, choice);
		this.renderer = renderer;
	}

	/**
	 * Retrieves the <code>FillBlankConsoleRenderer</code> that rendered the
	 * question for which this event is raised.
	 * 
	 * @return <code>FillBlankConsoleRenderer</code>
	 *            that rendered the question for which this event is raised.
	 */
	public FillBlankConsoleRenderer getRenderer()
	{
		return renderer;
	}

}
