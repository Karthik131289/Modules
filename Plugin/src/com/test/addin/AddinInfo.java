package com.test.addin;

import java.net.URLClassLoader;

/**
 * Created by DV21 on 23-04-2015.
 */
public class AddinInfo {
    String addinName = null;
    String className = null;
    Class cls;
    URLClassLoader classLoader;
    IAddin addin;

    public AddinInfo( String addinName , String className ) throws ClassNotFoundException , InstantiationException , IllegalAccessException {
        this.setAddinName( addinName );
        this.setClassName( className );
        this.setClassLoader( AddinManager.getAddinLoader() );

        Class tempCls = this.getClassLoader().loadClass( className );
        this.setCls( tempCls );

        this.createAddinInstance( this.getCls() );
    }

    public void createAddinInstance( Class cls ) throws InstantiationException , IllegalAccessException {
        this.addin = (IAddin) cls.newInstance();
    }

    public IAddin getAddinInstance() {
        return this.addin;
    }

    public String getAddinName() {
        return addinName;
    }

    public void setAddinName(String addinName) {
        this.addinName = addinName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public URLClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader( URLClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
