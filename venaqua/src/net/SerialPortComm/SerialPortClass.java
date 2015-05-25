package net.SerialPortComm;

import javax.comm.*;
import javax.swing.JOptionPane;

import net.utills.Convert;
import net.utills.StringCollection;
import net.wimpi.modbus.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.*;
import net.wimpi.modbus.util.*;

import java.io.IOException;
import java.util.*;



public class SerialPortClass
{
	String respons_str = null ;
	public static boolean comOpen_flag = false ;
	boolean res_flag = true;
	
	Vector<String> v_response =new Vector<String>();
	
	/* The important instances of the classes mentioned before */
    ModbusSerialTransport Transport ;
	static ModbusCoupler modbusCoupler = null;
	static SerialParameters params;
	static SerialConnection con = null; 				//the connection
	ModbusSerialTransaction trans = null; 		//the transaction
	ModbusRequest mreq = null;					//the modbus request

	
	Register reges = null;
	Register[] registers = null;
	DefaultProcessImageFactory dpif = null;
	
	//Write Single Register(Word)
	WriteSingleRegisterRequest write_sreq = null;
	WriteSingleRegisterResponse write_sres = null;
	
	//Write Multiple Registers(Words)
	WriteMultipleRegistersRequest write_mreq = null;
	WriteMultipleRegistersResponse write_mres = null;
	
	//Write Multiple Coils
	WriteMultipleCoilsRequest write_mult_coil_req = null;
	WriteMultipleCoilsResponse write_mult_coil_res = null;
	BitVector vector_data = null;
	
	//write single coil
	WriteCoilRequest write_single_coil_req = null;
	WriteCoilResponse write_single_coil_res = null;
	
	int unitid = 1; 		//the unit identifier we will be talking to
	
	
	public static int trans_del=15;
	public static int recev_del=10;
	public static int timeout_del=500;
	
	public static void main(String args[])throws IOException
	{
		try
		{
			createSerial("COM1", 9600, 8, SerialPort.PARITY_EVEN,1,Modbus.SERIAL_ENCODING_RTU,true);
			
			//boolean f= spc.readDatas(30, 4);
			//System.out.println("Res "+spc.readDatas(55, 1,1));
		}
		catch(Exception e)
		{	
			e.printStackTrace();	
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes" })
	public static Vector<String> getAvailablePorts()
	{
		Vector<String> res = new Vector<String>();
		try
		{
			String drivername = "com.sun.comm.Win32Driver";
			CommDriver driver = (CommDriver)Class.forName(drivername).newInstance();
			driver.initialize(); 		

			Enumeration comm = CommPortIdentifier.getPortIdentifiers() ;
			
			System.out.println( "Listing comm ports...." );
			
			while( comm.hasMoreElements() )
			{
				CommPortIdentifier port = (CommPortIdentifier) comm.nextElement();
				System.out.println( " PORT : " + port.getName() + "  Type : " + port.getPortType() );
				
				if( port.getPortType() == CommPortIdentifier.PORT_SERIAL && !res.contains( port.getName() ) )
					res.add( port.getName() );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			res.removeAllElements();
		}
		finally
		{
			return res;
		}
	}
	
	public static boolean createSerial(String portN, int baudRate, int dataBits, int parity, int stopBits, String ecoding, boolean echo )
	{
		try
		{
			String drivername = "com.sun.comm.Win32Driver";
			CommDriver driver = (CommDriver)Class.forName(drivername).newInstance();
			driver.initialize(); 		
			
			//2. Set master identifier
			modbusCoupler = ModbusCoupler.getReference();
			modbusCoupler.setUnitID(1);
			modbusCoupler.setMaster(true);

			//3. Setup serial parameters
			params = new SerialParameters();
			params.setPortName(portN);
			params.setBaudRate(baudRate);
			params.setDatabits(dataBits);
			params.setParity(parity);
			params.setStopbits(stopBits);
			params.setEncoding(Modbus.SERIAL_ENCODING_RTU);
			params.setEcho(echo);
			
			//4. Open the connection
			con = new SerialConnection(params);
			con.open();
			comOpen_flag= true;
			
			System.out.println( "Serial Port " + params.getPortName() + " " + (con.isOpen()==true ? " Opened." : " not Opened Correctly.") );
		}
		catch(Exception e1)
		{
			System.err.println("ERROR in Serial Port Initialization : "+ e1.getMessage() );
			JOptionPane.showMessageDialog( null , "Cannot open selected Port... \n \n Kindly check your connection. \n\n" , StringCollection.Warning , JOptionPane.WARNING_MESSAGE );
			e1.printStackTrace();
			con.close();
			comOpen_flag=false;
		}
		return comOpen_flag;
	}
	
	public static boolean conClose()
	{
		try
		{
			con.close();
			comOpen_flag=false;
		}
		catch(Exception e)
		{
			System.out.println("Error in Connection Close : "+ e.getMessage() );
			e.printStackTrace();
			comOpen_flag=true;
		}
		return comOpen_flag;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static Vector readDatas( Integer id , Integer start_add , Integer word_count ) 
	{
		Vector result = new Vector();
		
		try
		{
			/********** Read Multiple Datas ************/
			
			// Prepare a request
			ReadMultipleRegistersRequest read_req = new ReadMultipleRegistersRequest();
			read_req.setUnitID(id);
			read_req.setHeadless();
			read_req.setReference(start_add);
			read_req.setWordCount(word_count);
						
			// Prepare a transaction
			ModbusSerialTransaction trans = new ModbusSerialTransaction(con);
			trans.setRequest(read_req);
			trans.setRetries(3);
			trans.setTransDelayMS(trans_del);
			trans.setRecevDelayMS(recev_del);
			trans.setTime_out(timeout_del);
			
			// Execute the transaction
			System.out.println( "Sending Status Read Request...." );
			System.out.println( "Sent Request  : " + read_req.getHexMessage() );
				
			trans.execute();
			ModbusResponse mr = trans.getResponse();
			
			// Process Response
			ReadMultipleRegistersResponse read_res = ( ReadMultipleRegistersResponse ) mr ;
			System.out.println( "Recvd Response: " + read_res.getHexMessage() );
						
			// Evaluate Response Frame
			if( read_res.getUnitID()==id && read_res.getFunctionCode() == 3 )
			{
				result.clear();
				result.removeAllElements();
				
				for( int i=0 ; i < read_res.getWordCount() ; i++ )
				{
					result.add( Convert.toHexString( read_res.getRegisterValue(i) ) );
				}
				System.out.println( " Response Vector : " + result + " Count: " + result.size() );
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			result.clear();
			result.removeAllElements();
		}
		
		return result;
	}
	
	public boolean writeSingleData(int id , int start_add,byte[] bytes)
	{
		try
		{
			/************ Write Single Data **************/
			
			//5. Prepare a request
			dpif = new DefaultProcessImageFactory();
			reges = new SimpleRegister( bytes[0] , bytes[1] );
			write_sreq = new WriteSingleRegisterRequest( start_add , reges );
			//write_sreq.setUnitID( unitid );
			write_sreq.setUnitID( id );
			write_sreq.setHeadless();
			
			// Prepare a Transaction
			System.out.println("Preparing Transaction....");
			trans = new ModbusSerialTransaction( con );
			trans.setRequest( write_sreq );
			trans.setRetries(5);
			trans.setTransDelayMS(trans_del);
			trans.setRecevDelayMS(recev_del);
			trans.setTime_out(timeout_del);
			
			// Execute Transaction
			System.out.println("Executing Transaction....");
			System.out.println("Sent Request: " + write_sreq.getHexMessage() );
			trans.execute();
			
			// Get Response
			System.out.println("Getting Response....");
			write_sres = ( WriteSingleRegisterResponse ) trans.getResponse();
			
			System.out.println( "Response : FCode= " + Integer.parseInt( write_sres.getFunctionCode() + "" , 16 ) + " msg= " + write_sres.getHexMessage() );
			respons_str = write_sres.getHexMessage();
			
			System.out.println("Transaction Completed.");
			
			System.out.println("Evaluating Response....");
			if( write_sres.getFunctionCode() == 6 )
			{
				res_flag = true;
				System.out.println( "Transaction Success!" );
				
			}
			else
			{ 
				res_flag=false;
				System.out.println( " Transaction Failed! " );
			}
		}
		catch(Exception e)
		{	
			System.out.println(" Error in Single Write Operation : " + e.getMessage() );
			e.printStackTrace();
			res_flag = false; 
			respons_str = "exp" ; 
		}
		
		return res_flag;
	}
	
	public boolean writeMultipleDatas(int id , int start_add,byte[] bytes)
	{
		try
		{
			/********* Write Multiple Data ************/
			
			//5. Prepare a request	
			int bytecount = bytes.length / 2;
			registers = new Register[ bytecount ];
			for( int i=0,j=0 ; i<bytes.length ; i=i+2,j++ )
			{
				registers[ j ] = new SimpleRegister( bytes[ i ] , bytes[ i+1 ] );
			}
			
			write_mreq = new WriteMultipleRegistersRequest();
			write_mreq.setDataLength(9);
			write_mreq.setReference(start_add);
			write_mreq.setRegisters(registers);
			write_mreq.setUnitID(id);
			write_mreq.setHeadless();
			
			// Preparing Transaction
			System.out.println("Preparing Transaction....");
			trans = new ModbusSerialTransaction( con );
			trans.setRequest( write_mreq );
			trans.setTransDelayMS(trans_del);
			trans.setRecevDelayMS(recev_del);
			trans.setTime_out(timeout_del);
			trans.setRetries(5);
			
			// Execute Transaction
			System.out.println("Executing Transaction....");
			System.out.println( " Sent Request: " + write_mreq.getHexMessage() );
			trans.execute();
			
			// Get Response
			System.out.println( " Getting Response.... " );
			write_mres = ( WriteMultipleRegistersResponse ) trans.getResponse();
			
			System.out.println( " Response " + Integer.parseInt( ( write_mres.getFunctionCode() + "" ) , 16 ) + " msg: " + write_mres.getHexMessage() );
			
			System.out.println("Transaction Completed.");
			
			// Evaluate Response
			System.out.println("Evaluating Response....");
			if( write_mres.getFunctionCode() == 16 )
			{	
				res_flag = true;
				System.out.println( "Transaction Success!" );
			}
			else
			{	
				res_flag = false;
				System.out.println( "Transaction Failed!" );
			}
		}
		catch( Exception e )
		{	
			System.out.println(" Error in Multiple Write Operation : " + e.getMessage() );
			e.printStackTrace();
			res_flag = false;
			respons_str = "exp" ;
		}
		return res_flag;
	}
	
	public boolean writesinglecoil( int id , int start_add , boolean state )
	{
		try
		{
			write_single_coil_req = new WriteCoilRequest();
			write_single_coil_req.setUnitID( id );
			write_single_coil_req.setReference(start_add);
			write_single_coil_req.setCoil(state);
			write_single_coil_req.setHeadless();
			write_single_coil_req.setDataLength(20);
			
			System.out.println("Preparing Transaction....");
			trans = new ModbusSerialTransaction( con );
			trans.setRequest( write_single_coil_req );
			trans.setRetries( 3 );
			trans.setTransDelayMS(recev_del);
			trans.setRecevDelayMS(trans_del);
			trans.setTime_out(timeout_del);
			
			System.out.println( " Sent Request: " + write_single_coil_req.getHexMessage() );
			// Execute Transaction
			System.out.println("Executing Transaction....");
			trans.execute();
			
			System.out.println( " Sent Request: " + write_single_coil_req.getHexMessage() );
			
			// Get Response
			System.out.println( " Getting Response.... " );
			write_single_coil_res = ( WriteCoilResponse ) trans.getResponse();
			
			System.out.println( " Response " + Integer.parseInt( ( write_single_coil_res.getFunctionCode() + "" ) , 16 ) + " msg: " + write_single_coil_res.getHexMessage() );
			
			System.out.println("Transaction Completed.");
			
			// Evaluate Response
			System.out.println("Evaluating Response....");
			if( write_single_coil_res.getFunctionCode() == 5 )
			{	
				res_flag = true;
				System.out.println( "Transaction Success!" );
			}
			else
			{	
				res_flag = false;
				System.out.println( "Transaction Failed!" );
			}
		}
		catch( Exception e )
		{
			System.out.println(" Error in Multiple Write Coils : " + e.getMessage() );
			e.printStackTrace();
			res_flag = false;
			respons_str = "exp" ;
		}
		
		return res_flag;
	}
	
	public boolean writemultiplecoils( int id , int start_add , byte[] data )
	{
		try
		{
			write_mult_coil_req = new WriteMultipleCoilsRequest( );
			write_mult_coil_req.setUnitID(id);
			write_mult_coil_req.setReference(start_add);
			write_mult_coil_req.setCoils(vector_data.createBitVector(data));
			write_mult_coil_req.setHeadless();
			write_mult_coil_req.setDataLength(20);

			// Preparing Transaction
			System.out.println("Preparing Transaction....");
			trans = new ModbusSerialTransaction( con );
			trans.setRequest( write_mult_coil_req );
			trans.setRetries( 3 );
			trans.setTransDelayMS(recev_del);
			trans.setRecevDelayMS(trans_del);
			trans.setTime_out(timeout_del);
			
			/*if( Recev_Delay_ms >0  )
			{	trans.setRecevDelayMS(Recev_Delay_ms);	}
			if( Trans_Delay_ms >0  )
			{	trans.setTransDelayMS(Trans_Delay_ms);	}*/
			
			
			
			System.out.println( " Sent Request: " + write_mult_coil_req.getHexMessage() );
			// Execute Transaction
			System.out.println("Executing Transaction....");
			trans.execute();
			
			System.out.println( " Sent Request: " + write_mult_coil_req.getHexMessage() );
			
			// Get Response
			System.out.println( " Getting Response.... " );
			write_mult_coil_res = ( WriteMultipleCoilsResponse ) trans.getResponse();
			
			System.out.println( " Response " + Integer.parseInt( ( write_mult_coil_res.getFunctionCode() + "" ) , 16 ) + " msg: " + write_mult_coil_res.getHexMessage() );
			
			System.out.println("Transaction Completed.");
			
			// Evaluate Response
			System.out.println("Evaluating Response....");
			if( write_mult_coil_res.getFunctionCode() == 15 )
			{	
				res_flag = true;
				System.out.println( "Transaction Success!" );
			}
			else
			{	
				res_flag = false;
				System.out.println( "Transaction Failed!" );
			}
		}
		catch( Exception e)
		{
			System.out.println(" Error in Multiple Write Coils : " + e.getMessage() );
			e.printStackTrace();
			res_flag = false;
			respons_str = "exp" ;
		}
		
		return res_flag;
	}
}