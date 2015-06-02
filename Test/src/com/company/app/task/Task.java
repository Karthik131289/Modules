package com.company.app.task;

/**
 * Created by DV21 on 20-02-2015.
 */
public abstract class Task implements Runnable {
    private String taskName = "";

    public Task( String taskName ) {
        this.setTaskName( taskName );
    }

    @Override
    public void run() {
        Execution();
    }
    abstract protected void Execution();

    public String getTaskName() {
        return taskName;
    }

    protected void setTaskName( String taskName ) {
        this.taskName = taskName;
    }
}
