package com.elvenrings.qdemo.view.swing.markers;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.interfaces.Marker;
import com.elvenrings.qdemo.model.SingleChoice;
import com.elvenrings.qdemo.view.events.Event;
import com.elvenrings.qdemo.view.events.SingleChoiceSelectionEvent;

/**
 * Determines if a Single-Choice Question has been answered correctly or
 * otherwise.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class SingleChoiceMarker implements Marker
{
	/** {@inheritDoc} */
	@Override
	public boolean isCorrect(Event event)
	{
		SingleChoiceSelectionEvent e = (SingleChoiceSelectionEvent) event;

		SingleChoice choice = (SingleChoice) e.getChoice();

		Integer input = e.getInput();

		boolean val = false;

		try
		{
			val = choice.getCorrectChoice() == input;
		} catch (InvalidChoiceException e1)
		{
			e1.printStackTrace();
		}
		return val;
	}
}
