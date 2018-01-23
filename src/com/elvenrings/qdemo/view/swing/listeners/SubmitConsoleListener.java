package com.elvenrings.qdemo.view.swing.listeners;

import com.elvenrings.qdemo.view.events.console.FillBlankConsoleEvent;
import com.elvenrings.qdemo.view.events.console.MultipleChoiceConsoleSelectionEvent;
import com.elvenrings.qdemo.view.events.console.SingleChoiceConsoleSelectionEvent;

/**
 * Classes that implement this interface receive notifications from
 * ConsoleRenderers which they have registered with.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public interface SubmitConsoleListener
{
	/**
	 * Callback for when a Fill-In-The-Blank is answered from the console.
	 * 
	 * @param event
	 *            FillBlankConsoleEvent event generated when a Fill-In-The-Blank
	 *            Question is answered from the console.
	 */
	public void fillBlankConsoleEventOccurred(FillBlankConsoleEvent event);

	/**
	 * Callback for when a Single-Choice is answered from the console.
	 * 
	 * @param event
	 *            SingleChoiceConsoleSelectionEvent event generated when a
	 *            Single-Choice Question is answered from the console.
	 */
	public void singleChoiceConsoleEventOccurred(SingleChoiceConsoleSelectionEvent event);

	/**
	 * Callback for when a Multiple-Choice is answered from the console.
	 * 
	 * @param event
	 *            MultipleChoiceConsoleSelectionEvent event generated when a
	 *            Multiple-Choice Question is answered from the console.
	 */
	public void multipleChoiceConsoleEventOccurred(MultipleChoiceConsoleSelectionEvent event);
}
