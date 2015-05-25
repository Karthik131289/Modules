package net.gui;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ScrollPaneConstants;

import net.gui.Home.BreakUpDetails;
import net.gui.Home.GeneralInfo;
import net.gui.Home.HouseList;
import net.gui.Home.UIUpdate_Home;
import net.swingx.component.XInternalFrame;
import net.swingx.component.XScrollPane;
import net.swingx.component.XSplitPane;

public class window_Home
{
	Container contentPane;
	XInternalFrame frameHome;
	HouseList hList;
	GeneralInfo houseInfo;
	BreakUpDetails breakupDetails;
		
	Vector<String> houseList;
	Vector<String> inletList;
	
	@SuppressWarnings("static-access")
	public window_Home( Vector<String> hList , Vector<String> inList ) 
	{
		this.houseList = hList;
		this.inletList = inList;
		initGUI();
		
		UIUpdate_Home uiTimer = new UIUpdate_Home();
		this.hList.houseSelection[0].doClick();
		uiTimer.startTimer();
	}
	
	private void initGUI()
	{
		try
		{
			frameHome = new XInternalFrame("Home", false, false, true,false);
			//frameHome.setBorder(new CompoundBorder(new LineBorder(new Color(153, 180, 209), 2, true), new MatteBorder(2, 3, 3, 3, (Color) new Color(185, 209, 234))));
			frameHome.getContentPane().setLayout(new BorderLayout(0, 0));
			frameHome.setPreferredSize( new Dimension(1100, 750));
			frameHome.setFittoFullSize();
			
			contentPane = frameHome.getContentPane();
			{
				
				houseInfo = new GeneralInfo();
				breakupDetails = new BreakUpDetails( inletList );
				
				XSplitPane SpliPane_Vertical = new XSplitPane( XSplitPane.VERTICAL_SPLIT , houseInfo.generalInfo , breakupDetails.breakupInfo );
				SpliPane_Vertical.setOneTouchExpandable(false);
				SpliPane_Vertical.setEnabled(false);
				SpliPane_Vertical.setDividerLocation(250);
				SpliPane_Vertical.setContinuousLayout(true);
				
				hList = new HouseList( houseList );
				
				XScrollPane scroll = new XScrollPane( hList.panel_HouseList );
				scroll.setViewportView(  hList.panel_HouseList  );
				scroll.setPreferredSize( new Dimension( 30,  hList.panel_HouseList .getHeight() ) );
				scroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
				scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
				scroll.setBorder(null);
								
				XSplitPane SpliPane_Horizontal = new XSplitPane( XSplitPane.HORIZONTAL_SPLIT , scroll, SpliPane_Vertical );
				SpliPane_Horizontal.setOneTouchExpandable(false);
				SpliPane_Horizontal.setEnabled(false);
				SpliPane_Horizontal.setDividerLocation(180);
				//SpliPane_Horizontal.setResizeWeight(0);
				SpliPane_Horizontal.setContinuousLayout(true);
				contentPane.add( SpliPane_Horizontal , BorderLayout.CENTER );
			}
			
			frameHome.pack();
			frameHome.setVisible(true);
			frameHome.setSelected(true);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
}
