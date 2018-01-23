package com.elvenrings.qdemo.view.swing.listeners;

import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;

/**
 * Classes that implement this interface receive notifications from
 * SwingRenderers which they have registered with.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public interface SubmitSwingListener
{
	/**
	 * Callback for when a Fill-In-The-Blank is answered from a Swing Component
	 * (QPanel).
	 * 
	 * @param event
	 *            FillBlankConsoleEvent event generated when a Fill-In-The-Blank
	 *            Question is answered from a QPanel.
	 */
	public void fillBlankSwingEventOccurred(FillBlankSwingEvent event);

	/**
	 * Callback for when a Single-Choice is answered from a Swing Component
	 * (QPanel).
	 * 
	 * @param event
	 *            SingleChoiceConsoleSelectionEvent event generated when a
	 *            Single-Choice Question is answered from the QPanel.
	 */
	public void singleChoiceSwingEventOccurred(SingleChoiceSwingSelectionEvent event);

	/**
	 * Callback for when a Multiple-Choice is answered from a Swing Component
	 * (QPanel).
	 * 
	 * @param event
	 *            MultipleChoiceConsoleSelectionEvent event generated when a
	 *            Multiple-Choice Question is answered from the QPanel.
	 */
	public void multipleChoiceSwingEventOccurred(MultipleChoiceSwingSelectionEvent event);
}
