package com.company.app.config;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by DV21 on 27-04-2015.
 */
public class Section implements ISection {

    private IConfiguration configuration;
    private String secName;
    private Map<String,String> entries;

    public Section(String secName, Configuration configuration) {
        this.secName = secName;
        this.configuration = configuration;
        this.entries = Collections.synchronizedMap( new LinkedHashMap<String, String>() );
    }

    @Override
    public String getSectionName() {
        return this.secName;
    }

    @Override
    public String getKeyValue(String key) {
        return this.entries.get( key );
    }

    @Override
    public String getKeyValue(String key, String defaultVal) {
        String ret = this.getKeyValue( key );
        if( ret == null )
            ret = defaultVal;
        return ret;
    }

    @Override
    public void addKeyValue(String key, String val) {
        entries.put( key , val );
    }

    @Override
    public boolean removeKeyValue(String key) {
        Object ret = this.entries.remove( key );
        return (ret!=null ? true : false) ;
    }

    @Override
    public void removeAllKeys() {
        this.entries.clear();
    }

    @Override
    public int getKeyCount() {
        return this.entries.size();
    }

    @Override
    public String[] getKeys() {
        String[] ret = new String[ this.getKeyCount() ];
        this.entries.keySet().toArray( ret );
        return ret;
    }

    @Override
    public String[] getValues() {
        String[] ret = new String[ this.getKeyCount() ];
        this.entries.values().toArray( ret );
        return ret;
    }

    @Override
    public void write(PrintWriter writer) {
        Iterator< Map.Entry<String,String> > it = entries.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry<String,String> entry = it.next();
            String key = entry.getKey();
            String val = entry.getValue();

            writer.println( key + "=" + (val!=null ? val : "" ) );
        }
    }
}
