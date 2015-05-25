package net.Details;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import net.gui.dialog_AdminSettings;
import net.gui.dialog_FactorySettings;



public class readSettings 
{
	protected final static String _settingsPath = new File("").getAbsolutePath()+"/config/settings.xml";

	private File xml;
	private DocumentBuilderFactory 	factory;
	private DocumentBuilder 		build;
	private Document				doc;
	
	public static void main( String args[] )
	{
		readSettings sett = new readSettings();
		sett.parseXMLFile();
		
		sett.readSettings( sett.processDocument( "settings" ) );
		sett.readUsers( sett.processDocument("users") );
		sett.readAdminSettings( sett.processDocument( "admin_settings" ) );
	}

	public void parseXMLFile()
	{
		try
		{
			this.xml = new File( _settingsPath );
			this.factory = DocumentBuilderFactory.newInstance();
			this.build 	= factory.newDocumentBuilder();
			this.doc 	= build.parse( xml );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public NodeList processDocument( String nodeName )
	{
		NodeList nodeList = null;
		
		try
		{
			Element rootElement = null;
			
			rootElement = this.doc.getDocumentElement();
			
			nodeList = rootElement.getElementsByTagName( nodeName );
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		
		return nodeList;
	}
	
	public void readSettings( NodeList nodelist )
	{
		try
		{
			Element element;
			
			if( nodelist!=null && nodelist.getLength()>0 )
			{
				Element settingsElem = (Element)nodelist.item(0);
				
				/** Refresh Interval **/
				element = (Element)settingsElem.getElementsByTagName("refreshinterval").item(0);
				dialog_FactorySettings._REFRESH_INTERVAL = Integer.parseInt( element.getTextContent() ) ;
				
				/** Polling Delay **/
				element = (Element)settingsElem.getElementsByTagName("poll_delay").item(0);
				dialog_FactorySettings._POLL_DELAY = Integer.parseInt( element.getTextContent() ) ;
				
				System.out.println( "_POLL_DELAY : " + dialog_FactorySettings._POLL_DELAY );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public HashMap<String,String> readUsers( NodeList nodelist )
	{
		HashMap<String,String> usr = new HashMap<String,String>();
		try
		{
			NodeList usrNodes;
			Element  usrElement;
			
			if( nodelist!=null && nodelist.getLength()>0 )
			{
				Element UsersElem = (Element)nodelist.item(0);
				
				/** User Details Uname & Pwd **/
				usrNodes = UsersElem.getElementsByTagName("user");
				
				if( usrNodes !=null )
				{
					for( int i=0; i<usrNodes.getLength(); i++ )
					{
						usrElement = (Element)usrNodes.item(i);
						
						usr.put( usrElement.getAttribute("uname") , usrElement.getAttribute("pwd") );
					}
				}
			}
			
			System.out.println( " User Details : " + usr );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		return usr;
	}
	
	public void readAdminSettings( NodeList nodelist )
	{
		try
		{
			Element element;
			
			if( nodelist!=null && nodelist.getLength()>0 )
			{
				Element settingsElem = (Element)nodelist.item(0);
				
				element = (Element)settingsElem.getElementsByTagName("fixed_maintenance").item(0);
				dialog_AdminSettings._FIXED_MAINTENANCE_COST = Float.parseFloat( element.getTextContent() ) ;
				
				element = (Element)settingsElem.getElementsByTagName("service_tax").item(0);
				dialog_AdminSettings._FIXED_MAINTENANCE_TAX = Float.parseFloat( element.getTextContent() ) ;
				
				System.out.println( "_FIXED_MAINTENANCE_COST : " + dialog_AdminSettings._FIXED_MAINTENANCE_COST );
				System.out.println( "_FIXED_MAINTENANCE_TAX : " + dialog_AdminSettings._FIXED_MAINTENANCE_TAX );
				
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
