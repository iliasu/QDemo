package com.elvenrings.qdemo.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

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
	private QPanel panel = null;

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
		panel.setLayout(new BorderLayout());

		JTextArea textArea = new JTextArea();
		JTextPane textPane = new JTextPane();
		StyledDocument document = textPane.getStyledDocument();
		Style def = textPane.getStyledDocument().getStyle(StyleContext.DEFAULT_STYLE);
		Style style = document.addStyle("regular", def);
		StyleConstants.setFontFamily(style, "SansSerif");


		Dimension dm = new Dimension(300, 185);
		textPane.setMaximumSize(dm);
		textPane.setMinimumSize(dm);
		textPane.setPreferredSize(dm);
		textPane.setEditable(false);
		textPane.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.add(scrollPane, BorderLayout.CENTER);

		Preamble preamble = question.getPreamble();
		for (String str : preamble.getText())
		{

			sb.append(str);
			try
			{
				document.insertString(document.getLength(), str, document.getStyle("regular"));
			} catch (BadLocationException e)
			{
				e.printStackTrace();
			}
		}
		sb.append("\n\n");
		try
		{
			document.insertString(document.getLength(), "\n\n", document.getStyle("regular"));
		} catch (BadLocationException e1)
		{
			e1.printStackTrace();
		}
		Body body = question.getBody();
		for (String str : body.getText())
		{
			try
			{
				document.insertString(document.getLength(), str, document.getStyle("regular"));
			} catch (BadLocationException e)
			{
				e.printStackTrace();
			}
			sb.append(str);
		}
		
		textArea.revalidate();
		textArea.repaint();
		textArea.setEditable(false);
		return panel;
	}



	public JButton getSubmitButton()
	{
		return this.submitButton;
	}
}
