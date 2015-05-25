package net.gui;


import net.gui.Alarms.FailureList;
import net.gui.Alarms.HouseList;
import net.gui.Alarms.MoreInfoList;
import net.gui.Alarms.UIUpdate_Alarms;
import net.gui.Home.UIUpdate_Home;
import net.swingx.component.XInternalFrame;
import net.swingx.component.XPanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Vector;


public class window_Alarms
{
	XInternalFrame frameAlarm;
	Container contentPane;
	XPanel houseList;
	XPanel failureList;
	XPanel moreInfoList;
	
	private Vector<String> hName;
	
	@SuppressWarnings("static-access")
	public window_Alarms( Vector<String> hList) 
	{
		this.hName = hList;
		initGUI();
		
		UIUpdate_Alarms uiTimer = new UIUpdate_Alarms();
		HouseList.house[0].doClick();
		uiTimer.startTimer();
	}
	
	private void initGUI()
	{
		try
		{
			frameAlarm = new XInternalFrame("Alarms", false, false, true , false );
			//frameAlarm.setBorder(new CompoundBorder(new LineBorder(new Color(153, 180, 209), 2, true), new MatteBorder(2, 3, 3, 3, (Color) new Color(185, 209, 234))));
			//frameAlarm.getContentPane().setLayout(new BorderLayout(5, 5));
			frameAlarm.getContentPane().setLayout( null );
			frameAlarm.setFittoFullSize();
			
			contentPane = frameAlarm.getContentPane();
			{
				HouseList hlist  = new HouseList( this.hName );
				hlist.houseList.setBounds( 1 , 2 , 1083 , 250 );
				contentPane.add( hlist.houseList );
				
				FailureList fList = new FailureList( this.hName );
				fList.FailureList.setBounds( 1, 255, 1083, 350);
				contentPane.add( fList.FailureList );
				
				//contentPane.add( new MoreInfoList( this.hName ).MoreInfoList , BorderLayout.SOUTH );
				{
					
				}
			}
			
			frameAlarm.pack();
			frameAlarm.setVisible(true);
			frameAlarm.setSelected(true);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
