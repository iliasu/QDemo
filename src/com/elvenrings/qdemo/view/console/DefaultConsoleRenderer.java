package com.elvenrings.qdemo.view.console;

import javax.swing.event.EventListenerList;

import com.elvenrings.qdemo.interfaces.ConsoleRenderer;
import com.elvenrings.qdemo.model.Body;
import com.elvenrings.qdemo.model.Preamble;
import com.elvenrings.qdemo.view.AbstractRenderer;
import com.elvenrings.qdemo.view.swing.listeners.SubmitConsoleListener;

/**
 * An abstract class that provides the following functionality<br>
 * 1. Ability to register/de-register listeners. <br>
 * 2. Rendering of the <code>Preamble</code> and <code>Body</code> parts of a
 * Question.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public abstract class DefaultConsoleRenderer extends AbstractRenderer implements ConsoleRenderer
{
	protected EventListenerList listeners = new EventListenerList();

	/**
	 * Registers a <code>SubmitConsoleListener</code> with this Renderer.
	 * 
	 * @param listener
	 *            <code>SubmitConsoleListener</code> to register with this Renderer.
	 */
	public void addSubmitListener(SubmitConsoleListener listener)
	{
		listeners.add(SubmitConsoleListener.class, listener);
	}

	/**
	 * De-registers a <code>SubmitConsoleListener</code> from this Renderer.
	 * 
	 * @param listener
	 *            <code>SubmitConsoleListener</code> to de-register from this
	 *            Renderer.
	 */
	public void removeSubmitListener(SubmitConsoleListener listener)
	{
		listeners.remove(SubmitConsoleListener.class, listener);
	}

	/**
	 * Renders the <code>Preamble</code> and <code>Body</code> parts of a Question.
	 * 
	 * @return Rendered <code>Question</code> as a String.
	 */
	public String render()
	{
		StringBuilder sb = new StringBuilder();

		Preamble preamble = question.getPreamble();

		for (String str : preamble.getText())
		{
			sb.append(str);
			sb.append("\n");
		}

		Body body = question.getBody();
		for (String str : body.getText())
		{
			sb.append(str);
			sb.append("\n");
		}

		return sb.toString();

	}

	/** {@inheritDoc} */
	@Override
	public abstract void getResponse();

}
