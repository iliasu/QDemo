package com.elvenrings.qdemo.view.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.model.Body;
import com.elvenrings.qdemo.model.Preamble;
import com.elvenrings.qdemo.view.AbstractRenderer;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

/**
 * An abstract class that provides the following functionality<br>
 * 1. Ability to register/de-register listeners. <br>
 * 2. Rendering of the <code>Preamble</code> and <code>Body</code> parts of a
 * Question on a {@link com.elvenrings.qdemo.view.swing.QPanel}.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public abstract class DefaultSwingRenderer extends AbstractRenderer implements SwingRenderer
{
	protected JButton submitButton;
	protected List<SubmitSwingListener> listeners = new ArrayList<SubmitSwingListener>();
	protected QPanel panel = null;

	/**
	 * Registers a <code>SubmitSwingListener</code> with this Renderer.
	 * 
	 * @param listener
	 *            <code>SubmitSwingListener</code> to register with this Renderer.
	 */
	public void addSubmitListener(SubmitSwingListener listener)
	{
		listeners.add(listener);
	}

	/**
	 * De-registers a <code>SubmitSwingListener</code> from this Renderer.
	 * 
	 * @param listener
	 *            <code>SubmitSwingListener</code> to de-register from this
	 *            Renderer.
	 */
	public void removeSubmitListener(SubmitSwingListener listener)
	{
		listeners.remove(listener);
	}
	
	/**
	 * Renders the <code>Preamble</code> and <code>Body</code> parts of a Question.
	 * 
	 * @return Rendered <code>Question</code> on a QPanel.
	 */
	@Override
	public QPanel render()
	{
		StringBuilder sb = new StringBuilder();
		panel = new QPanel();
		panel.setLayout(new GridBagLayout());
		JLabel label = new JLabel();
		layoutComponents(label);
		
		Preamble preamble = question.getPreamble();
		for (String str : preamble.getText())
		{
			sb.append(str);
		}

		Body body = question.getBody();
		for (String str : body.getText())
		{
			sb.append(str);
		}
		label.setText(sb.toString());
		return panel;
	}

	private void layoutComponents(JLabel label)
	{
		GridBagConstraints gc = panel.getConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.weighty = 1;
		panel.add(label,gc);
	}
	
}
