package com.company.app.task;

import java.util.Vector;

/**
 * Created by DV21 on 20-02-2015.
 */
public class Test {

    static final Vector<Task> tasks = new Vector<Task>();
    public static TaskManager taskManager;
    public static void main(String[] args) {

       /* System.out.println( "Creating Tasks...." );
        int i=1;
        while ( i<11 )
            tasks.add( createTask("Task-" + (i++)) );

        System.out.println( "Executing Tasks..." );

        execute();*/

        System.out.println( "Creating Tasks...." );
        int i=1;
        while ( i<11 )
            tasks.add( createTask("Task-" + (i++)) );

        System.out.println( "Executing Tasks..." );
         taskManager  = new TaskManager();
        taskManager.addAllTask( tasks );
        try {
            taskManager.setCycleInterval( 2000 );
        } catch (Exception e) {
            e.printStackTrace();
        }
        taskManager.start();
    }

    private static Task createTask( final String textToPrint )
    {
        Task task = null;
        if( textToPrint!=null )
            task = new Task() {
                @Override
                protected void Execution() {
                    System.out.println( textToPrint );
                }
            };
        return task;
    }
}
