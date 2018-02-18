package com.elvenrings.qdemo.activities;

import java.awt.Color;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.elvenrings.qdemo.app.CarouselControlBox;

public class QuizTimer extends TimerTask
{

	private JLabel countDownLabel;
	private long updateInterval;
	private CarouselControlBox controlBox;
	private QuizActivity quizActivity;
	private long taskDuration;
	private Timer timer;
	private static int sequence = 0;
	private int counter=0;
	private final static int HEARTBEAT = 1;
	
	public QuizTimer(JLabel countDownLabel, long updateInterval, CarouselControlBox controlBox, QuizActivity quizActivity, long taskDuration)
	{
		this.countDownLabel = countDownLabel;
		this.updateInterval = updateInterval;
		this.controlBox = controlBox;
		this.quizActivity = quizActivity;
		this.taskDuration = taskDuration;
		this.timer = new Timer("Timer:"+(++sequence));
		this.countDownLabel.setText(formatDuration(taskDuration));
	}
	
	@Override
	public void run()
	{
		counter++;
		taskDuration = taskDuration - HEARTBEAT;
		System.out.println("counter: " + counter + "| taskDuration: " + taskDuration);
		if(counter < updateInterval && taskDuration > 0)
		{
			return;
		}
		counter=0;

		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				if(taskDuration > 0)
				{
					if(taskDuration >= 60)
					{
						countDownLabel.setForeground(new Color(20, 128, 75));
					}
					else if( taskDuration < 60 && taskDuration > 20)
					{
						countDownLabel.setForeground(new Color(255, 185, 40));
					}
					else
					{
						countDownLabel.setForeground(Color.RED);
					}
					countDownLabel.setText(formatDuration(taskDuration));
				}
				else
				{
					timer.cancel();
					countDownLabel.setForeground(Color.LIGHT_GRAY);
					countDownLabel.setText("00:00:00");
					controlBox.submitExam();
					quizActivity.getQuizState().handle(quizActivity);
				}
			}
		});
		
	}

	private String formatDuration(long duration)
	{
		return String.format("%02d:%02d:%02d", duration / 3600,
                (duration % 3600) / 60, (duration % 60));
	}
	
	public void startTimer()
	{
		timer.schedule(this, new Date(), 1000);
	}
}
