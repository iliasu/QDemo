package com.elvenrings.qdemo.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.elvenrings.qdemo.interfaces.SwingRenderer;
import com.elvenrings.qdemo.model.FillBlankQuestion;
import com.elvenrings.qdemo.model.MultipleChoiceQuestion;
import com.elvenrings.qdemo.model.Question;
import com.elvenrings.qdemo.model.SingleChoiceQuestion;
import com.elvenrings.qdemo.view.swing.FillBlankSwingRenderer;
import com.elvenrings.qdemo.view.swing.MultipleChoiceSwingRenderer;
import com.elvenrings.qdemo.view.swing.QPanel;
import com.elvenrings.qdemo.view.swing.SingleChoiceSwingRenderer;
import com.elvenrings.qdemo.xml.ReadQuestions;

public class Test5
{

	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			System.err.println("Usage: " + "java Test5" + " <XMLFile>");
			System.exit(0);
		}
		String filename = args[0];
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					new MainFrame(filename);
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	//private List<JPanel> qPanels = new LinkedList<JPanel>();
	ListIterator<JPanel> listIterator = null;
	private QCarousel<SwingRenderer> carousel;
	private String traversalDirection = "right";

	public MainFrame(String file) throws Exception
	{
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		//UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		setSize(550, 450);
		setLayout(new BorderLayout());
		/*
		 * SingleChoiceQuestion sc1 = (SingleChoiceQuestion)
		 * QuestionFactory.GetQuestion(QuestionType.SINGLECHOICE, true);
		 * MultipleChoiceQuestion mc1 = (MultipleChoiceQuestion)
		 * QuestionFactory.GetQuestion(QuestionType.MULTICHOICE, true);
		 * FillBlankQuestion fb1 = (FillBlankQuestion)
		 * QuestionFactory.GetQuestion(QuestionType.FILLBLANK, true);
		 */

		ReadQuestions rq = new ReadQuestions(file);

		// fb1.setAttempts(3);
		// sc1.setAttempts(3);
		// mc1.setAttempts(3);

		SingleChoiceSwingRenderer scsr = null;
		MultipleChoiceSwingRenderer mcsr = null;
		FillBlankSwingRenderer fbsr = null;

		if (rq.questions == null)
		{
			System.err.println("rq is null, exiting...");
			System.exit(1);
		}
		QList<SwingRenderer> qlist = new QList<>();
		// qlist.addRenderer(scsr);
		// qlist.addRenderer(mcsr);
		// qlist.addRenderer(fbsr);
		for (Question q : rq.questions)
		{
			// SingleChoiceQuestion m = (SingleChoiceQuestion) q;
			if (q instanceof SingleChoiceQuestion)
			{
				SingleChoiceQuestion q2 = (SingleChoiceQuestion) q;
				scsr = new SingleChoiceSwingRenderer(q2);
				qlist.addRenderer(scsr);
			}
			if (q instanceof MultipleChoiceQuestion)
			{
				MultipleChoiceQuestion q2 = (MultipleChoiceQuestion) q;
				mcsr = new MultipleChoiceSwingRenderer(q2);
				qlist.addRenderer(mcsr);
			}
			if (q instanceof FillBlankQuestion)
			{
				FillBlankQuestion q2 = (FillBlankQuestion) q;
				fbsr = new FillBlankSwingRenderer(q2);
				qlist.addRenderer(fbsr);
			}
		}
		//System.out.println(scsr);
		//System.out.println(mcsr);
		//System.out.println(fbsr);
		// System.exit(0);
		// SingleChoiceSwingRenderer scsr = new SingleChoiceSwingRenderer(sc1);
		// MultipleChoiceSwingRenderer mcsr = new MultipleChoiceSwingRenderer(mc1);
		// FillBlankSwingRenderer fbsr = new FillBlankSwingRenderer(fb1);

		LogToConsole lc = new LogToConsole();
		qlist.addListener(lc);
		carousel = new QCarousel<>(qlist);

		mainPanel.setLayout(new GridBagLayout());
		final GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		// qPanels.add(startPanel);
		JLabel label = new JLabel("START PANEL");
		prevButton = new JButton("PREV");
		nextButton = new JButton("NEXT");
		prevButton.setOpaque(true);
		prevButton.setBackground(Color.BLACK);
		prevButton.setForeground(Color.WHITE);
		
		prevButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e)
			{
				prevButton.requestFocusInWindow();
				prevButton.setForeground(new Color(253, 189, 65));
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				prevButton.setForeground(Color.WHITE);
			}
		});
		nextButton.setOpaque(true);
		nextButton.setBackground(Color.BLACK);
		nextButton.setForeground(Color.WHITE);
		nextButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e)
			{
				nextButton.requestFocusInWindow();
				nextButton.setForeground(new Color(253, 189, 65));
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				nextButton.setForeground(Color.WHITE);
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				JPanel panel = null;
				try
				{
					if (traversalDirection.equals("right"))
					{
						panel = carousel.next();
					} else
					{
						carousel.next();
						panel = carousel.next();
						traversalDirection = "right";
					}
				} catch (NoSuchElementException e)
				{
					return;
				}
				mainPanel.removeAll();
				mainPanel.repaint();
				mainPanel.revalidate();

				mainPanel.add(panel, gc);
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
					if (traversalDirection.equals("left"))
					{
						panel = carousel.previous();
					} else
					{
						carousel.previous();
						panel = carousel.previous();
						traversalDirection = "left";
					}
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

		Dimension dimension = new Dimension(500, 300);
		for (QPanel p : carousel)
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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = this.getSize();
		int y = (screenSize.height  - size.height) / 2;
		int x = (screenSize.width - size.width) / 2;
		setLocation(x, y);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
