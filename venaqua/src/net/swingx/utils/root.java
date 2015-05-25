package net.swingx.utils;

import com.alee.laf.WebLookAndFeel;

public class root
{
	
	public static void initializeLAF() throws Exception
	{
		javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
	  	WebLookAndFeel.initializeManagers();
	}
}
