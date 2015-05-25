package net.gui.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

import net.Details.DataObject;

public class UIUpdate_Home implements ActionListener 
{
	private static final boolean debug = false; 
	
	private static Timer Timer_updateUI;
	
	protected static int selectedHomeIndex = 0;
	protected static String selectedHomeName = "";
	protected static int lastHomeIndex = 0;
	protected static String lastHomeName = "";
	
	protected static Float general_OverAllConsumption = 0.0f;
	protected static Float general_CurrentMonthConsumption = 0.0f;
	protected static Float general_CurrentMonthCost = 0.0f;
	
	public UIUpdate_Home() 
	{
		initTimer();	
	}
	
	private void initTimer()
	{
		Timer_updateUI = new Timer( 500 , this );
		Timer_updateUI.setInitialDelay(500);
		Timer_updateUI.setDelay(500);
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
			synchronized (ae)
			{
				if( debug )
					System.out.println( "Updating House ( " + selectedHomeName + " ) Info:" );
				
				if( !selectedHomeName.equals(lastHomeName) )
				{
					Vector<String> inlet = new Vector<String>();
					for( int i=0; i<DataObject.house[selectedHomeIndex].getInletCount(); i++ )
						inlet.add( DataObject.house[selectedHomeIndex].Inlets[i].getInletName() );
						
					BreakUpDetails.updateInletNames( inlet );
					
					lastHomeIndex = selectedHomeIndex;
					lastHomeName = selectedHomeName;
				}
				
				GeneralInfo.setHouseName( selectedHomeName );
				
				general_OverAllConsumption      = DataObject.house[selectedHomeIndex].getTotalConsumption();
				general_CurrentMonthConsumption = DataObject.house[selectedHomeIndex].getCurrentMonthConsumption();
				general_CurrentMonthCost        = DataObject.house[selectedHomeIndex].getCurrentMonthConsumptionCost();
				
				GeneralInfo.setOverAllConsumption( general_OverAllConsumption );
				GeneralInfo.setCurrentMonthConsumption( general_CurrentMonthConsumption );
				GeneralInfo.setCurrentMonthCost( general_CurrentMonthCost );
				
				int inletIndex = BreakUpDetails.getSelectedInlet();
				
				BreakUpDetails.setLiveFlow( DataObject.house[selectedHomeIndex].Inlets[inletIndex].getLiveFlow() );
				BreakUpDetails.setTotalConsumption( DataObject.house[selectedHomeIndex].Inlets[inletIndex].getTotalConsumption() );
				BreakUpDetails.setCurrentMonthConsumption( DataObject.house[selectedHomeIndex].Inlets[inletIndex].getCurrentMonthConsumption() );
				//BreakUpDetails.setCurrentMonthCost( DataObject.house[selectedHomeIndex].Inlets[inletIndex].getCurrentMonthConsumption() * DataObject.apartment[DataObject.house[selectedHomeIndex].getMasterID() ].getCostbyType(  DataObject.house[selectedHomeIndex].Inlets[inletIndex].getInletType() ) );
				BreakUpDetails.setCurrentMonthCost( DataObject.house[selectedHomeIndex].Inlets[inletIndex].getCurrentMonthConsumptionCost() );
				BreakUpDetails.setInletStatus( DataObject.house[selectedHomeIndex].Inlets[inletIndex].getFaultName() );
				BreakUpDetails.setWaterType( DataObject.house[selectedHomeIndex].Inlets[inletIndex].getInletType() );
				
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

}
