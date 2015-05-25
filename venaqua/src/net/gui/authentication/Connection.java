package net.gui.authentication;

import java.awt.Color;

import javax.comm.SerialPort;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.time.ClockType;
import com.alee.managers.notification.NotificationManager;

import net.SerialPortComm.SerialPortClass;
import net.SerialPortComm.SerialPortReader;
import net.swingx.component.XButton;
import net.swingx.component.XClock;
import net.swingx.component.XComboBox;
import net.swingx.component.XFrame;
import net.swingx.component.XLabel;
import net.swingx.component.XPanel;
import net.swingx.component.XPopupNotification;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.wimpi.modbus.Modbus;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import net.swingx.component.XTextField;

public class Connection extends XPanel implements ItemListener, ActionListener 
{

	private static final long serialVersionUID = 8275475214756971122L;
	
	private final static Integer _BAUDRATE = 9600;
	private final static Integer _DATABITS = SerialPort.DATABITS_8;
	private final static Integer _PARITY   = SerialPort.PARITY_EVEN;
	private final static Integer _STOPBITS = SerialPort.STOPBITS_1;
	private final static String  _ENCODING = Modbus.SERIAL_ENCODING_RTU;
	private final static Boolean _ECHO	   = true;
	
	private static DefaultComboBoxModel<String> commPortModel;
	private static XTextField txtBeforeRTS;
	private static XTextField txtAfterRTS;
	private static XTextField txtTimeOut;
	private static XButton butConnect;
	private static XButton butRefresh;
	private static XComboBox cboxCommPort;
	
	private static JDialog auth;
	private static XFrame  parentMDI;
	
	public Connection( JDialog dialog , XFrame parent ) 
	{
		auth = dialog;
		parentMDI = parent;
		
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Comm. Connection", TitledBorder.CENTER, TitledBorder.TOP, FontCollections.fontAuthenticationHeader , new Color(0, 0, 0)) );
		setSize(320, 250);
		setLayout(null);
		{
			XLabel lblCommPort = new XLabel("Comm. Port");
			lblCommPort.setBounds(40, 40, 90, 20);
			lblCommPort.setFont( FontCollections.fontAuthentication );
			add(lblCommPort);
			
			String data[] = {"COM1","COM2"};
			commPortModel = new DefaultComboBoxModel<String>( data );
			
			cboxCommPort = new XComboBox( commPortModel , 0 );
			cboxCommPort.setFont( FontCollections.fontAuthentication );
			cboxCommPort.setBounds( 165, 35, 90, 30);
			cboxCommPort.setRound(3);
			cboxCommPort.addItemListener(this);
			add(cboxCommPort);
			
			butRefresh = new XButton( IconCollections.refresh );
			butRefresh.setFont(new Font("Serif", Font.PLAIN, 14));
			butRefresh.setBounds(260, 35, 30 , 30);
			butRefresh.setAnimate(true);
			butRefresh.setRolloverShine(true);
			butRefresh.addActionListener(this);
			butRefresh.setBorderPainted(false);
			butRefresh.setRolloverShine(true);
			butRefresh.addActionListener( this );
			add(butRefresh);
			
			XLabel lblAfterRTS = new XLabel("After RTS Enabled ");
			lblAfterRTS.setFont(new Font("Serif", Font.PLAIN, 14));
			lblAfterRTS.setBounds(40, 85, 120, 20);
			add(lblAfterRTS);
			
			txtAfterRTS = new XTextField();
			txtAfterRTS.setFont(new Font("Serif", Font.PLAIN, 14));
			txtAfterRTS.setBounds(165, 80, 60, 30);
			txtAfterRTS.setText("15");
			add(txtAfterRTS);
			
			XLabel lblUnit = new XLabel(" ms ");
			lblUnit.setFont(new Font("Serif", Font.PLAIN, 14));
			lblUnit.setBounds(230, 85, 30 , 20);
			add(lblUnit);
			
			XLabel lblBeforeRTS = new XLabel("Before RTS Disabled ");
			lblBeforeRTS.setFont(new Font("Serif", Font.PLAIN, 14));
			lblBeforeRTS.setBounds(40, 130, 125, 20);
			add(lblBeforeRTS);
			
			txtBeforeRTS = new XTextField();
			txtBeforeRTS.setFont(new Font("Serif", Font.PLAIN, 14));
			txtBeforeRTS.setBounds(165, 125, 60, 30);
			txtBeforeRTS.setText("10");
			add(txtBeforeRTS);
			
			lblUnit = new XLabel(" ms ");
			lblUnit.setFont(new Font("Serif", Font.PLAIN, 14));
			lblUnit.setBounds(230, 130, 30 , 20);
			add(lblUnit);
			
			XLabel lblTimeOut = new XLabel("TimeOut");
			lblTimeOut.setFont(new Font("Serif", Font.PLAIN, 14));
			lblTimeOut.setBounds(40, 175, 120, 20);
			add(lblTimeOut);
			
			txtTimeOut = new XTextField();
			txtTimeOut.setFont(new Font("Serif", Font.PLAIN, 14));
			txtTimeOut.setBounds(165, 170, 60, 30);
			txtTimeOut.setText("1000");
			add(txtTimeOut);
			
			lblUnit = new XLabel(" ms ");
			lblUnit.setFont(new Font("Serif", Font.PLAIN, 14));
			lblUnit.setBounds(230, 175, 30 , 20);
			add(lblUnit);
			
			butConnect = new XButton("Connect");
			butConnect.setFont(new Font("Serif", Font.PLAIN, 14));
			butConnect.setBounds(110, 210, 90 , 30);
			butConnect.setAnimate(true);
			butConnect.setRolloverShine(true);
			butConnect.addActionListener(this);
			add(butConnect);
		}
	}


	@Override
	public void itemStateChanged(ItemEvent ie) 
	{
		
	}


	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		Object ob = ae.getSource();
		
		if( ob.equals( butRefresh ) )
		{
			 final XPopupNotification notificationPopup = new XPopupNotification();
             notificationPopup.setIcon ( IconCollections.refresh );
             notificationPopup.setDisplayTime ( 5000 );

             final XClock clock = new XClock();
             clock.setClockType ( ClockType.timer );
             clock.setTimeLeft ( 6000 );
             clock.setTimePattern ( "'\t\tPlease wait.... \n This notification will close in' ss 'seconds'" );
             
             notificationPopup.setContent ( new GroupPanel ( clock ) );
             NotificationManager.showNotification ( notificationPopup );
             clock.start ();
             
             commPortModel = new DefaultComboBoxModel<String>( SerialPortClass.getAvailablePorts() ); 
             updateCommPorts( commPortModel );
             
		}
		else if( ob.equals( butConnect ) )
		{	
             if(  !txtAfterRTS.getText().isEmpty() && !txtBeforeRTS.getText().isEmpty() &&  !txtTimeOut.getText().isEmpty() )
             {
	             SerialPortClass.trans_del = Integer.parseInt( txtAfterRTS.getText() );
	             SerialPortClass.recev_del = Integer.parseInt( txtBeforeRTS.getText() );
	             SerialPortClass.timeout_del = Integer.parseInt( txtTimeOut.getText() );
            	 SerialPortClass.createSerial( getCommPort() , _BAUDRATE , _DATABITS, _PARITY, _STOPBITS, _ENCODING, _ECHO );
	             
	             if( SerialPortClass.comOpen_flag )
	             {
	            	 auth.setVisible(false);
	            	 parentMDI.setEnabled(true);
	            	 
	            	 SerialPortReader spReader = new SerialPortReader();
	            	 
	             }
             }
             else
             {
            	 JOptionPane.showMessageDialog( this , "Please fill all the fields....", "Error!", JOptionPane.ERROR_MESSAGE );
             }
		}
		
	}
	
	@SuppressWarnings("unchecked")
	protected void updateCommPorts( DefaultComboBoxModel<String> model )
	{
		cboxCommPort.setModel( model );
		cboxCommPort.setSelectedIndex(0);
		cboxCommPort.repaint();
	}
	
	protected static String getCommPort()
	{
		return cboxCommPort.getSelectedItem().toString();
	}
}
