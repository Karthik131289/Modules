package net.Details;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class readConfiguration
{
	private final static String _configPath = new File("").getAbsolutePath()+"/config/factory.xml";
	
	private File xml;
	private DocumentBuilderFactory 	factory;
	private DocumentBuilder 		build;
	private Document				doc;
	
	public Apartment apartment;
	public Inlet	 inlet;
	public House     house;
	
	public static void main( String args[] )
	{
		new readConfiguration();
	}
	
	public readConfiguration( ) 
	{
		this.xml = new File( _configPath );
		
		if( this.xml.exists() )
		{
			parseXMLFile( this.xml );
			parseDocument(this.doc);
		}
		
		for( int i=0; i<DataObject.apartment.length; i++ )
		{
			System.out.println( "\nMaster ID : " + DataObject.apartment[i].getMasterID() );
			System.out.println( "\tHCount : " + DataObject.apartment[i].getHouseCount() );
			System.out.println( "\tTypes : " + DataObject.apartment[i].getInletTypes() );
			System.out.println( "\tCost : " + DataObject.apartment[i].getInletCost() );
		}
		
		for( int i=0; i< DataObject.house.length; i++ )
		{
			System.out.println( "\nHName : " + DataObject.house[i].getHouseName() );
			System.out.println( "Inlet Count : " + DataObject.house[i].getInletCount() );
			for( int j=0; j<DataObject.house[i].getInletCount() ; j++ )
				System.out.println( "Inlet Details : " + DataObject.house[i].getInletDetails(j) );

		}
		
	}
	
	private void parseXMLFile( File f )
	{
		try
		{
			this.factory = DocumentBuilderFactory.newInstance();
			this.build 	= factory.newDocumentBuilder();
			this.doc 	= build.parse( xml );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	private void parseDocument( Document doc )
	{
		try
		{
			Element rootElement = null , Apartment = null ;
			NodeList nodeList = null;
			
			rootElement = this.doc.getDocumentElement();
			
			nodeList = rootElement.getElementsByTagName("apartment");
			updateApartmentDetails( nodeList );
			
			nodeList = rootElement.getElementsByTagName("house");
			updateHouseDetails( nodeList );

		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/*private void updateApartmentDetails( NodeList node )
	{
		try
		{
			Integer hCount = 0;
			String type = "";
			Float  cost = 0.0f;
			HashMap<String,Float> lineDetail = new HashMap<String,Float>();
			
			Element element = (Element) node.item(0);
			NodeList childNode = element.getElementsByTagName("housecount");
			
			hCount = Integer.parseInt( ((Element)childNode.item(0)).getAttribute("count") ) ;
			
			
			childNode = element.getElementsByTagName("waterline");
			if( childNode!=null && childNode.getLength()>0 )
			{
				Element childElement;
				for( int i=0; i<childNode.getLength(); i++ )
				{
					childElement = (Element) childNode.item(i);
					type = childElement.getAttribute("type");
					cost = Float.parseFloat( childElement.getAttribute("cost"));
					
					lineDetail.put( type , cost );
				}
			}
			
			DataObject.apartment = new Apartment( hCount , lineDetail );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}*/
	
	private void updateHouseDetails( NodeList node )
	{
		try
		{
			String hName="";
			Integer masterID=0;
			Integer inletCount=0;
			String accountNo="";
			String ownerName="";
			String addressLine1="";
			String addressLine2="";
			Vector<Inlet> inlet = new Vector<Inlet>();
			
			if( node!=null && node.getLength()>0 )
			{
				DataObject.house = new House[ node.getLength() ];
				
				Element element;
				NodeList childNode;
				for( int i=0; i<node.getLength(); i++ )
				{
					element = (Element) node.item(i);
					hName = element.getAttribute("name");
					masterID = Integer.parseInt( element.getAttribute("master") );
					inletCount = Integer.parseInt( element.getAttribute("inletcount") );
					accountNo  = element.getAttribute("accountno");
					ownerName  = element.getAttribute("owner");
					addressLine1  = element.getAttribute("addressline1");
					addressLine2  = element.getAttribute("addressline2");
							
					childNode = element.getElementsByTagName("inlet");
					for( int j=0; j<childNode.getLength(); j++ )
					{
						Element childElement = (Element) childNode.item(j);
						Inlet in = new Inlet( childElement.getAttribute("name"), childElement.getAttribute("watertype") );
						in.setBillPreMonthConsumption( Float.parseFloat( childElement.getAttribute( "billedconsumption" ) ) );
						inlet.add( in );
					}
					
					DataObject.house[i] = new House( hName , masterID , inletCount , accountNo , ownerName , addressLine1 , addressLine2 , inlet );
					
					inlet.removeAllElements();
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	private void updateApartmentDetails( NodeList node )
	{
		try
		{
			HashMap<String,Float> lineDetail = new HashMap<String,Float>();
			
			if( node!=null && node.getLength()>0 )
			{
				DataObject.apartment = new Apartment[ node.getLength() ];
				
				Element element;
				NodeList childNode;
				for( int i=0; i<node.getLength(); i++ )
				{
					element = (Element) node.item(i);
					Integer MasterId = Integer.parseInt( element.getAttribute("id") );
					
					childNode = element.getElementsByTagName("housecount");
					Integer hCount = Integer.parseInt( ((Element)childNode.item(0)).getAttribute("count") ) ;
					
					childNode = element.getElementsByTagName("waterline");
					if( childNode!=null && childNode.getLength()>0 )
					{
						Element childElement;
						for( int j=0; j<childNode.getLength(); j++ )
						{
							childElement = (Element) childNode.item(j);
							String type = childElement.getAttribute("type");
							Float cost = Float.parseFloat( childElement.getAttribute("cost"));						
							lineDetail.put( type , cost );
						}
					}
					
					DataObject.apartment[i] = new Apartment( MasterId , hCount , lineDetail );
					lineDetail.clear();
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

}
