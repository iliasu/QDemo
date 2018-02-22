package com.elvenrings.qdemo.app;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

import com.elvenrings.qdemo.utils.TrackerException;
import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;
import com.elvenrings.qdemo.view.swing.listeners.QuestionFileLoadListener;
import com.elvenrings.qdemo.view.swing.markers.GroupGrader;
import com.elvenrings.qdemo.view.swing.markers.SingleGrader;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class Loader implements QuestionFileLoadListener
{
	ApplicationContext context;

	Loader(ApplicationContext context)
	{
		this.context = context;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	public void XMLFileLoaded(QuestionFileLoadEvent event)
	{
		ApplicationContext context = ApplicationContext.getInstance();
		JPanel welcomePanel = new JPanel();
		welcomePanel.add(new JLabel("Welcome"));
		JPanel endPanel = new JPanel();
		endPanel.add(new JLabel("End"));

		JCheckBoxMenuItem welcomeScreen = (JCheckBoxMenuItem) context.get("welcomeScreen");
		JCheckBoxMenuItem endScreen = (JCheckBoxMenuItem) context.get("endScreen");
		JRadioButtonMenuItem singleSubmit = (JRadioButtonMenuItem) context.get("singleSubmit");
		JRadioButtonMenuItem groupSubmit = (JRadioButtonMenuItem) context.get("groupSubmit");
		JComboBox<CarouselContainer> combobox = (JComboBox<CarouselContainer>) context.get("carouselList");
		JTextArea updateArea = (JTextArea) context.get("updateArea");

		if (!welcomeScreen.isSelected())
		{
			welcomePanel = null;
		}
		if (!endScreen.isSelected())
		{
			endPanel = null;
		}

		try
		{
			ReadQuestions readQuestions = event.getReadQuestions();
			String name = readQuestions.questionSetName + "|" + event.hashCode();
			DefaultCarousel carousel = new DefaultCarousel(name, readQuestions, welcomePanel, endPanel,
					singleSubmit.isSelected(), false);

			CarouselControlBox controlBox = new CarouselControlBox(carousel, groupSubmit.isSelected());
			CarouselContainer container = new CarouselContainer(carousel, controlBox);
			GroupGrader groupGrader = new GroupGrader();
			groupGrader.addGroupGraderResultListener(controlBox);

			SingleGrader singleGrader = new SingleGrader();
			singleGrader.addSingleGraderResultListener(controlBox);

			if (groupSubmit.isSelected())
			{
				controlBox.setGraderListener(groupGrader);
			} else
			{
				controlBox.setSingleGraderListener(singleGrader);
			}

			combobox.addItem(container);
			combobox.setSelectedItem(container);
			updateArea.append(event.getFileName() + " successfully loaded as: " + carousel.toString() + "\n");
			combobox.requestFocus();

		} catch (TrackerException e)
		{
			updateArea.append("failure to load file: " + event.getFileName() + "\n");
			e.printStackTrace();
		}

		updateArea.repaint();
		updateArea.revalidate();
	}

}
