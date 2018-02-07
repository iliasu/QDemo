package com.elvenrings.qdemo.interfaces;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.view.swing.QPanel;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

/**
 * Implementation classes of this interface render <code>Question</code>s on
 * Swing components specifically <code>QPanel</code> which is a subclass of
 * <code>JPanel</code>.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public interface SwingRenderer
{
	/**
	 * Returns a <code>QPanel</code> with a question rendered on it.
	 * 
	 * @return QPanel with a question rendered on it.
	 */
	public QPanel render();
	
	public void addSubmitListener(SubmitSwingListener listener);
	
	public void removeSubmitListener(SubmitSwingListener listener);
	
	public JComponent[] getInputComponent();
	
	public JLabel getMessageLabel();
	
	public JButton getSubmitButton();
	
	public JLabel getColorStatusLabel();
	
	public Question getQuestion();
}
