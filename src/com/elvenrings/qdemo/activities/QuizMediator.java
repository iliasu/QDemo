package com.elvenrings.qdemo.activities;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;

import com.elvenrings.qdemo.app.CarouselContainer;
import com.elvenrings.qdemo.app.DefaultCarousel;
import com.elvenrings.qdemo.app.DefaultCarousel.CarouselMediator;
import com.elvenrings.qdemo.utils.TrackerException;

public class QuizMediator
{
	private JButton startButton;
	private JButton groupSubmitButton;
	private CarouselMediator carouselMediator;
	private JComboBox<CarouselContainer> combobox;
	private DefaultCarousel carousel;
	private JMenuItem loadItem;
	private MainTimer timer;
	private boolean timed;

	public MainTimer getTimer()
	{
		return timer;
	}

	public void setTimer(MainTimer timer)
	{
		this.timer = timer;
	}

	public DefaultCarousel getCarousel()
	{
		return carousel;
	}

	public void setCarousel(DefaultCarousel carousel)
	{
		this.carousel = carousel;
	}

	public QuizMediator(boolean timed)
	{
		this.timed = timed;
	}

	public void init()
	{
		this.startButton.setEnabled(true);
		this.groupSubmitButton.setEnabled(false);
		this.combobox.setEnabled(true);
		this.carouselMediator.disableAll();

	}

	public void quizStarted()
	{
		this.startButton.setEnabled(false);
		this.groupSubmitButton.setEnabled(false);
		this.combobox.setEnabled(false);
		try
		{
			/*this.carousel.setAutoScroll(false);
			if (timed)
			{
				this.carouselMediator.disableAll();
			} else
			{
				this.carousel.setAutoScroll(true);
				this.carouselMediator.init();
			}*/
			this.carousel.setAutoScroll(timed);
			this.carouselMediator.init();

		} catch (TrackerException e)
		{
			e.printStackTrace();
		}
	}

	public CarouselMediator getCarouselMediator()
	{
		return carouselMediator;
	}

	public void setCarouselMediator(CarouselMediator carouselMediator)
	{
		this.carouselMediator = carouselMediator;
	}

	public void quizEnded()
	{
		this.startButton.setEnabled(false);
		this.groupSubmitButton.setEnabled(false);
		this.combobox.setEnabled(true);
		try
		{
			this.carousel.setAutoScroll(false);
			this.carouselMediator.init();
		} catch (TrackerException e)
		{
			e.printStackTrace();
		}

		this.combobox.repaint();

	}

	public void quizNext()
	{
		this.startButton.setEnabled(false);
		this.groupSubmitButton.setEnabled(false);
		this.combobox.setEnabled(false);
		this.combobox.repaint();
	}

	public boolean isTimed()
	{
		return timed;
	}

	public void setTimed(boolean timed)
	{
		this.timed = timed;
	}

	public JButton getStartButton()
	{
		return startButton;
	}

	public void setStartButton(JButton startButton)
	{
		this.startButton = startButton;
	}

	public JButton getGroupSubmitButton()
	{
		return groupSubmitButton;
	}

	public void setGroupSubmitButton(JButton groupSubmitButton)
	{
		this.groupSubmitButton = groupSubmitButton;
	}

	public JComboBox<CarouselContainer> getCombobox()
	{
		return combobox;
	}

	public void setCombobox(JComboBox<CarouselContainer> combobox)
	{
		this.combobox = combobox;
	}

	public JMenuItem getLoadItem()
	{
		return loadItem;
	}

	public void setLoadItem(JMenuItem loadItem)
	{
		this.loadItem = loadItem;
	}

}
