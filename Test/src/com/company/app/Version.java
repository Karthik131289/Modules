package com.company.app;

/**
 * Created by DV21 on 28-04-2015.
 */
public class Version {

    public static final String _VERSION_DELIM = ".";
    public static final String _MAX = "max";
    public static final String _MIN = "min";
    public static final String _MICRO = "micro";

    private String versionStr = null;
    private int maxVersion , minVersion , microVersion;

    public Version( String versionStr ) {
        this.versionStr = versionStr;
        populateVersionDetail();
    }

    public int getMicroVersion() {
        return microVersion;
    }

    public int getMinVersion() {
        return minVersion;
    }

    public int getMaxVersion() {
        return maxVersion;
    }

    public String getVersionStr() {
        return versionStr;
    }

    public String getVersionDetail( String part ) {
        String version = null;
        String[] parts = this.getVersionStr().split( _VERSION_DELIM == "." ? "\\." : _VERSION_DELIM  );
        if( parts.length == 3 ) {
            if( part.equals( _MAX ) )
                version = parts[0];
            else if( part.equals( _MIN ) )
                version = parts[1];
            else if( part.equals( _MICRO ) )
                version = parts[2];
        }
        return version;
    }

    private void populateVersionDetail() {
        String parts[] = this.getVersionStr().split( _VERSION_DELIM == "." ? "\\." : _VERSION_DELIM  );
        if( parts.length == 3 ) {
            this.maxVersion = Integer.parseInt( parts[0] );
            this.minVersion = Integer.parseInt( parts[1] );
            this.microVersion = Integer.parseInt( parts[2] );
        }
    }

    public int compare(Version toCompVer) {
        int ret = 0;

        if( this.getMaxVersion() == toCompVer.getMaxVersion() )
            if ( this.getMinVersion() == toCompVer.getMinVersion() )
                if ( this.getMicroVersion() == toCompVer.getMicroVersion() )
                    ret = 0;
                else
                    ret = this.getMicroVersion() > toCompVer.getMicroVersion() ? 1 : -1;
            else
                ret = this.getMinVersion() > toCompVer.getMinVersion() ? 1 : -1;
        else
            ret = this.getMaxVersion() > toCompVer.getMaxVersion() ? 1 : -1 ;

        return ret;
    }
}
