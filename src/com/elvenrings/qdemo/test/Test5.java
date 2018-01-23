package com.elvenrings.qdemo.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.QPanel;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;

public class Test5
{

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				new MainFrame();
			}
		});
	}

}

class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel = new JPanel();
	private JPanel controlPanel = new JPanel();
	private JPanel startPanel = new JPanel();
	private JButton nextButton = null;
	private JButton prevButton = null;
	private List<JPanel> qPanels = new LinkedList<JPanel>();
	ListIterator<JPanel> listIterator = null;
	private QCarousel<SwingRenderer> carousel;

	public MainFrame()
	{
		setSize(600, 400);
		setLayout(new BorderLayout());
		SingleChoiceQuestion sc1 = (SingleChoiceQuestion) QuestionFactory.GetQuestion(QuestionType.SINGLECHOICE, true);
		MultipleChoiceQuestion mc1 = (MultipleChoiceQuestion) QuestionFactory.GetQuestion(QuestionType.MULTICHOICE,
				true);
		FillBlankQuestion fb1 = (FillBlankQuestion) QuestionFactory.GetQuestion(QuestionType.FILLBLANK, true);

		SingleChoiceSwingRenderer scsr = new SingleChoiceSwingRenderer(sc1);
		MultipleChoiceSwingRenderer mcsr = new MultipleChoiceSwingRenderer(mc1);
		FillBlankSwingRenderer fbsr = new FillBlankSwingRenderer(fb1);
		
		QList<SwingRenderer> qlist = new QList<>();
		qlist.addRenderer(scsr);
		qlist.addRenderer(mcsr);
		qlist.addRenderer(fbsr);
		LogToConsole lc = new LogToConsole();
		qlist.addListener(lc);
		carousel = new QCarousel<>(qlist);

		mainPanel.setLayout(new GridBagLayout());
		final GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		qPanels.add(startPanel);
		JLabel label = new JLabel("START PANEL");
		prevButton = new JButton("PREV");
		nextButton = new JButton("NEXT");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				JPanel panel = null;
				try
				{
					panel = carousel.next();
				} catch (NoSuchElementException e)
				{
					return;
				}
				mainPanel.removeAll();
				mainPanel.repaint();
				mainPanel.revalidate();

				mainPanel.add(panel,gc);
				mainPanel.repaint();
				mainPanel.revalidate();
			}
		});

		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				JPanel panel = null;
				try
				{
					panel = carousel.previous();
				} catch (NoSuchElementException e)
				{
					return;
				}
				
				mainPanel.removeAll();
				mainPanel.repaint();
				mainPanel.revalidate();

				mainPanel.add(panel);
				mainPanel.repaint();
				mainPanel.revalidate();
			}
		});

		startPanel.add(label);
		startPanel.setBackground(Color.white);
		
		
		
		Dimension dimension = new Dimension(400,300);
		for(QPanel p : carousel)
		{
			p.setMinimumSize(dimension);
			p.setPreferredSize(dimension);
		}
		mainPanel.add(carousel.next(), gc);
		mainPanel.setBackground(new Color(23, 63, 114));
		controlPanel.add(prevButton);
		controlPanel.add(nextButton);
		controlPanel.setBackground(Color.black);
		add(mainPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
