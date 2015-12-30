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

    public static final Dimension MIN_SIZE_PANEL_LEFT = new Dimension( 50 , 250 );
    public static final Dimension MIN_SIZE_PANEL_BOTTOM = new Dimension( 300 , 50 );
    public static final Dimension MIN_SIZE_PANEL_CONTENT = new Dimension( 250 , 250 );
    public static final Dimension MIN_SIZE_FRAME_MAIN = new Dimension( 300 , 300 );

    private JFrame frame;
    private JSplitPane horizontalSplit;
    private JSplitPane verticalSplit;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private DockWindow bottomDockWindow;
    private ConsoleWindow consoleWindow;
    private JPanel leftPanel;
    private XStatusBar statusBar;

    public static void main(String[] args) {
        new mainWindow();
    }

    public mainWindow() {
        this.initUI();
        startTimer();
    }

    private void initUI() {

        this.frame = new JFrame();
        this.frame.setTitle( appMain._APP_NAME );
        this.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.frame.setMinimumSize( MIN_SIZE_FRAME_MAIN );
        this.frame.setLayout( new BorderLayout(5,5) );
        {
            this.verticalSplit = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
            this.verticalSplit.setContinuousLayout( true );
            this.verticalSplit.setDividerSize(5);
            {
                this.contentPanel = new JPanel();
                this.contentPanel.setLayout(new MigLayout());
                this.contentPanel.setMinimumSize( MIN_SIZE_PANEL_CONTENT );
                //this.contentPanel.setBorder( new LineBorder( Color.BLUE ) );
                {
                    JPanel temp = new JPanel();
                    temp.setPreferredSize( new Dimension( 100,100 ) );
                    //temp.setBorder( new LineBorder( Color.DARK_GRAY ) );
                    this.contentPanel.add( temp );
                }

                this.bottomPanel = new JPanel();
                this.bottomPanel.setLayout(new BorderLayout(1, 1));
                this.bottomPanel.setMinimumSize( MIN_SIZE_PANEL_BOTTOM );
                //this.bottomPanel.setBorder( new LineBorder( Color.MAGENTA ) );
                {
                    this.bottomDockWindow = new DockWindow();
                    this.bottomPanel.add( this.bottomDockWindow , BorderLayout.CENTER );
                    {
                        consoleWindow = new ConsoleWindow();
                        consoleWindow.setBorder( new LineBorder( Color.DARK_GRAY ) );
                        this.bottomDockWindow.addDockPanel( ConsoleWindow.CONSOLE_WINDOW , consoleWindow );
                    }
                }

                this.verticalSplit.revalidate();
                this.verticalSplit.setTopComponent( this.contentPanel );
                this.verticalSplit.setBottomComponent( this.bottomPanel );
            }

            this.horizontalSplit = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
            this.horizontalSplit.setContinuousLayout( true );
            this.horizontalSplit.setDividerSize(5);
            {
                this.leftPanel = new JPanel();
                this.leftPanel.setLayout(new BorderLayout(1, 1));
                this.leftPanel.setMinimumSize(MIN_SIZE_PANEL_LEFT);
                //this.leftPanel.setBorder( new LineBorder( Color.DARK_GRAY ) );
                {


                }

                this.horizontalSplit.revalidate();
                this.horizontalSplit.setLeftComponent( this.leftPanel );
                this.horizontalSplit.setRightComponent( this.verticalSplit );
            }

            this.statusBar = new XStatusBar();
            this.statusBar.setBorder( new LineBorder( Color.GRAY ) );
            this.statusBar.setStatusText(XStatusBar.STATUS_IDLE);
            this.statusBar.setProgressBounds(XStatusBar.PROGRESS_MIN_VAL, XStatusBar.PROGRESS_MAX_VAL);
            this.statusBar.setProgressValue(XStatusBar.PROGRESS_MIN_VAL);
            this.statusBar.showProgress(true);

            this.frame.add( this.horizontalSplit , BorderLayout.CENTER );
            this.frame.add(this.statusBar, BorderLayout.SOUTH);
        }
        this.frame.setLocation( com.company.app.ui.utills.calcWindowLocation( this.frame.getSize() ) );
        this.frame.setVisible( true );
        this.frame.pack();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JSplitPane getHorizontalSplitPane() {
        return this.horizontalSplit;
    }

    public JSplitPane getVerticalSplitPane() {
        return this.verticalSplit;
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
