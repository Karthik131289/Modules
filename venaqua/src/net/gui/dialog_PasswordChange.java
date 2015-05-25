package net.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.alee.extended.image.WebImage;

import net.Details.updateSettings;
import net.gui.authentication.Login;
import net.swingx.component.XButton;
import net.swingx.component.XFrame;
import net.swingx.component.XLabel;
import net.swingx.component.XPasswordField;
import net.swingx.component.XTextField;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class dialog_PasswordChange extends JDialog
{

	private static final long serialVersionUID = 5292407952579841213L;
	
	private XFrame parentFrm;
	private Container contentPane;

	
	public dialog_PasswordChange( XFrame frm )
	{
		super( frm );
		this.parentFrm = frm; 
		
		initGUI();
	}
	
	private void initGUI()
	{
		try
		{
			this.addWindowListener( new WindowAdapter( ) 
			{
				public void windowClosed(WindowEvent we )
				{	
					parentFrm.setEnabled(true);
					parentFrm.setFocusableWindowState(true);
				}
			});
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setPreferredSize( new Dimension( 350 , 250 ) );
			this.setTitle("Venaqua - Change Password");
			this.setIconImages( IconCollections.getAppLogo() );
			this.setResizable(false);
			contentPane = this.getContentPane();
			this.contentPane.setLayout(null);
			{
				XLabel lblUName = new XLabel("User Name");
				lblUName.setFont(FontCollections.fontMenu);
				lblUName.setDrawShade(true);
				lblUName.setBounds(40, 30, 120, 30);
				contentPane.add(lblUName);
				
				XTextField txtUName = new XTextField( Login._USERNAME );
				txtUName.setFont(FontCollections.fontMenu);
				txtUName.setDrawShade(true);
				txtUName.setEditable(false);
				txtUName.setTransferHandler(null);
				txtUName.setBounds(160, 30, 130, 30);
				contentPane.add(txtUName);
				
				XLabel lblPasswordCurrent = new XLabel("Current Password");
				lblPasswordCurrent.setFont(FontCollections.fontMenu);
				lblPasswordCurrent.setDrawShade(true);
				lblPasswordCurrent.setBounds(40, 75, 120, 30);
				contentPane.add(lblPasswordCurrent);
				
				final XPasswordField pwdCurrent = new XPasswordField();
				pwdCurrent.setFont(FontCollections.fontMenu);
				pwdCurrent.setDrawShade(true);
				pwdCurrent.setLeadingComponent( new WebImage(IconCollections.key) );
				pwdCurrent.setTransferHandler(null);
				pwdCurrent.setBounds(160, 75, 130, 30);
				contentPane.add(pwdCurrent);
				
				XLabel lblPasswordNew = new XLabel("New Password");
				lblPasswordNew.setFont(FontCollections.fontMenu);
				lblPasswordNew.setDrawShade(true);
				lblPasswordNew.setBounds(40, 120, 120, 30);
				contentPane.add(lblPasswordNew);
				
				final XPasswordField pwdNew = new XPasswordField();
				pwdNew.setFont(FontCollections.fontMenu);
				pwdNew.setDrawShade(true);
				pwdNew.setLeadingComponent( new WebImage(IconCollections.key) );
				pwdNew.setTransferHandler(null);
				pwdNew.setBounds(160, 120, 130, 30);
				contentPane.add(pwdNew);
				
				XButton but_PWDChange = new XButton("Change");
				but_PWDChange.setFont(FontCollections.fontMenu);
				but_PWDChange.setDrawShade(true);
				but_PWDChange.setBounds(80, 170 , 80, 30);
				contentPane.add(but_PWDChange);
				but_PWDChange.addActionListener( new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						if( (pwdCurrent.getPassword().length==0) || (pwdNew.getPassword().length==0) || (pwdNew.getPassword().length==0)  )
							JOptionPane.showMessageDialog( null , " Please fill all the fields..." , "Error!", JOptionPane.ERROR_MESSAGE);
						else
						{
							
							if( validatePasswordChange( Login._USERNAME , pwdCurrent.getPassword() , pwdNew.getPassword() , pwdNew.getPassword() ) )
								
							{
								updateSettings updateSett = new updateSettings();
								updateSett.parseXMLFile();
								updateSett.modifyUserDetail( updateSett.processDocument("users"), Login._USERNAME , new String( pwdNew.getPassword() ) );
								updateSett.updateXMLFile();
								
								JOptionPane.showMessageDialog( null , "Sucessfully Changed...!", "Information!", JOptionPane.INFORMATION_MESSAGE);
								
								pwdCurrent.setText("");
								pwdNew.setText("");
							}
							else
							{
								pwdCurrent.setText("");
								pwdNew.setText("");
							}
						}

					}
				});
				
				XButton but_PWDClear = new XButton("Cancel");
				but_PWDClear.setFont(FontCollections.fontMenu);
				but_PWDClear.setDrawShade(true);
				but_PWDClear.setBounds(180, 170 , 80, 30);
				contentPane.add(but_PWDClear);
				but_PWDClear.addActionListener( new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						pwdCurrent.setText("");
						pwdNew.setText("");
					}
				});
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( null , "Error occured in GUI Initialization...!", StringCollection.Error, JOptionPane.ERROR_MESSAGE );
		}
	}

	public void showDialog()
	{
		this.setVisible(true);
		this.setFocusableWindowState(true);
		this.pack();
		this.setLocation( (int)parentFrm.getLocation().getX()+(parentFrm.getWidth()/2)-(this.getWidth()/2) ,  (int)parentFrm.getLocation().getY()+(parentFrm.getHeight()/2)-(this.getHeight()/2) );
	}
	
	private boolean validatePasswordChange( String uName , char[] currPWD , char[] newPWD , char[] retypedPWD )
	{
		boolean res = false;
		
		System.out.println( "newPWD : " + Arrays.toString( newPWD ) + " retypedPWD : " + Arrays.toString(retypedPWD) +  " currPWD : " + Arrays.toString(currPWD) +" or Pwd : " + Arrays.toString(Login._PASSWORD ) );
		if( Arrays.equals( newPWD , retypedPWD ) )
		{			
			if(  Arrays.equals(  currPWD , Login._PASSWORD ) )
				res = true;
			else
				JOptionPane.showMessageDialog( null , " Current Password field contains wrong password..." , "Error!", JOptionPane.ERROR_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog( null , " New Password or ReTyped Password contains wrong value..." , "Error!", JOptionPane.ERROR_MESSAGE);
		
		return res;
	}
	
}
