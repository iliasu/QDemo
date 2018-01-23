package com.elvenrings.qdemo.interfaces;

import com.elvenrings.qdemo.view.events.Event;

/**
 * Implementation classes of this interface determine if a question has been
 * answered correctly or otherwise after receiving an event after certain input
 * actions have been performed.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public interface Marker
{
	/**
	 * Returns a boolean indicating if a question has been answered correctly or
	 * wrongly in response to an input event.
	 * 
	 * @return boolean indicating if a question has been answered correctly or
	 *         wrongly
	 * @param event
	 *            with information about question answered
	 */
	public  boolean isCorrect(Event event);
}
