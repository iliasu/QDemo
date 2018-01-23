package com.elvenrings.qdemo.view.swing.markers;

import java.util.Set;

import com.elvenrings.qdemo.exceptions.InvalidChoiceException;
import com.elvenrings.qdemo.interfaces.Marker;
import com.elvenrings.qdemo.model.MultipleChoice;
import com.elvenrings.qdemo.view.events.Event;
import com.elvenrings.qdemo.view.events.MultipleChoiceSelectionEvent;

/**
 * Determines if a Multiple-Choice Question has been answered correctly or
 * otherwise.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class MultipleChoiceMarker implements Marker
{

	/** {@inheritDoc} */
	@Override
	public boolean isCorrect(Event event)
	{
		MultipleChoiceSelectionEvent e = (MultipleChoiceSelectionEvent) event;
		MultipleChoice choice = (MultipleChoice) e.getChoice();

		Set<Integer> input = e.getInput();

		Set<Integer> correctChoices = null;
		try
		{
			correctChoices = choice.getCorrectChoices();
		} catch (InvalidChoiceException e1)
		{
			e1.printStackTrace();
		}

		if (correctChoices == null)
		{
			return false;
		}

		if (input == null)
		{
			return false;
		}

		if (correctChoices.size() != input.size())
		{
			return false;
		}

		for (Integer i : input)
		{
			if (!correctChoices.contains(i))
			{
				return false;
			}
		}

		return true;
	}

}
