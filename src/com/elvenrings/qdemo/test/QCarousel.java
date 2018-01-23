package com.elvenrings.qdemo.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.view.swing.QPanel;

public class QCarousel<T extends SwingRenderer> implements Iterable<QPanel>
{
	private List<QPanel> qPanels = new LinkedList<QPanel>();
	private QList<T> qlist;
	private ListIterator<QPanel> iterator;

	public QCarousel(QList<T> qlist)
	{
		this.qlist = qlist;
		setup();
		iterator = qPanels.listIterator();
	}

	public QCarousel(QList<T> qlist, int initialPosition)
	{
		this.qlist = qlist;
		setup();
		iterator = qPanels.listIterator(initialPosition);
	}

	private void setup()
	{
		qPanels.clear();

		for (T renderer : qlist)
		{
			qPanels.add(renderer.render());
		}
		iterator = qPanels.listIterator();
	}

	public QPanel next()
	{
		return iterator.next();
	}

	public QPanel previous()
	{
		return iterator.previous();
	}

	@Override
	public Iterator<QPanel> iterator()
	{
		return qPanels.iterator();
	}

}
