package com.elvenrings.qdemo.view.events.swing;

import java.util.Set;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.view.events.MultipleChoiceSelectionEvent;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;

/**
* An event that is generated when a user answers a Multiple-Choice type of
* question from a <b>Swing</b> component.
* 
* @author Iliasu Salifu
* @author iliasu@elvenrings.com
* @since 1.0
*/
public class MultipleChoiceSwingSelectionEvent extends MultipleChoiceSelectionEvent
{
	private MultipleChoiceSwingRenderer renderer;

	/**
	 * Saves a copy of the Renderer in the event object thus allowing event
	 * listeners an opportunity to interact with the renderer
	 * 
	 * @param renderer
	 *            <code>MultipleChoiceSwingRenderer</code> that rendered the
	 *            question for which this event is raised.
	 * @param input
	 *            Integer representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	public MultipleChoiceSwingSelectionEvent(MultipleChoiceSwingRenderer renderer, Set<Integer> input, Choice choice)
	{
		super(input, choice);
		this.renderer = renderer;
	}

	/**
	 * Retrieves the <code>MultipleChoiceSwingRenderer</code> that rendered the
	 * question for which this event is raised.
	 * 
	 * @return <code>MultipleChoiceSwingRenderer</code>
	 *            that rendered the question for which this event is raised.
	 */
	public MultipleChoiceSwingRenderer getRenderer()
	{
		return renderer;
	}
}
