package net.Details;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class updateSettings
{
	
	private File xml;
	
	private DocumentBuilderFactory 	factory;
	private DocumentBuilder 		build;
	private Document				doc;
	
	private TransformerFactory      transFactory;
	private Transformer				transform;
	private DOMSource				domSrc;
	private StreamResult			streamRes;
	

	public void parseXMLFile()
	{
		try
		{
			this.xml = new File( readSettings._settingsPath );
			this.factory = DocumentBuilderFactory.newInstance();
			this.build 	= factory.newDocumentBuilder();
			this.doc 	= build.parse( xml );
		} catch( SAXException e )
		{
			e.printStackTrace();
		} catch( IOException e )
		{
			e.printStackTrace();
		} catch( ParserConfigurationException e )
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
	
	public void modifySettings( NodeList settingsNode , String nodeName, String newVal )
	{
		try
		{
			Element settElem = (Element)settingsNode.item(0);
			Node childNode = (Node)settElem.getElementsByTagName( nodeName ).item(0);
			
			childNode.setTextContent( newVal );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	public void modifyUserDetail( NodeList usersNode , String uName , String newPwd )
	{
		try
		{
			Element settElem = (Element)usersNode.item(0);
			NodeList childNodes = settElem.getElementsByTagName( "user" );
			
			for( int i=0; i<childNodes.getLength() ; i++ )
			{
				Element elemUsr = (Element)childNodes.item(i);
		
				String uname = elemUsr.getAttribute("uname");
				String pwd   = elemUsr.getAttribute("pwd");
				
				System.out.println( "uname : " + uname + " pwd : " + pwd );
				if( uname.equalsIgnoreCase( uName ) )
				{
					elemUsr.getAttributes().getNamedItem("pwd").setTextContent( newPwd );
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public void updateXMLFile()
	{
		try
		{
			transFactory = TransformerFactory.newInstance();
			transform    = transFactory.newTransformer();
			domSrc       = new DOMSource( this.doc );
			streamRes    = new StreamResult( this.xml );
			
			transform.transform( domSrc , streamRes );
			System.out.println( "Done...!" );
		}
		catch( TransformerException  e )
		{
			e.printStackTrace();
		}
	}
	
}
