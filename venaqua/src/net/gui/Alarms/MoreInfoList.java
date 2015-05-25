package net.gui.Alarms;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.alee.extended.painter.Painter;
import com.alee.global.StyleConstants;

import net.swingx.component.SeaGlassButtonPainter;
import net.swingx.component.XButton;
import net.swingx.component.XPanel;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Vector;

public class MoreInfoList
{
	//Components Declaration
	public JPanel MoreInfoList;
	private XButton house[];
	private XButton preHouseList;
	private XButton nextHouseList;
	
	
	public MoreInfoList( Vector<String> hList )
	{
		initGUI();
	}

	private void initGUI()
	{
		try
		{
			MoreInfoList = new JPanel( );
			//MoreInfoList.setUndecorated(false);
			//MoreInfoList.setMargin(1);
			MoreInfoList.setSize(1000, 250);
			//MoreInfoList.setRound( StyleConstants.mediumRound );
			MoreInfoList.setLayout(null);
			{
				
				
			}
			
			
		}
		catch( Exception  e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( MoreInfoList , "Error occured while updating House Info Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
	
	public void updateHouseList( Vector<String> hName )
	{
		try
		{
			GridBagConstraints gbc;
			house = new XButton[ hName.size() ];
			Painter painter = new SeaGlassButtonPainter();
			houseButtonAction action = new houseButtonAction();
			
			int i=0;
			for( XButton houseButton : house ) 
			{
				houseButton = new XButton( hName.elementAt(i) , IconCollections.home );
				houseButton.setActionCommand( String.valueOf(i) );
				houseButton.setPainter( painter );
				houseButton.setMoveIconOnPress(false);
				houseButton.addActionListener( action );
				
				gbc = new GridBagConstraints();
				gbc.gridx = (i%10)+1;
				gbc.gridy = (i/10)+1;
				gbc.gridwidth=1;
				gbc.gridheight=1;
				MoreInfoList.add( houseButton , gbc );
				
				i++;
			}
			
			preHouseList = new XButton( "<" );
			preHouseList.setActionCommand( "Previous" );
			preHouseList.setPainter( painter );
			preHouseList.setMoveIconOnPress(false);
			preHouseList.addActionListener( action );
			
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth=1;
			gbc.gridheight=7;
			gbc.anchor = GridBagConstraints.CENTER;
			MoreInfoList.add( preHouseList , gbc );
			
			nextHouseList = new XButton( ">" );
			nextHouseList.setActionCommand( "Next" );
			nextHouseList.setPainter( painter );
			nextHouseList.setMoveIconOnPress(false);
			nextHouseList.addActionListener( action );
			
			gbc = new GridBagConstraints();
			gbc.gridx = 11;
			gbc.gridy = 0;
			gbc.gridwidth=1;
			gbc.gridheight=7;
			gbc.anchor = GridBagConstraints.CENTER;
			MoreInfoList.add( nextHouseList , gbc );
			
			MoreInfoList.repaint();
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( MoreInfoList , "Error occured while updating House List Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
}
