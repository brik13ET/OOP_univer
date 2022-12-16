/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

import Vehicle.IVehicle;

/**
 *
 * @author user0
 */
public class RunnablePrintTitle implements Runnable
{
    private TransportSynchronizer t;
    private int count = 0;
    
    public RunnablePrintTitle(TransportSynchronizer _t, int _count)
    {
        t = _t;
        count = _count;
    }
    
    @Override
    public void run()
    {
        for (int i = 0; i < count; i++)
            try {
                t.printModel();
            } catch (InterruptedException ex) {
                System.err.println(ex);
                return;
            }
    }
}
