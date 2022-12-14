package Threads;

import Vehicle.IVehicle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user0
 */
public class ThreadTitlesOutput extends Thread {
    private    IVehicle v;
    
    public ThreadTitlesOutput(IVehicle v)
    {
        super(v.getManufacture()+"Title");
        this.v = v;
    }
    
    @Override
    public void run()
    {
        var tm = v.getModelsTitle();
        for (String d : tm)
        {
            System.out.println("T:\t"+d);
        }
    }
}
