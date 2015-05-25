package net.Details;
import java.util.Vector;

public class House
{
	private String houseName = "";
	private String accountNo = "";
	private String owner = "";
	private String addressLine1 = "";
	private String addressLine2 = "";
	private Integer masterID=0;
	private Integer inletCount = 0;
	public Inlet Inlets[];
	
	public House( String hName , Integer mID , Integer inCount , String acNo , String ownerName , String addrLine1 , String addrLine2 , Vector<Inlet>inlets )
	{
		this.houseName = hName;
		this.masterID = mID;
		this.inletCount = inCount;
		this.accountNo = acNo;
		this.owner = ownerName;
		this.addressLine1 = addrLine1;
		this.addressLine2 = addrLine2;
		this.initInletDetails( inlets );
	}
	
	private void initInletDetails( Vector<Inlet>inlets )
	{
		Inlets = new Inlet[ inlets.size() ];
		int i=0;
		
		for( Inlet f : inlets )
		{
			Inlets[i] = f;
			Inlets[i].setInletCost( DataObject.getInletCost( Inlets[i].getInletType() , this.masterID ) );
			i++;
		}
	}
	
	public String getHouseName()
	{
		return this.houseName;
	}
	public Integer getMasterID()
	{
		return this.masterID;
	}
	public Integer getInletCount()
	{
		return this.inletCount;
	}
	public Float getTotalConsumption()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getTotalConsumption();
		
		return res;
	}
	public Float getCurrentMonthConsumption()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getCurrentMonthConsumption();
		
		return res;
	}
	public Float getPreMonthConsumption()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getPreMonthConsumption();
		
		return res;
	}
	public Float getBillPreMonthConsumption()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getBillPreMonthConsumption();
		
		return res;
	}
	public Float getBillCurrMonthConsumption()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getBillCurrMonthConsumption();
		
		return res;
	}
	public Float getTotalConsumptionCost()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getTotalConsumptionCost();
		
		return res;
	}
	public Float getCurrentMonthConsumptionCost()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getCurrentMonthConsumptionCost();
		
		return res;
	}
	public Float getPreMonthConsumptionCost()
	{
		Float res = 0.0f;
		
		for( Inlet in : this.Inlets )
			res = res + in.getPreMonthConsumptionCost();
		
		return res;
	}
	public Float getBillPreMonthConsumptionCost()
	{
		Float res = 0.0f;
		for( Inlet in : this.Inlets )
			res = res + in.getBillPreMonthConsumptionCost();
		return res;
	}
	public Float getBillCurrMonthConsumptionCost()
	{
		Float res = 0.0f;
		for( Inlet in : this.Inlets )
			res = res + in.getBillCurrMonthConsumptionCost();
		return res;
	}
	
	public Vector<Object> getInletDetails( Integer inletNo )
	{
		Vector<Object> res = new Vector<Object>();
		
		res.add( this.Inlets[ inletNo ].getInletName() );
		res.add( this.Inlets[ inletNo ].getInletType() );
		res.add( this.Inlets[ inletNo ].getLiveFlow() );
		res.add( this.Inlets[ inletNo ].getTotalConsumption() );
		res.add( this.Inlets[ inletNo ].getPreMonthConsumption() );
		res.add( this.Inlets[ inletNo ].getFaultName() );
		
		return res;
	}
	
	public void setHouseName( String name )
	{
		this.houseName = name;
	}
	public void setMasterID( Integer mID )
	{
		this.masterID = mID;
	}
	public void setInletCount( Integer count )
	{
		this.inletCount = count ;
	}
	public boolean hasFault()
	{
		boolean res = false;
		
		for( Inlet in : this.Inlets )
		{
			if( !( in.getFaultName().equalsIgnoreCase("NORMAL") ) )
			{
				res = true;
				break;
			}
		}
		
		return res;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getAddress( String seperator )
	{
		return this.getAddressLine1() + seperator + this.getAddressLine2();
	}
}