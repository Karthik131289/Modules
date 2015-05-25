package net.Details;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class updateConfiguration 
{
	private final static String _configPath = new File("").getAbsolutePath()+"/config/factory.xml";
	
	private File xml;
	private DocumentBuilderFactory 	factory;
	private DocumentBuilder 		build;
	private Document				doc;
	
	public Apartment apartment;
	public Inlet	 inlet;
	public House     house;
	
	public static void main(String[] args) 
	{
		updateConfiguration uc = new updateConfiguration();
		uc.updateInletData( "House 5", "Inlet 1", "100" );
	}
	
	public updateConfiguration()
	{
		this.xml = new File( _configPath );
		
		if( this.xml.exists() )
		{
			parseXMLFile( this.xml );
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
	
	public void updateInletData( String hName , String inletName , String val )
	{
		try 
		{
			Element rootElm = this.doc.getDocumentElement();
			NodeList houseList = rootElm.getElementsByTagName( "house" );
			
			if( houseList != null && houseList.getLength()>0  )
			{
				/**** Iterate House ****/
				for( int i=0; i<houseList.getLength(); i++ )
				{
					Element house = (Element) houseList.item( i );
					if( house.getAttribute( "name" ).equals( hName ) )
					{
						/*** Iterate Inlet ****/
						NodeList inletList = house.getElementsByTagName( "inlet" );
						if( inletList!=null && inletList.getLength()>0 )
						{
							for( int j=0; j<inletList.getLength(); j++ )
							{
								Element inlet = (Element) inletList.item( j );
								if( inlet.getAttribute( "name" ).equals( inletName ) )
								{
									NamedNodeMap inletAttributes = inlet.getAttributes();
									Node attrBilledCons = inletAttributes.getNamedItem( "billedconsumption" );
									attrBilledCons.setTextContent( val );
									
									updateXML();
								}
							}
						}
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void updateXML()
	{
		try
		{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult( new File( _configPath ) );
			transformer.transform(source, result);
	 
			System.out.println("Done");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
