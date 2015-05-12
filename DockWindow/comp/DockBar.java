package comp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by DV21 on 12-05-2015.
 */
public class DockBar extends JToolBar {

    public DockBar() {
        super();
        this.initDockBar();
    }

    private void initDockBar() {
        this.setFloatable( false );
        this.setBorderPainted( false );
        this.setBorder( new LineBorder( Color.DARK_GRAY ) );
        this.setOrientation( JToolBar.HORIZONTAL );
    }
}
