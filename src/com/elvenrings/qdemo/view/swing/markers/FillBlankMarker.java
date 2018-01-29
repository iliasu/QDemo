package com.elvenrings.qdemo.view.swing.markers;

import com.elvenrings.qdemo.interfaces.Marker;
import com.elvenrings.qdemo.model.FillBlank;
import com.elvenrings.qdemo.view.events.Event;
import com.elvenrings.qdemo.view.events.FillBlankEvent;

/**
 * Determines if a Fill-In-The-Blank Question has been answered correctly or
 * otherwise.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class FillBlankMarker implements Marker
{
	

	public FillBlankMarker()
	{
	}

	/** {@inheritDoc} */
	@Override
	public boolean isCorrect(Event event)
	{
		FillBlankEvent e = (FillBlankEvent) event;
		FillBlank choice = (FillBlank) e.getChoice();

		String[] input = e.getInput();

		return choice.matches(input);
	}

}
