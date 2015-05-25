package net.gui.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import net.swingx.component.XToggleButton;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class houseButtonAction implements ActionListener 
{

	@Override
	public void actionPerformed( ActionEvent ae ) 
	{
		try
		{
			XToggleButton togButton = (XToggleButton) ae.getSource();
			final String actionText[] =  togButton.getActionCommand().split(":");
			
			UIUpdate_Home.selectedHomeIndex = Integer.parseInt( actionText[0] );
			UIUpdate_Home.selectedHomeName = actionText[1];
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog( null , "Error occured in House Button Action", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );

		}
	}

}
