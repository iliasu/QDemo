package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.utils.Tracker;
import com.elvenrings.qdemo.utils.TrackerException;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class DefaultCarousel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private Tracker tracker;
	private ReadQuestions readQuestions;
	List<Question> questions;
	private CardLayout cardManager;
	InternalMediator mediator = new InternalMediator();
	private int count = 0;
	private boolean submitImmediate = true;
	private String name;

	public DefaultCarousel(String name, ReadQuestions readQuestions, JPanel firstPanel, JPanel lastPanel,
			boolean submitImmediate) throws TrackerException
	{
		this.name = name;
		this.readQuestions = readQuestions;
		this.submitImmediate = submitImmediate;
		questions = this.readQuestions.questions;
		cardManager = new CardLayout();

		this.setLayout(new BorderLayout());
		mainPanel.setLayout(cardManager);
		buttonPanel.setLayout(new BorderLayout());

		mainPanel.setPreferredSize(new Dimension(800, 600));
		buttonPanel.setPreferredSize(new Dimension(800, 30));
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);

		if (firstPanel != null)
		{
			this.add(Integer.toString(++count), firstPanel);
		}

		createQuestionPanels();

		if (lastPanel != null)
		{
			this.add(Integer.toString(++count), firstPanel);
		}

		tracker = new Tracker();
		tracker.init(1, count, 1);

		NextButton nextButton = new NextButton("Next", mediator);
		PrevButton prevButton = new PrevButton("Prev", mediator);
		FirstButton firstButton = new FirstButton("First", mediator);
		LastButton lastButton = new LastButton("Last", mediator);

		Box box = Box.createHorizontalBox();
		box.add(firstButton);
		box.add(Box.createHorizontalGlue());
		box.add(prevButton);
		box.add(Box.createHorizontalGlue());
		box.add(nextButton);
		box.add(Box.createHorizontalGlue());
		box.add(lastButton);
		buttonPanel.add(box);

		mediator.registerNext(nextButton);
		mediator.registerPrev(prevButton);
		mediator.registerFirst(firstButton);
		mediator.registerLast(lastButton);

		this.revalidate();

	}

	@Override
	public String toString()
	{
		return "<" + name + ">";
	}

	protected void createQuestionPanels()
	{
		SingleChoiceSwingRenderer scsr = null;
		MultipleChoiceSwingRenderer mcsr = null;
		FillBlankSwingRenderer fbsr = null;

		for (Question q : questions)
		{
			if (q instanceof SingleChoiceQuestion)
			{
				SingleChoiceQuestion q2 = (SingleChoiceQuestion) q;
				scsr = new SingleChoiceSwingRenderer(q2);
				mainPanel.add(Integer.toString(++count), scsr.render());
				JButton submitButton = scsr.getSubmitButton();
				if (submitImmediate)
				{
					submitButton.setEnabled(true);
				} else
				{
					submitButton.setEnabled(false);
				}

			}
			if (q instanceof MultipleChoiceQuestion)
			{
				MultipleChoiceQuestion q2 = (MultipleChoiceQuestion) q;
				mcsr = new MultipleChoiceSwingRenderer(q2);
				mainPanel.add(Integer.toString(++count), mcsr.render());
				JButton submitButton = mcsr.getSubmitButton();
				if (submitImmediate)
				{
					submitButton.setEnabled(true);
				} else
				{
					submitButton.setEnabled(false);
				}
			}
			if (q instanceof FillBlankQuestion)
			{
				FillBlankQuestion q2 = (FillBlankQuestion) q;
				fbsr = new FillBlankSwingRenderer(q2);
				mainPanel.add(Integer.toString(++count), fbsr.render());
				JButton submitButton = fbsr.getSubmitButton();
				if (submitImmediate)
				{
					submitButton.setEnabled(true);
				} else
				{
					submitButton.setEnabled(false);
				}
			}
		}
	}

	protected class InternalMediator
	{
		private NextButton nextButton;
		private PrevButton prevButton;
		private FirstButton firstButton;
		private LastButton lastButton;

		void registerNext(NextButton nextButton)
		{
			this.nextButton = nextButton;
		}

		void registerPrev(PrevButton prevButton)
		{
			this.prevButton = prevButton;
		}

		void registerFirst(FirstButton firstButton)
		{
			this.firstButton = firstButton;
		}

		void registerLast(LastButton lastButton)
		{
			this.lastButton = lastButton;
		}

		void next() throws TrackerException
		{
			tracker.next();
			if (tracker.hasNext())
			{
				nextButton.setEnabled(true);
				lastButton.setEnabled(true);
			} else
			{
				nextButton.setEnabled(false);
				lastButton.setEnabled(false);
			}

			if (tracker.hasPrev())
			{
				prevButton.setEnabled(true);
				firstButton.setEnabled(true);
			} else
			{
				prevButton.setEnabled(false);
				firstButton.setEnabled(false);
			}

		}

		void prev() throws TrackerException
		{
			tracker.prev();
			if (tracker.hasNext())
			{
				nextButton.setEnabled(true);
				lastButton.setEnabled(true);
			} else
			{
				nextButton.setEnabled(false);
				lastButton.setEnabled(false);
			}

			if (tracker.hasPrev())
			{
				prevButton.setEnabled(true);
				firstButton.setEnabled(true);
			} else
			{
				prevButton.setEnabled(false);
				firstButton.setEnabled(false);
			}
		}

		void first() throws TrackerException
		{
			tracker.moveToFirst();
			firstButton.setEnabled(false);
			prevButton.setEnabled(false);
			if (tracker.hasNext())
			{
				nextButton.setEnabled(true);
				lastButton.setEnabled(true);
			}
		}

		void last() throws TrackerException
		{
			tracker.moveToLast();
			lastButton.setEnabled(false);
			nextButton.setEnabled(false);
			if (tracker.hasPrev())
			{
				firstButton.setEnabled(true);
				prevButton.setEnabled(true);
			}
		}
	}

	protected class NextButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		InternalMediator med;

		NextButton(String label, InternalMediator med)
		{
			this.setText(label);
			this.med = med;
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					try
					{

						med.next();
						cardManager.next(mainPanel);

					} catch (TrackerException e1)
					{
						e1.printStackTrace();
					}
				}
			});
		}
	}

	protected class PrevButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		InternalMediator med;

		PrevButton(String label, InternalMediator med)
		{
			this.setText(label);
			this.med = med;
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					try
					{

						med.prev();
						cardManager.previous(mainPanel);

					} catch (TrackerException e1)
					{
						e1.printStackTrace();
					}
				}
			});
		}
	}

	protected class FirstButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		InternalMediator med;

		FirstButton(String label, InternalMediator med)
		{
			this.setText(label);
			this.med = med;
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					try
					{

						med.first();
						cardManager.first(mainPanel);

					} catch (TrackerException e1)
					{
						e1.printStackTrace();
					}
				}
			});
		}
	}

	protected class LastButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		InternalMediator med;

		LastButton(String label, InternalMediator med)
		{
			this.setText(label);
			this.med = med;
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					try
					{

						med.last();
						cardManager.last(mainPanel);

					} catch (TrackerException e1)
					{
						e1.printStackTrace();
					}
				}
			});
		}
	}
}
