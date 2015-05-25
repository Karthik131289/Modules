package net.gui.Home;

import com.alee.global.StyleConstants;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import net.swingx.component.XButton;
import net.swingx.component.XComboBox;
import net.swingx.component.XLabel;
import net.swingx.component.XPanel;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;


public class BreakUpDetails
{
	//Components Declaration
	public XPanel breakupInfo;
	private static XComboBox cbox_Inlet;
	private static XLabel lbl_WaterFlow_val;
	private static XLabel lbl_TotalConsumption_val;
	private static XLabel lbl_CurrentMonthConsumption_val;
	private static XLabel lbl_CurrentMonthCost_val;
	private static XLabel lbl_Watertype;
	private static XLabel lbl_InletStatus;
	
	private final static DecimalFormat df_consumption = new DecimalFormat("0000.000");
	private final static DecimalFormat df_flow = new DecimalFormat("##00.0#");
	private final static DecimalFormat df_cost = new DecimalFormat("0000.00");
		
	public BreakUpDetails( Vector<String> inletName )
	{
		initGUI(inletName);
	}
	
	private void initGUI(  Vector<String> inletName )
	{
		try
		{
			breakupInfo = new XPanel();
			breakupInfo.setUndecorated(false);
			breakupInfo.setSize( 970 , 600 );
			breakupInfo.setMargin(20);
			breakupInfo.setRound( StyleConstants.mediumRound );
			breakupInfo.setLayout(null);
			{
				XLabel title = new XLabel("Break-Up Details");
				title.setBounds(335, 15, 140, 25);
				title.setFont(FontCollections.fontHomeInfo_Header);
				title.setDrawShade(true);
				breakupInfo.add( title );
				
				XLabel lbl_SelectInlet = new XLabel(" Select Inlet Name ");
				lbl_SelectInlet.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_SelectInlet.setDrawShade(true);
				lbl_SelectInlet.setBounds(163,95,150,30); 
				breakupInfo.add(lbl_SelectInlet);
				
				cbox_Inlet = new XComboBox( inletName , 0 );
				cbox_Inlet.setFont(FontCollections.fontHomeInfo_Footer);
				cbox_Inlet.setBounds(313, 95, 120, 30);  
				cbox_Inlet.setRound(3);
				breakupInfo.add(cbox_Inlet);
								
				lbl_WaterFlow_val = new XLabel("00.0");
				lbl_WaterFlow_val.setFont(FontCollections.fontHomeInfo_values);
				lbl_WaterFlow_val.setDrawShade(true);
				lbl_WaterFlow_val.setBounds(545, 40, 185, 100);  
				breakupInfo.add(lbl_WaterFlow_val);
				
				XLabel lbl_WaterFlow = new XLabel(" Water Flow ( lpm )");
				lbl_WaterFlow.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_WaterFlow.setDrawShade(true);
				lbl_WaterFlow.setBounds(550, 135, 140, 30);		
				breakupInfo.add(lbl_WaterFlow);
				
				lbl_TotalConsumption_val = new XLabel("0000.00");
				lbl_TotalConsumption_val.setFont(FontCollections.fontHomeInfo_values);
				lbl_TotalConsumption_val.setDrawShade(true);
				lbl_TotalConsumption_val.setBounds(30, 170, 300, 100); 	
				breakupInfo.add(lbl_TotalConsumption_val);
				
				XLabel lbl_TotalConsumption = new XLabel(" Total Consumption ( Kltrs ) ");
				lbl_TotalConsumption.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_TotalConsumption.setDrawShade(true);
				lbl_TotalConsumption.setBounds(65, 270, 190, 30);		
				breakupInfo.add(lbl_TotalConsumption);
				
				lbl_CurrentMonthConsumption_val = new XLabel("0000.00");
				lbl_CurrentMonthConsumption_val.setFont(FontCollections.fontHomeInfo_values);
				lbl_CurrentMonthConsumption_val.setDrawShade(true);
				lbl_CurrentMonthConsumption_val.setBounds(345, 170, 300, 100);		
				breakupInfo.add(lbl_CurrentMonthConsumption_val);
				
				XLabel lbl_CurrentMonthConsumption = new XLabel(" Current Month Consumption ( Kltrs ) ");
				lbl_CurrentMonthConsumption.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_CurrentMonthConsumption.setDrawShade(true);
				lbl_CurrentMonthConsumption.setBounds(350, 270, 256, 30);			
				breakupInfo.add(lbl_CurrentMonthConsumption);
				
				lbl_CurrentMonthCost_val = new XLabel("0000.0");
				lbl_CurrentMonthCost_val.setFont(FontCollections.fontHomeInfo_values);
				lbl_CurrentMonthCost_val.setDrawShade(true);
				lbl_CurrentMonthCost_val.setBounds(660, 170, 300, 100);				
				breakupInfo.add(lbl_CurrentMonthCost_val);

				XLabel lbl_CurrentMonthCost = new XLabel(" Current Month Cost ( INR ) ");
				lbl_CurrentMonthCost.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_CurrentMonthCost.setDrawShade(true);
				lbl_CurrentMonthCost.setBounds(670, 270, 200, 30);					
				breakupInfo.add(lbl_CurrentMonthCost);
				
				lbl_InletStatus = new XLabel("<html> Inlet Status : <FONT COLOR=GREEN>"+ "Healthy..." +"</FONT></html>");
				lbl_InletStatus.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_InletStatus.setDrawShade(true);
				lbl_InletStatus.setBounds(45, 315, 300, 30);						
				breakupInfo.add(lbl_InletStatus);
				
				lbl_Watertype = new XLabel(" Water Type :  Hot Water ");
				lbl_Watertype.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_Watertype.setDrawShade(true);
				lbl_Watertype.setBounds(667, 315, 200, 30);							
				breakupInfo.add(lbl_Watertype);
				
			}
			
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( breakupInfo , "Error occured while updating Breakup Info Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
	
	protected static void setLiveFlow( Float flow )
	{
		lbl_WaterFlow_val.setText( String.valueOf( df_flow.format(flow) ) );
	}
	protected static void setTotalConsumption( Float cons )
	{
		lbl_TotalConsumption_val.setText( String.valueOf( df_consumption.format(cons) ) );
	}
	protected static void setCurrentMonthConsumption( Float cons )
	{
		lbl_CurrentMonthConsumption_val.setText( String.valueOf( df_consumption.format(cons) ) );
	}
	protected static void setCurrentMonthCost( Float cost )
	{
		lbl_CurrentMonthCost_val.setText( String.valueOf( df_cost.format(cost) ) );
	}
	protected static void setWaterType( String type )
	{
		lbl_Watertype.setText( " Water Type :  " + type );
	}
	protected static void setInletStatus( String status)
	{
		if( status.equalsIgnoreCase("NORMAL") )
			lbl_InletStatus.setText( "<html> Inlet Status : <FONT COLOR=GREEN>"+ status +".... </FONT></html>" );
		else
			lbl_InletStatus.setText( "<html> Inlet Status : <FONT COLOR=RED>"+ status +".... </FONT></html>" );
	}
	@SuppressWarnings("unchecked")
	protected static void updateInletNames( Vector<String> name )
	{
		cbox_Inlet.setModel( new DefaultComboBoxModel<String>(name) );
		cbox_Inlet.setSelectedIndex(0);
	}
	protected static Integer getSelectedInlet()
	{
		return cbox_Inlet.getSelectedIndex();
	}
	
}
