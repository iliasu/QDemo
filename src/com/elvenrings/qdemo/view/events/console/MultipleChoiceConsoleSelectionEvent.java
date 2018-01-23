package com.elvenrings.qdemo.view.events.console;

import java.util.Set;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.view.console.MultipleChoiceConsoleRenderer;
import com.elvenrings.qdemo.view.events.MultipleChoiceSelectionEvent;

/**
 * An event that is generated when a user answers a Multiple-Choice type of
 * question from the <b>console</b>.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class MultipleChoiceConsoleSelectionEvent extends MultipleChoiceSelectionEvent
{

	private MultipleChoiceConsoleRenderer renderer;

	/**
	 * Saves a copy of the Renderer in the event object thus allowing event
	 * listeners an opportunity to interact with the renderer
	 * 
	 * @param renderer
	 *            <code>MultipleChoiceConsoleRenderer</code> that rendered the
	 *            question for which this event is raised.
	 * @param input
	 *            Integer representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	public MultipleChoiceConsoleSelectionEvent(MultipleChoiceConsoleRenderer renderer, Set<Integer> input,
			Choice choice)
	{
		super(input, choice);
		this.renderer = renderer;
	}

	/**
	 * Retrieves the <code>MultipleChoiceConsoleRenderer</code> that rendered the
	 * question for which this event is raised.
	 * 
	 * @return <code>MultipleChoiceConsoleRenderer</code>
	 *            that rendered the question for which this event is raised.
	 */
	public MultipleChoiceConsoleRenderer getRenderer()
	{
		return renderer;
	}
}
