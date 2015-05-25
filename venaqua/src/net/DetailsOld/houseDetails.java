package net.DetailsOld;

import java.util.Vector;

public class houseDetails
{
	private String houseName = null;
	private int    inletCount;
	Vector<inletDetails> inlet = new Vector<inletDetails>();
	
	public houseDetails( String hName , int incount )
	{
		this.setHouseName(hName);
		this.setInletCount(incount);
		this.generateInletInfo();
	}
	
	public String getHouseName()
	{
		return houseName;
	}
	public void setHouseName(String houseName) 
	{
		this.houseName = houseName;
	}

	public int getInletCount() 
	{
		return inletCount;
	}
	public void setInletCount(int inletCount) 
	{
		this.inletCount = inletCount;
	}
	
	private void generateInletInfo()
	{
		inlet.clear();
		
		int inCount = this.getInletCount();
	}
}
