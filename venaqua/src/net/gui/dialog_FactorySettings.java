package net.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

import net.Details.updateSettings;
import net.SerialPortComm.SerialPortReader;
import net.swingx.component.XButton;
import net.swingx.component.XFrame;
import net.swingx.component.XLabel;
import net.swingx.component.XSpinner;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class dialog_FactorySettings extends JDialog 
{

	private static final long serialVersionUID = 67897899182088506L;
	
	public static Integer _REFRESH_INTERVAL = 30000;
	public static Integer _POLL_DELAY = 1000;
	private static Integer _REFRESH_INTERVAL_STEPUP = 250;
	private static Integer _POLL_DELAY_STEPUP = 250;
	private static Integer _POLL_DELAY_MIN = 1000;
	private static Integer _POLL_DELAY_MAX = 60000;
		
	private XFrame parentFrame;
	private Container contentPane;
	private XSpinner refreshInterval;
	private XSpinner pollDelay;

	public dialog_FactorySettings( XFrame parent ) 
	{
		super( parent );
		this.parentFrame = parent;
		initUI();
	}
	
	private void initUI()
	{
		try
		{
			this.addWindowListener( new WindowAdapter( ) 
			{
				public void windowClosed(WindowEvent we )
				{
					/** To view a updated value when reopening a window **/
					refreshInterval.setValue( _REFRESH_INTERVAL );
					pollDelay.setValue( _POLL_DELAY );
					
					parentFrame.setEnabled(true);
					parentFrame.setFocusableWindowState(true);
				}
			});
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setPreferredSize( new Dimension( 350 , 250 ) );
			this.setTitle("Venaqua - Refresh Interval");
			this.setIconImages( IconCollections.getAppLogo() );
			this.setResizable(false);
			contentPane = this.getContentPane();
			this.contentPane.setLayout(null);
			{
				XLabel refresh = new XLabel("Refresh Interval");
				refresh.setFont(FontCollections.fontMenu);
				refresh.setDrawShade(true);
				refresh.setBounds(30, 40, 130, 30);
				contentPane.add(refresh);
				
				refreshInterval = new XSpinner();
				refreshInterval.setModel( new SpinnerNumberModel( _REFRESH_INTERVAL , SerialPortReader._DEFAULT_REPEAT_DELAY, SerialPortReader._MAX_REPEAT_DELAY , _REFRESH_INTERVAL_STEPUP ) );
				refreshInterval.setRound(3);
				refreshInterval.setBounds( 160 , 40 , 100 , 30 );
				contentPane.add(refreshInterval);
				
				XLabel refreshUnit = new XLabel("msec");
				refreshUnit.setFont(FontCollections.fontMenu);
				refreshUnit.setDrawShade(true);
				refreshUnit.setBounds(280, 40, 60, 30);
				contentPane.add(refreshUnit);
				
				XLabel lbl_pollDelay = new XLabel("Delay between polls");
				lbl_pollDelay.setFont(FontCollections.fontMenu);
				lbl_pollDelay.setDrawShade(true);
				lbl_pollDelay.setBounds(30, 90, 130, 30);
				contentPane.add(lbl_pollDelay);
				
				pollDelay = new XSpinner();
				pollDelay.setModel( new SpinnerNumberModel( _POLL_DELAY , _POLL_DELAY_MIN , _POLL_DELAY_MAX , _POLL_DELAY_STEPUP ) );
				pollDelay.setRound(3);
				pollDelay.setBounds( 160 , 90 , 100 , 30 );
				contentPane.add(pollDelay);
				
				XLabel pollDelayUnit = new XLabel("msec");
				pollDelayUnit.setFont(FontCollections.fontMenu);
				pollDelayUnit.setDrawShade(true);
				pollDelayUnit.setBounds(280, 90, 60, 30);
				contentPane.add(pollDelayUnit);
				
				XButton but_refreshInterval = new XButton("Change");
				but_refreshInterval.setFont(FontCollections.fontMenu);
				but_refreshInterval.setDrawShade(true);
				but_refreshInterval.setBounds(117, 150, 80, 30);
				contentPane.add(but_refreshInterval);
				but_refreshInterval.addActionListener( new ActionListener() 
				{	
					@Override
					public void actionPerformed(ActionEvent ae)
					{
						try
						{
							updateSettings updateSett = new updateSettings();
							updateSett.parseXMLFile();
							updateSett.modifySettings( updateSett.processDocument("settings") , "refreshinterval" , refreshInterval.getValue().toString() );
							updateSett.modifySettings( updateSett.processDocument("settings") , "poll_delay" , pollDelay.getValue().toString() );
							updateSett.updateXMLFile();
							
							_REFRESH_INTERVAL = Integer.parseInt( refreshInterval.getValue().toString() );
							_POLL_DELAY = Integer.parseInt( pollDelay.getValue().toString() );
							SerialPortReader.changeUpdateDelay( _POLL_DELAY );
							
							JOptionPane.showMessageDialog( null , "Sucessfully Changed...!", "Information!", JOptionPane.INFORMATION_MESSAGE);
						}
						catch( Exception e )
						{
							e.printStackTrace();
						}
					}
				});
				
				XLabel Notes = new XLabel("<HTML><pr><b>Note : &nbsp 1 sec = 1000 msec &nbsp&nbsp&nbsp 1 min = 60000 msec</b></pr></HTML>");
				Notes.setFont(FontCollections.fontMenu);
				Notes.setDrawShade(true);
				Notes.setBounds(15, 190, 330, 30);
				contentPane.add(Notes);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();			
			JOptionPane.showInternalMessageDialog( null , "Error occured in GUI Initialization...!", StringCollection.Error, JOptionPane.ERROR_MESSAGE );
		}
	}
	
	public void showDialog()
	{
		this.setVisible(true);
		this.setFocusableWindowState(true);
		this.pack();
		this.setLocation( (int)parentFrame.getLocation().getX()+(parentFrame.getWidth()/2)-(this.getWidth()/2) ,  (int)parentFrame.getLocation().getY()+(parentFrame.getHeight()/2)-(this.getHeight()/2) );
	}
	
}
