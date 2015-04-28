package com.config;

import java.io.File;
import java.io.IOException;

/**
 * Created by DV21 on 27-04-2015.
 */
public class ConfigUtill {
    private static IConfiguration config;

    public static void createConfig( String companyName , String appName , String filePath ) throws IOException {
        File file = new File( filePath );
        System.out.println( file.exists() );
        createConfig( companyName , appName , file );
    }

    public static void createConfig( String companyName , String appName , File file ) throws IOException {
        boolean newFile = !( file.exists() );
        config = new Configuration( file.getPath() , newFile );
    }

    public static IConfiguration getConfig() {
        return config;
    }
}
