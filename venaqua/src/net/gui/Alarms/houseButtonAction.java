package net.gui.Alarms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import net.gui.Home.UIUpdate_Home;
import net.swingx.component.XButton;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class houseButtonAction implements ActionListener 
{

	@Override
	public void actionPerformed( ActionEvent ae ) 
	{
		try
		{
			XButton Button = (XButton) ae.getSource();
		
			final String actionText[] =  Button.getActionCommand().split(":");
			
			if(  !actionText[1].equalsIgnoreCase("NA") )
			{
				UIUpdate_Alarms.selectedHouseIndex = Integer.parseInt( actionText[0] );
				UIUpdate_Alarms.selectedHouseName = actionText[1];
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog( null , "Error occured in House Button Action", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );

		}
	}

}
