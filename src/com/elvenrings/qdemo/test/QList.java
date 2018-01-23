package com.elvenrings.qdemo.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

public class QList<T extends SwingRenderer> implements Iterable<T>
{
	private List<T> list = new LinkedList<T>();
	private Set<SubmitSwingListener> listeners = new HashSet<SubmitSwingListener>();

	public void addRenderer(T renderer)
	{
		list.add(renderer);
		for (SubmitSwingListener listener : listeners)
		{
			renderer.addSubmitListener(listener);
		}
	}

	public void removeRenderer(T renderer)
	{
		list.remove(renderer);
		for (SubmitSwingListener listener : listeners)
		{
			renderer.removeSubmitListener(listener);
		}
	}

	public void addListener(SubmitSwingListener listener)
	{
		listeners.add(listener);
		for (T t : list)
		{
			t.addSubmitListener(listener);
		}
	}

	public void removeListener(SubmitSwingListener listener)
	{
		listeners.remove(listener);
		for (T t : list)
		{
			t.removeSubmitListener(listener);
		}
	}

	public int size()
	{
		return list.size();
	}

	public T get(int index)
	{
		return list.get(index);
	}

	public ListIterator<T> listIterator()
	{
		return list.listIterator();
	}

	@Override
	public Iterator<T> iterator()
	{
		return list.iterator();
	}
}
