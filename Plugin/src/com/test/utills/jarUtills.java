package com.test.utills;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by DV21 on 12-02-2015.
 */
public class jarUtills {

    public static JarFile getJar( String path ) throws Exception {
        JarFile jar = null ;
        jar = new JarFile( path );
        return jar;
    }
    public static JarFile getJar( File file ) throws Exception {
        JarFile jar = null;
        jar = new JarFile( file );
        return jar;
    }

    public static Enumeration<JarEntry> getJarEntries( String path ) throws Exception {
        return getJarEntries(getJar(path));
    }
    public static Enumeration<JarEntry> getJarEntries( File file ) throws Exception {
        return getJarEntries(getJar(file));
    }
    public static Enumeration<JarEntry> getJarEntries(JarFile jar) throws Exception {
        Enumeration<JarEntry> contents = null ;
        contents = jar.entries();

        return contents;
    }

    public static JarEntry getJarEntry( JarFile jar , String name ) throws Exception {
        JarEntry entry = null;
        entry = (JarEntry) jar.getEntry( name );

        return entry;
    }
    public static InputStream getJarEntryInputStream( JarFile jar , String name ) throws Exception {
        return getJarEntryInputStream( jar , getJarEntry( jar , name ) );
    }
    public static InputStream getJarEntryInputStream( JarFile jar , JarEntry entry ) throws Exception {
        InputStream inputStream = null;
        inputStream = jar.getInputStream( entry );

        return inputStream;
    }

    public static List<String> getJarEntriesNameList( String path ) throws Exception {
        return getJarEntriesNameList( getJar( path ) );
    }
    public static List<String> getJarEntriesNameList( File file ) throws Exception {
        return getJarEntriesNameList( getJar( file ) );
    }
    public static List<String> getJarEntriesNameList( JarFile jar ) throws Exception {
        return getJarEntriesNameList( getJarEntries( jar ) );
    }
    public static List<String> getJarEntriesNameList( Enumeration<JarEntry> entries ) throws Exception {
        List<String> nameList = null;
        nameList = new Vector<String>();
            while ( entries.hasMoreElements() )
                nameList.add( entries.nextElement().getName() );

        return nameList;
    }

}
