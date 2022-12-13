/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

import Vehicle.Automobile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author user0
 */
public class RunnableReadFile implements Runnable {
    
    private String fileName;
    private BlockingQueue q;
    
    public RunnableReadFile(String fileName, BlockingQueue q)
    {
        this.fileName = fileName;
        this.q = q;
    }
    
    @Override
    public void run() {
        BufferedReader f;
        try {
            var file = new FileReader(fileName);
            f = new BufferedReader(file);
            var mark = f.readLine();
            var vehicle = new Automobile(mark, 0);
            q.add(vehicle);
        } catch (Exception ex) {
            System.err.println(ex);
            return;
        }
    }
    
}
