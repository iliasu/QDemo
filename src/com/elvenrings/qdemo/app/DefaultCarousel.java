package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.utils.Tracker;
import com.elvenrings.qdemo.utils.TrackerException;
import com.elvenrings.qdemo.view.swing.DefaultSwingRenderer;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.QPanel;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class DefaultCarousel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private Tracker tracker;
	private ReadQuestions readQuestions;
	private List<Question> questions;
	private CardLayout cardManager;
	private CarouselMediator mediator;
	private Map<String, DefaultSwingRenderer> rendererMap;
	private List<SwingRenderer> rendererList = new ArrayList<>();
	private int count = 0;
	private boolean quizMode = true;
	private boolean autoscroll = false;
	private String name;

	private NextButton nextButton;
	private PrevButton prevButton;
	private FirstButton firstButton;
	private LastButton lastButton;
	private PageField pageField;

	public void moveNext()
	{
		mediator.nextButton.execute();
	}
	public void movePrev()
	{
		mediator.prevButton.execute();
	}
	public void moveFirst()
	{
		mediator.firstButton.execute();
	}
	public void moveLast()
	{
		mediator.lastButton.execute();
	}
	public FirstButton getFirstButton()
	{
		return firstButton;
	}
	
	public NextButton getNextButton()
	{
		return nextButton;
	}
	
	public PrevButton getPrevButton()
	{
		return prevButton;
	}
	
	public LastButton getLastButton()
	{
		return lastButton;
	}
	
	public PageField getPageField()
	{
		return pageField;
	}
	
	public void setAutoScroll(boolean autoscroll)
	{
		this.autoscroll = autoscroll;
		this.mediator.setAutoScroll(autoscroll);
	}
	
	public boolean getAutoscroll()
	{
		return autoscroll;
	}

	public DefaultCarousel(String name, ReadQuestions readQuestions, JPanel firstPanel, JPanel lastPanel,
			boolean quizMode, boolean autoscroll) throws TrackerException
	{
		this.mediator = new CarouselMediator(quizMode, autoscroll);
		this.name = name;
		this.readQuestions = readQuestions;
		this.quizMode = quizMode;
		this.autoscroll = autoscroll;
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
			mainPanel.add(Integer.toString(++count), firstPanel);
		}

		createQuestionPanels();

		if (lastPanel != null)
		{
			mainPanel.add(Integer.toString(++count), lastPanel);
		}

		tracker = new Tracker();
		tracker.init(1, count, 1);

		nextButton = new NextButton(">", mediator);
		prevButton = new PrevButton("<", mediator);
		pageField = new PageField(mediator);
		firstButton = new FirstButton("<<", mediator);
		lastButton = new LastButton(">>", mediator);

		Box box = Box.createHorizontalBox();
		box.add(firstButton);
		box.add(Box.createHorizontalGlue());
		box.add(prevButton);
		box.add(Box.createHorizontalGlue());
		box.add(pageField);
		box.add(Box.createHorizontalGlue());
		box.add(nextButton);
		box.add(Box.createHorizontalGlue());
		box.add(lastButton);
		buttonPanel.add(box);

		this.revalidate();
		mediator.init();

	}

	@Override
	public String toString()
	{
		return name;
	}

	public List<SwingRenderer> getRendererList()
	{
		return rendererList;
	}

	public CarouselMediator getMediator()
	{
		return mediator;
	}

	protected void createQuestionPanels()
	{
		SingleChoiceSwingRenderer scsr = null;
		MultipleChoiceSwingRenderer mcsr = null;
		FillBlankSwingRenderer fbsr = null;
		rendererMap = new HashMap<>();
		for (Question q : questions)
		{
			if (q instanceof SingleChoiceQuestion)
			{
				SingleChoiceQuestion q2 = (SingleChoiceQuestion) q;
				scsr = new SingleChoiceSwingRenderer(q2);
				rendererList.add(scsr);
				String i = Integer.toString(++count);
				QPanel panel = scsr.render();
				rendererMap.put(i, scsr);
				mainPanel.add(i, panel);
				JButton submitButton = scsr.getSubmitButton();
				if (quizMode)
				{
					submitButton.setVisible(true);
				} else
				{
					submitButton.setVisible(false);
				}

			}
			if (q instanceof MultipleChoiceQuestion)
			{
				MultipleChoiceQuestion q2 = (MultipleChoiceQuestion) q;
				mcsr = new MultipleChoiceSwingRenderer(q2);
				rendererList.add(mcsr);
				String i = Integer.toString(++count);
				QPanel panel = mcsr.render();
				rendererMap.put(i, mcsr);
				mainPanel.add(i, panel);
				JButton submitButton = mcsr.getSubmitButton();
				if (quizMode)
				{
					submitButton.setVisible(true);
				} else
				{
					submitButton.setVisible(false);
				}
			}
			if (q instanceof FillBlankQuestion)
			{
				FillBlankQuestion q2 = (FillBlankQuestion) q;
				fbsr = new FillBlankSwingRenderer(q2);
				rendererList.add(fbsr);
				String i = Integer.toString(++count);
				QPanel panel = fbsr.render();
				rendererMap.put(i, fbsr);
				mainPanel.add(i, panel);
				JButton submitButton = fbsr.getSubmitButton();
				if (quizMode)
				{
					submitButton.setVisible(true);
				} else
				{
					submitButton.setVisible(false);
				}
			}
			
			System.out.println("Renderermap size: " + rendererMap.size());
		}
	}

	public Map<String, DefaultSwingRenderer> getRendererMap()
	{
		return rendererMap;
	}
	public class CarouselMediator
	{
		private NextButton nextButton;
		private PrevButton prevButton;
		private FirstButton firstButton;
		private LastButton lastButton;
		private PageField page;
		private boolean quizMode;
		private boolean autoscroll;

		public CarouselMediator()
		{
			this.quizMode = false;
		}

		public void setAutoScroll(boolean autoscroll)
		{
			this.autoscroll = autoscroll;
		}

		public CarouselMediator(boolean quizMode, boolean autoscroll)
		{
			this.quizMode = quizMode;
			this.autoscroll = autoscroll;
		}

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

		void registerPageField(PageField page)
		{
			this.page = page;
		}

		public void disableAll()
		{
			nextButton.setEnabled(false);
			lastButton.setEnabled(false);
			prevButton.setEnabled(false);
			firstButton.setEnabled(false);
			page.setEnabled(false);
		}

		public void init() throws TrackerException
		{
			page.setEnabled(true);
			page.setText(Integer.toString(tracker.getCurrent()));
			if(autoscroll)
			{
				page.setEditable(false);
			}
			if (tracker.hasNext())
			{
				
				if (autoscroll)
				{
					lastButton.setEnabled(false);
					nextButton.setEnabled(false);
				} else
				{
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
				}

			} else
			{
				nextButton.setEnabled(false);
				lastButton.setEnabled(false);
			}

			if (tracker.hasPrev())
			{
				
				if (autoscroll)
				{
					firstButton.setEnabled(false);
					prevButton.setEnabled(false);
				} else
				{
					firstButton.setEnabled(true);
					prevButton.setEnabled(true);
				}
			} else
			{
				prevButton.setEnabled(false);
				firstButton.setEnabled(false);
			}

		}

		private void next() throws TrackerException
		{
			tracker.next();
			page.setText(Integer.toString(tracker.getCurrent()));
			if (tracker.hasNext())
			{
				
				if (autoscroll)
				{
					nextButton.setEnabled(false);
					lastButton.setEnabled(false);
				}
				else
				{
					nextButton.setEnabled(true);
					lastButton.setEnabled(true);
				}
			} else
			{
				nextButton.setEnabled(false);
				lastButton.setEnabled(false);
			}

			if (tracker.hasPrev())
			{
				
				if (autoscroll)
				{
					firstButton.setEnabled(false);
					prevButton.setEnabled(false);
				}
				else
				{
					firstButton.setEnabled(true);
					prevButton.setEnabled(true);
				}
			} else
			{
				prevButton.setEnabled(false);
				firstButton.setEnabled(false);
			}

		}

		private void set(int pos) throws TrackerException
		{
			if (!tracker.setCurrent(pos))
			{
				return;
			}

			if (tracker.hasNext())
			{
				if(autoscroll)
				{
					nextButton.setEnabled(false);
					lastButton.setEnabled(false);
				}
				else {
				nextButton.setEnabled(true);
				lastButton.setEnabled(true);
				}
			} else
			{
				nextButton.setEnabled(false);
				lastButton.setEnabled(false);
			}

			if (tracker.hasPrev())
			{
				if(autoscroll)
				{
					prevButton.setEnabled(false);
					firstButton.setEnabled(false);
				}else {
				prevButton.setEnabled(true);
				firstButton.setEnabled(true);
				}
			} else
			{
				prevButton.setEnabled(false);
				firstButton.setEnabled(false);
			}

		}

		private void prev() throws TrackerException
		{
			tracker.prev();
			page.setText(Integer.toString(tracker.getCurrent()));
			if (tracker.hasNext())
			{
				
				if (autoscroll)
				{
					nextButton.setEnabled(false);
					lastButton.setEnabled(false);
				}
				{
					nextButton.setEnabled(true);
					lastButton.setEnabled(true);
				}
			} else
			{
				nextButton.setEnabled(false);
				lastButton.setEnabled(false);
			}

			if (tracker.hasPrev())
			{
				
				if (autoscroll)
				{
					firstButton.setEnabled(false);
					prevButton.setEnabled(false);
				}else
				{
					firstButton.setEnabled(true);
					prevButton.setEnabled(true);
				}
			} else
			{
				prevButton.setEnabled(false);
				firstButton.setEnabled(false);
			}
		}

		private void first() throws TrackerException
		{
			tracker.moveToFirst();
			page.setText(Integer.toString(tracker.getCurrent()));
			firstButton.setEnabled(false);
			prevButton.setEnabled(false);
			if (tracker.hasNext())
			{
				
				if (autoscroll)
				{
					lastButton.setEnabled(false);
					nextButton.setEnabled(false);
				}
				else
				{
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
				}
			}
		}

		private void last() throws TrackerException
		{
			tracker.moveToLast();
			page.setText(Integer.toString(tracker.getCurrent()));
			lastButton.setEnabled(false);
			nextButton.setEnabled(false);
			if (tracker.hasPrev())
			{
				if (autoscroll)
				{
					firstButton.setEnabled(false);
					prevButton.setEnabled(false);
				}
				else
				{
					firstButton.setEnabled(true);
					prevButton.setEnabled(true);
				}
				
			}
		}
	}

	public class NextButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		CarouselMediator med;

		NextButton(String label, CarouselMediator med)
		{
			this.setText(label);
			this.med = med;
			this.med.registerNext(this);
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					execute();
				}
			});
		}
		
		public void execute()
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
	}

	public class PrevButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		CarouselMediator med;

		PrevButton(String label, CarouselMediator med)
		{
			this.setText(label);
			this.med = med;
			this.med.registerPrev(this);
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					execute();
				}
			});
		}
		
		public void execute()
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
	}

	public class FirstButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		CarouselMediator med;

		FirstButton(String label, CarouselMediator med)
		{
			this.setText(label);
			this.med = med;
			this.med.registerFirst(this);
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					execute();
				}
			});
		}
		
		public void execute()
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
	}

	public class LastButton extends JButton
	{
		private static final long serialVersionUID = 1L;
		CarouselMediator med;

		LastButton(String label, CarouselMediator med)
		{
			this.setText(label);
			this.med = med;
			this.med.registerLast(this);
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					execute();
				}
			});
		}
		
		public void execute()
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
	}

	public class PageField extends JTextField
	{
		private static final long serialVersionUID = 1L;
		CarouselMediator med;

		PageField(CarouselMediator med)
		{
			this.setHorizontalAlignment(JTextField.CENTER);
			this.med = med;
			this.med.registerPageField(this);
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						String strPos = PageField.this.getText();
						Integer intPos = Integer.parseInt(PageField.this.getText());
						med.set(intPos);
						cardManager.show(mainPanel, strPos);
					} catch (NumberFormatException e1)
					{
						System.err.println(e1);
					} catch (TrackerException e2)
					{
						e2.printStackTrace();
					}
				}
			});

		}
		
	}
}
