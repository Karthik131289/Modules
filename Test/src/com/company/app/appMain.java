package com.company.app;

import com.company.app.config.ConfigUtill;
import com.company.app.config.Configuration;
import com.company.app.config.IConfiguration;
import com.company.app.config.ISection;
import com.company.app.ui.splash.splashWindow;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class appMain {

    public static final String _COMPANY_NAME = "Company";
    public static final String _APP_NAME = "AppName";
    public static final String _CONFIG_DIR = "config";
    public static final String _PLUGIN_DIR = "plugin";
    public static final String[] _DEFAULT_DIRS = { _CONFIG_DIR , _PLUGIN_DIR };
    public static final String _CFG_FILE_NAME = "appName.cfg";

    private static IConfiguration config;

    public static void main(String[] args) {

        JREUtills.checkJREValidity();
        System.out.println( _APP_NAME + " home dir : " + HomeUtills.getAppPath() );
        HomeUtills.verifyDefaultDirExists();
        HomeUtills.canWriteToDefaultDir();
        initConfig();

        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                splashWindow splahWin = new splashWindow( config );
            }
        });
    }

    private static void initConfig() {
        try {
            String configPath = HomeUtills.getConfigDirPath() + File.separator + _CFG_FILE_NAME;
            ConfigUtill.createConfig( _COMPANY_NAME, _APP_NAME, configPath );
            config = ConfigUtill.getConfig();
            dispConfig();
        } catch ( IOException ie ) {
            ie.printStackTrace();
        }
    }

    private static void dispConfig() {
        System.out.println( "Reading Config file..." );
        String secNames[] = config.getSectionNames();
        for( int i=0; i<secNames.length; i++ ) {
            System.out.println( "[ " + secNames[i] + " ]" );

            ISection sec = config.getSection( secNames[i] );
            String fields[] = sec.getKeys();
            for( int j=0; j<fields.length; j++ ) {
                System.out.println( "\t" + fields[j] + " : " + sec.getKeyValue( fields[j] ) );
            }
        }
    }
}
