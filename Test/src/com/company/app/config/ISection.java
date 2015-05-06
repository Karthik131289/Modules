package com.company.app.config;

import java.io.PrintWriter;

/**
 * Created by DV21 on 27-04-2015.
 */
public interface ISection {

    String getSectionName();
    String getKeyValue( String key );
    String getKeyValue( String key , String defaultVal );
    void addKeyValue(String key, String val);
    boolean removeKeyValue( String key );
    void removeAllKeys();
    int getKeyCount();
    String[] getKeys();
    String[] getValues();
    void write( PrintWriter writer );
}
