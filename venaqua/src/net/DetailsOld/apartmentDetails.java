package net.DetailsOld;

import java.util.ArrayList;
import java.util.HashMap;

public class apartmentDetails 
{
	private int houseCount;
	private HashMap<String, Float> WaterLine_Detail;
	
	
	public apartmentDetails()
	{
		this.houseCount=0;
		this.WaterLine_Detail = new HashMap<String,Float>();
	}
	
	public apartmentDetails( int totHouse , HashMap<String, Float>LineDetails )
	{
		this.houseCount = totHouse;
		this.WaterLine_Detail = LineDetails;
	}
	
	protected int getHouseCount()
	{
		return houseCount;
	}

	protected void setHouseCount(int houseCount) 
	{
		this.houseCount = houseCount;
	}

	protected HashMap<String, Float> getWaterLine_Detail()
	{
		HashMap<String, Float> temp = new HashMap<String, Float>();
		temp.putAll( this.WaterLine_Detail );
		
		return temp;
	}

	protected void setWaterLine_Detail(HashMap<String, Float> inlet_WaterDetails )
	{
		this.WaterLine_Detail.putAll(inlet_WaterDetails); 
	}
	
	protected ArrayList<String> getWaterTypes()
	{
		ArrayList<String> types = new ArrayList<String>( this.WaterLine_Detail.keySet() );
		return types;
	}
	
	protected ArrayList<Float> getPriceList()
	{
		ArrayList<Float> price = new ArrayList<Float>( this.WaterLine_Detail.values() );
		return price;
	}
}
