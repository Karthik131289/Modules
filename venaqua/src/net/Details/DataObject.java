package net.Details;

public class DataObject 
{

	public static Apartment apartment[];
	public static House house[];
	
	
	public static Float getInletCost( String inletType , Integer masterID ) throws NullPointerException,ArrayIndexOutOfBoundsException
	{
		Float cost = 0.0f;
				
		for( Apartment ap : apartment )
		{
			if( ap.getMasterID() == masterID ) 
			{
				cost = ap.getCostbyType( inletType );
			}
		}
		
		return cost;
	}
	
}
