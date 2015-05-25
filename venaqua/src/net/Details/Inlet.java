package net.Details;

import net.utills.Convert;

public class Inlet
{
	public enum faultTypes
	{ 
		NORMAL , LEAKAGE , BURSTPIPE , NOFLOW , LOWTEMP , HIGHTEMP , COMMUNICATIONFAIL; 
	}

	private String inletName = "";
	private String inletType = "";
	private Float  inletCost = 0.0f;
	private Float  liveFlow  = 0.0f;
	private Float  totalConsumption = 0.0f;
	private Float  preMonthConsumption = 0.0f;
	private Float  billPreMonthConsumption = 0.0f;
	private Integer fault = 0;
	
	public Inlet( String name , String type )
	{
		this.inletName = name;
		this.inletType = type;
	}
	public Inlet( String name , String type , Float cost ,Float flow , Float totalconsump , Float premonthconsump , Integer flt )
	{
		this.inletName = name;
		this.inletType = type;
		this.inletCost = cost;
		this.liveFlow  = flow;
		this.totalConsumption = totalconsump;
		this.preMonthConsumption = premonthconsump;
		this.fault = flt;
	}
	
	public String getInletName()
	{
		return this.inletName;
	}
	public String getInletType()
	{
		return this.inletType;
	}
	/**
	 * @return this method returns cost per liter for this inlet 
	 */
	public Float getInletCost()
	{
		return this.inletCost;
	}
	public Float getLiveFlow()
	{
		return this.liveFlow;
	}
	public Float getTotalConsumption()
	{
		return this.totalConsumption;
	}
	public Float getPreMonthConsumption()
	{
		return this.preMonthConsumption;
	}
	public Float getCurrentMonthConsumption()
	{
		return ( getTotalConsumption()-getPreMonthConsumption() ) ;
	}
	public Float getBillPreMonthConsumption()
	{
		return this.billPreMonthConsumption;
	}
	public Float getBillCurrMonthConsumption()
	{
		return this.getPreMonthConsumption() - this.getBillPreMonthConsumption();
		
	}
	public Float getTotalConsumptionCost()
	{
		return Convert.toLiters( this.totalConsumption ) * this.inletCost ;
	}
	public Float getPreMonthConsumptionCost()
	{
		return Convert.toLiters( this.preMonthConsumption ) * this.inletCost ;
	}
	public Float getCurrentMonthConsumptionCost()
	{
		return Convert.toLiters( getTotalConsumption()-getPreMonthConsumption() )*this.inletCost  ;
	}
	public Float getBillPreMonthConsumptionCost()
	{
		return Convert.toLiters( this.billPreMonthConsumption ) * this.inletCost;
	}
	public Float getBillCurrMonthConsumptionCost()
	{
		return Convert.toLiters( this.getPreMonthConsumption() - this.getBillPreMonthConsumption() )*this.inletCost;
		
	}
	public Integer getFaultCode()
	{
		return this.fault;
	}
	public String getFaultName()
	{
		String res = null;
		int i=0;
		for( faultTypes name : faultTypes.values() )
		{
			if( i== this.fault )
			{
				res = name.toString();
				break;
			}
			i++;
		}
		return res;
	}
	
	public void setInletName( String name )
	{
		this.inletName = name ;
	}
	public void setInletType( String type )
	{
		this.inletType = type ;
	}
	/**
	 * @param cost - cost per liter for this inlet 
	 */
	public void setInletCost(Float cost )
	{
		this.inletCost = cost;
	}
	public void setLiveFlow( Float flow )
	{
		this.liveFlow = flow ;
	}
	public void setTotalConsumption( Float consump )
	{
		this.totalConsumption = consump ;
	}
	public void setPreMonthConsumption( Float premonthconsump )
	{
		this.preMonthConsumption = premonthconsump;
	}
	public void setBillPreMonthConsumption( Float billedConsump ) 
	{
		this.billPreMonthConsumption = billedConsump;
	}
	public void setFault( Integer code )
	{
		this.fault = code ;
	}
	public void setFault( faultTypes type )
	{
		int i=0;
		for( faultTypes name : faultTypes.values() )
		{
			if( name == type )
				break;
			i++;
		}
		this.fault = i ;
	}
}