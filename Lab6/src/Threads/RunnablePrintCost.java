/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

import Vehicle.IVehicle;
import java.io.PrintStream;

/**
 *
 * @author user0
 */
public class RunnablePrintCost implements Runnable
{
    private TransportSynchronizer t;
    private IVehicle _v;
    
    public RunnablePrintCost(IVehicle v, TransportSynchronizer _t)
    {
        t = _t;
        _v = v;
    }
    
    @Override
    public void run()
    {
        var cm = _v.getModelsCost();
        for (double d : cm) {
            var w = (PrintStream)t.WaitForQueue(this);
            w.println(d);
            t.LeaveQueue(this);
        }
    }
}
