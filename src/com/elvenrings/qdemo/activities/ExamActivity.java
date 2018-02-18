package com.elvenrings.qdemo.activities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.elvenrings.qdemo.activities.states.ExamInitialState;
import com.elvenrings.qdemo.activities.states.ExamState;
import com.elvenrings.qdemo.activities.states.ExamStateContext;
import com.elvenrings.qdemo.app.ApplicationContext;
import com.elvenrings.qdemo.app.CarouselContainer;
import com.elvenrings.qdemo.app.CarouselControlBox;
import com.elvenrings.qdemo.app.DefaultCarousel;
import com.elvenrings.qdemo.app.OptionsDialog;
import com.elvenrings.qdemo.utils.TrackerException;
import com.elvenrings.qdemo.view.events.swing.QuestionFileLoadEvent;
import com.elvenrings.qdemo.view.swing.listeners.QuestionFileLoadListener;
import com.elvenrings.qdemo.view.swing.markers.GroupGrader;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class ExamActivity extends Activity implements ExamStateContext, QuestionFileLoadListener
{
	private ApplicationContext context;
	private static volatile int sequence = 0;
	private ExamMediator mediator;
	private ExamState examState;

	public ExamActivity(ApplicationContext context)
	{
		this.context = context;
		this.mediator = new ExamMediator(false);
		sequence++;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	public void XMLFileLoaded(QuestionFileLoadEvent event)
	{

		context = ApplicationContext.getInstance();
		OptionsDialog optionsDialog = (OptionsDialog) context.get("optionsDialog");

		if (!optionsDialog.isExamMode())
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
			String name = "Exam Mode<" + readQuestions.questionSetName + "[" + sequence++ + "]>";
			DefaultCarousel carousel = new DefaultCarousel(name, readQuestions, welcomePanel, endPanel, false);

			CarouselControlBox controlBox = new CarouselControlBox(carousel, true);
			CarouselContainer container = new CarouselContainer(carousel, controlBox);
			GroupGrader groupGrader = new GroupGrader();
			groupGrader.addGroupGraderResultListener(controlBox);
			JButton startButton = controlBox.getStartButton();
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					ExamTimer timer = new ExamTimer(controlBox.getCountDownLabel(),
							optionsDialog.getExamDisplayInterval(), controlBox, ExamActivity.this,
							optionsDialog.getExamTime() * 60);

					if (optionsDialog.isExamTimeAttackSet())
					{
						mediator.setTimed(true);
						mediator.setTimer(timer);
						timer.startTimer();
					} else
					{
						mediator.setTimed(false);
					}
					examState.handle(ExamActivity.this);

				}
			});

			JButton groupSubmitButton = controlBox.getGroupSubmitButton();
			groupSubmitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					int result = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to submit all questions at once?", "Confirm Submission",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION)
					{
						controlBox.submitExam();
						examState.handle(ExamActivity.this);
					}

				}
			});
			mediator.setCarouselMediator(carousel.getMediator());
			mediator.setGroupSubmitButton(groupSubmitButton);
			mediator.setStartButton(startButton);
			mediator.setCombobox(combobox);
			mediator.setCarousel(carousel);
			mediator.init();
			examState = new ExamInitialState();
			examState.handle(this);

			controlBox.setGraderListener(groupGrader);

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
	public ExamMediator getExamMediator()
	{
		return mediator;
	}

	@Override
	public void setState(ExamState state)
	{
		this.examState = state;
	}

	public ExamState getExamState()
	{
		return examState;
	}

	public void setExamState(ExamState examState)
	{
		this.examState = examState;
	}

}
