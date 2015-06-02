package com.company.app.ui.splash;

import com.company.app.task.Task;
import com.company.app.ui.components.Panels.XSplashPanel;

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
    private List<Task> tasks;

    public splashTask( JProgressBar pBar , XSplashPanel splashPanel ) {
        this.pBar = pBar;
        this.splashPanel = splashPanel;
        this.status = new Vector<Object>();
        this.tasks = new Vector<Task>();
        this.initTasks();
    }

    private void initTasks() {
        Task t1 = getSampleTask( 1 );
        Task t2 = getSampleTask( 2 );
        Task t3 = getSampleTask( 3 );
        Task t4 = getSampleTask( 4 );
        Task t5 = getSampleTask( 5 );
        Task t6 = getSampleTask( 6 );
        Task t7 = getSampleTask( 7 );
        Task t8 = getSampleTask( 8 );
        Task t9 = getSampleTask( 9 );
        Task t10 = getSampleTask( 10 );

        this.tasks.add( t1 );
        this.tasks.add( t2 );
        this.tasks.add( t3 );
        this.tasks.add( t4 );
        this.tasks.add( t5 );
        this.tasks.add( t6 );
        this.tasks.add( t7 );
        this.tasks.add( t8 );
        this.tasks.add( t9 );
        this.tasks.add( t10 );
    }

    private Task getSampleTask( int i ) {
        Task task = null ;
        task = new Task( ""+i ) {
            @Override
            protected void Execution() {
                System.out.println( "Executing Task : " + this.getTaskName() );
            }
        };

        return task;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while( steps < totalSteps ) {
            progress += incVal;
            steps++;

            status.clear();
            status.add( progress );                                             // Progress in number
            status.add( "Steps completed : " + steps + " / " + totalSteps );    // Progress in Message
            publish(status);

            Task task = tasks.get( steps-1 );
            if( task !=null )
                task.run();                                                     // Executing task

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
