package net.gui.Home;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.alee.extended.label.WebLinkLabel;
import com.alee.global.StyleConstants;

import net.SerialPortComm.SerialPortReader;
import net.swingx.component.XButton;
import net.swingx.component.XImageLabel;
import net.swingx.component.XLabel;
import net.swingx.component.XPanel;
import net.swingx.component.XProgressOverlay;
import net.swingx.component.XSearchField;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JPanel;

public class GeneralInfo
{
	//Components Declaration
	public XPanel generalInfo;
	private static XLabel title ;
	private static XLabel lbl_OverallConsumption_val;
	private static XLabel lbl_CurrentMonthConsumption_val ;
	private static XLabel lbl_CurrentMonthCost_val ;
	
	private final static DecimalFormat df_consumption = new DecimalFormat("0000.000");
	private final static DecimalFormat df_cost = new DecimalFormat("0000.00");

	public GeneralInfo()
	{
		initGUI();
	}

	private void initGUI()
	{
		try
		{
			generalInfo = new XPanel();
			generalInfo.setUndecorated(false);
			generalInfo.setMargin(1);
			generalInfo.setSize(970, 270);
			generalInfo.setRound( StyleConstants.mediumRound );
			generalInfo.setLayout(null);
			{
				title = new XLabel("General Information ( Home 1 )");
				title.setBounds(297, 34, 262, 24);
				title.setFont(FontCollections.fontHomeInfo_Header);
				title.setDrawShade(true);
				generalInfo.add( title );
				
				lbl_OverallConsumption_val = new XLabel("0000.00");
				lbl_OverallConsumption_val.setBounds(30, 95, 300, 100);
				lbl_OverallConsumption_val.setFont(FontCollections.fontHomeInfo_values);
				lbl_OverallConsumption_val.setDrawShade(true);
				generalInfo.add(lbl_OverallConsumption_val);
				
				lbl_CurrentMonthConsumption_val = new XLabel("0000.00");
				lbl_CurrentMonthConsumption_val.setBounds(345, 95, 300, 100);
				lbl_CurrentMonthConsumption_val.setFont(FontCollections.fontHomeInfo_values);
				lbl_CurrentMonthConsumption_val.setDrawShade(true);
				generalInfo.add(lbl_CurrentMonthConsumption_val);
				
				lbl_CurrentMonthCost_val = new XLabel("0000.0");
				lbl_CurrentMonthCost_val.setBounds(660, 95, 300, 100);
				lbl_CurrentMonthCost_val.setFont(FontCollections.fontHomeInfo_values);
				lbl_CurrentMonthCost_val.setDrawShade(true);
				generalInfo.add(lbl_CurrentMonthCost_val);
				
				XLabel lbl_OverallConsumption_Unit = new XLabel("( Kltrs )");
				lbl_OverallConsumption_Unit.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_OverallConsumption_Unit.setDrawShade(true);
				//generalInfo.add(lbl_OverallConsumption_Unit, gbc);
				
				XLabel lbl_CurrentMonthConsumption_Unit = new XLabel("( Kltrs )");
				lbl_CurrentMonthConsumption_Unit.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_CurrentMonthConsumption_Unit.setDrawShade(true);
				//generalInfo.add(lbl_CurrentMonthConsumption_Unit, gbc);
				
				XLabel lbl_CurrentMonthCost_Unit = new XLabel("( INR )");
				lbl_CurrentMonthCost_Unit.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_CurrentMonthCost_Unit.setDrawShade(true);
				//generalInfo.add(lbl_CurrentMonthCost_Unit, gbc);
				
				XLabel lbl_OverallConsumption = new XLabel(" Over All Consumption ( Kltrs ) ");
				lbl_OverallConsumption.setBounds(52, 206, 212, 22);
				lbl_OverallConsumption.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_OverallConsumption.setDrawShade(true);
				generalInfo.add(lbl_OverallConsumption);
				
				XLabel lbl_CurrentMonthConsumption = new XLabel(" Current Month Consumption ( Kltrs ) ");
				lbl_CurrentMonthConsumption.setBounds(350, 206, 256, 22);
				lbl_CurrentMonthConsumption.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_CurrentMonthConsumption.setDrawShade(true);
				generalInfo.add(lbl_CurrentMonthConsumption);
				
				XLabel lbl_CurrentMonthCost = new XLabel(" Current Month Cost ( INR ) ");
				lbl_CurrentMonthCost.setBounds(670, 206, 212, 22);
				lbl_CurrentMonthCost.setFont(FontCollections.fontHomeInfo_Footer);
				lbl_CurrentMonthCost.setDrawShade(true);
				generalInfo.add(lbl_CurrentMonthCost);
				
				
				final XSearchField searchHome = new XSearchField(true);
				final XButton searchButton = new XButton(IconCollections.find);

				searchHome.setBounds(716, 10, 175, 30);
				searchHome.setFont( FontCollections.fontMenu );
				searchHome.setRound(6);
				searchHome.setTrailingComponent( searchButton );
				searchHome.addKeyListener( new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent ke) {		}
					
					@Override
					public void keyReleased(KeyEvent e) {

						if( e.getKeyCode() == KeyEvent.VK_ENTER )
							searchButton.doClick();
					}
					
					@Override
					public void keyPressed(KeyEvent e) {	}
				});
				generalInfo.add( searchHome );
				
				searchButton.setUndecorated(true);
				searchButton.addActionListener( new ActionListener() 
				{	
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						String data = searchHome.getText();
						if( !data.isEmpty() )
						{
							for( int i=0; i<HouseList.houseSelection.length; i++ )
							{
								if( HouseList.getHouseName(i).equalsIgnoreCase(data) )
									HouseList.doHouseSelected(i);
							}
						}
					}
				});
				
				
				
				final XProgressOverlay Refresh = new XProgressOverlay();
				Refresh.setConsumeEvents(false);
				
				final XButton refreshButton = new XButton(" Click to Sync. " , IconCollections.startRefresh );
				refreshButton.setRound(10);
				refreshButton.addActionListener( new ActionListener() 
				{	
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						boolean showLoad = !Refresh.isShowLoad();
						
						// Changing progress visibility
		                Refresh.setShowLoad ( showLoad );

		                // Changing buttons text and icons
		                refreshButton.setText ( showLoad ? "Click to Stop" : "Click to Sync." );
		                refreshButton.setIcon ( showLoad ? IconCollections.stopRefresh : IconCollections.startRefresh  );
		                
		                if(showLoad) 
		                {
		                	SerialPortReader.resume();
		                	SerialPortReader.restartUpdateTimer();
		                }
		                else
		                {
		                	SerialPortReader.pause();
		                	SerialPortReader.restartUpdateTimer();
		                }
					}
				});
				Refresh.setComponent( refreshButton );
				Refresh.setBounds(10, 10, 120, 30);
				Refresh.setUndecorated(true);
				generalInfo.add( Refresh );

			}
		}
		catch( Exception  e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( generalInfo , "Error occured while updating House Info Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
	
	protected static void setHouseName( String name )
	{
		
		title.setText( "General Information ( "+ name +" )" );
	}
	protected static void setOverAllConsumption( Float num)
	{
		
		lbl_OverallConsumption_val.setText( String.valueOf( df_consumption.format(num) ) );
	}
	protected static void setCurrentMonthConsumption( Float num )
	{
		
		lbl_CurrentMonthConsumption_val.setText( String.valueOf( df_consumption.format(num) ) );
	}
	protected static void setCurrentMonthCost( Float num )
	{
		
		lbl_CurrentMonthCost_val.setText( String.valueOf( df_cost.format(num) ) );
	}
}
