package com.test.utills;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Vector;

/**
 * Created by DV21 on 12-02-2015.
 */
public class SimpleNodeList extends Vector implements NodeList  {

    public SimpleNodeList(){
        super();
    }
    public void add( Node node ){
        super.add( node );
    }
    public int getLength(){
        return super.size();
    }
    public Node item( int index ){
        return (Node) super.get( index );
    }
}
