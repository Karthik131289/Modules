package com.test.addin;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * Created by DV21 on 24-04-2015.
 */
public class PluginLoader extends URLClassLoader {

    public PluginLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public PluginLoader(URL[] urls) {
        super(urls);
    }

    public PluginLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
