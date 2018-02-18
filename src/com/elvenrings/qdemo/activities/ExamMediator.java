package com.elvenrings.qdemo.activities;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.elvenrings.qdemo.app.CarouselContainer;
import com.elvenrings.qdemo.app.DefaultCarousel;
import com.elvenrings.qdemo.app.DefaultCarousel.CarouselMediator;
import com.elvenrings.qdemo.utils.TrackerException;

public class ExamMediator
{
	private JButton startButton;
	private JButton groupSubmitButton;
	private CarouselMediator carouselMediator;
	private JComboBox<CarouselContainer> combobox;
	private DefaultCarousel carousel;
	private ExamTimer timer;
	private boolean timed; 
	

	public ExamTimer getTimer()
	{
		return timer;
	}


	public void setTimer(ExamTimer timer)
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


	public ExamMediator(boolean timed)
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
	public void examStarted()
	{
		this.startButton.setEnabled(false);
		this.groupSubmitButton.setEnabled(true);
		this.combobox.setEnabled(false);
		try
		{
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


	public void examEnded()
	{
		this.startButton.setEnabled(false);
		this.groupSubmitButton.setEnabled(false);
		this.combobox.setEnabled(true);
		this.carousel.getFirstButton().doClick();
		if(timed)
		{
			this.timer.cancelTimer();
		}
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

	
}
