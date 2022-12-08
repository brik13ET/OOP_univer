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
public class RunnablePrintCost2 implements Runnable {
    
    IVehicle v;
    ReentrantLock lock;
    
    public RunnablePrintCost2(IVehicle v, ReentrantLock lock)
    {
        this.v = v;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        for (var e : v.getModelsCost()) {
            System.out.println(e);
        }
        lock.unlock();
    }
}
