package net.SerialPortComm;

public interface DataBlock 
{
	public static final Integer SIZE_BASEBLOCK = 21;
	public static final Integer ADDR_HOUSECOUNT = 1;
	public static final Integer ADDR_WATERTYPE1 = 2;
	public static final Integer ADDR_WATERTYPE2 = 6;
	public static final Integer ADDR_WATERTYPE3 = 10;
	public static final Integer ADDR_WATERTYPE4 = 14;
	public static final Integer ADDR_COST_TYPE1 = 18;
	public static final Integer ADDR_COST_TYPE2 = 19;
	public static final Integer ADDR_COST_TYPE3 = 20;
	public static final Integer ADDR_COST_TYPE4 = 21;
	public static final Integer QTY_HOUSECOUNT = 1;
	public static final Integer QTY_WATERTYPE1 = 4;
	public static final Integer QTY_WATERTYPE2 = 4;
	public static final Integer QTY_WATERTYPE3 = 4;
	public static final Integer QTY_WATERTYPE4 = 4;
	public static final Integer QTY_COST_TYPE1 = 1;
	public static final Integer QTY_COST_TYPE2 = 1;
	public static final Integer QTY_COST_TYPE3 = 1;
	public static final Integer QTY_COST_TYPE4 = 1;
	
	public static final Integer SIZE_HOUSE_HEADER = 5;
	public static final Integer ADDR_HOUSE_START = 22;
	public static final Integer QTY_HOUSE_NAME = 4;
	public static final Integer QTY_HOUSE_INLETCOUNT = 1;
	
	public static final Integer SIZE_INLETBLOCK = 13;
	public static final Integer QTY_INLET_NAME = 2;
	public static final Integer QTY_INLET_TYPE = 1;
	public static final Integer QTY_INLET_FLOW = 1;
	public static final Integer QTY_INLET_CONSUM_PREMONTH_END = 4;
	public static final Integer QTY_INLET_CONSUM_OVERALL = 4;
	public static final Integer QTY_INLET_FAULT = 1;

}
