package net.gui.authentication;

import net.swingx.component.XButton;
import net.swingx.component.XLabel;
import net.swingx.component.XPanel;
import net.swingx.component.XPasswordField;
import net.swingx.component.XTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

import com.alee.extended.image.WebImage;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.Details.readSettings;



public class Login extends XPanel
{

	private static final long serialVersionUID = -2532660390307625655L;
	
	public static String _USERNAME = "factory";
	public static char _PASSWORD[] = { 'D','E','N','V','I','K' };

	public Login()
	{
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Login", TitledBorder.CENTER, TitledBorder.TOP, FontCollections.fontAuthenticationHeader , new Color(0, 0, 0)) );
		setSize(320, 250);
		setLayout(null);
		{
			XLabel lblUname = new XLabel(" User Name  : ");
			lblUname.setBounds( 40, 50, 95, 20 );
			lblUname.setFont( FontCollections.fontAuthentication );
			add(lblUname);
			
			XLabel lblPassword = new XLabel(" Password    : ");
			lblPassword.setFont(new Font("Serif", Font.PLAIN, 14));
			lblPassword.setBounds(40, 95, 95, 20);
			add(lblPassword);
			
			final XTextField txtUname = new XTextField();
			txtUname.setBounds( 150, 45, 130, 30 );
			txtUname.setFont( FontCollections.fontAuthentication );
			add(txtUname);
			
			final XPasswordField txtPassword = new XPasswordField();
			txtPassword.setFont(new Font("Serif", Font.PLAIN, 14));
			txtPassword.setBounds(150, 90, 130, 30);
			txtPassword.setLeadingComponent( new WebImage(IconCollections.key) );
			add(txtPassword);
			
			XButton butLogin = new XButton("Login");
			butLogin.setFont(new Font("Serif", Font.PLAIN, 14));
			butLogin.setBounds(50, 150, 100, 30);
            HotkeyManager.registerHotkey ( this, butLogin, Hotkey.ENTER );
			butLogin.addActionListener( new ActionListener() 
			{	
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					if( !txtUname.getText().isEmpty() && !(txtPassword.getPassword().length==0) )
					{
						char defauldPWD[] = null;
						
						readSettings sett = new readSettings();
						sett.parseXMLFile();
						HashMap<String,String> users = new HashMap<String,String>( sett.readUsers( sett.processDocument("users") ) );
						
						if( users.containsKey( txtUname.getText() ) )
						{
							defauldPWD = users.get( txtUname.getText() ).toCharArray() ;
							
							if( Arrays.equals( txtPassword.getPassword() , defauldPWD ) )
							{
								_USERNAME = txtUname.getText();
								_PASSWORD = txtPassword.getPassword();
								
								Authentication.changeIndicatorLocation(2);
							}
							else
								JOptionPane.showMessageDialog( null , "Invalid UserName or Password. \n\t          Please Try Again...", "Error!", JOptionPane.ERROR_MESSAGE );
						}
						else
						{
							JOptionPane.showMessageDialog( null , "Invalid UserName or Password. \n\t          Please Try Again...", "Error!", JOptionPane.ERROR_MESSAGE );
						}
						
						users.clear();
					}
					else
						JOptionPane.showMessageDialog( null , "UserName or Password field is empty. \n\t            Please Try Again...", "Error!", JOptionPane.ERROR_MESSAGE );
				}
			});
			add(butLogin);
			
			XButton butCancel = new XButton("Cancel");
			butCancel.setFont(new Font("Serif", Font.PLAIN, 14));
			butCancel.setBounds(170, 150, 100, 30);
            HotkeyManager.registerHotkey ( this, butCancel, Hotkey.ESCAPE );
			butCancel.addActionListener( new ActionListener() 
			{	
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					txtUname.setText("");
					txtPassword.setText("");
				}
			});
			add(butCancel);
		}
	}
}
