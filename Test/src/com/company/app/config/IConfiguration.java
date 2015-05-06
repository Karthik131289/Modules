package com.company.app.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by DV21 on 27-04-2015.
 */
public interface IConfiguration {

    ISection createSection( String secName );
    /**
     * Gets the specified section. If a section by that name does not exist
     * a new section by that name is created
     *
     * @return ISection object
     * @see #findSection
     * @see ISection
     */
    ISection getSection(String secName);

    /**
     * Gets the specified section. If a section by that name does not exist
     * this method returns null.
     *
     * @see #getSection
     * @see ISection
     */
    ISection findSection(String secName);

    /**
     * Removes the specified section.
     *
     * @return true if the section was removed. false if the specified
     *         section does not exist
     * @see #getSection
     * @see ISection
     */
    boolean removeSection(String secName);

    int getSectionCount();

    String[] getSectionNames();

    ISection[] getSections();

    void removeAllSection();

    boolean save() throws IOException;

    void read(BufferedReader reader) throws IOException;

    boolean flush() throws IOException;
}
