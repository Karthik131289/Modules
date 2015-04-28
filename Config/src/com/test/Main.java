package com.test;

import com.config.ConfigUtill;
import com.config.IConfiguration;
import com.config.ISection;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String path = Main.class.getResource("designer.cfg").toString().replace( "file:/" , "" );
            System.out.println( path );
            ConfigUtill.createConfig( "comp" , "app" , path );
            IConfiguration config = ConfigUtill.getConfig();

            String[] secNames = config.getSectionNames();
            for( String secName : secNames ) {
                System.out.println( "[" + secName + "]" );
                ISection section = config.findSection( secName );
                String[] keys = section.getKeys();

                for ( String key : keys ) {
                    System.out.print( "\t" + key + "=" + section.getKeyValue( key ) + "\n" );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
