package com.elvenrings.qdemo.view.swing;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
	//private GridBagConstraints gc = new GridBagConstraints();
	private static final long serialVersionUID = 1L;
	protected JTabbedPane tabbedPane;
	protected JPanel questionPanel;
	protected JPanel solutionPanel;

	/** Default constructor creates a new QPanel */
	public QPanel()
	{
		tabbedPane = new JTabbedPane();
		questionPanel = new JPanel();
		solutionPanel = new JPanel();
		tabbedPane.addTab("question", questionPanel);
		tabbedPane.addTab("solution", solutionPanel);
		tabbedPane.setEnabledAt(1, false);
	}
	
	public JPanel getQuestionPanel()
	{
		return this.questionPanel;
	}
	
	public JPanel getSolutionPanel()
	{
		return this.solutionPanel;
	}
	
	public JTabbedPane getTabbedPane()
	{
		return this.tabbedPane;
	}
	//** Returns a <code>GridBagConstraints</code> object.
	// * 
	// * @return <code>GridBagConstraints</code> object
	// */
	//public GridBagConstraints getConstraints()
	//{
	//	return gc;
	//}
}
