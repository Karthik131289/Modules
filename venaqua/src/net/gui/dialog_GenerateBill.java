package net.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.alee.global.StyleConstants;
import com.alee.utils.FileUtils;

import net.Details.DataObject;
import net.Details.updateConfiguration;
import net.gui.authentication.Login;
import net.report.waterBill;
import net.swingx.component.XButton;
import net.swingx.component.XComboBox;
import net.swingx.component.XDateField;
import net.swingx.component.XDirectoryChooser;
import net.swingx.component.XFrame;
import net.swingx.component.XLabel;
import net.swingx.component.XPanel;
import net.swingx.utils.DialogOption;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

public class dialog_GenerateBill extends JDialog
{

	private static final long serialVersionUID = 5292407952579841213L;
	private static final String _desktopPath = System.getProperty("user.home")+ File.separator + "Desktop";
	private static final int _dueDateOffset = 10;
	private static final String _BILL_TYPE_INDIVIDUAL = "House( Single )";
	private static final String _BILL_TYPE_CONSOLIDATED = "Consolidated";

	private XFrame parentFrm;
	private Container contentPane;
	private XDirectoryChooser billDirectory = null;
	
	private String billType = _BILL_TYPE_INDIVIDUAL;

	private Vector<String> houseList;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	private final static DecimalFormat df_consumption = new DecimalFormat("0000.000");
	private final static DecimalFormat df_cost = new DecimalFormat("0000.00");
	
	public dialog_GenerateBill( XFrame frm , Vector<String> hList )
	{
		super( frm );
		this.parentFrm = frm; 
		this.houseList = new Vector<String>(hList);
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
			this.setPreferredSize( new Dimension( 350 , 300 ) );
			this.setTitle("Venaqua - Bill Generation");
			this.setIconImages( IconCollections.getAppLogo() );
			this.setResizable(false);
			contentPane = this.getContentPane();
			this.contentPane.setLayout(null);
			{
				XLabel lblHouse = new XLabel("Select House");
				lblHouse.setFont(FontCollections.fontMenu);
				lblHouse.setDrawShade(true);
				lblHouse.setBounds(40, 80, 120, 30);
				contentPane.add(lblHouse);
				
				final XComboBox cboxHouse = new XComboBox( this.houseList );
				cboxHouse.setFont(FontCollections.fontMenu);
				cboxHouse.setBounds(160, 80, 130, 30);
				cboxHouse.setRound(3);
				contentPane.add(cboxHouse);
				
				XLabel lblBillDate = new XLabel("Billing Date");
				lblBillDate.setFont(FontCollections.fontMenu);
				lblBillDate.setDrawShade(true);
				lblBillDate.setBounds(40, 125, 120, 30);
				contentPane.add(lblBillDate);
				
				final XDateField dateFieldBillDate = new XDateField( new Date() );
				dateFieldBillDate.setFont(FontCollections.fontMenu);
				dateFieldBillDate.setDrawShade(true);
				dateFieldBillDate.setHorizontalAlignment(SwingConstants.LEFT);
				dateFieldBillDate.setBounds(160, 125, 130, 30);
				dateFieldBillDate.setDateFormat( dateFormat );
				dateFieldBillDate.setEnabled(false);
				contentPane.add(dateFieldBillDate);
				
				XLabel lblBillLocation = new XLabel("Save Bill in ");
				lblBillLocation.setFont(FontCollections.fontMenu);
				lblBillLocation.setDrawShade(true);
				lblBillLocation.setBounds(40, 170, 120, 30);
				contentPane.add(lblBillLocation);
				
				final XButton butBillDirectory = new XButton("Choose Folder..");
				butBillDirectory.setFont(FontCollections.fontMenu);
				butBillDirectory.setDrawShade(true);
				butBillDirectory.setBounds(160, 170, 130, 30);
				butBillDirectory.setHorizontalAlignment(SwingConstants.LEFT);
				butBillDirectory.addActionListener( new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						setDefaultBillPath();
		                billDirectory.setVisible ( true );

		                if ( billDirectory.getResult () == DialogOption.OK_OPTION )
		                {
		                    File file = billDirectory.getSelectedDirectory ();
		                    butBillDirectory.setIcon ( FileUtils.getFileIcon ( file ) );
		                    butBillDirectory.setText ( FileUtils.getDisplayFileName ( file ) );
		                }
					}
				});
				contentPane.add(butBillDirectory);
				
				XPanel billTypePanel = new XPanel();
				billTypePanel.setLayout(null);
				billTypePanel.setUndecorated(false);
				billTypePanel.setRound( StyleConstants.smallRound );
				billTypePanel.setBounds( 30 , 20 , 280 , 40 );
				contentPane.add( billTypePanel );
				{
					ButtonGroup grp = new ButtonGroup();
					
					final JRadioButton radio_Bill_Individual = new JRadioButton( _BILL_TYPE_INDIVIDUAL , true );
					radio_Bill_Individual.setBounds( 20 , 10 , 130 , 20 );
					billTypePanel.add( radio_Bill_Individual );
					grp.add( radio_Bill_Individual );
					radio_Bill_Individual.addActionListener( new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							billType = radio_Bill_Individual.isSelected()== true ? _BILL_TYPE_INDIVIDUAL : _BILL_TYPE_CONSOLIDATED ;
							cboxHouse.setEnabled( true );
						}
					});
					
					final JRadioButton radio_Bill_Cosolidated = new JRadioButton( _BILL_TYPE_CONSOLIDATED , false );
					radio_Bill_Cosolidated.setBounds( 160 , 10 , 100 , 20 );
					billTypePanel.add( radio_Bill_Cosolidated );
					grp.add( radio_Bill_Cosolidated );
					radio_Bill_Cosolidated.addActionListener( new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							billType = radio_Bill_Cosolidated.isSelected()== true ? _BILL_TYPE_CONSOLIDATED : _BILL_TYPE_INDIVIDUAL ;
							cboxHouse.setEnabled( false );
						}
					});
					
				}
				
				
				XButton but_GenerateBill = new XButton("Generate Bill");
				but_GenerateBill.setFont(FontCollections.fontMenu);
				but_GenerateBill.setDrawShade(true);
				but_GenerateBill.setBounds(120, 215 , 100, 30);
				contentPane.add(but_GenerateBill);
				but_GenerateBill.addActionListener( new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent ae) 
					{
						if( billType.equalsIgnoreCase(_BILL_TYPE_INDIVIDUAL  ) )
							createIndividualHouseBill( dateFieldBillDate , cboxHouse );
						else
							createConsolidatedBill( dateFieldBillDate , cboxHouse );
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
	
	private void setDefaultBillPath() 
	{
		if ( billDirectory == null ) {
            billDirectory = new XDirectoryChooser ( null, "Choose Folder.." );   
			billDirectory.setSelectedDirectory( new File(_desktopPath ) );
		}
	}
	
	private void createConsolidatedBill( XDateField dateFieldBillDate , XComboBox cboxHouse  )
	{
		
	}
	
	private void createIndividualHouseBill( XDateField dateFieldBillDate , XComboBox cboxHouse  ) 
	{		
		Calendar dueDateCal = Calendar.getInstance();
		dueDateCal.setTime( dateFieldBillDate.getDate() );
		dueDateCal.add( Calendar.DATE , _dueDateOffset );
		String billDate = dateFieldBillDate.getDateFormat().format( dateFieldBillDate.getDate() );
		String dueDate  = dateFormat.format( dueDateCal.getTime() ).toString();
		
		waterBill bill = new waterBill();
		
		if( billDirectory == null )
			setDefaultBillPath();
		String rootPath = billDirectory.getSelectedDirectory().getPath();
		String parentFolder = "Water_Bill-"+billDate;
		String pdfName = cboxHouse.getSelectedItem().toString().trim();
		System.out.println( "Path : " + rootPath + " folder : "+ parentFolder + " House : " + pdfName );
		bill.isFileExists( rootPath , parentFolder , pdfName );
		
		bill.initPDF();
		bill.initBillSettings();
		bill.addBillHeaderLayout();	
		bill.addCustomerDetails( DataObject.house[ cboxHouse.getSelectedIndex() ].getOwner() , DataObject.house[ cboxHouse.getSelectedIndex() ].getAddress("\n") );
		
		bill.addBillHeaderDetails( DataObject.house[ cboxHouse.getSelectedIndex() ].getAccountNo() , billDate, dueDate );
		
		//Float preMonthCons  = DataObject.house[ cboxHouse.getSelectedIndex() ].getPreMonthConsumption();
		Float preMonthCons  = DataObject.house[ cboxHouse.getSelectedIndex() ].getBillPreMonthConsumption() ;
		//Float currMonthCons = DataObject.house[ cboxHouse.getSelectedIndex() ].getCurrentMonthConsumption();
		Float currMonthCons = DataObject.house[ cboxHouse.getSelectedIndex() ].getBillCurrMonthConsumption();
		Float totCons		= DataObject.house[ cboxHouse.getSelectedIndex() ].getTotalConsumption();
		//Float dueAmt		= DataObject.house[ cboxHouse.getSelectedIndex() ].getCurrentMonthConsumptionCost();
		Float dueAmt		= DataObject.house[ cboxHouse.getSelectedIndex() ].getBillCurrMonthConsumptionCost();
		bill.addConsumptionDetails( String.valueOf( df_consumption.format( preMonthCons ) ) + " KL" ,
									String.valueOf( df_consumption.format( currMonthCons) ) + " KL" , 
									String.valueOf( df_consumption.format( totCons      ) ) + " KL" , 
									String.valueOf( df_cost.format( dueAmt ) ) + " Rs." );
		
		Vector<String> inletName = new Vector<String>();
		Vector<String> totConsump = new Vector<String>();
		Vector<String> costPerLtr = new Vector<String>();
		Vector<String> totAmt = new Vector<String>();
		for( int i=0 ; i<DataObject.house[cboxHouse.getSelectedIndex() ].getInletCount() ; i++ )
		{
			inletName.add ( DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getInletName() );
			//totConsump.add( DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getCurrentMonthConsumption().toString() );
			totConsump.add( String.valueOf( df_consumption.format( DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getBillCurrMonthConsumption() ) ) );
			costPerLtr.add( String.valueOf( df_cost.format( DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getInletCost() ) ) );
			//totAmt.add    ( DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getCurrentMonthConsumptionCost().toString() );
			totAmt.add    ( String.valueOf( df_cost.format( DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getBillCurrMonthConsumptionCost() ) ) );
		}
		bill.addConsumptionSummaryDetails( inletName , totConsump , costPerLtr , totAmt );
		
		bill.addReturnSlipSummary("www.denvik.in",DataObject.house[ cboxHouse.getSelectedIndex() ].getAccountNo(), billDate , dueDate , String.valueOf( df_cost.format( DataObject.house[ cboxHouse.getSelectedIndex() ].getCurrentMonthConsumptionCost() ) ) +" Rs." );
		
		bill.closePDF();
		
		for( int i=0 ; i<DataObject.house[cboxHouse.getSelectedIndex() ].getInletCount() ; i++ )
		{
			updateConfiguration update = new updateConfiguration();
			update.updateInletData( DataObject.house[ cboxHouse.getSelectedIndex() ].getHouseName() , DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getInletName() , DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getPreMonthConsumption()+"" );
			DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].setBillPreMonthConsumption( DataObject.house[cboxHouse.getSelectedIndex() ].Inlets[i].getPreMonthConsumption() );
		}
		bill.viewPdf();
		
		JOptionPane.showMessageDialog( null , "Bill Generated....!", "Information!", JOptionPane.INFORMATION_MESSAGE );
	}
	
}
