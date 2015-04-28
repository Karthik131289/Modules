package com.plugin;

import com.test.addin.IAddin;

/**
 * Created by DV21 on 24-04-2015.
 */
public class addinMain implements IAddin {
    @Override
    public boolean init() {
        System.out.println( "Addin initialized..." );
        new addinSubClass();
        return true;
    }

    @Override
    public void exit() {
        System.out.println( "exiting addin..." );
    }
}
