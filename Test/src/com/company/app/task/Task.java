package com.company.app.task;

/**
 * Created by DV21 on 20-02-2015.
 */
public abstract class Task implements Runnable {

    @Override
    public void run() {
        Execution();
    }
    abstract protected void Execution();
}
