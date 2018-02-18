package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import com.elvenrings.qdemo.activities.ExamActivity;
import com.elvenrings.qdemo.activities.QuizActivity;

public class Application extends JFrame
{

	private XMLFileLoader xmlFileLoader = new XMLFileLoader();
	private JTabbedPane tabbedPane;
	private JPanel mainPanel;
	private JPanel repoPanel;
	private JPanel mainWestPanel;
	private JPanel mainSouthPanel;
	private JPanel mainNorthPanel;
	private JPanel mainCentralPanel;
	JComboBox<CarouselContainer> carouselList;
	private JTextArea updateArea;
	private JCheckBoxMenuItem welcomeScreen;
	private JCheckBoxMenuItem endScreen;
	private JRadioButtonMenuItem singleSubmit;
	private JRadioButtonMenuItem groupSubmit;
	private AboutDialog aboutDialog;
	private OptionsDialog optionsDialog;
	
	private static final long serialVersionUID = 1L;
	private static ApplicationContext context = ApplicationContext.getInstance();

	public Application()
	{
		super("QDemo");
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		layoutMenus();
		layoutComponents();

		setSize(970, 600);
		setVisible(true);
	}

	private void layoutComponents()
	{
		optionsDialog = new OptionsDialog(this);
		tabbedPane = new JTabbedPane();
		mainPanel = new JPanel();
		repoPanel = new JPanel();

		mainPanel.setLayout(new BorderLayout());

		mainWestPanel = new JPanel();
		mainWestPanel.setLayout(new BorderLayout());
		mainWestPanel.setPreferredSize(new Dimension(200, 300));
		mainWestPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		mainWestPanel.setBackground(new Color(206, 217, 228));
		// mainWestPanel.setBackground(Color.RED);

		mainSouthPanel = new JPanel();
		mainSouthPanel.setLayout(new BorderLayout());

		mainNorthPanel = new JPanel();
		mainNorthPanel.setLayout(new BorderLayout());
		mainNorthPanel.setBorder(BorderFactory.createEtchedBorder());

		mainCentralPanel = new JPanel();
		mainCentralPanel.setLayout(new BorderLayout());

		carouselList = new JComboBox<>();
		carouselList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					CarouselContainer container = (CarouselContainer) e.getItem();
					mainCentralPanel.removeAll();
					mainCentralPanel.add(container.getCarousel());
					mainCentralPanel.repaint();
					mainCentralPanel.revalidate();

					mainWestPanel.removeAll();
					mainWestPanel.add(container.getCarouselControlBox());
					mainWestPanel.repaint();
					mainWestPanel.revalidate();
					carouselList.setSelectedItem(container);
				}
			}

		});

		carouselList.setPreferredSize(new Dimension(300, 30));

		Box northBox = Box.createHorizontalBox();

		northBox.add(new JLabel("Loaded Files: "));
		northBox.add(Box.createHorizontalGlue());
		northBox.add(carouselList);
		mainNorthPanel.add(northBox, BorderLayout.WEST);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.add(mainWestPanel, JSplitPane.LEFT);
		splitPane.add(mainCentralPanel, JSplitPane.RIGHT);
		mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);
		mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
		mainPanel.add(splitPane, BorderLayout.CENTER);

		updateArea = new JTextArea();
		updateArea.setMaximumSize(new Dimension(updateArea.getMaximumSize().width, 80));
		updateArea.setPreferredSize(new Dimension(updateArea.getMaximumSize().width, 80));
		updateArea.setEditable(false);

		tabbedPane.add("Home", mainPanel);
		tabbedPane.addTab("Repository", repoPanel);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		this.add(tabbedPane, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(updateArea);
		this.add(scrollPane, BorderLayout.SOUTH);

		populateContext();
	}

	private void populateContext()
	{
		ApplicationContext.put("mainpanel", mainPanel);
		ApplicationContext.put("repoPanel", repoPanel);
		ApplicationContext.put("mainwestpanel", mainWestPanel);
		ApplicationContext.put("mainsoutpanel", mainSouthPanel);
		ApplicationContext.put("mainnorthpanel", mainNorthPanel);
		ApplicationContext.put("carouselList", carouselList);
		ApplicationContext.put("tabbedPane", tabbedPane);
		ApplicationContext.put("updateArea", updateArea);
		ApplicationContext.put("welcomeScreen", welcomeScreen);
		ApplicationContext.put("endScreen", endScreen);
		ApplicationContext.put("singleSubmit", singleSubmit);
		ApplicationContext.put("groupSubmit", groupSubmit);
		ApplicationContext.put("optionsDialog", optionsDialog);
	}

	public void layoutMenus()
	{

		JMenuBar menuBar = new JMenuBar();
		/*--------------------------------*/
		/* Menus */
		/*--------------------------------*/
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenu settingsMenu = new JMenu("Settings");
		settingsMenu.setMnemonic(KeyEvent.VK_S);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		


		/*--------------------------------*/
		/* Menu Items */
		/*--------------------------------*/
		JMenuItem loadItem = new JMenuItem("Load Questions");
		loadItem.setMnemonic(KeyEvent.VK_L);
		loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		loadItem.addActionListener(xmlFileLoader);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});


		
		JMenuItem loadOptionsItem = new JMenuItem("Load Options");
		loadOptionsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (optionsDialog == null)
				{
					optionsDialog = new OptionsDialog(Application.this);
				}
				optionsDialog.setLocationRelativeTo(Application.this);
				optionsDialog.setVisible(true);
			}
			
		});

		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic(KeyEvent.VK_A);
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (aboutDialog == null)
				{
					aboutDialog = new AboutDialog();
				}
				aboutDialog.setLocationRelativeTo(Application.this);
				aboutDialog.setVisible(true);

			}
		});
		/*--------------------------------*/

		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);


		helpMenu.add(aboutItem);
		
		settingsMenu.add(loadOptionsItem);

		menuBar.add(fileMenu);
		menuBar.add(settingsMenu);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);
		
		ExamActivity examActivity = new ExamActivity(context);
		QuizActivity quizActivity = new QuizActivity(context);
		xmlFileLoader.addQuestionFileLoadListener(examActivity);
		xmlFileLoader.addQuestionFileLoadListener(quizActivity);

	}

	public void layoutToolbar()
	{
		JToolBar toolbar = new JToolBar();

		JButton loadBtn = new JButton("Load");
		toolbar.add(loadBtn);

		add(toolbar, BorderLayout.NORTH);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				Application application = new Application();
				application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Dimension size = application.getSize();
				int y = (screenSize.height - size.height) / 2;
				int x = (screenSize.width - size.width) / 2;
				application.setLocation(x, y);
			}
		});
	}

}
