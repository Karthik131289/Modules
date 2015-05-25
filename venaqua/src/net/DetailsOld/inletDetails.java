package net.DetailsOld;

public class inletDetails 
{
	public enum FaultDetail
	{
		LEAKAGE,BURSTPIPE,NOFLOW,LOWTEMP,HIGHTEMP,COMFAIL,RESERVED
	};
	
	
	private String inletName = null;
	private String waterType = null;
	private float  liveFlow;
	private float  overallConsumption;
	private FaultDetail faultInfo;
	
	public inletDetails( String inName , String inType , float inflow , float inConsumption , FaultDetail fault ) 
	{
		this.setInletName(inName);
		this.setWaterType(inType);
		this.setLiveFlow(inflow);
		this.setOverallConsumption(inConsumption);
		this.setFaultInfo(fault);
	}

	public String getInletName() 
	{
		return inletName;
	}
	public void setInletName(String inletName) 
	{
		this.inletName = inletName;
	}

	public String getWaterType()
	{
		return waterType;
	}
	public void setWaterType(String waterType) 
	{
		this.waterType = waterType;
	}

	public float getLiveFlow()
	{
		return liveFlow;
	}
	public void setLiveFlow(float liveFlow) 
	{
		this.liveFlow = liveFlow;
	}

	public float getOverallConsumption() 
	{
		return overallConsumption;
	}
	public void setOverallConsumption(float overallConsumption) 
	{
		this.overallConsumption = overallConsumption;
	}

	public FaultDetail getFaultInfo() 
	{
		return faultInfo;
	}

	public void setFaultInfo(FaultDetail faultInfo) 
	{
		this.faultInfo = faultInfo;
	}
}
