package com.company.app.ui.splash;

import com.company.app.ui.components.XSplashPanel;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by DV21 on 07-05-2015.
 */
public class splashTask extends SwingWorker<Void, List<Object>> {
    private static final int _MAX = 100;

    private JProgressBar pBar;
    private XSplashPanel splashPanel;

    private int totalSteps = 10;
    private int incVal = _MAX /totalSteps;
    private int progress = 0 , steps = 0;
    private List<Object> status;

    public splashTask( JProgressBar pBar , XSplashPanel splashPanel ) {
        this.pBar = pBar;
        this.splashPanel = splashPanel;
        this.status = new Vector<Object>();
    }

    @Override
    protected Void doInBackground() throws Exception {
        while( steps < totalSteps ) {
            progress += incVal;
            steps++;

            status.clear();
            status.add( progress );
            status.add( "Steps completed : " + steps + " / " + totalSteps );
            publish(status);

            try {
                Thread.sleep( 200 );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void process(List<List<Object>> chunks) {
        List<Object> entry = chunks.get( chunks.size()-1 );
        pBar.setValue( (Integer)entry.get(0) );
        splashPanel.setStatusText( (String)entry.get(1) );
    }

    @Override
    protected void done() {
        splashWindow.gotoAppMainWindow();
    }
}
