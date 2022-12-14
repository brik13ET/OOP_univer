/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author user0
 */
import Vehicle.*;
import Threads.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException
    {
        Moped m = new Moped("🛵🛵🛵", 1000);
        // 1
        if (false)
        {
            ThreadTitlesOutput tt = new ThreadTitlesOutput(m);
            ThreadCostOutput tc = new ThreadCostOutput(m);

            tt.setPriority(Thread.MAX_PRIORITY);
            tc.setPriority(Thread.MIN_PRIORITY);

            System.out.println("thread \"print cost\" prior: " +tc.getPriority() + "\nthread \"print titles\" prior: " + tt.getPriority());

            tc.start();
            tt.start();
        }
        // 2
        if (false)
        {
            TransportSynchronizer tSync = new TransportSynchronizer(m);
            Runnable r1 = new RunnablePrintTitle(tSync, m.getModelCount());
            Runnable r2 = new RunnablePrintCost(tSync, m.getModelCount());
        
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            t2.start();
        }
        // 3
        if (false)
        {
            var lock = new ReentrantLock();
            Runnable r1 = new RunnablePrintTitles2(m, lock);
            Runnable r2 = new RunnablePrintCost2(m, lock);
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            t2.start();
        }
        // 4
        if (false)
        {
            IVehicle v1 = new Automobile("Машина",100);
            IVehicle v2 = new Scooter("Дешёвый мотоцикл",100);
            IVehicle v3 = new Quadricycle("Почти машина",100);
            IVehicle v4 = new Moped("Велик на стероидах",100);
            
            Runnable r1 = new RunnablePrintMark(v1);
            Runnable r2 = new RunnablePrintMark(v2);
            Runnable r3 = new RunnablePrintMark(v3);
            Runnable r4 = new RunnablePrintMark(v4);
            
            var tp = Executors.newFixedThreadPool(2);
            
            tp.submit(r1);
            tp.submit(r2);
            tp.submit(r3);
            tp.submit(r4);
            
            tp.shutdown();
        }
        // 5
        if (false)
        {
            var q = new ArrayBlockingQueue(2);
            
            var r1 = new RunnableReadFile("1.txt", q);
            var r2 = new RunnableReadFile("2.txt", q);
            var r3 = new RunnableReadFile("3.txt", q);
            var r4 = new RunnableReadFile("4.txt", q);
            var r5 = new RunnableReadFile("5.txt", q);
            
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            Thread t3 = new Thread(r3);
            Thread t4 = new Thread(r4);
            Thread t5 = new Thread(r5);
            
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            for (int i = 0; i < 5; i++) {
                Object o = q.take();
                System.out.println( ((Automobile)o).getManufacture() );
            }
        }
    }
    
}
