package com.elvenrings.qdemo.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import com.elvenrings.qdemo.utils.Mediator;

public class Application extends JFrame
{
	
	private Mediator mediator = new Mediator();
	//private QuestionMap question = new QuestionMap();
	private XMLFileLoader xmlFileLoader = new XMLFileLoader();
	private JTabbedPane tabbedPane;
	JPanel mainPanel;
	private JPanel testPanel;
	private JPanel mainWestPanel;
	private JPanel mainSouthPanel;
	private JPanel mainNorthPanel;
	private JPanel mainCentralPanel;
	JComboBox<DefaultCarousel> cmbLoadedFiles;
	private JTextArea updateArea;
	
	private static final long serialVersionUID = 1L;
	private ApplicationContext context = ApplicationContext.getInstance();
	public Application()
	{
		super("QDemo");
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		layoutMenus();
		//layoutToolbar();
		layoutComponents();
		
		setSize(970,600);
		setVisible(true);
	}
	
	
	private void layoutComponents()
	{
		tabbedPane = new JTabbedPane();
		mainPanel = new JPanel();
		testPanel = new JPanel();
		
		
		
		mainPanel.setLayout(new BorderLayout());
		
		mainWestPanel = new JPanel();
		mainWestPanel.setPreferredSize(new Dimension(200,300));
		mainWestPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		mainWestPanel.setBackground(new Color(206, 217, 228));
		
		mainSouthPanel = new JPanel();
		mainSouthPanel.setLayout(new BorderLayout());
		
		mainNorthPanel = new JPanel();
		mainNorthPanel.setLayout(new BorderLayout());
		mainNorthPanel.setBorder(BorderFactory.createEtchedBorder());
		
		mainCentralPanel = new JPanel();
		mainCentralPanel.setLayout(new BorderLayout());
		
		
		cmbLoadedFiles = new JComboBox<>();
		cmbLoadedFiles.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					DefaultCarousel carousel = (DefaultCarousel) e.getItem();
					mainCentralPanel.removeAll();
					mainCentralPanel.add(carousel);
					mainCentralPanel.repaint();
					mainCentralPanel.revalidate();
				}
			}
			
		});
		//cmbLoadedFiles.setEnabled(false);
		//cmbLoadedFiles.addItem("-------------------------------------");
		cmbLoadedFiles.setPreferredSize(new Dimension(300,30));
	
		Box northBox = Box.createHorizontalBox();
		
		northBox.add(new JLabel("Question Files: "));
		northBox.add(Box.createHorizontalGlue());
		northBox.add(cmbLoadedFiles);
		mainNorthPanel.add(northBox, BorderLayout.WEST);
		
		
		mainPanel.add(mainWestPanel, BorderLayout.WEST);
		mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);
		mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
		mainPanel.add(mainCentralPanel, BorderLayout.CENTER);
		
		updateArea = new JTextArea();
		updateArea.setEditable(false);
		updateArea.append("Lock and loaded...:)\n");
		
		
		tabbedPane.add("Main", mainPanel);
		tabbedPane.addTab("Test", testPanel);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		this.add(tabbedPane, BorderLayout.CENTER);
		this.add(new JScrollPane(updateArea), BorderLayout.SOUTH);
		
		ApplicationContext.put("mainpanel", mainPanel);
		ApplicationContext.put("testPanel", testPanel);
		ApplicationContext.put("mainwestpanel", mainWestPanel);
		ApplicationContext.put("mainsoutpanel", mainSouthPanel);
		ApplicationContext.put("mainnorthpanel", mainNorthPanel);
		ApplicationContext.put("cmbLoadedFiles", cmbLoadedFiles);
		ApplicationContext.put("tabbedPane", tabbedPane);
		ApplicationContext.put("updateArea", updateArea);
	}


	public void layoutMenus()
	{
		ComboPopulator populator = new ComboPopulator();
		xmlFileLoader.addQuestionFileLoadListener(populator);
		
		JMenuBar menuBar = new JMenuBar();
		/*--------------------------------*/
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		/*--------------------------------*/
		JMenuItem loadItem = new JMenuItem("Load Questions");
		loadItem.setMnemonic(KeyEvent.VK_L);
		loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK ));
		loadItem.addActionListener(xmlFileLoader);
		
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic(KeyEvent.VK_A);
		/*--------------------------------*/
		
		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
				
		helpMenu.add(aboutItem);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
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
			}
		});
	}

}
