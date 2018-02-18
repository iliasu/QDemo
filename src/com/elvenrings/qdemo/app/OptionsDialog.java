package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class OptionsDialog extends JDialog
{

	private static final long serialVersionUID = 1L;
	private JRadioButton quizMode;
	private JRadioButton examMode;
	private JRadioButton examTimeAttackOn;
	private JRadioButton examTimeAttackOff;
	
	private JCheckBox welcomeCheck;
	private JCheckBox endCheck;
	
	//private JSpinner quizUpdateInterval;
	private JSpinner examUpdateInterval;
	//private JSpinner quizQuestionTime;
	private JSpinner examTotalTime;
	
	public OptionsDialog(JFrame parent)
	{
		super(parent,true);
		
		quizMode = new JRadioButton("Quiz");
		examMode = new JRadioButton("Exam");
		ButtonGroup modeGroup = new ButtonGroup();
		examMode.setSelected(true);
	
		welcomeCheck = new JCheckBox("Welcome Screen");
		welcomeCheck.setSelected(true);
		endCheck = new JCheckBox("End Screen");
		endCheck.setSelected(true);
		
		examTimeAttackOn = new JRadioButton("On");
		examTimeAttackOff = new JRadioButton("Off");
		
		
		ButtonGroup examTimeAttackGroup = new ButtonGroup();
		
		modeGroup.add(quizMode);
		modeGroup.add(examMode);
		examTimeAttackGroup.add(examTimeAttackOn);
		examTimeAttackGroup.add(examTimeAttackOff);
		
		
		examTimeAttackOn.setSelected(true);
		
		examUpdateInterval = new JSpinner(new SpinnerNumberModel(1,1,60,1));
		examTotalTime = new JSpinner(new SpinnerNumberModel(30,1,3600,1));
		
		Box vBox = Box.createVerticalBox();
		
		JPanel modePanel = new JPanel();
		modePanel.setLayout(new GridLayout(1,2));
		modePanel.add(examMode);
		modePanel.add(quizMode);
		modePanel.setBorder(getBorder("Load Mode"));
		
		
		JPanel screenPanel = new JPanel();
		screenPanel.setLayout(new GridLayout(1,2));
		screenPanel.add(welcomeCheck);
		screenPanel.add(endCheck);
		screenPanel.setBorder(getBorder("Screens"));
		
		
		JPanel examTimeAttackPanel = new JPanel();
		examTimeAttackPanel.setLayout(new GridLayout(1,2));
		examTimeAttackPanel.add(examTimeAttackOn);
		examTimeAttackPanel.add(examTimeAttackOff);
		examTimeAttackPanel.setBorder(getBorder("Timing Mode"));
		
		
		JPanel quizPanel = new JPanel();
		JPanel examPanel = new JPanel();
		examPanel.setLayout(new BorderLayout());
		JPanel examTimePanel = new JPanel();
		
		examTimePanel.setLayout(new GridLayout(2,4));
		examTimePanel.add(new JLabel("Update Interval (seconds)"));
		examTimePanel.add(examUpdateInterval);
		examTimePanel.add(new JLabel("Exam Time (minutes)"));
		examTimePanel.add(examTotalTime);
		examPanel.add(examTimeAttackPanel, BorderLayout.NORTH);
		examPanel.add(examTimePanel, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Exam", examPanel);
		tabbedPane.addTab("Quiz", quizPanel);
		
		tabbedPane.setBorder(getBorder("Mode Options"));
		vBox.add(modePanel);
		vBox.add(screenPanel);
		add(vBox, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
		
		
		//setSize(400,247);
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	public int getExamDisplayInterval()
	{
		return (Integer) examUpdateInterval.getValue();
	}
	
	public int getExamTime()
	{
		return (Integer) examTotalTime.getValue();
	}
	
	public boolean isExamTimeAttackSet()
	{
		return examTimeAttackOn.isSelected();
	}
	
	public boolean isWelcomeScreenSet()
	{
		return welcomeCheck.isSelected();
	}
	
	public boolean isEndScreenSet()
	{
		return endCheck.isSelected();
	}
	
	public boolean isExamMode()
	{
		return examMode.isSelected();
	}
	
	public boolean isQuizMode()
	{
		return quizMode.isSelected();
	}
	private Border getBorder(String title)
	{
		Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border border = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
		return border;
	}
}

