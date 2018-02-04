package com.elvenrings.qdemo.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.xml.sax.SAXException;

import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;
import com.elvenrings.qdemo.view.swing.listeners.QuestionFileLoadListener;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class XMLFileLoader implements ActionListener
{
	private List<QuestionFileLoadListener> listeners = new ArrayList<>();
	private File lastFile;

	public void addQuestionFileLoadListener(QuestionFileLoadListener listener)
	{
		listeners.add(listener);
	}

	public void removeQuestionFileLoadListener(QuestionFileLoadListener listener)
	{
		listeners.remove(listener);
	}

	public void fireFileLoadEvent(QuestionFileLoadEvent event)
	{
		for (QuestionFileLoadListener listener : listeners)
		{
			listener.XMLFileLoaded(event);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ReadQuestions rq;
		JFileChooser fc = new JFileChooser(lastFile);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new XmlFileFilter());

		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			lastFile = file;
			System.out.println(file);

			try
			{
				rq = new ReadQuestions(file.getAbsolutePath());
				QuestionFileLoadEvent event = new QuestionFileLoadEvent(rq, file.getName());
				fireFileLoadEvent(event);

			} catch (IOException e1)
			{
				JOptionPane.showMessageDialog(null, "Unable to read file: " + file.getName(), "Error Message",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (SAXException e2)
			{
				JOptionPane.showMessageDialog(null, "Unable to parse xml file: " + file.getName(), "Error Message",
						JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}

	}

}

class XmlFileFilter extends FileFilter
{

	@Override
	public boolean accept(File f)
	{
		if (f.isDirectory())
		{
			return true;
		}

		if (f.getName().endsWith(".xml"))
		{
			return true;
		}

		return false;
	}

	@Override
	public String getDescription()
	{
		return "(.xml) files only";
	}

}