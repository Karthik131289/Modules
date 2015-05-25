package net.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.layout.ToolbarLayout;

import net.Details.*;
import net.gui.authentication.Authentication;
import net.gui.authentication.Login;
import net.swingx.component.XFrame;
import net.swingx.component.XPanel;
import net.swingx.component.XProgressBar;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class window_MDI
{

	// Property Declarations
	
	
	
	// GUI Component Declarations
	public XFrame frmVenaqua;
	private JDesktopPane desktopPane;
	
	
	// Other class Object Declaration
	private window_Home home;
	private window_Alarms alarms;
	private dialog_AdminSettings        dialogAdmin;
	private dialog_FactorySettings 		dialogFactory;
	private dialog_PasswordChange 		dialogPWDChange;
	private dialog_GenerateBill   		dialogWaterBill;
	
	private static JLabel status_info;
	private static XProgressBar progressBar;
	private static float progressVal = 0.0f;


	Vector<String> houseList = new Vector<String>();
	Vector<String> inletList = new Vector<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					window_MDI window = new window_MDI();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create a application.
	 */
	public window_MDI()
	{
		for( int i=0; i<DataObject.house.length ; i++ )
			houseList.add( DataObject.house[i].getHouseName() );
		
		for( int i=0; i<DataObject.house[0].getInletCount() ; i++ )
			inletList.add( DataObject.house[0].Inlets[i].getInletName() );

		
		initGUI();				
		this.openAllScreens();
		
		Authentication authWindow = new Authentication( this.frmVenaqua );
		authWindow.showDialog( this.desktopPane , this.desktopPane.getLocation() );
		this.frmVenaqua.setEnabled(false);
	}

	/**
	 * Initialize contents of the frame.
	 */
	private void initGUI() 
	{
		try
		{
			frmVenaqua = new XFrame();
			{ 	
				//javax.swing.UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			  	//javax.swing.UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
			  	//javax.swing.UIManager.setLookAndFeel( com.jtattoo.plaf.acryl.AcrylLookAndFeel.class.getCanonicalName() );
			  	//javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
			  	//WebLookAndFeel.initializeManagers();
			  	
				frmVenaqua.setUndecorated(false);
				SwingUtilities.updateComponentTreeUI(frmVenaqua);
			}
			
			frmVenaqua.setTitle("VenAqua");
			frmVenaqua.setIconImages( IconCollections.getAppLogo() );
			frmVenaqua.setPreferredSize( new Dimension(1100, 700));
			frmVenaqua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmVenaqua.getContentPane().setLayout(new BorderLayout(0, 0));
			
				JMenuBar menuBar = new JMenuBar();
				menuBar.setSize( frmVenaqua.getWidth() , 50 );
				frmVenaqua.setJMenuBar(menuBar);
				{
					JMenu mnHome = new JMenu("Home");
					mnHome.addMenuListener(new MenuListener() {
						public void menuCanceled(MenuEvent arg0) {
						}
						public void menuDeselected(MenuEvent arg0) {
						}
						public void menuSelected(MenuEvent arg0) {
							try
							{													
								desktopPane.getDesktopManager().activateFrame(home.frameHome);
							}
							catch( Exception e )
							{
								e.printStackTrace();
							}
						}
					});
					mnHome.setMnemonic('H');
					mnHome.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						}
					});
					mnHome.setFont( FontCollections.fontMenu );
					menuBar.add(mnHome);
					
					JMenu mnuAlarms = new JMenu("Alarms");
					mnuAlarms.addMenuListener(new MenuListener() {
						public void menuCanceled(MenuEvent arg0) {
						}
						public void menuDeselected(MenuEvent arg0) {
						}
						public void menuSelected(MenuEvent arg0) {
							try
							{
								desktopPane.getDesktopManager().activateFrame( alarms.frameAlarm );
							}
							catch( Exception e )
							{
								e.printStackTrace();
							}
						}
					});
					mnuAlarms.setMnemonic('A');
					mnuAlarms.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						}
					});
					mnuAlarms.setFont( FontCollections.fontMenu );
					menuBar.add(mnuAlarms);
					
					JMenu mnuSettings = new JMenu("Options");
					mnuSettings.setMnemonic('O');
					mnuSettings.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent arg0) 
						{
							
						}
					});
					mnuSettings.setFont( FontCollections.fontMenu );
					menuBar.add(mnuSettings);
					{
							JMenuItem mnuMapInfo = new JMenuItem("Mapping Information");
							mnuMapInfo.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent arg0)
								{
									JOptionPane.showMessageDialog( null , "Map Info....","Information",JOptionPane.INFORMATION_MESSAGE);
								}
							});
							//mnuMapInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
							mnuMapInfo.setFont(FontCollections.fontMenu);
							//mnuSettings.add(mnuMapInfo);
							
							JSeparator separator = new JSeparator();
							//mnuSettings.add(separator);
							
							
							JMenuItem mnuPassword = new JMenuItem("Change Password" , IconCollections.mnuPassword );
							mnuPassword.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0)
								{
									if( Login._USERNAME.equalsIgnoreCase("ADMIN") || Login._USERNAME.equalsIgnoreCase("FACTORY") )
									{
										if( dialogPWDChange == null )
											dialogPWDChange = new dialog_PasswordChange( frmVenaqua );
										
										dialogPWDChange.showDialog();
										frmVenaqua.setEnabled(false);
	
										//JOptionPane.showMessageDialog( null , "Change PWD....","Information",JOptionPane.INFORMATION_MESSAGE);
									}
									else
										JOptionPane.showMessageDialog( null , "Insufficient user login..." ,"Access Denied...!",JOptionPane.WARNING_MESSAGE);
								}
							});
							//mnuPassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
							mnuPassword.setFont(FontCollections.fontMenu);
							mnuSettings.add(mnuPassword);
							
							JSeparator separator_2 = new JSeparator();
							mnuSettings.add(separator_2);
							
							JMenuItem mnuBill = new JMenuItem("Generate Bill", IconCollections.mnuBill );
							mnuBill.addActionListener(new ActionListener() 
							{
								public void actionPerformed(ActionEvent arg0) 
								{
									if( Login._USERNAME.equalsIgnoreCase("ADMIN") || Login._USERNAME.equalsIgnoreCase("FACTORY") )
									{
										if( dialogWaterBill == null )
											dialogWaterBill = new dialog_GenerateBill( frmVenaqua , houseList );
										
										dialogWaterBill.showDialog();
										frmVenaqua.setEnabled(false);
										//JOptionPane.showMessageDialog( null , " Test window : generate bill....","Information",JOptionPane.INFORMATION_MESSAGE);
									}
									else
										JOptionPane.showMessageDialog( null , "Insufficient user login..." ,"Access Denied...!",JOptionPane.WARNING_MESSAGE);
								}
							});
							//mnuBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
							mnuBill.setFont(FontCollections.fontMenu);
							mnuSettings.add(mnuBill);
							
							JSeparator separator_1 = new JSeparator();
							mnuSettings.add(separator_1);
							
							JMenuItem mnuFactorySettings = new JMenuItem("Factory Settings" , IconCollections.mnuRefreshInterval);
							mnuFactorySettings.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent arg0) 
								{
									if( Login._USERNAME.equalsIgnoreCase("FACTORY") )
									{
										if( dialogFactory == null )
											dialogFactory = new dialog_FactorySettings( frmVenaqua ); 
										
										dialogFactory.showDialog();
										frmVenaqua.setEnabled(false);
									}
									else
										JOptionPane.showMessageDialog( null , "Insufficient user login..." ,"Access Denied...!",JOptionPane.WARNING_MESSAGE);
								}
							});
							//mnuRefreshInterval.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
							mnuFactorySettings.setFont(FontCollections.fontMenu);
							mnuSettings.add(mnuFactorySettings);
							
							JSeparator separator_3 = new JSeparator();
							mnuSettings.add(separator_3);
							
							JMenuItem mnuAdminSettings = new JMenuItem("Admin Settings" , IconCollections.mnuRefreshInterval);
							mnuAdminSettings.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent arg0) 
								{
									if( Login._USERNAME.equalsIgnoreCase("ADMIN") || Login._USERNAME.equalsIgnoreCase("FACTORY") )
									{
										if( dialogAdmin == null )
											dialogAdmin = new dialog_AdminSettings( frmVenaqua ); 
										
										dialogAdmin.showDialog();
										frmVenaqua.setEnabled(false);
									}
									else
										JOptionPane.showMessageDialog( null , "Insufficient user login..." ,"Access Denied...!",JOptionPane.WARNING_MESSAGE);
								}
							});
							//mnuAdminSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
							mnuAdminSettings.setFont(FontCollections.fontMenu);
							mnuSettings.add(mnuAdminSettings);
					}
					
					JMenu mnuHelp = new JMenu("Help");
					mnuHelp.addMenuListener(new MenuListener() {
						public void menuCanceled(MenuEvent arg0) {}
						public void menuDeselected(MenuEvent arg0) {}
						public void menuSelected(MenuEvent arg0) {}
					});
					mnuHelp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) { }
					});
					mnuHelp.setFont( FontCollections.fontMenu );
					menuBar.add(mnuHelp);
					{
						JMenuItem mnuAbout = new JMenuItem("About Us" , IconCollections.mnuAbout);
						mnuAbout.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent arg0)
							{
								try
								{	
									SwingUtilities.invokeLater( new Runnable() {
										public void run()
										{
											@SuppressWarnings("unused")
											window_AboutUs abt = new window_AboutUs( frmVenaqua );
										}
									});
									
								}
								catch( Exception e )
								{
									e.printStackTrace();
									JOptionPane.showMessageDialog( null , "Could not show About window...", StringCollection.Error, JOptionPane.ERROR_MESSAGE);
								}
							}
						});
						//mnuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
						mnuAbout.setFont(FontCollections.fontMenu);
						mnuHelp.add(mnuAbout);
					}
					
					XPanel linkPanel = new XPanel();
					linkPanel.setLayout( new BorderLayout() );
					{
						WebLinkLabel lblLink = new WebLinkLabel();
						lblLink.setLink("www.denvik.in");
						lblLink.setIcon( IconCollections.DenvikLogo );
						lblLink.setText("");
						lblLink.setHorizontalAlignment( SwingConstants.RIGHT );
						lblLink.setToolTipText("Click here to goto our website... ");
						linkPanel.add( lblLink , BorderLayout.EAST );
					}
					menuBar.add( Box.createHorizontalGlue() );
					menuBar.add( linkPanel , ToolbarLayout.END );
					
				}
			
				desktopPane = new JDesktopPane();
				desktopPane.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
				frmVenaqua.getContentPane().add(desktopPane, BorderLayout.CENTER);
				
				JPanel statuspanel = new JPanel();
				statuspanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(185, 209, 234), null, null, new Color(185, 209, 234)));
				statuspanel.setLayout(new BorderLayout(0, 0));
				frmVenaqua.getContentPane().add(statuspanel, BorderLayout.SOUTH);
				{			
					JLabel status = new JLabel(" Status : ");
					status.setFont( FontCollections.fontStatusBar );
					statuspanel.add(status,BorderLayout.LINE_START);
					
					status_info = new JLabel(" ");
					status_info.setFont( FontCollections.fontStatusBar );
					statuspanel.add(status_info,BorderLayout.CENTER);
				
					progressBar = new XProgressBar();
					progressBar.setProgressTopColor(  new Color( 60, 179, 113, 255) );
					progressBar.setStringPainted(true);
					progressBar.setFont(FontCollections.fontMenu);
					progressBar.setBorderPainted(false);
					progressBar.setIndeterminate(false);
					progressBar.setPreferredSize( new Dimension( 200, 20));
					statuspanel.add(progressBar,BorderLayout.LINE_END);
					progressBar.setValue(0);
				}	
				
			frmVenaqua.setVisible(true);
			frmVenaqua.pack();
		}
		catch( Exception e )
		{
			JOptionPane.showInternalMessageDialog( null , "Error occured in GUI Initialization...!", StringCollection.Error, JOptionPane.ERROR_MESSAGE );
		}
	}
	
	private void openAllScreens()
	{
		try
		{
			SwingUtilities.invokeLater
			(
				new Runnable()
				{
					public void run() 
					{
						try
						{
							alarms = new window_Alarms(houseList);
							desktopPane.add(alarms.frameAlarm , JDesktopPane.DEFAULT_LAYER.intValue()-1);
							alarms.frameAlarm.setMaximum(true);
							
							home  = new window_Home(houseList,inletList);
							desktopPane.add(home.frameHome , JDesktopPane.DEFAULT_LAYER.intValue()+1 );
							home.frameHome.setMaximum(true);
							home.frameHome.setSelected(true);
							
							desktopPane.getDesktopManager().activateFrame( home.frameHome );
						}
						catch( Exception e )
						{
							e.printStackTrace();			
							JOptionPane.showInternalMessageDialog( null , "Error occured while initializing sub windows...!", StringCollection.Error, JOptionPane.ERROR_MESSAGE );
						}
					}
				}
			);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static String getStatusInfo()
	{
		return status_info.getText();
	}
	public static void setStatusInfo( String status )
	{
		status_info.setText( status );
	}
	public static float getProgress()
	{
		return progressVal;  
	}
	public static void setProgress( float val )
	{
		progressBar.setValue((int)val);
		progressVal = val;
		if( val>0.0f )
			progressBar.setRequestFocusEnabled(true);
	}
}
