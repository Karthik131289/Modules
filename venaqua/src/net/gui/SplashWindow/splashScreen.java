package net.gui.SplashWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.alee.extended.panel.WebOverlay;

import net.swingx.utils.*;
import net.swingx.component.XImageLabel;
import net.swingx.component.XLabel;
import net.swingx.component.XProgressBar;
import net.gui.window_MDI;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;


public class splashScreen extends JWindow
{
	
	private static final long serialVersionUID = 1L;
	
	protected static splashScreen splash;
	private window_MDI MDIWindow;
	
	private Color     		colorBG;
	
	private XProgressBar	pbar;
	private XLabel status;
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater
		(
			new Runnable()
			{
				public void run()
				{
					splash = new splashScreen();
					splash.setVisible(true);
					
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					splash.setLocation( (dim.width-splash.getWidth())/2 ,  (dim.height-splash.getHeight())/2 );
				}
			}
		);
		
	}
	
	public splashScreen()
	{
		initAttributes();
		initSplashScreen();
		updateSplashScreen();
	}
	
	private void initSplashScreen()
	{
		try
		{
			root.initializeLAF();
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog( null , "Could not initialize Look and Feel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE );
		}
		
		try
		{
			this.setOpacity(1.0f);
			this.setPreferredSize( new Dimension( 500 , 300 ) );
			getContentPane().setLayout(null);
			
			XImageLabel img = new XImageLabel( IconCollections.Logo );
			img.setBounds(0, 0, 500, 277);
			img.setRound(6);
			getContentPane().add( img );
			
			/*XLabel imgLabel = new XLabel( "Initializing....",IconCollections.Logo , JLabel.CENTER );
			imgLabel.setBounds(0, 0, 500, 277);
			imgLabel.setVerticalTextPosition( JLabel.BOTTOM );
			imgLabel.setHorizontalTextPosition( JLabel.LEADING );
			imgLabel.setFont( FontCollections.fontStatusBar );
			imgLabel.setForeground( Color.RED );
			//getContentPane().add( imgLabel );
			 */			
			
			status = new XLabel("Intializing...");
			status.setBounds(0, 252, 500, 25);
			status.setForeground( Color.RED );
			status.setBackground(Color.BLACK);
			getContentPane().add( status );
			
			/*WebOverlay overlayPanel = new WebOverlay();
			overlayPanel.setBounds( 0 , 0 , 500 , 277 );
			overlayPanel.setComponent( status );
			overlayPanel.addOverlay( img , SwingUtilities.LEADING , SwingUtilities.TOP );
			overlayPanel.setComponentMargin( -1 , -1, 0 , img.getPreferredSize().width );
			getContentPane().add( overlayPanel );*/
			
			pbar = new XProgressBar();
			pbar.setBounds(0, 277, 500, 23);
			pbar.setBorderPainted(false);
			pbar.setStringPainted(true);
			pbar.setIndeterminate(false);
			pbar.setOpaque(false);
			pbar.setRound(6);
			getContentPane().add( pbar );
			
			
			this.pack();
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog( null , "Error occured while initializing Splash screen... ", StringCollection.Error , JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
	protected void closeSplashScreen()
	{
		MDIWindow = new window_MDI();
		
		this.setVisible(false);
		this.setFocusableWindowState(false);
		
		MDIWindow.frmVenaqua.setVisible(true);
		MDIWindow.frmVenaqua.setLocationRelativeTo(this);
		MDIWindow.frmVenaqua.setFocusableWindowState( true );
	}
	
	protected void updateSplashScreen( )
	{
		Task task = new Task( this.pbar );
		task.execute();
	}
	
	private void initAttributes()
	{
		colorBG = new Color( 100 , 149 , 237 , 50 );  // blue
		//colorBG = new Color( 0 , 0 , 0 , 125 );      // black
	}

}
