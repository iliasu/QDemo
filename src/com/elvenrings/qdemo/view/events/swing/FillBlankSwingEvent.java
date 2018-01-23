package com.elvenrings.qdemo.view.events.swing;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.view.events.FillBlankEvent;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;

/**
 * An event that is generated when a user answers a Fill-In-The-Blank type of
 * question from a <b>Swing</b> component.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlankSwingEvent extends FillBlankEvent
{
	private FillBlankSwingRenderer renderer;

	/**
	 * Saves a copy of the Renderer in the event object thus allowing event
	 * listeners an opportunity to interact with the renderer
	 * 
	 * @param renderer
	 *            <code>FillBlankSwingRenderer</code> that rendered the question
	 *            for which this event is raised.
	 * @param input
	 *            Integer representing the user input.
	 * @param choice
	 *            The choices presented to the user.
	 */
	public FillBlankSwingEvent(FillBlankSwingRenderer renderer, String[] input, Choice choice)
	{
		super(input, choice);
		this.renderer = renderer;
	}

	/**
	 * Retrieves the <code>FillBlankSwingRenderer</code> that rendered the
	 * question for which this event is raised.
	 * 
	 * @return <code>FillBlankSwingRenderer</code>
	 *            that rendered the question for which this event is raised.
	 */
	public FillBlankSwingRenderer getRenderer()
	{
		return renderer;
	}

}
