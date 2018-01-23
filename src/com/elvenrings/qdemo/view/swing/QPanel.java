package com.elvenrings.qdemo.view.swing;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

/**
 * A subclass of JPanel, this class bundles with itself a GridBagConstraints
 * constraints object with which other classes can use for laying out components
 * on this panel.
 * 
 * @author Iliasu Salifu
 * @author iliasu@elvenrings.com
 * @since 1.0
 */
public class QPanel extends JPanel
{
	private GridBagConstraints gc = new GridBagConstraints();
	private static final long serialVersionUID = 1L;

	/** Default constructor creates a new QPanel */
	public QPanel()
	{
		super();
	}
	/** Returns a <code>GridBagConstraints</code> object.
	 * 
	 * @return <code>GridBagConstraints</code> object
	 */
	public GridBagConstraints getConstraints()
	{
		return gc;
	}
}
