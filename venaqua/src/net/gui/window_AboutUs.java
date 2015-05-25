package net.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.swingx.component.XImageLabel;
import net.swingx.component.XLabel;
import net.swingx.component.XPanel;
import net.utills.IconCollections;

import com.alee.extended.label.WebLinkLabel;
import com.alee.laf.rootpane.WebDialog;

public class window_AboutUs extends WebDialog
{
	private static final long serialVersionUID = 6043602967767311109L;
	
	private static final String Title = "Venaqua Billing Software";
	private static final String AppVersion = "1.0.0";
	private static final String BuildJDK = "1.8.0(32-bit)";
	private static final String Website = "www.denvik.in";

	public window_AboutUs( JFrame frm ) 
	{
		super(frm);
		try
		{
			this.setPreferredSize( new Dimension(490, 400));
			this.setTitle("About");
			this.setIconImages( IconCollections.getAppLogo() );
			this.setVisible(true);
			this.setFocusableWindowState(true);
			this.setResizable(false);
			this.setLayout( new BorderLayout(0,1));
			{
				XLabel logo = new XLabel( IconCollections.AboutLogo);
				this.getContentPane().add( logo , BorderLayout.CENTER );
				
				XPanel infoPanel = new XPanel();
				infoPanel.setPreferredSize( new Dimension( 490 , 100 ) );
				infoPanel.setBorder( new LineBorder(Color.GRAY, 1));
				infoPanel.setLayout(null);
				this.getContentPane().add( infoPanel , BorderLayout.SOUTH );
				{
					XLabel lblTitle = new XLabel(Title);
					lblTitle.setFont( new Font( Font.SERIF, Font.BOLD, 16 ) );
					lblTitle.setBounds( 15 , 5 , 220, 30 );
					infoPanel.add( lblTitle );
					
					XLabel lblVersion = new XLabel("Released Version - " + AppVersion );
					lblVersion.setFont( new Font( Font.SERIF, Font.PLAIN, 14 ) );
					lblVersion.setBounds( 30 , 35 , 220, 20 );
					infoPanel.add( lblVersion );
					
					XLabel lblBuildJDK = new XLabel("Build Java Version - " + BuildJDK );
					lblBuildJDK.setFont( new Font( Font.SERIF, Font.PLAIN, 14 ) );
					lblBuildJDK.setBounds( 30 , 55 , 220, 20 );
					infoPanel.add( lblBuildJDK );
					
					XLabel lblCurrentJDK = new XLabel("Running Java Version - " + System.getProperty("java.version") + "(" + System.getProperty("sun.arch.data.model") + "-bit)" );
					lblCurrentJDK.setFont( new Font( Font.SERIF, Font.PLAIN, 14 ) );
					lblCurrentJDK.setBounds( 30 , 75 , 290, 20 );
					infoPanel.add( lblCurrentJDK );
					
					WebLinkLabel lblDenvikLogo = new WebLinkLabel();
					lblDenvikLogo.setLink("www.denvik.in");
					lblDenvikLogo.setToolTipText("Click here to goto our website... ");
					lblDenvikLogo.setIcon( IconCollections.AboutDenvikLogo );
					lblDenvikLogo.setText("");
					lblDenvikLogo.setHorizontalAlignment( SwingConstants.RIGHT );
					lblDenvikLogo.setFont( new Font( Font.SERIF, Font.PLAIN, 14 ) );
					lblDenvikLogo.setBounds( 313 , 1 , 170, 65 );
					infoPanel.add( lblDenvikLogo );
					
					XLabel lblWebsite = new XLabel("Website - " + Website );
					lblWebsite.setFont( new Font( Font.SERIF, Font.PLAIN, 14 ) );
					lblWebsite.setBounds( 320 , 70 , 160, 20 );
					infoPanel.add( lblWebsite );
					
				}
			}
			this.pack();
			this.setLocation( (int)frm.getLocation().getX()+(frm.getWidth()/2)-(this.getWidth()/2) ,  (int)frm.getLocation().getY()+(frm.getHeight()/2)-(this.getHeight()/2) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		System.out.println( "loc X--> " + this.getLocation().getX() + " loc Y--> " + this.getLocation().getY() );
	}
}
