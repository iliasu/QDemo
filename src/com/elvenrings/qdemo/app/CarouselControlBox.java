package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.elvenrings.qdemo.interfaces.Choice;
import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.utils.Tally;
import com.elvenrings.qdemo.view.events.Event;
import com.elvenrings.qdemo.view.events.GroupGradeEvent;
import com.elvenrings.qdemo.view.events.GroupGradeResultEvent;
import com.elvenrings.qdemo.view.events.SingleGradeResultEvent;
import com.elvenrings.qdemo.view.events.swing.FillBlankSwingEvent;
import com.elvenrings.qdemo.view.events.swing.MultipleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.events.swing.SingleChoiceSwingSelectionEvent;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.listeners.GroupGraderListener;
import com.elvenrings.qdemo.view.swing.listeners.GroupGraderResultListener;
import com.elvenrings.qdemo.view.swing.listeners.SingleGraderResultListener;
import com.elvenrings.qdemo.view.swing.listeners.SubmitSwingListener;

public class CarouselControlBox extends JPanel implements GroupGraderResultListener, SingleGraderResultListener
{
	private static final long serialVersionUID = 1L;

	private DefaultCarousel carousel;
	private JButton groupSubmitButton;
	private JLabel resultLabel;
	private JLabel attemptLabel;
	private GroupGraderListener groupListener;
	private SubmitSwingListener singleListener;
	private boolean groupSubmit = true;
	private Tally tally = new Tally();
	private static Icon correctIcon = new ImageIcon("images/icon_correct.png");
	private static Icon wrongIcon = new ImageIcon("images/icon_wrong.png");

	public CarouselControlBox(DefaultCarousel carousel, boolean groupSubmit)
	{
		this.carousel = carousel;
		this.groupSubmit = groupSubmit;

		setupResultLabel();
		setupAttemptLabel();
		setupGroupSubmitButton(groupSubmit);

		this.setLayout(new BorderLayout());
		this.add(resultLabel, BorderLayout.NORTH);
		this.add(attemptLabel, BorderLayout.CENTER);
		this.add(groupSubmitButton, BorderLayout.SOUTH);

	}

	private void setupGroupSubmitButton(boolean groupSubmit)
	{
		this.groupSubmitButton = new JButton("Submit all");
		this.groupSubmitButton.setEnabled(groupSubmit);
		if (groupSubmit)
		{
			this.groupSubmitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					int result = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to submit all questions at once?", "Confirm Submission",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION)
					{
						System.out.println("Sending grading event");
						// GroupGradeEvent event = new GroupGradeEvent();
						Map<Question, Event> eventMap = new HashMap<>();

						for (SwingRenderer renderer : carousel.rendererList)
						{
							if (renderer instanceof FillBlankSwingRenderer)
							{
								FillBlankSwingRenderer fbsr = (FillBlankSwingRenderer) renderer;
								List<String> input = fbsr.getInput();
								Choice choice = fbsr.getQuestion().getChoice();

								String[] input2 = input.toArray(new String[input.size()]);
								FillBlankSwingEvent fbe = new FillBlankSwingEvent(fbsr, input2, choice);
								eventMap.put(fbsr.getQuestion(), fbe);
								fbsr.getTextField().setEnabled(false);
							}

							if (renderer instanceof SingleChoiceSwingRenderer)
							{
								SingleChoiceSwingRenderer scsr = (SingleChoiceSwingRenderer) renderer;
								Integer input = scsr.getInput();
								Choice choice = scsr.getQuestion().getChoice();
								SingleChoiceSwingSelectionEvent sce = new SingleChoiceSwingSelectionEvent(scsr, input,
										choice);
								eventMap.put(scsr.getQuestion(), sce);
								JRadioButton[] radioButtons = scsr.getRadioButtons();
								for (JRadioButton button : radioButtons)
								{
									button.setEnabled(false);
								}
							}

							if (renderer instanceof MultipleChoiceSwingRenderer)
							{
								MultipleChoiceSwingRenderer mcsr = (MultipleChoiceSwingRenderer) renderer;
								Set<Integer> input = mcsr.getInput();
								Choice choice = mcsr.getQuestion().getChoice();

								MultipleChoiceSwingSelectionEvent mce = new MultipleChoiceSwingSelectionEvent(mcsr,
										input, choice);
								eventMap.put(mcsr.getQuestion(), mce);
								JCheckBox[] checkBoxes = mcsr.getCheckBoxes();
								for (JCheckBox checkBox : checkBoxes)
								{
									checkBox.setEnabled(false);
								}
							}

						}

						GroupGradeEvent groupGradeEvent = new GroupGradeEvent(eventMap);
						fireGroupGraderEvent(groupGradeEvent);

					}
				}
			});

		}

	}

	private void setupResultLabel()
	{
		this.resultLabel = new JLabel("");
		this.resultLabel.setPreferredSize(new Dimension(100, 70));
		this.resultLabel.setOpaque(true);
		this.resultLabel.setBackground(Color.white);
		this.resultLabel.setVerticalTextPosition(SwingConstants.CENTER);
		this.resultLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		this.resultLabel.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
	}

	private void setupAttemptLabel()
	{
		this.attemptLabel = new JLabel("");
		this.attemptLabel.setPreferredSize(new Dimension(100, 70));
		this.attemptLabel.setOpaque(true);
		this.attemptLabel.setBackground(Color.white);
		this.attemptLabel.setVerticalTextPosition(SwingConstants.CENTER);
		this.attemptLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		this.attemptLabel.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
	}

	public void fireGroupGraderEvent(GroupGradeEvent event)
	{
		if (groupListener != null)
		{
			groupListener.gradingActivitySubmitted(event);
			CarouselControlBox.this.groupSubmitButton.setEnabled(false);
		}
	}

	public void setSingleGraderListener(SubmitSwingListener listener)
	{
		this.singleListener = listener;

		if (!groupSubmit)
		{
			List<SwingRenderer> rendererList = carousel.rendererList;
			for (SwingRenderer renderer : rendererList)
			{

				renderer.addSubmitListener(singleListener);
				renderer.getMessageLabel().setText("Attempts Remaining: " + renderer.getQuestion().getAttempts());
			}
		}
	}

	public void setGraderListener(GroupGraderListener listener)
	{
		this.groupListener = listener;
	}

	@Override
	public void gradingActivityCompleted(GroupGradeResultEvent event)
	{
		int correct = event.getCorrect();
		int total = event.getTotal();
		Map<SwingRenderer, Boolean> rendererMap = event.getRendererMap();
		for (SwingRenderer renderer : rendererMap.keySet())
		{
			JLabel label = renderer.getColorStatusLabel();
			if (rendererMap.get(renderer))
			{
				label.setIcon(correctIcon);
			} else
			{
				label.setIcon(wrongIcon);
			}

		}
		tally.incrementCorrect(correct);
		tally.incrementWrong(total - correct);
		writeToLabel();

	}

	private void writeToLabel()
	{
		int correct = tally.getCorrect();
		int total = tally.getTotal();
		Double percentage = ((double) correct / total);
		NumberFormat percentInstance = NumberFormat.getPercentInstance();
		percentInstance.setMaximumFractionDigits(1);
		resultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		resultLabel.setText("<html><strong>Score:</strong> " + correct + "/" + total + " <strong>("
				+ percentInstance.format(percentage) + ")</strong></html>");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public void gradingActivityCompleted(SingleGradeResultEvent event)
	{
		Integer attempts = event.getAttempt();
		Question question = event.getQuestion();
		boolean correct = event.isCorrect();
		SwingRenderer renderer = event.getRenderer();
		Integer allowedAttempts = question.getAttempts();

		handle(attempts, correct, renderer, allowedAttempts);
	}

	private void handle(Integer attempts, boolean correct, SwingRenderer renderer, Integer allowedAttempts)
	{
		renderer.getMessageLabel().setText("Attempts Remaining: " + (allowedAttempts - attempts));
		JComponent[] inputComponents = renderer.getInputComponent();
		if (correct)
		{
			renderer.getSubmitButton().setEnabled(false);
			renderer.getColorStatusLabel().setIcon(correctIcon);
			for (JComponent component : inputComponents)
			{
				component.setEnabled(false);
			}
			tally.incrementCorrect();
			writeToLabel();
			System.out.println("Correct after " + attempts + " attempt(s).");
		} else
		{
			if (attempts >= allowedAttempts)
			{
				renderer.getSubmitButton().setEnabled(false);
				renderer.getColorStatusLabel().setIcon(wrongIcon);
				for (JComponent component : inputComponents)
				{
					component.setEnabled(false);

				}
				tally.incrementWrong();
				writeToLabel();
				System.out.println("Wrong after " + attempts + " attempt(s).");
			} else
			{
				System.out.println("Remaining attempts:" + (allowedAttempts - attempts));
			}
		}
	}

}
