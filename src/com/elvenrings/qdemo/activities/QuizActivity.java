package com.elvenrings.qdemo.activities;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

import com.elvenrings.qdemo.app.ApplicationContext;
import com.elvenrings.qdemo.app.CarouselContainer;
import com.elvenrings.qdemo.app.CarouselControlBox;
import com.elvenrings.qdemo.app.DefaultCarousel;
import com.elvenrings.qdemo.app.OptionsDialog;
import com.elvenrings.qdemo.utils.TrackerException;
import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;
import com.elvenrings.qdemo.view.swing.listeners.QuestionFileLoadListener;
import com.elvenrings.qdemo.view.swing.markers.SingleGrader;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class QuizActivity extends Activity implements QuestionFileLoadListener
{
	private ApplicationContext context;
	private static volatile int sequence = 0;
	
	
	public QuizActivity(ApplicationContext context)
	{
		this.context = context;
		sequence++;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	public void XMLFileLoaded(QuestionFileLoadEvent event)
	{
		context = ApplicationContext.getInstance();
		OptionsDialog optionsDialog = (OptionsDialog) context.get("optionsDialog");
		
		if(!optionsDialog.isQuizMode())
		{
			return;
		}

		JComboBox<CarouselContainer> combobox = (JComboBox<CarouselContainer>) context.get("carouselList");
		JTextArea updateArea = (JTextArea) context.get("updateArea");

		JPanel welcomePanel = new JPanel();
		welcomePanel.add(new JLabel("Welcome"));
		JPanel endPanel = new JPanel();
		endPanel.add(new JLabel("End"));

		if (!optionsDialog.isWelcomeScreenSet())
		{
			welcomePanel = null;
		}
		if (!optionsDialog.isEndScreenSet())
		{
			endPanel = null;
		}

		try
		{
			ReadQuestions readQuestions = event.getReadQuestions();
			String name = "Quiz Mode |" + readQuestions.questionSetName + "[" + sequence++ +"]"; 
			DefaultCarousel carousel = new DefaultCarousel(name, readQuestions, welcomePanel, endPanel, true);

			CarouselControlBox controlBox = new CarouselControlBox(carousel, false);
			CarouselContainer container = new CarouselContainer(carousel, controlBox);
			//GroupGrader groupGrader = new GroupGrader();
			//groupGrader.addGroupGraderResultListener(controlBox);

			 SingleGrader singleGrader = new SingleGrader();
			 singleGrader.addSingleGraderResultListener(controlBox);

			// if (groupSubmit.isSelected())
			// {
			//controlBox.setGraderListener(groupGrader);
			// } else
			// {
			 controlBox.setSingleGraderListener(singleGrader);
			// }

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
