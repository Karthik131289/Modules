package net.gui.Alarms;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;

import com.alee.global.StyleConstants;

import net.swingx.component.XButton;
import net.swingx.component.XLabel;
import net.swingx.component.XPanel;
import net.swingx.component.XScrollPane;
import net.swingx.component.XTable;
import net.utills.FontCollections;
import net.utills.IconCollections;
import net.utills.StringCollection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.FlowLayout;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class FailureList
{
	//Components Declaration
	public XPanel FailureList;
	static protected XLabel lblMoreFault_Heading;
	private XLabel lblMoreFault_NoFlow;
	private XLabel lblMoreFault_Leakage;
	private XLabel lblMoreFault_BurstPipe;
	private XLabel lblMoreFault_CommFail;
	static protected XLabel lblInletNames[];
	private XButton but_InletNext;
	private XButton but_InletPre;
	
	protected static short pageIndex=0;
	
	public FailureList( Vector<String> hList )
	{
		initGUI();
		initInletList(hList);
	}

	private void initGUI()
	{
		try
		{
			FailureList = new XPanel();
			FailureList.setUndecorated(false);
			FailureList.setMargin(1);
			FailureList.setRound( StyleConstants.mediumRound );
			FailureList.setSize( 1075 , 440 );
			FailureList.setLayout( null );
			{
				
				lblMoreFault_Heading = new XLabel(" Alarm Details - ");
				lblMoreFault_Heading.setBounds(420, 30 , 250, 30);
				lblMoreFault_Heading.setDrawShade(true);
				lblMoreFault_Heading.setFont(FontCollections.fontAlarmInfo_MoreFaults);
				FailureList.add(lblMoreFault_Heading);
				
				lblMoreFault_NoFlow = new XLabel(" No Flow" , IconCollections.inlet_NoFlow);
				lblMoreFault_NoFlow.setBounds(90, 270 , 150, 50);
				lblMoreFault_NoFlow.setToolTipText("No water flow in One of the Inlet for long time.");
				lblMoreFault_NoFlow.setDrawShade(true);
				lblMoreFault_NoFlow.setFont(FontCollections.fontAlarmInfo_MoreFaults);
				FailureList.add(lblMoreFault_NoFlow);
				
				lblMoreFault_Leakage = new XLabel(" Leakage", IconCollections.inlet_Leakage);
				lblMoreFault_Leakage.setBounds(280, 270, 220, 50);
				lblMoreFault_Leakage.setToolTipText("Water Leakage in One of the Inlet for long time.");
				lblMoreFault_Leakage.setDrawShade(true);
				lblMoreFault_Leakage.setFont(FontCollections.fontAlarmInfo_MoreFaults);
				FailureList.add(lblMoreFault_Leakage);
				
				lblMoreFault_BurstPipe = new XLabel(" Burst Pipe", IconCollections.inlet_BurstPipe);
				lblMoreFault_BurstPipe.setBounds(540, 270, 170, 50);
				lblMoreFault_BurstPipe.setToolTipText("One the Inlet's Pipe line is broken for a while.");
				lblMoreFault_BurstPipe.setDrawShade(true);
				lblMoreFault_BurstPipe.setFont(FontCollections.fontAlarmInfo_MoreFaults);
				FailureList.add(lblMoreFault_BurstPipe);
				
				lblMoreFault_CommFail = new XLabel(" Communication Failure", IconCollections.inlet_CommFail);
				lblMoreFault_CommFail.setBounds(750, 270, 240, 50);
				lblMoreFault_CommFail.setToolTipText(" One of the slave device is not responding to Venaqua Master.");
				lblMoreFault_CommFail.setDrawShade(true);
				lblMoreFault_CommFail.setFont(FontCollections.fontAlarmInfo_MoreFaults);
				FailureList.add(lblMoreFault_CommFail);
				
			}
			
			XPanel panel = new XPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), " More Failure Details", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(20, 20, 1045, 130 );
			panel.setUndecorated(false);
			panel.setName( " More Failure Details" );
			//FailureList.add(panel);
			panel.setLayout(null);
			{
				
				
			}
			
			
		}
		catch( Exception  e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( FailureList , "Error occured while updating House Info Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
	
	public void initInletList( final Vector<String> hName )
	{
		try
		{
			lblInletNames = new XLabel[12];
			
			int x=15,y=90;
			
			but_InletPre = new XButton( "<" );
			but_InletPre.setBounds( x, y , 50 , 140 );  
			but_InletPre.setActionCommand( "Previous" );
			but_InletPre.setHorizontalAlignment(JButton.CENTER);
			but_InletPre.setMoveIconOnPress(false);
			but_InletPre.addActionListener( new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					if( pageIndex==0 )
						pageIndex = (short)(hName.size()/12);
					else
						pageIndex--;
				}
			} );
			FailureList.add( but_InletPre );
			
			int i=0;
			x=80;y=110;
			for( XLabel lbl_Inlet : lblInletNames ) 
			{
				if( i<12 )
				{
					x = 80+(5*(i%6))+(150*(i%6));
					y = 110+(10*(i/6))+(40*(i/6));
				}
				
				lbl_Inlet = new XLabel( IconCollections.ok_tick );
				lbl_Inlet.setHorizontalAlignment( JButton.LEFT);
				lbl_Inlet.setBorder( new LineBorder( Color.GRAY , 1 , true ) );
				
				if( i<12 )
				{
					if( i<hName.size() )
						lbl_Inlet.setText( hName.elementAt(i) );
					else
						lbl_Inlet.setText("NA");
					
					lbl_Inlet.setBounds(  x ,  y , 150 , 40 );
					FailureList.add( lbl_Inlet );
				}
				
				lblInletNames[i] = lbl_Inlet;
				i++;
			}
			
			x=1020;y=90;
			but_InletNext = new XButton( ">" );
			but_InletNext.setBounds( x, y , 50 , 140 );  
			but_InletNext.setActionCommand( "Next" );			
			but_InletNext.setHorizontalAlignment(JButton.CENTER);
			but_InletNext.setMoveIconOnPress(false);
			but_InletNext.addActionListener( new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent ae) 
				{
					if( hName.size()>((pageIndex+1)*12) )
						pageIndex++;
						
					else
						pageIndex = 0;
				}
			} );
			FailureList.add( but_InletNext );
			
			FailureList.repaint();
		}
		catch( Exception e )
		{
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( FailureList , "Error occured while updating House List Panel....", StringCollection.Error , JOptionPane.ERROR_MESSAGE, IconCollections.Error );
		}
	}
}
