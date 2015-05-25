package net.gui.Home;

import java.awt.FlowLayout;
import java.io.ObjectInputStream.GetField;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.painter.Painter;
import com.alee.global.StyleConstants;

import net.swingx.component.SeaGlassButtonPainter;
import net.swingx.component.XPanel;
import net.swingx.component.XToggleButton;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class HouseList
{	
	// Variable or Object Declarations
	
	//Component Declarations
	public XPanel panel_HouseList;
	public static XToggleButton houseSelection[];
	
	
	public HouseList( Vector<String> hList )
	{
		initGUI();
		updateHouseListPanel(hList);
	}
	
	private void initGUI()
	{
		try
		{
			panel_HouseList = new XPanel();
			panel_HouseList.setUndecorated(false);
			//panel_HouseList.setLayout( new GridLayout( 13 , 1 ,2,0) );
			panel_HouseList.setLayout( new VerticalFlowLayout( FlowLayout.LEFT , 2 , 1 ) );
			panel_HouseList.setMargin(5);
			panel_HouseList.setRound( StyleConstants.mediumRound );
			panel_HouseList.setBorder(null);
			{
				
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( panel_HouseList , "Error occured while updating House List Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
	
	public void updateHouseListPanel( Vector<String> hName )
	{
		try
		{
			ButtonGroup grp = new ButtonGroup();
			houseSelection = new XToggleButton[ hName.size() ];
			houseButtonAction action = new houseButtonAction();
			
			int i=0;
			for( XToggleButton house : houseSelection ) 
			{
				house = new XToggleButton( hName.elementAt(i) , IconCollections.home );
				house.setActionCommand( i + ":" +hName.elementAt(i) );
				house.setAnimate(true);
				house.setHorizontalAlignment( JToggleButton.LEFT );
				house.addActionListener( action );
				panel_HouseList.add( house );
				grp.add( house );
				
				houseSelection[i] = house;
				i++;
			}
			
			panel_HouseList.repaint();
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( panel_HouseList , "Error occured while updating House List Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
	
	protected static void doHouseSelected( int hIndex )
	{
		houseSelection[hIndex].doClick();
	}
	protected static String getHouseName( int hIndex )
	{
		return houseSelection[hIndex].getText() ;
	}

}
