package com.company.app.ui;

import java.awt.*;

/**
 * Created by DV21 on 06-05-2015.
 */
public class utills {

    public static final Dimension getDesktopSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static final Point getDesktopMidPoint() {
        Point loc = null;
        Dimension size = getDesktopSize();
        loc = new Point( size.width/2 , size.height/2 );
        return loc;
    }
}
