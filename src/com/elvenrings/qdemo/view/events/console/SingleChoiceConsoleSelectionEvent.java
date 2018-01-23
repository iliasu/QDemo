package com.elvenrings.qdemo.view.events.console;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.view.console.SingleChoiceConsoleRenderer;
import com.elvenrings.qdemo.view.events.SingleChoiceSelectionEvent;

/**
 * An event that is generated when a user answers a Single-Choice type of
 * question from the <b>console</b>.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoiceConsoleSelectionEvent extends SingleChoiceSelectionEvent
{

	private SingleChoiceConsoleRenderer renderer;

	/**
	 * Saves a copy of the Renderer in the event object thus allowing event
	 * listeners an opportunity to interact with the renderer
	 * 
	 * @param renderer
	 *            <code>SingleChoiceConsoleRenderer</code> that rendered the
	 *            question for which this event is raised.
	 * @param input
	 *            Integer representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	public SingleChoiceConsoleSelectionEvent(SingleChoiceConsoleRenderer renderer, Integer input, Choice choice)
	{
		super(input, choice);
		this.renderer = renderer;
	}

	/**
	 * Retrieves the <code>SingleChoiceConsoleRenderer</code> that rendered the
	 * question for which this event is raised.
	 * 
	 * @return <code>SingleChoiceConsoleRenderer</code>
	 *            that rendered the question for which this event is raised.
	 */
	public SingleChoiceConsoleRenderer getRenderer()
	{
		return renderer;
	}
}
