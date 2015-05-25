package net.Details;
import java.util.*;

public class Apartment
{
	private HashMap<String,Float> Inlet;
	private int houseCount = 0;
	private int masterID = 0;
	
	public Apartment( Integer mID , Integer hCount , HashMap<String,Float> line )
	{
		this.masterID = mID;
		this.houseCount = hCount;
		initLineDetails(line);
	} 
	
	
	public Integer getMasterID()
	{
		return this.masterID;
	}
	public void setMasterID( Integer id )
	{
		this.masterID = id ;
	}
	public Integer getHouseCount()
	{
		return this.houseCount;
	}
	public void setHouseCount( Integer hCount )
	{
		this.houseCount = hCount;
	}
	private void initLineDetails(HashMap<String,Float>inlets )
	{
		this.Inlet = new HashMap<String,Float>( inlets );
	}
	public Vector<String> getInletTypes()
	{
		Vector<String> res = new Vector<String>();
		
		for( String str : this.Inlet.keySet() )
			res.add( str );
			
		return res;
	}
	public String getInletType( int typeIndex )
	{
		String res = null;
		
		Vector<String> types = new Vector<String>(  this.Inlet.keySet() );
		res = types.elementAt( typeIndex );
		return res;
	}
	public Vector<Float> getInletCost()
	{
		Vector<Float> res = new Vector<Float>();
		
		for( String str : this.Inlet.keySet() )
			res.add( this.Inlet.get(str) );
		
		return res;
	}
	public Float getInletCost( int typeIndex )
	{
		Float res = null;
		
		Vector<Float> types = new Vector<Float>(  this.Inlet.values() );
		res = types.elementAt( typeIndex );
		return res;
	}
	public Float getCostbyType( String type )
	{
		Float res = this.Inlet.get( type );
		return res;
	}
}