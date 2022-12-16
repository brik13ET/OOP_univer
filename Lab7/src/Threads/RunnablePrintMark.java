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
public class RunnablePrintMark implements Runnable {

    IVehicle v;
    
    public RunnablePrintMark(IVehicle v)
    {
        this.v =v;
    }
    
    @Override
    public void run() {
        System.out.println(v.getManufacture());
    }
    
}
