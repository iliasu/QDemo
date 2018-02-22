package com.elvenrings.qdemo.activities;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.elvenrings.qdemo.app.CarouselControlBox;
import com.elvenrings.qdemo.view.swing.DefaultSwingRenderer;
import com.elvenrings.qdemo.view.swing.QPanel;

public class QuizTimer extends TimerTask
{

	private JLabel countDownLabel;
	private long updateInterval;
	private CarouselControlBox controlBox;
	private long taskDuration;
	private Timer timer;
	private static int sequence = 0;
	private int counter = 0;
	private final static int HEARTBEAT = 1;
	private int page;
	private volatile boolean running = false;
	
	
	public QuizTimer(JLabel countDownLabel, long updateInterval, CarouselControlBox controlBox, long taskDuration, int page)
	{
		this.countDownLabel = countDownLabel;
		this.updateInterval = updateInterval;
		this.controlBox = controlBox;
		this.taskDuration = taskDuration;
		this.timer = new Timer("Timer:" + (++sequence));
		QuizTimer.this.countDownLabel.setText(formatDuration(taskDuration));
		this.page = page;
		
		System.out.println("QUIZ TIMER CREATED IN THREAD: " + Thread.currentThread());
	}

	@Override
	public synchronized void run()
	{
		counter++;
		taskDuration = taskDuration - HEARTBEAT;
		System.out.println("counter: " + counter + "| taskDuration: " + taskDuration);
		if (counter < updateInterval && taskDuration > 0)
		{
			return;
		}
		counter = 0;

		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				Map<String, DefaultSwingRenderer> rendererMap = controlBox.getCarousel().getRendererMap();
				DefaultSwingRenderer renderer = rendererMap.get(Integer.toString(page));
				if(renderer == null)
				{
					System.out.println("Renderer is null");
				}
				System.out.println(renderer.getQuestion());
				Boolean questionAnswered = controlBox.isQuestionAnswered(renderer.getQuestion());
				
				if(questionAnswered)
				{
					System.err.println("Question answered");
					cancelTimer();
				}
				
				if (taskDuration > 0)
				{
					if (taskDuration >= 60)
					{
						countDownLabel.setForeground(new Color(20, 128, 75));
					} else if (taskDuration < 60 && taskDuration > 20)
					{
						countDownLabel.setForeground(new Color(255, 185, 40));
					} else
					{
						countDownLabel.setForeground(Color.RED);
					}
					countDownLabel.setText(formatDuration(taskDuration));
				} else
				{
					cancelTimer();
					countDownLabel.setForeground(Color.LIGHT_GRAY);
					countDownLabel.setText("");

					JButton submitButton = renderer.getSubmitButton();
					
					renderer.getQuestion().setAttempts(1);
					submitButton.doClick();
				}
			}
		});

	}

	private String formatDuration(long duration)
	{
		return String.format("%02d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}

	public void startTimer()
	{
		timer.schedule(this, new Date(), 1000);
		running = true;

	}

	public synchronized void cancelTimer()
	{
		if(running == false)
		{
			return;
		}
		timer.cancel();
		running = false;
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				//countDownLabel.setText("FINISHED");
				countDownLabel.setForeground(Color.LIGHT_GRAY);
			}
		});
		System.err.println("Running status: " + running);
	//	System.exit(1);
	}

	public synchronized boolean isStillRunning()
	{
		return running;
	}
}
