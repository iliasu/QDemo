package com.elvenrings.qdemo.view.events.swing;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.view.events.SingleChoiceSelectionEvent;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;

/**
 * An event that is generated when a user answers a Single-Choice type of
 * question from a <b>Swing</b> component.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoiceSwingSelectionEvent extends SingleChoiceSelectionEvent
{
	private SingleChoiceSwingRenderer renderer;

	/**
	 * Saves a copy of the Renderer in the event object thus allowing event
	 * listeners an opportunity to interact with the renderer
	 * 
	 * @param renderer
	 *            <code>SingleChoiceSwingRenderer</code> that rendered the
	 *            question for which this event is raised.
	 * @param input
	 *            Integer representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	public SingleChoiceSwingSelectionEvent(SingleChoiceSwingRenderer renderer, Integer input, Choice choice)
	{
		super(input, choice);
		this.renderer = renderer;
	}

	/**
	 * Retrieves the <code>SingleChoiceSwingRenderer</code> that rendered the
	 * question for which this event is raised.
	 * 
	 * @return <code>SingleChoiceSwingRenderer</code>
	 *            that rendered the question for which this event is raised.
	 */
	public SingleChoiceSwingRenderer getRenderer()
	{
		return renderer;
	}
}
