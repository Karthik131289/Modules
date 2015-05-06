package com.company.app;

import javax.swing.*;

/**
 * Created by DV21 on 05-05-2015.
 */
public class JREUtills {

    public static final String _JRE_MIN_VERSION = "1.6.0";
    public static final String _JAVA_VERSION = "java.version";

    public static final void checkJREValidity() {
        System.out.println( "Available JRE : " + getAvailJREVersion().getVersionStr() );
        System.out.println( "Min Reqd JRE : " + getMinJREVersion().getVersionStr() );

        int res = getAvailJREVersion().compare( getMinJREVersion() );
        if( res < 0 )  // not less than or equal to Min JRE
        {
            JOptionPane.showMessageDialog(null, "This application requires JRE " + getMinJREVersion().getVersionStr() + " or above. Attempting to run it with version " + getAvailJREVersion().getVersionStr() + " failed", "Incompatible Java Version", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public static final String getJREVersion() {
        String version = null;
        version = System.getProperty( _JAVA_VERSION );
        version = version.substring( 0 , version.indexOf( "_" ) );      // Removes number after underscore.
        return version;
    }

    public static final Version getMinJREVersion() {
        Version version = new Version( _JRE_MIN_VERSION );
        return version;
    }

    public static final Version getAvailJREVersion() {
        Version version = new Version( getJREVersion() );
        return version;
    }
}
