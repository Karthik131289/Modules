package net.SerialPortComm;

import java.util.Iterator;
import java.util.Vector;

import net.Details.DataObject;

public class DataProcessing
{

	@SuppressWarnings("rawtypes")
	protected static void processInletData( Vector data )
	{
		@SuppressWarnings("unchecked")
		Iterator<String> it = data.iterator();
		
		String dataHex = "";
		// Process Inlet Name - 2 Register
		dataHex = it.next().toString().concat( it.next().toString() );
		
		// Inlet Water Type - 1 Register
		dataHex = it.next().toString();
		//DataObject.house[SerialPortReader._houseIndexCons].Inlets[SerialPortReader._inletIndex].setInletType( DataObject.apartment[SerialPortReader._masterIndex].getInletType( Integer.parseInt(dataHex) ) );
		
		// Flow Live - 1 Register
		dataHex = it.next().toString();
		DataObject.house[SerialPortReader._houseIndexCons].Inlets[SerialPortReader._inletIndex].setLiveFlow( (Integer.parseInt( dataHex , 16)/1.0f) );
		
		// Pre Month End Consumption - 4 Register
		dataHex = it.next().toString().concat( it.next().toString() ).concat( it.next().toString() ).concat( it.next().toString() );
		DataObject.house[SerialPortReader._houseIndexCons].Inlets[SerialPortReader._inletIndex].setPreMonthConsumption( (Long.parseLong( dataHex , 16)/1000.0f) );    // liter to Kilo liter so divided by 1000
		
		// OverAll Consumption - 4 Register
		dataHex = it.next().toString().concat( it.next().toString() ).concat( it.next().toString() ).concat( it.next().toString() );
		DataObject.house[SerialPortReader._houseIndexCons].Inlets[SerialPortReader._inletIndex].setTotalConsumption( (Long.parseLong( dataHex , 16)/1000.0f) );      // liter to Kilo liter so divided by 1000
				
		// FaultStatus - 1 Register
		dataHex = it.next().toString();
		int temp = Integer.parseInt( dataHex , 16 );
		String binaryCode = String.format( "%16s", Integer.toBinaryString( temp ) ).replaceAll( " " , "0" );
		binaryCode = new StringBuffer( binaryCode ).reverse().toString();
		int faultCode = binaryCode.indexOf("1")==-1 ? 0 : (binaryCode.indexOf("1")+1) ;
		DataObject.house[SerialPortReader._houseIndexCons].Inlets[SerialPortReader._inletIndex].setFault( faultCode  );

	}

}
