package com.elvenrings.qdemo.activities;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.elvenrings.qdemo.app.CarouselControlBox;

public class MainTimer extends TimerTask
{
	private JLabel countDownLabel;
	private long updateInterval;
	private CarouselControlBox controlBox;
	private QuizActivity quizActivity;
	private long taskDuration;
	private Timer timer;
	private static int sequence = 0;
	private final static int HEARTBEAT = 1;
	private boolean welcome;
	private int page;
	private long taskDurationCopy;
	private long switchDelay;
	private TimerState timerState = new TimerState();
	private int questionCount;
	private int currentCount = 0;

	private WaitTimer wt;
	private QuizTimer tm;

	public MainTimer(JLabel countDownLabel, long updateInterval, CarouselControlBox controlBox,
			QuizActivity quizActivity, long taskDuration, boolean welcome, boolean end, long switchDelay)
	{
		this.countDownLabel = countDownLabel;
		this.updateInterval = updateInterval;
		this.controlBox = controlBox;
		this.quizActivity = quizActivity;
		this.taskDuration = taskDuration;
		this.taskDurationCopy = taskDuration;
		this.timer = new Timer("Timer:" + (++sequence));
		this.countDownLabel.setText(formatDuration(taskDuration));
		this.switchDelay = switchDelay;
		this.welcome = welcome;
		this.questionCount = this.controlBox.getCarousel().getRendererList().size();

		if (this.welcome)
		{
			page = 1;
		} else
		{
			page = 0;
		}
	}

	@Override
	public void run()
	{
		System.err.println("MAIN TIMER STATE: " + timerState.getState());
		System.out.println("MAIN TIMER THREAD: " + Thread.currentThread());

		if (timerState.getState() == TimerState.STARTED)
		{
			System.err.println("Creating new Quiz Timer");
			page++;
			currentCount++;
			try
			{
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run()
					{
						tm = new QuizTimer(countDownLabel, updateInterval, controlBox, taskDuration, page);
					}
				});
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			System.out.println("===TIMER=== " + tm);
			tm.startTimer();
			timerState.setState(TimerState.RUNNING);
			return;
		}

		if (timerState.getState() == TimerState.RUNNING)
		{
			if (!tm.isStillRunning())
			{

				timerState.setState(TimerState.STOPPED);
				System.err.println("Timer not running, state now: " + timerState.getStateString());
			} else
			{
				System.err.println("Timer still running: " + timerState.getStateString());
			}
			return;
		}

		if (timerState.getState() == TimerState.STOPPED)
		{

			if (currentCount >= questionCount)
			{
				timerState.setState(TimerState.DONE);
				cancelTimer();
				System.err.println("Timer now CANCELLED with state now: " + timerState.getStateString());
				return;

			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					countDownLabel.setForeground(new Color(127, 179, 102));
					countDownLabel.setText("WAITING...");
				}
			});

			wt = new WaitTimer(switchDelay, controlBox.getCarousel());
			wt.startTimer();
			timerState.setState(TimerState.WAITING);
			System.err.println("Timer now waiting with state now: " + timerState.getStateString());
			return;
		}
		if (timerState.getState() == TimerState.WAITING)
		{

			if (!wt.isStillRunning())
			{
				timerState.setState(TimerState.STARTED);
				System.err.println("Timer wait done, state now: " + timerState.getStateString());

			} else
			{
				System.err.println("Timer still waiting: " + timerState.getStateString());
			}
			return;
		}

		if (timerState.getState() == TimerState.DONE)
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					countDownLabel.setText("COMPLETED");
				}
			});
		}
	}

	private String formatDuration(long duration)
	{
		return String.format("%02d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}

	public void startTimer()
	{
		System.out.println("Main Timer started.");
		if (timerState.getState() == TimerState.INITIAL)
		{
			System.out.println("Main Timer state: " + timerState.getStateString());
			SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
					if (welcome)
					{
						String text = controlBox.getCarousel().getPageField().getText();
						if ("1".equals(text))
						{
							System.out.println("Autoscroll done");
							controlBox.getCarousel().getNextButton().execute();
							controlBox.getCarousel().getMediator().disableAll();

						} else
						{
							System.out.println("No autoscroll");
						}

					}
				}
			});
		}

		timerState.setState(TimerState.STARTED);
		System.out.println("Main Timer state now: " + timerState.getStateString());
		timer.schedule(this, new Date(), 100);

	}

	public void cancelTimer()
	{
		timer.cancel();
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				// countDownLabel.setText("FINISHED");
				countDownLabel.setForeground(Color.LIGHT_GRAY);
				controlBox.getCarousel().moveFirst();
				quizActivity.getQuizState().handle(quizActivity);
			}
		});
		System.out.println("Setting state to finished");

	}

}

class TimerState
{
	public static final int INITIAL = 0;
	public static final int WAITING = 1;
	public static final int STOPPED = 2;
	public static final int STARTED = 3;
	public static final int RUNNING = 4;
	public static final int DONE = 5;
	private int currentState = 1;

	public TimerState()
	{
		currentState = TimerState.INITIAL;
	}

	public int getState()
	{
		return currentState;
	}

	public void setState(int state)
	{
		this.currentState = state;
	}

	public String getStateString()
	{
		String stateString = "";

		switch (currentState)
		{
		case 0:
			stateString = "INITIAL";
			break;
		case 1:
			stateString = "WAITING";
			break;
		case 2:
			stateString = "STOPPED";
			break;
		case 3:
			stateString = "STARTED";
			break;
		case 4:
			stateString = "RUNNING";
			break;
		case 5:
			stateString = "DONE";
			break;
		default:
			stateString = "UNKNOWN";
			break;
		}

		return stateString;
	}

}
