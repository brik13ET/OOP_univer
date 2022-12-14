/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

import Vehicle.IVehicle;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author user0
 */
public class RunnablePrintTitles2 implements Runnable {
    
    private IVehicle v;
    private ReentrantLock lock;
    
    public RunnablePrintTitles2(IVehicle v, ReentrantLock lock)
    {
        this.v = v;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try 
        {
            for (String t : v.getModelsTitle()) {
                System.out.println(t);
            }
        }
        finally {
            lock.unlock();
        }
    }
}
