package net.gui.Alarms;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.alee.global.StyleConstants;

import net.Details.DataObject;
import net.swingx.component.XButton;
import net.swingx.component.XPanel;
import net.utills.IconCollections;
import net.utills.StringCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class HouseList
{
	//Components Declaration
	public XPanel houseList;
	public static XButton house[];
	private XButton preHouseList;
	private XButton nextHouseList;
	
	private static short pageIndex = 0;
	
	public HouseList( Vector<String> hList )
	{
		initGUI();
		updateHouseList(hList);
		
	}

	private void initGUI()
	{
		try
		{
			houseList = new XPanel();
			houseList.setUndecorated(false);
			houseList.setMargin(1);
			houseList.setSize( 1075, 250 );
			houseList.setPaintFocus(false);
			houseList.setRound( StyleConstants.mediumRound );
			houseList.setLayout( null );
			{
				
				
			}
			
			
		}
		catch( Exception  e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( houseList , "Error occured while updating House Info Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
	
	protected static int getPageIndex()
	{
		return pageIndex;
	}
	
	public void updateHouseList( final Vector<String> hName )
	{
		try
		{
			//house = new XButton[ hName.size() ];
			house = new XButton[30];
			houseButtonAction action = new houseButtonAction();
			
			int x=15,y=15;
			
			preHouseList = new XButton( "<" );
			preHouseList.setBounds( x, y , 50 , 220 );
			preHouseList.setActionCommand( "Previous" );
			preHouseList.setHorizontalAlignment(JButton.CENTER);
			preHouseList.setMoveIconOnPress(false);
			preHouseList.addActionListener( new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					if( pageIndex==0 )
						pageIndex = (short)(hName.size()/30);
					else
						pageIndex--;
					
					for( int i=0; i<30; i++ )
					{
						if( i<( hName.size()-(pageIndex*30) ) )
						{
							house[i].setText( hName.elementAt( (pageIndex*30)+i ) );
							house[i].setIcon( DataObject.house[ (pageIndex*30)+i ].hasFault() ? IconCollections.error_tick : IconCollections.ok_tick );
							house[i].setActionCommand( ( (pageIndex*30)+i ) + ":" + hName.elementAt( (pageIndex*30)+i ) );
						}
						else
						{
							house[i].setText("NA");
							house[i].setIcon( IconCollections.ok_tick );
							house[i].setActionCommand( ( (pageIndex*30)+i ) + ":NA" );
						}
					}
					
				}
			} );
			houseList.add( preHouseList );
			
			int i=0;
			x=80;y=15;
			for( XButton houseButton : house ) 
			{
				if( i<30 )
				{
					x = 80+(5*(i%6))+(150*(i%6));
					y = 15+(5*(i/6))+(40*(i/6));
				}
				
				houseButton = new XButton( IconCollections.ok_tick );
				houseButton.setHorizontalAlignment( JButton.LEFT);
				houseButton.addActionListener( action );
				
				if( i<30 )
				{
					if( i<hName.size() )
					{
						houseButton.setText( hName.elementAt(i) );
						houseButton.setActionCommand( ( (pageIndex*30)+i ) + ":" + hName.elementAt(i) );
					}
					else
					{
						houseButton.setText("NA");
						houseButton.setActionCommand( ( (pageIndex*30)+i ) + ":NA" );
					}
					
					houseButton.setBounds(  x ,  y , 150 , 40 );
					houseList.add( houseButton );
				}
				
				house[i] = houseButton;
				i++;
			}
			
			x=1020;y=15;
			nextHouseList = new XButton( ">" );
			nextHouseList.setBounds( x, y , 50 , 220 );
			nextHouseList.setActionCommand( "Next" );			
			nextHouseList.setHorizontalAlignment(JButton.CENTER);
			nextHouseList.setMoveIconOnPress(false);
			nextHouseList.addActionListener( new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					if( hName.size()>((pageIndex+1)*30) )
						pageIndex++;
						
					else
						pageIndex = 0;
						
					for( int i=0; i<30; i++ )
					{
						if( i<( hName.size()-(pageIndex*30) ) )
						{
							house[i].setText( hName.elementAt( (pageIndex*30)+i ) );
							house[i].setIcon( DataObject.house[ (pageIndex*30)+i ].hasFault() ? IconCollections.error_tick : IconCollections.ok_tick );
							house[i].setActionCommand( ( (pageIndex*30)+i ) + ":" + hName.elementAt( (pageIndex*30)+i ) );
						}
						else
						{
							house[i].setText("NA");
							house[i].setIcon( IconCollections.ok_tick );
							house[i].setActionCommand( ( (pageIndex*30)+i ) + ":NA" );
						}
					}
				}
			} );
			houseList.add( nextHouseList );
			
			houseList.repaint();
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( houseList , "Error occured while updating House List Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
}
