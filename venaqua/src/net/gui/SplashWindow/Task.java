package net.gui.SplashWindow;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import net.Details.readConfiguration;
import net.Details.readSettings;
import net.utills.StringCollection;

public class Task extends SwingWorker<Integer, String>
{
	private int progress =0;
	private JProgressBar progressBar;
	
	public Task( JProgressBar pbar )
	{
		this.progressBar = pbar;
	}
	
	@Override
	protected Integer doInBackground() throws Exception
	{
		setProgress(progress);
		
		while( progress<100 )
		{
			try
			{
				Thread.sleep(10);
				progress++;
				
				if( progress == 50 )
					new readConfiguration();
				else if( progress == 75 )
				{
					readSettings sett = new readSettings();
					sett.parseXMLFile();
					sett.readSettings( sett.processDocument("settings") );
					sett.readAdminSettings( sett.processDocument("admin_settings") );
				}
				
				setProgress(progress);
				publish(progress+"");
				
				
			}
			catch( Exception e )
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog( splashScreen.splash.getContentPane() , "Error occured..." ,StringCollection.Error, JOptionPane.ERROR_MESSAGE);
				
			}
		}
		
		return null;
	}
	
	@Override
	public void process( List<String> value )
	{
		Iterator<String> ie = value.iterator();
		while( ie.hasNext() )
			this.progressBar.setValue( Integer.parseInt( ie.next() ) );
	}
	
	@Override
	protected void done()
	{
		splashScreen.splash.closeSplashScreen();
	}

}
