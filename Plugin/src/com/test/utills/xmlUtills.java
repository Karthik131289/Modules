package com.test.utills;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by DV21 on 12-02-2015.
 */
public class xmlUtills {

    public static Document createDocument( String filePath ) throws SAXException,ParserConfigurationException,IOException
    {
        File file = new File( filePath );
        return createDocument( file );
    }
    public static Document createDocument( File file ) throws SAXException,ParserConfigurationException,IOException
    {
        BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
        return createDocument( bis );
    }
    public static Document createDocument( InputStream inputStream ) throws SAXException,ParserConfigurationException,IOException
    {
        return createDocument( createInputSource( inputStream ) );
    }
    public static Document createDocument( InputSource inputSource ) throws SAXException,ParserConfigurationException,IOException
    {
        Document doc = null;
        DocumentBuilderFactory factory = createDocumentBuilderFactory();
        DocumentBuilder builder = createDocumentBuilder( factory );
        builder.setEntityResolver( new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource( new StringReader( "" ) );
            }
        });
        doc = builder.parse( inputSource );
        return doc;
    }

    public static InputSource createInputSource( InputStream inputStream )
    {
        return new InputSource( inputStream );
    }

    public static DocumentBuilder createDocumentBuilder( DocumentBuilderFactory factory ) throws ParserConfigurationException
    {
        return factory.newDocumentBuilder();
    }

    public static DocumentBuilderFactory createDocumentBuilderFactory()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory;
    }

    public static Element getDocumentElement( Document doc )
    {
        return doc.getDocumentElement();
    }

    public static NodeList getChildElements( Element element ){
        final SimpleNodeList nodeList = new SimpleNodeList();
        NodeList list = element.getChildNodes();
        int listLength = list.getLength();
        for( int i=0; i<listLength; i++ ) {
            Node elm = list.item( i );
            if ( elm instanceof Element )
                nodeList.add( elm );
        }
        return nodeList;
    }
    public static Node getChildElementByTagName( Element element , String tagName ){
        final SimpleNodeList nodeList;
        Node targetNode = null ;

        nodeList = (SimpleNodeList) getChildElements( element );
        int length = nodeList.getLength();
        for ( int i=0; i<length; i++ )
        {
            Node node = nodeList.item( i );
            if( node.getNodeName().equals( tagName ) )
            {
                targetNode = node;
                break;
            }
        }
        return targetNode;
    }
    public static NodeList getChildElementsByTagName( Element element , String[] tagName ) {
        final SimpleNodeList targetNodeList = new SimpleNodeList();
        NodeList nodeList = getChildElements( element );
        int length = nodeList.getLength();
        for ( int i=0; i<length; i++ )
        {
            Node node = nodeList.item( i );
            for( int j=0; j<tagName.length; j++ ) {
                if ( node.getNodeName().equals( tagName[i] ) )
                    targetNodeList.add( node );
            }
        }
        return nodeList;
    }
}
