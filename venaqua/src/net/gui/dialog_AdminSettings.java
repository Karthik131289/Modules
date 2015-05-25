package net.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import net.Details.updateSettings;
import net.swingx.component.XButton;
import net.swingx.component.XFrame;
import net.swingx.component.XLabel;
import net.swingx.component.XTextField;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class dialog_AdminSettings extends JDialog 
{	
	private static final long serialVersionUID = 2562011841766452208L;
	
	public static Float _FIXED_MAINTENANCE_COST = 2000.0f;
	public static Float _FIXED_MAINTENANCE_TAX = 2.5f;
	
	private final static DecimalFormat df_cost = new DecimalFormat("0000.00");
	private final static DecimalFormat df_tax = new DecimalFormat("#00.00");

		
	private XFrame parentFrame;
	private Container contentPane;
	private XTextField txt_Maintenance_Cost;
	private XTextField txt_Maintenance_Tax;

	public dialog_AdminSettings( XFrame parent )  
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
					/** To view a updated value when reopening a window **/
					txt_Maintenance_Cost.setText( df_cost.format( _FIXED_MAINTENANCE_COST ) );
					txt_Maintenance_Tax.setText( df_tax.format( _FIXED_MAINTENANCE_TAX ) );
					
					parentFrame.setEnabled(true);
					parentFrame.setFocusableWindowState(true);
				}
			});
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setPreferredSize( new Dimension( 350 , 250 ) );
			this.setTitle("Venaqua - Admin Settings");
			this.setIconImages( IconCollections.getAppLogo() );
			this.setResizable(false);
			contentPane = this.getContentPane();
			this.contentPane.setLayout(null);
			{
				XLabel lbl_Maintenance_Cost = new XLabel("Maintenance Cost");
				lbl_Maintenance_Cost.setFont(FontCollections.fontMenu);
				lbl_Maintenance_Cost.setDrawShade(true);
				lbl_Maintenance_Cost.setBounds(30, 40, 130, 30);
				contentPane.add(lbl_Maintenance_Cost);
				
				txt_Maintenance_Cost = new XTextField( df_cost.format(_FIXED_MAINTENANCE_COST) );
				txt_Maintenance_Cost.setRound(3);
				txt_Maintenance_Cost.setBounds( 160 , 40 , 100 , 30 );
				contentPane.add(txt_Maintenance_Cost);
				
				XLabel lbl_Maintenance_Cost_Unit = new XLabel("INR");
				lbl_Maintenance_Cost_Unit.setFont(FontCollections.fontMenu);
				lbl_Maintenance_Cost_Unit.setDrawShade(true);
				lbl_Maintenance_Cost_Unit.setBounds(280, 40, 60, 30);
				contentPane.add(lbl_Maintenance_Cost_Unit);
				
				XLabel lbl_Maintenance_Tax = new XLabel("Service Tax");
				lbl_Maintenance_Tax.setFont(FontCollections.fontMenu);
				lbl_Maintenance_Tax.setDrawShade(true);
				lbl_Maintenance_Tax.setBounds(30, 90, 130, 30);
				contentPane.add(lbl_Maintenance_Tax);
				
				txt_Maintenance_Tax = new XTextField( df_tax.format(_FIXED_MAINTENANCE_TAX) );
				txt_Maintenance_Tax.setRound(3);
				txt_Maintenance_Tax.setBounds( 160 , 90 , 100 , 30 );
				contentPane.add(txt_Maintenance_Tax);
				
				XLabel lbl_Maintenance_Tax_Unit = new XLabel("%");
				lbl_Maintenance_Tax_Unit.setFont(FontCollections.fontMenu);
				lbl_Maintenance_Tax_Unit.setDrawShade(true);
				lbl_Maintenance_Tax_Unit.setBounds(280, 90, 60, 30);
				contentPane.add(lbl_Maintenance_Tax_Unit);
				
				XButton but_change = new XButton("Change");
				but_change.setFont(FontCollections.fontMenu);
				but_change.setDrawShade(true);
				but_change.setBounds(117, 150, 80, 30);
				contentPane.add(but_change);
				but_change.addActionListener( new ActionListener() 
				{	
					@Override
					public void actionPerformed(ActionEvent ae)
					{
						try
						{
							if( !txt_Maintenance_Cost.getText().isEmpty() && !txt_Maintenance_Tax.getText().isEmpty() )
							{
								_FIXED_MAINTENANCE_COST = Float.parseFloat( txt_Maintenance_Cost.getText().trim() );
								_FIXED_MAINTENANCE_TAX = Float.parseFloat( txt_Maintenance_Tax.getText().trim() );
								
								updateSettings updateSett = new updateSettings();
								updateSett.parseXMLFile();
								updateSett.modifySettings( updateSett.processDocument("admin_settings") , "fixed_maintenance" , df_cost.format( _FIXED_MAINTENANCE_COST ) );
								updateSett.modifySettings( updateSett.processDocument("admin_settings") , "service_tax" , df_tax.format( _FIXED_MAINTENANCE_TAX ) );
								updateSett.updateXMLFile();
								
								JOptionPane.showMessageDialog( null , "Sucessfully Changed...!", StringCollection.Info, JOptionPane.INFORMATION_MESSAGE);

							}
							else
								JOptionPane.showMessageDialog( null , "Error occured... Please check all the fields...!", StringCollection.Error, JOptionPane.ERROR_MESSAGE );
							
							
						}
						catch( Exception e )
						{
							e.printStackTrace();
						}
					}
				});
				
				XLabel Notes = new XLabel("<HTML><pr><b>Note : &nbsp 1 sec = 1000 msec &nbsp&nbsp&nbsp 1 min = 60000 msec</b></pr></HTML>");
				Notes.setFont(FontCollections.fontMenu);
				Notes.setDrawShade(true);
				Notes.setBounds(15, 165, 330, 30);
				//contentPane.add(Notes);
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
		this.setLocation( (int)parentFrame.getLocation().getX()+(parentFrame.getWidth()/2)-(this.getWidth()/2) ,  (int)parentFrame.getLocation().getY()+(parentFrame.getHeight()/2)-(this.getHeight()/2) );
	}
	
}
