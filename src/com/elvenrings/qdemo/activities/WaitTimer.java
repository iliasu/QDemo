package com.elvenrings.qdemo.activities;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import com.elvenrings.qdemo.app.DefaultCarousel;

public class WaitTimer extends TimerTask
{
	private long sleepTime;
	private DefaultCarousel carousel;
	private Timer timer;
	private int sequence = 0;
	private volatile boolean running = true;
	private volatile boolean started = false;
	private volatile boolean firstPass = true;

	public WaitTimer(long sleepTime, DefaultCarousel carousel)
	{
		this.sleepTime = sleepTime;
		this.carousel = carousel;
		this.timer = new Timer("Wait Timer:" + (++sequence));
	}

	@Override
	public void run()
	{
		if (running)
		{
			if (!started)
			{
				// allows this timer to run at least 2 times.
				started = true;
			} else
			{
				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						carousel.getNextButton().execute();
					}
				});

				timer.cancel();
				running = false;
			}

		}
	}

	public void startTimer()
	{
		System.out.println("WAIT THREAD: " + Thread.currentThread());
		timer.schedule(this, new Date(), this.sleepTime);
		running = true;
	}

	public boolean isStillRunning()
	{
		return running;
	}

}
