package net.gui.authentication;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import net.swingx.component.XFrame;
import net.swingx.component.XLabel;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;


public class Authentication extends JDialog
{

	private static final long serialVersionUID = -2714037064976381264L;
	
	private XFrame parentFrame;
	private Container contentPane;
	private static XLabel lblIndicator;
	private static Connection connection;
	private static Login login ;

	public Authentication( XFrame parent ) 
	{
		super( parent );
		this.parentFrame = parent;
		initUI();
	}
	
	private void initUI()
	{
		try
		{
			this.addWindowListener( new WindowAdapter( ) 
			{
				public void windowClosed(WindowEvent we )
				{
					System.exit(0);
				}
			});
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setPreferredSize( new Dimension( 550 , 350 ));
			this.setTitle("Venaqua - Authentication");
			this.setIconImages( IconCollections.getAppLogo() );
			this.setResizable(false);
			contentPane = this.getContentPane();
			this.contentPane.setLayout(null);
			{
				JPanel InfoPanel = new JPanel();
				InfoPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Authentication", TitledBorder.LEADING, TitledBorder.TOP, FontCollections.fontAuthenticationHeader ));
				InfoPanel.setForeground(new Color(0, 0, 0));
				InfoPanel.setBounds(20, 30, 175, 250);
				InfoPanel.setLayout(null);
				contentPane.add(InfoPanel);
				{
					XLabel lblLogin = new XLabel("  1 .  Login  ");
					lblLogin.setBounds(50, 45, 110, 30);
					lblLogin.setFont( FontCollections.fontAuthentication );
					lblLogin.setDrawShade(true);
					InfoPanel.add(lblLogin);
					
					XLabel lblConnection = new XLabel("  2 .  Connection  ");
					lblConnection.setBounds(50, 85, 110, 30);
					lblConnection.setFont( FontCollections.fontAuthentication );
					lblConnection.setDrawShade(true);
					InfoPanel.add(lblConnection);
					
					lblIndicator = new XLabel( IconCollections.indicator );
					lblIndicator.setHorizontalAlignment(SwingConstants.CENTER);
					lblIndicator.setBounds(20, 45, 30, 30);
					lblIndicator.setFont( FontCollections.fontAuthentication );
					lblIndicator.setDrawShade(true);
					InfoPanel.add(lblIndicator);
				}
				
				login = new Login();
				login.setLocation(195, 30);
				login.setVisible(true);
				contentPane.add( login );
				
				connection = new Connection( this , this.parentFrame );
				connection.setLocation(195, 30);
				connection.setVisible(false);
				contentPane.add( connection );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();			
			JOptionPane.showInternalMessageDialog( null , "Error occured in GUI Initialization...!", StringCollection.Error, JOptionPane.ERROR_MESSAGE );
		}
	}
	
	public void showDialog( JComponent comp , Point loc )
	{
		this.setVisible(true);
		this.setFocusableWindowState(true);
		this.setLocation( 300 , 250 );
		this.pack();
	}
	
	protected static void changeIndicatorLocation( int loc )
	{
		if( loc==2  )
		{
			lblIndicator.setBounds( 20 , 85 , 30, 30 );
			connection.setVisible(true);
			login.setVisible(false);
		}
		else
		{
			lblIndicator.setBounds( 20 , 45 , 30, 30 );
			connection.setVisible(false);
			login.setVisible(true);
		}
	}
	
	protected void closeDialog()
	{
		this.dispose();
	}
}
