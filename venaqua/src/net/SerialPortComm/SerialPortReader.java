package net.SerialPortComm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;
import javax.xml.crypto.Data;

import net.Details.DataObject;
import net.DetailsOld.inletDetails;
import net.gui.dialog_FactorySettings;
import net.gui.window_MDI;

public class SerialPortReader implements DataBlock, ActionListener
{
	private static final Boolean _DEBUG = false; 
	public static final Integer _DEFAULT_INITIAL_DELAY = 250;
	public static final Integer _DEFAULT_REPEAT_DELAY = 250;
	public static final Integer _MAX_REPEAT_DELAY = 120000;
	
	protected static Integer _masterIndex = 0;	
	protected static Integer _houseIndex = 0;
	
	/**
	 * _houseIndexCons - It is mandatory,because DataObject.House[] contains complete house list from all Venaqua Master. 
	 *                   Its Value will added by one(1) till final venaqua master data fetched.Then reinitialized to Zero(0).
	 */
	protected static Integer _houseIndexCons = 0;
	protected static Integer _inletIndex = 0;
	
	public static Boolean _resetTimeDelay = false;
	//private static Integer _pauseDelay = 60000;

	private Integer addr = DataBlock.SIZE_BASEBLOCK + DataBlock.SIZE_HOUSE_HEADER; 
	
	private static Timer updateTimer;
	
	public SerialPortReader()
	{
		initTimer();
		startUpdateTimer();
	}
	
	private void initTimer()
	{
		try
		{
			updateTimer = new Timer( _DEFAULT_INITIAL_DELAY , this ); 
			updateTimer.setInitialDelay( _DEFAULT_INITIAL_DELAY );
			updateTimer.setDelay( dialog_FactorySettings._POLL_DELAY );
			updateTimer.setRepeats(true);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void startUpdateTimer()
	{
		if( updateTimer!=null )
			updateTimer.start();
	}
	public static void  stopUpdateTimer() 
	{
		if( updateTimer!=null )
			updateTimer.stop();
	}
	public static void  restartUpdateTimer() 
	{
		if( updateTimer!=null )
			updateTimer.restart();
	}
	public static Boolean isUpdateTimerRunning()
	{
		Boolean res = false;
		
		if( updateTimer!=null )
			res = updateTimer.isRunning();
		
		return res;
	}
	public static Integer getUpdateDelay()
	{
		Integer delay=0;
		if( updateTimer!=null )
			delay = updateTimer.getDelay();
		
		return delay;
	}
	public static void changeUpdateDelay( Integer delay )
	{
		if( updateTimer!=null )
			updateTimer.setDelay( delay );
	}
	public static void resume()
	{
		_resetTimeDelay = false;
		changeUpdateDelay( dialog_FactorySettings._POLL_DELAY );
		//changeUpdateDelay( _DEFAULT_INITIAL_DELAY );
		
		window_MDI.setProgress(0);
		window_MDI.setStatusInfo("Idle...");
	}
	public static void pause()
	{
		changeUpdateDelay( dialog_FactorySettings._REFRESH_INTERVAL );
		_resetTimeDelay = true;
		
		window_MDI.setProgress(0);
		window_MDI.setStatusInfo("Idle...");
	}

	@Override
	public void actionPerformed(ActionEvent ae )
	{
		try
		{
			System.out.println( "Time : " + java.time.LocalTime.now() );
			Object ob = ae.getSource();
			
			if( ob.equals( updateTimer ) )
			{
				if( _DEBUG )	
				{
					//System.out.println( System.currentTimeMillis() );
					System.out.println( "_houseIndex : " +  _houseIndex + " _inletIndex : " + _inletIndex + "  --> " + (addr+1) );
				}
								
				if( _resetTimeDelay )
				{	
					resume();
					_masterIndex=0;
					_houseIndex=0;
					_houseIndexCons=0;
					_inletIndex=0;
				}
				
				window_MDI.setStatusInfo( "Fetching Information Inlet - " + DataObject.house[_houseIndexCons].Inlets[_inletIndex].getInletName() + " in House - " + DataObject.house[_houseIndexCons].getHouseName() +"..."  );
				
				// Test
				@SuppressWarnings("rawtypes")
				//Vector res = SerialPortClass.readDatas( DataObject.house[_houseIndexCons].getMasterID() , (addr+1) , DataBlock.SIZE_INLETBLOCK );
				Vector res = new Vector();
				
				if( res.size()>=SIZE_INLETBLOCK )
				{
					if( _DEBUG )
					{
						System.out.println( "_houseIndexCons : "+ _houseIndexCons + "_masterIndex : " +  _masterIndex + "_houseIndex : " +  _houseIndex + " _inletIndex : " + _inletIndex );
						System.out.println( "inlet data : " + res );
					}
					DataProcessing.processInletData( res );
					window_MDI.setStatusInfo( window_MDI.getStatusInfo()+" Done!");
				}
				else
				{
					window_MDI.setStatusInfo( window_MDI.getStatusInfo()+" Failed!!!");
				}
				
				addr = addr + DataBlock.SIZE_INLETBLOCK;
				
				if( _inletIndex < DataObject.house[_houseIndexCons].getInletCount()-1 )
					_inletIndex++;
				else
				{
					_inletIndex = 0;
					
					window_MDI.setProgress( window_MDI.getProgress() + 100.0f/(float)DataObject.house.length);
					
					if( _houseIndex<DataObject.apartment[_masterIndex].getHouseCount()-1 )
					{
						_houseIndex++;
						_houseIndexCons++;
						addr = addr + DataBlock.SIZE_HOUSE_HEADER;
					}
					else
					{
						_houseIndex=0;
						_houseIndexCons++;

						addr = DataBlock.SIZE_BASEBLOCK + DataBlock.SIZE_HOUSE_HEADER;
						
						if(_masterIndex<DataObject.apartment.length-1 )
							_masterIndex++;
						else
						{
							pause();
							_inletIndex=0;
							_houseIndex=0;
							_houseIndexCons=0;
							_masterIndex=0;
						}
					}
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	 }
	
}
