package com.company.app;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by DV21 on 05-05-2015.
 */
public class HomeUtills {
    public static final Boolean _IS_COMMAND_LINE = Boolean.FALSE;
    public static final String _USER_DIR = "user.dir";

    public static final String getAppPath() {
        String res = null;
        res = System.getProperty( _USER_DIR );
        return res;
    }

    public static final String getConfigDirPath() {
        String res = null;
        res = getAppPath() + File.separator + appMain._CONFIG_DIR;
        return res;
    }

    public static final String getPluginDirPath() {
        String res = null;
        res = getAppPath() + File.separator + appMain._PLUGIN_DIR;
        return res;
    }

    public static final void verifyDefaultDirExists() {
        boolean res = false;
        String homeDir = getAppPath();

        for ( String defaultDir : appMain._DEFAULT_DIRS ) {
            String dirPath = homeDir + File.separator + defaultDir;
            System.out.print("Is dir '" + defaultDir + "' exists : ");
            res = new File( dirPath ).exists();
            System.out.println( res );
            if( !res ) {
                JOptionPane.showMessageDialog( null, "Missing "+ appMain._APP_NAME + " home folder - '" + defaultDir + "' or its contents.\n" +
                                "Check whether " + appMain._APP_NAME + " has been installed correctly.",
                        "Missing App folder", JOptionPane.ERROR_MESSAGE);
                System.exit( -1 );
            }
        }
    }

    public static final void canWriteToDefaultDir() {
        boolean res = false;
        String homeDir = getAppPath();

        for( String defaultDir : appMain._DEFAULT_DIRS ) {
            String dirPath = homeDir + File.separator + defaultDir;
            res = new File( dirPath ).canWrite();
            if( res )
            {
                File temp = new File( dirPath , "dummy.txt" );
                try {
                    temp.createNewFile();
                    res = temp.canWrite();
                    if( res )
                        temp.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println( "Is dir - '" + defaultDir + "' has write access : " + res );
            if( !res ) {
                JOptionPane.showMessageDialog(null, "User Home folder '" + defaultDir + "' does not have write permission.\n" ,
                        "Write Access denied...!", JOptionPane.ERROR_MESSAGE);
                System.exit( -1 );
            }
        }
    }


}
