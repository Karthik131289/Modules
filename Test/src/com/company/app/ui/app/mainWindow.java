package com.company.app.ui.app;

import com.company.app.appMain;
import com.company.app.ui.components.Bars.XStatusBar;
import com.company.app.ui.components.Panels.DockWindow;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;

/**
 * Created by DV21 on 08-05-2015.
 */
public class mainWindow {

    private JFrame frame;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private DockWindow bottomDockWindow;
    private ConsoleWindow consoleWindow;
    private JPanel leftPanel;
    private XStatusBar statusBar;

    public mainWindow() {
        this.initUI();
        startTimer();
    }

    private void initUI() {
        this.frame = new JFrame();
        this.frame.setTitle( appMain._APP_NAME );
        this.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.frame.setLayout( new BorderLayout(5,5) );
        {
            this.leftPanel = new JPanel();
            this.leftPanel.setLayout(new BorderLayout(1, 1));
            this.frame.add( this.leftPanel , BorderLayout.WEST );

            this.contentPanel = new JPanel();
            this.contentPanel.setLayout(new MigLayout());
            this.frame.add( this.contentPanel , BorderLayout.CENTER );
            {
                JPanel temp = new JPanel();
                temp.setPreferredSize( new Dimension( 100,100 ) );
                temp.setBorder( new LineBorder( Color.DARK_GRAY ) );
                this.contentPanel.add( temp );
            }

            this.bottomPanel = new JPanel();
            this.bottomPanel.setLayout(new BorderLayout(1, 1));
            this.frame.add( this.bottomPanel , BorderLayout.SOUTH );
            {
                this.bottomDockWindow = new DockWindow();
                this.bottomPanel.add( this.bottomDockWindow , BorderLayout.CENTER );
                {
                    consoleWindow = new ConsoleWindow();
                    consoleWindow.setBorder( new LineBorder( Color.DARK_GRAY ) );
                    this.bottomDockWindow.addDockPanel( ConsoleWindow.CONSOLE_WINDOW , consoleWindow );
                }

                this.statusBar = new XStatusBar();
                this.statusBar.setBorder( new LineBorder( Color.DARK_GRAY ) );
                this.statusBar.setStatusText(XStatusBar.STATUS_IDLE);
                this.statusBar.setProgressBounds(XStatusBar.PROGRESS_MIN_VAL, XStatusBar.PROGRESS_MAX_VAL);
                this.statusBar.setProgressValue(XStatusBar.PROGRESS_MIN_VAL);
                this.statusBar.showProgress(true);
                this.bottomPanel.add(this.statusBar, BorderLayout.SOUTH);
            }
        }
        this.frame.setVisible( true );
        this.frame.pack();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JPanel getContentPanel() {
        return this.contentPanel;
    }

    public JPanel getLeftPanel() {
        return this.leftPanel;
    }

    public JPanel getBottomPanel() {
        return this.bottomPanel;
    }

    public DockWindow getBottomDockWindow() {
        return this.bottomDockWindow;
    }

    public XStatusBar getStatusBar() {
        return statusBar;
    }

    public void exitApp() {
        System.exit( 0 );
    }

    private void startTimer() {
        final java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                consoleWindow.writeLine( "Console Window ......................................................................." , (counter%2) == 1 ? "normal": "error" );
                counter++;

                if( counter >= 10 )
                    timer.cancel();
            }
        };

        timer.schedule( task , 1000 , 1000 );
    }
}
