package net.gui.Alarms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

import net.Details.DataObject;
import net.gui.Home.BreakUpDetails;
import net.utills.IconCollections;


public class UIUpdate_Alarms implements ActionListener
{
	private static final boolean debug = true; 
	
	protected static String selectedHouseName="";
	protected static int selectedHouseIndex=0;
	protected static String lastHouseName="";
	protected static int lastHouseIndex=0;
	
	private static Timer Timer_updateUI;
	private static int   houseCounter=0;
	
	public UIUpdate_Alarms() 
	{
		initTimer();	
	}
	
	private void initTimer()
	{
		Timer_updateUI = new Timer( 250 , this );
		Timer_updateUI.setInitialDelay(500);
		Timer_updateUI.setDelay(250);
		Timer_updateUI.setRepeats(true);
	}
	
	public static void stopTimer()
	{
		Timer_updateUI.stop();
	}
	
	public static void startTimer()
	{
		Timer_updateUI.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			if( debug )
				System.out.println( "Updating House ( " + selectedHouseName + " ) Alarms...." );
			
			/*** OnSelection of different house ***/
			if( !selectedHouseName.equals( lastHouseName ) )
			{
				Vector<String> inletNames = new Vector<String>();
				for( int i=0; i<DataObject.house[selectedHouseIndex].getInletCount(); i++ )
					inletNames.add( DataObject.house[selectedHouseIndex].Inlets[i].getInletName() );
					
				for( int i=0; i<12; i++ )
				{
					if( i<( inletNames.size()-(FailureList.pageIndex*12) ) )
						FailureList.lblInletNames[i].setText( inletNames.elementAt( (FailureList.pageIndex*12)+i ) );
					else
						FailureList.lblInletNames[i].setText("NA");
				}
				
				FailureList.lblMoreFault_Heading.setText( " Alarm Details - " + selectedHouseName );
				
				lastHouseIndex = selectedHouseIndex;
				lastHouseName = selectedHouseName;
			}
			
			/*** Update Inlet notification icon for selected House ***/
			for( int i=0; i<DataObject.house[selectedHouseIndex].getInletCount(); i++ )
				FailureList.lblInletNames[i].setIcon( IconCollections.inletFailure[ DataObject.house[selectedHouseIndex].Inlets[i].getFaultCode() ] );
			
			/*** Update House notification icon for current house group ***/
			if(debug)
			{
				System.out.println("houseCounter : "+ houseCounter + " Cons HouseIndex : "+ ((HouseList.getPageIndex()*30)+houseCounter) );
				System.out.println( "HouseList.house : " + HouseList.house.length + " DataObject.house : " + DataObject.house.length );
			}
			
			int index = (HouseList.getPageIndex()*30)+houseCounter;
			if( index < DataObject.house.length ) 
				HouseList.house[houseCounter].setIcon( DataObject.house[ index ].hasFault() ? IconCollections.error_tick : IconCollections.ok_tick );
			
			if( houseCounter<29 )
				houseCounter++;	
			else
				houseCounter=0;
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
/*	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			if( debug )
				System.out.println( "Updating House ( " + selectedHouseName + " ) Alarms...." );
			
			if( !selectedHouseName.equals(lastHouseName) )
			{
				Vector<String> inletNames = new Vector<String>();
				for( int i=0; i<DataObject.house[selectedHouseIndex].getInletCount(); i++ )
					inletNames.add( DataObject.house[selectedHouseIndex].Inlets[i].getInletName() );
					
				for( int i=0; i<12; i++ )
				{
					if( i<( inletNames.size()-(FailureList.pageIndex*12) ) )
						FailureList.lblInletNames[i].setText( inletNames.elementAt( (FailureList.pageIndex*12)+i ) );
					else
						FailureList.lblInletNames[i].setText("NA");
				}
				
				FailureList.lblMoreFault_Heading.setText( " Alarm Details - " + selectedHouseName );
				
				lastHouseIndex = selectedHouseIndex;
				lastHouseName = selectedHouseName;
			}
			
			*//**** Update selected house's Inlet status ****//* 
			for( int i=0; i<DataObject.house[selectedHouseIndex].getInletCount(); i++ )
			{
				FailureList.lblInletNames[i].setIcon( IconCollections.inletFailure[ DataObject.house[selectedHouseIndex].Inlets[i].getFaultCode() ] );
			}
			if( (selectedHouseIndex/30) == HouseList.getPageIndex() )
				HouseList.house[ selectedHouseIndex<30 ? selectedHouseIndex : selectedHouseIndex-(HouseList.getPageIndex()*30) ].setIcon( DataObject.house[selectedHouseIndex].hasFault() ? IconCollections.error_tick : IconCollections.ok_tick );

			//if( debug )
			//	System.out.println( "houseCounter : " + houseCounter +" HIndex" + ((HouseList.getPageIndex()*30)+houseCounter) + " Fault : " + DataObject.house[(HouseList.getPageIndex()*30)+houseCounter].hasFault() );
			
			*//**** Update other house's Inlet Status ****//*
			if(  ( (HouseList.getPageIndex()*30)+houseCounter ) == selectedHouseIndex )
				houseCounter++;
			
			if( houseCounter >= 30 || houseCounter >= DataObject.house.length )
				houseCounter=0;
			
			if(debug)
			{
				System.out.println("houseCounter : "+ houseCounter + " Cons HouseIndex : "+ ((HouseList.getPageIndex()*30)+houseCounter) );
				System.out.println( "HouseList.house : " + HouseList.house.length + " DataObject.house : " + DataObject.house.length );
			}
			
			if( ( (HouseList.getPageIndex()*30)+houseCounter )<DataObject.house.length )
				HouseList.house[houseCounter].setIcon( DataObject.house[ (HouseList.getPageIndex()*30)+houseCounter ].hasFault() ? IconCollections.error_tick : IconCollections.ok_tick );

			houseCounter++;
			
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		
	}
	*/

}
