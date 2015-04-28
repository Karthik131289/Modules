package com.test;

import com.sun.deploy.util.StringUtils;
import com.test.addin.AddinManager;

public class Main {

    public static void main(String[] args) {

        try
        {
            String pluginDirPath = "D:\\Karthik\\JAVA\\Workspace\\Test\\plugin";
            AddinManager manager = new AddinManager( pluginDirPath );
            manager.initAddins();
            manager.exitAddins();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
