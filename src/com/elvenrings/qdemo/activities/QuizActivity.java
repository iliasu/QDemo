package com.elvenrings.qdemo.activities;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.elvenrings.qdemo.activities.states.QuizInitialState;
import com.elvenrings.qdemo.activities.states.QuizState;
import com.elvenrings.qdemo.activities.states.QuizStateContext;
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

public class QuizActivity extends Activity implements QuizStateContext, QuestionFileLoadListener
{
	private ApplicationContext context;
	private static volatile int sequence = 0;
	private QuizMediator mediator;
	private QuizState quizState;

	public QuizActivity(ApplicationContext context)
	{
		this.context = context;
		this.mediator = new QuizMediator(false);
		sequence++;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	public void XMLFileLoaded(QuestionFileLoadEvent event)
	{
		context = ApplicationContext.getInstance();
		OptionsDialog optionsDialog = (OptionsDialog) context.get("optionsDialog");

		if (!optionsDialog.isQuizMode())
		{
			return;
		}

		JComboBox<CarouselContainer> combobox = (JComboBox<CarouselContainer>) context.get("carouselList");
		JMenuItem loadItem = (JMenuItem) context.get("loadItem");
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
			String name = "Quiz Mode |" + readQuestions.questionSetName + "[" + sequence++ + "]";
			DefaultCarousel carousel = new DefaultCarousel(name, readQuestions, welcomePanel, endPanel, true, true);

			CarouselControlBox controlBox = new CarouselControlBox(carousel, false);
			CarouselContainer container = new CarouselContainer(carousel, controlBox);
			SingleGrader singleGrader = new SingleGrader();
			singleGrader.addSingleGraderResultListener(controlBox);

			JButton startButton = controlBox.getStartButton();
			JLabel countDownLabel = controlBox.getCountDownLabel();
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					MainTimer timer = new MainTimer(countDownLabel,
							optionsDialog.getQuizDisplayInterval(), controlBox, QuizActivity.this,
							optionsDialog.getQuestionTime(), optionsDialog.isWelcomeScreenSet(),
							optionsDialog.isEndScreenSet(), optionsDialog.getQuizSwitchDelay() * 1000);

					
					if (optionsDialog.isQuizTimeAttackSet())
					{
						mediator.setTimed(true);
						mediator.setTimer(timer);
						timer.startTimer();
					} else
					{
						countDownLabel.setForeground(new Color(50, 108, 220));;
						countDownLabel.setText("UNTIMED");
						mediator.setTimed(false);
					}
					quizState.handle(QuizActivity.this);

				}
			});

			JButton groupSubmitButton = controlBox.getGroupSubmitButton();
			mediator.setCarouselMediator(carousel.getMediator());
			mediator.setGroupSubmitButton(groupSubmitButton);
			mediator.setStartButton(startButton);
			mediator.setCombobox(combobox);
			mediator.setCarousel(carousel);
			mediator.setLoadItem(loadItem);
			mediator.init();
			quizState = new QuizInitialState();
			quizState.handle(this);

			controlBox.setSingleGraderListener(singleGrader);

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

	@Override
	public QuizMediator getQuizMediator()
	{
		return mediator;
	}

	@Override
	public void setState(QuizState state)
	{
		this.quizState = state;
	}

	public QuizState getQuizState()
	{
		return quizState;
	}

	public void setQuizState(QuizState quizState)
	{
		this.quizState = quizState;
	}

}
