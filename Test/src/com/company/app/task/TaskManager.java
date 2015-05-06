package com.company.app.task;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by DV21 on 20-02-2015.
 */
public class TaskManager {
    public static final short MIN_DURATION = 250;

    private Vector<Task> taskList;
    private Timer timer;
    private TimerTask timerTask;

    private long initialDelay = 500;
    private long interval = 250;
    private long cycleInterval = 250;

    public TaskManager(){
        this.taskList = new Vector<Task>();
    }

    public Vector<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(Vector<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask( Task task ) {
        this.taskList.add( task );
    }

    public void addTask( Task task , int index ) {
        this.taskList.add( index , task );
    }

    public void addAllTask( List<Task> task )
    {
        this.taskList.addAll( task );
    }

    public void addAllTask( List<Task> task , int index )
    {
        this.taskList.addAll( index , task );
    }

    public void removeFirstTask() {
        this.removeTask( 0 );
    }

    public void removeLastTask() {
        this.removeTask( this.getTaskCount()-1 );
    }

    public void removeTask( int index ) {
        this.taskList.remove( index );
    }

    public int getTaskCount() {
        return this.taskList.size();
    }

    public Task getTask( int index ){
        return this.taskList.get( index );
    }

    public Task getFirstTask() {
        return this.getTask( 0 );
    }

    public Task getLastTask() {
        return this.getTask( this.getTaskCount()-1 );
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public long getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(long initialDelay) throws Exception {
        if( initialDelay >= MIN_DURATION )
            this.initialDelay = initialDelay;
        else
            throw new Exception( "Illegal Value.Lowest value for this parameter is " + MIN_DURATION );
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) throws Exception {
        if( interval >= MIN_DURATION )
            this.interval = interval;
        else
            throw new Exception( "Illegal Value.Lowest value for this parameter is " + MIN_DURATION );
    }

    public long getCycleInterval() {
        return cycleInterval;
    }

    public void setCycleInterval(long cycleInterval) throws Exception {
        if( cycleInterval >= MIN_DURATION )
         this.cycleInterval = cycleInterval;
        else
            throw new Exception( "Illegal Value.Lowest value for this parameter is " + MIN_DURATION );
    }

    private void executeTasks() {
        this.timer = new Timer( "TaskManager" );
        this.timerTask = new TimerTask() {
            boolean wait = false;
            long waitDuration = 0;
            int index = 0;

            @Override
            public void run() {
                try {
                    if( !wait ) {
                        getTask(index).run();

                        index++;
                        if (index >= getTaskCount()) {
                            index = 0;
                            wait = true;
                        }
                    } else {
                        waitDuration += getInterval();
                        if( waitDuration >= getCycleInterval() ) {
                            wait = false;
                            waitDuration = 0;
                        }
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        };
        this.timer.scheduleAtFixedRate( this.timerTask , this.getInitialDelay() , this.getInterval() );
    }

    public void stop() {
        this.timer.cancel();
    }

    public void start() {
        this.executeTasks();
    }

}
