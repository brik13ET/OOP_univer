package Threads;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user0
 */
import Vehicle.IVehicle;

public class ThreadCostOutput extends Thread {
    IVehicle v;
    
    public ThreadCostOutput(IVehicle v)
    {
        super(v.getManufacture()+"Cost");
        this.v = v;
    }
    
    @Override
    public void run()
    {
        synchronized (v){
            var cm = v.getModelsCost();
            for (double d : cm)
            {
                System.out.println("C:\t" + d);
            }
            v.notifyAll();
        }
    }
        
}
