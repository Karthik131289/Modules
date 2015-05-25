package net.utills;

import java.awt.Image;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;

import R.IA;

public final class IconCollections 
{
	public static final ImageIcon Logo 		  = new ImageIcon( IconCollections.class.getResource( "icons/VenAquaSplash.jpg" ) );
	public static final ImageIcon AboutLogo   = new ImageIcon( IconCollections.class.getResource( "icons/VenAqua-About.jpg" ) );
	public static final ImageIcon AboutDenvikLogo = new ImageIcon( IconCollections.class.getResource( "icons/Denvik_logo1.jpg" ));
	public static final ImageIcon Error		  = new ImageIcon( IconCollections.class.getResource( "icons/logo.png" ) );
	public static final ImageIcon Warning     = new ImageIcon( IconCollections.class.getResource( "icons/logo.png" ) );
	public static final ImageIcon Information = new ImageIcon( IconCollections.class.getResource( "icons/logo.png" ) );
	public static final ImageIcon DenvikLogo  = new ImageIcon( IconCollections.class.getResource( "icons/Denvik_logo.jpg" ) );
	public static final ImageIcon home		  = new ImageIcon( IconCollections.class.getResource( "icons/home.png" ) );
	public static final ImageIcon error_notify = new ImageIcon( IconCollections.class.getResource( "icons/error_notification.png" ) );
	public static final ImageIcon ok_notify = new ImageIcon( IconCollections.class.getResource( "icons/ok_notification.png" ) );
	public static final ImageIcon ok_tick = new ImageIcon( IconCollections.class.getResource( "icons/ok_tick.png" ) );
	public static final ImageIcon error_tick = new ImageIcon( IconCollections.class.getResource( "icons/error_tick.png" ) );
	public static final ImageIcon backward = new ImageIcon( IconCollections.class.getResource( "icons/backward.png" ) );
	public static final ImageIcon forward = new ImageIcon( IconCollections.class.getResource( "icons/forward.png" ) );
	public static final ImageIcon find = new ImageIcon( IconCollections.class.getResource( "icons/find.png" ) );
	public static final ImageIcon startRefresh= new ImageIcon( IconCollections.class.getResource( "icons/start.png" ) );
	public static final ImageIcon stopRefresh = new ImageIcon( IconCollections.class.getResource( "icons/stop.png" ) );
	public static final ImageIcon key = new ImageIcon( IconCollections.class.getResource( "icons/key.png" ) );
	public static final ImageIcon refresh = new ImageIcon( IconCollections.class.getResource( "icons/Refresh.png" ) );
	public static final ImageIcon indicator = new ImageIcon( IconCollections.class.getResource( "icons/indicator.png" ) );
	
	public static final ImageIcon inlet_Leakage = new ImageIcon( IconCollections.class.getResource( "icons/inlet_Leakage.jpg" ) );
	public static final ImageIcon inlet_Leakage_Notification = new ImageIcon( IconCollections.class.getResource( "icons/inlet_Leakage_Notification.jpg" ) );
	public static final ImageIcon inlet_BurstPipe = new ImageIcon( IconCollections.class.getResource( "icons/inlet_BurstPipe.png" ) );
	public static final ImageIcon inlet_BurstPipe_Notification = new ImageIcon( IconCollections.class.getResource( "icons/inlet_BurstPipe_Notification.png" ) );
	public static final ImageIcon inlet_NoFlow = new ImageIcon( IconCollections.class.getResource( "icons/inlet_NoFlow.jpg" ) );
	public static final ImageIcon inlet_NoFlow_Notification = new ImageIcon( IconCollections.class.getResource( "icons/inlet_NoFlow_Notification.jpg" ) );
	public static final ImageIcon inlet_LowTemp = new ImageIcon( IconCollections.class.getResource( "icons/inlet_LowTemp.png" ) );
	public static final ImageIcon inlet_HighTemp = new ImageIcon( IconCollections.class.getResource( "icons/inlet_HighTemp.png" ) );
	public static final ImageIcon inlet_CommFail = new ImageIcon( IconCollections.class.getResource( "icons/inlet_CommFail.jpg" ) );
	public static final ImageIcon inlet_CommFail_Notification = new ImageIcon( IconCollections.class.getResource( "icons/inlet_CommFail_Notification.jpg" ) );

	public static final ImageIcon inletFailure[] = { ok_tick , inlet_Leakage_Notification, inlet_BurstPipe_Notification , inlet_NoFlow_Notification , inlet_LowTemp , inlet_HighTemp , inlet_CommFail_Notification };
	
	public static final ImageIcon applogo_64 = new ImageIcon( IconCollections.class.getResource( "icons/appLogo_64.jpg" ) );
	public static final ImageIcon applogo_128 = new ImageIcon( IconCollections.class.getResource( "icons/appLogo_128.jpg" ) );
	public static final ImageIcon applogo_256 = new ImageIcon( IconCollections.class.getResource( "icons/appLogo_256.jpg" ) );
	public static final ImageIcon applogo_512 = new ImageIcon( IconCollections.class.getResource( "icons/appLogo_512.jpg" ) );
	
	public static final ImageIcon mnuHome = new ImageIcon( IconCollections.class.getResource( "icons/mnuHome.png" ) );
	public static final ImageIcon mnuRefreshInterval = new ImageIcon( IconCollections.class.getResource( "icons/mnuRefreshInterval.png" ) );
	public static final ImageIcon mnuPassword = new ImageIcon( IconCollections.class.getResource( "icons/mnuPassword.png" ) );
	public static final ImageIcon mnuBill = new ImageIcon( IconCollections.class.getResource( "icons/mnuBill.png" ) );
	public static final ImageIcon mnuAbout = new ImageIcon( IconCollections.class.getResource( "icons/mnuAbout.png" ) );
	

	public static final Vector<Image> getAppLogo()
	{
		Vector<Image> appLogo = new Vector<Image>();
		appLogo.add( applogo_64.getImage() );
		appLogo.add( applogo_128.getImage() );
		appLogo.add( applogo_256.getImage() );
		appLogo.add( applogo_512.getImage() );

		return appLogo;
	}
	


}
