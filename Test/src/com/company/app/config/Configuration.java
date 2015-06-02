package com.company.app.config;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by DV21 on 27-04-2015.
 */
public class Configuration implements IConfiguration {

    private String configPath;
    private Map<String,ISection> sections = Collections.synchronizedMap( new LinkedHashMap<String, ISection>() );

    public Configuration( String path, boolean newFile ) throws IOException {
        this.configPath = path;
        if( newFile )
            createNewConfig();
        else
            openExistingConfig();
    }

    private void createNewConfig() {
        removeAllSection();
    }

    private void openExistingConfig() throws IOException {
        removeAllSection();
        FileInputStream fis = new FileInputStream( this.configPath );
        try {
            InputStreamReader isr = new InputStreamReader( fis );
            BufferedReader reader = new BufferedReader( isr );
            read( reader );
        } finally {
            fis.close();
        }
    }

    private void addKeyValue( ISection sec , String key , String val ) {
        sec.addKeyValue( key , val );
    }

    @Override
    public ISection createSection( String secName ) {
        ISection sec = new Section( secName , this );
        sections.put( secName , sec );
        return sec;
    }
    @Override
    public ISection getSection(String secName) {
        ISection sec = findSection( secName );
        if( sec == null )
            sec = createSection( secName );
        return sec;
    }

    @Override
    public ISection findSection(String secName) {
        ISection sec = (ISection) sections.get( secName );
        return sec;
    }

    @Override
    public boolean removeSection(String secName) {
        Object sec = sections.remove( secName );
        return (sec!=null ? true : false) ;
    }

    @Override
    public int getSectionCount() {
        return this.sections.size();
    }

    @Override
    public String[] getSectionNames() {
        String[] ret = new String[ this.getSectionCount() ];
        this.sections.keySet().toArray( ret );
        return ret;
    }

    @Override
    public ISection[] getSections() {
        ISection[] ret = new ISection[ this.getSectionCount() ];
        this.sections.values().toArray( ret );
        return ret;
    }

    @Override
    public void removeAllSection() {
        sections.clear();
    }

    @Override
    public synchronized boolean save() throws IOException {
        boolean ret = false;
        FileOutputStream fos = new FileOutputStream( this.configPath );
        try {
            OutputStreamWriter osw = new OutputStreamWriter( fos );
            PrintWriter writer = new PrintWriter( osw );

            Iterator<Map.Entry<String, ISection>> it = sections.entrySet().iterator();
            while ( it.hasNext() ) {
                Map.Entry<String, ISection> entry = it.next();
                String secName = entry.getKey();
                ISection section = entry.getValue();

                writer.println();
                writer.println( "[" + secName + "]" );
                section.write( writer );

                writer.flush();
                ret = true;
            }
        } finally {
            fos.close();
        }
        return ret;
    }

    @Override
    public synchronized void read(BufferedReader reader) throws IOException {
        ISection section = null ;
        String line;
        while ( (line = reader.readLine()) != null ) {
            line = line.trim();

            int openPos = line.indexOf( '[' );
            if( openPos == 0 ) {
                int closePos = line.indexOf( ']' );
                if( closePos != -1 ) {
                    String secName = line.substring( openPos+1 , closePos );
                    section = createSection( secName );
                }
            } else {
                int delimPos = line.indexOf( '=' );
                if( delimPos != -1 ) {
                    String key = line.substring( 0 , delimPos );
                    String val = line.substring( delimPos+1 , line.length() );
                    key = key.trim();
                    val = val.trim();
                    addKeyValue( section , key , val );
                } else  {
                    if ( !line.equals("") ) {
                        addKeyValue( section , line.trim() , "" );
                    }
                }
            }
        }
    }

    public String getConfigPath() {
        return configPath;
    }

    @Override
    public boolean flush() throws IOException {
        return save();
    }
}
