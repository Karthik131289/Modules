package com.company.app.ui.splash;

import com.company.app.config.IConfiguration;
import com.company.app.ui.app.mainWindow;
import com.company.app.ui.components.Panels.XSplashPanel;
import com.company.app.ui.utills;
import com.fluxicon.slickerbox.components.SlickerProgressBar;

import javax.swing.*;
import java.awt.*;

public class splashWindow extends JWindow {
    public static final String _SPLASH_IMAGE_PATH = "../icons/designersplash.gif";
    private static final Font _STATUS_FONT = new Font( Font.SANS_SERIF , Font.PLAIN , 11 );

    private IConfiguration config;
    private static mainWindow mainFrame;

    private static splashWindow splashWin;
    private XSplashPanel splashPanel;
    private JProgressBar progressBar;
    private ImageIcon splashImage;

    public splashWindow( IConfiguration cfg ) {
        this.config = cfg;
        initUI();
        executeTasks();
        initSplashWin();
    }

    private void initUI() {
        try {
            splashImage = new ImageIcon(this.getClass().getResource(_SPLASH_IMAGE_PATH));

            super.setLayout(new BorderLayout(0, 0));
            {
                splashPanel = new XSplashPanel(this.splashImage);
                splashPanel.setStatusText("Initializing...", Color.WHITE, _STATUS_FONT);
                super.add(splashPanel, BorderLayout.CENTER);

                progressBar = new JProgressBar();
                progressBar.setOrientation( SlickerProgressBar.HORIZONTAL );
                progressBar.setMaximum(100);
                progressBar.setValue(0);
                progressBar.setBorderPainted(true);
                progressBar.setStringPainted(true);
                Dimension prefDim = progressBar.getPreferredSize();
                prefDim.setSize(prefDim.getWidth(), 15);
                progressBar.setPreferredSize(prefDim);
                super.add(progressBar, BorderLayout.SOUTH);
            }
            super.setSize( this.splashImage.getIconWidth() , this.splashImage.getIconHeight() + 10 );
            super.setLocation( getSplashWindowLocation() );
            super.setVisible(true);
            super.pack();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private Point getSplashWindowLocation() {
        Point loc = new Point();
        Point screenMid = utills.getDesktopMidPoint();
        loc.x = screenMid.x - (super.getWidth()/2);
        loc.y = screenMid.y - (super.getHeight()/2);
        return loc;
    }

    private void executeTasks() {
        splashTask task = new splashTask( progressBar , splashPanel );
        task.execute();
    }

    protected static void gotoAppMainWindow() {
        mainFrame = new mainWindow();
        splashWin.dispose();
    }

    protected void initSplashWin() {
        splashWin = this;
    }

}
