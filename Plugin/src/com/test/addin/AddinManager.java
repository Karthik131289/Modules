package com.test.addin;

import com.test.utills.jarUtills;
import com.test.utills.xmlUtills;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by DV21 on 23-04-2015.
 */
public class AddinManager {

    private String addinDirPath = null;
    private File addinDir = null;
    private List<File> jarFilesList;
    private List<Document> documentList;
    private List<AddinInfo> addinInfoList;
    private List<IAddin> addins;
    private List<URL> addinURLs = new ArrayList<URL>();

    private static URLClassLoader addinLoader;

    public AddinManager( String addinDirPath) throws Exception {
        this( new File(addinDirPath) );
    }
    public AddinManager( File dir ) throws Exception {
        this.setAddinDir(dir);
        this.setAddinDirPath(dir.getPath());
        this.getAllJarFiles();
        this.readAllJars();

        URL[] urls = new URL[ this.addinURLs.size() ];
        this.addinURLs.toArray(urls);
        initClassLoader( urls );

        this.readXMLDocuments();
        this.readAddins();
    }

    private void getAllJarFiles() {
        File[] files = this.addinDir.listFiles();

        if( jarFilesList == null )
            jarFilesList = new ArrayList<File>();
        else
            jarFilesList.clear();

        for( File file : files )
            if( file.getName().toLowerCase().endsWith( ".jar" ) )
                this.jarFilesList.add( file );
    }

    private void readAllJars() throws Exception {

        if( documentList == null )
            documentList = new ArrayList<Document>();
        else
            documentList.clear();

        Iterator<File> it = this.jarFilesList.iterator();
        while ( it.hasNext() ) {
            File file = it.next();

            URL url = new URL( "jar:" + file.toURL()+ "!/" );
            System.out.println( url );
            this.addinURLs.add(url);

            JarFile jarFile = jarUtills.getJar( file );
            JarEntry jarEntry = jarUtills.getJarEntry( jarFile , "META-INF/designer.xml" );

            InputStream inputStream = jarFile.getInputStream( jarEntry );
            Document xmlDoc = xmlUtills.createDocument( inputStream );
            documentList.add( xmlDoc );
        }
    }

    private void readXMLDocuments() throws Exception {

        if( addinInfoList == null )
            addinInfoList = new ArrayList<AddinInfo>();
        else
            addinInfoList.clear();

        Iterator<Document> it = this.documentList.iterator();
        while ( it.hasNext() ) {
            Document xml = it.next();
            Element rootElm = xml.getDocumentElement();
            Element addinElm = (Element)xmlUtills.getChildElementByTagName( rootElm , "Addin" );
            Element classElm = (Element)xmlUtills.getChildElementByTagName( addinElm , "Class" );
            String addinName = addinElm.getAttribute( "name" );
            String className = classElm.getTextContent();

            AddinInfo addinInfo = new AddinInfo( addinName , className );
            if( addinInfo != null )
                addinInfoList.add( addinInfo );
        }
    }

    private void readAddins() throws Exception {
        if( addins == null )
            addins = new ArrayList<IAddin>();
        else
            addins.clear();

        Iterator<AddinInfo> it = this.addinInfoList.iterator();
        while ( it.hasNext() ) {
            AddinInfo addinInfo = it.next();
            IAddin addin = addinInfo.getAddinInstance();

            if( addin != null )
                this.addins.add( addin );
        }
    }

    public void initAddins() throws Exception {
        Iterator<IAddin> it = this.addins.iterator();
        while ( it.hasNext() ) {
            IAddin addin = it.next();
            boolean res = addin.init();
            if( !res )
                throw new Exception( "Could not initialize Addin - " + addin.getClass().toString() );
        }
    }

    public void exitAddins() throws Exception {
        Iterator<IAddin> it = this.addins.iterator();
        while ( it.hasNext() ) {
            IAddin addin = it.next();
            addin.exit();
        }
    }

    private static void initClassLoader( URL[] urls ) {
        addinLoader = URLClassLoader.newInstance( urls );
    }

    public static URLClassLoader getAddinLoader() {
        return addinLoader;
    }

    public List<IAddin> getAddins() {
        return addins;
    }

    public List<AddinInfo> getAddinInfo() {
        return addinInfoList;
    }

    public String getAddinDirPath() {
        return addinDirPath;
    }

    public void setAddinDirPath(String addinDirPath) {
        this.addinDirPath = addinDirPath;
    }

    public File getAddinDir() {
        return addinDir;
    }

    public void setAddinDir(File addinDir) {
        this.addinDir = addinDir;
    }

    public List<URL> getAddinURLs() {
        return addinURLs;
    }
}
