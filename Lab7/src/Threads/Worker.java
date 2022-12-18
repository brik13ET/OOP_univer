package Threads;


import Vehicle.IVehicle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user0
 */
public class Worker implements Runnable {
    private Socket sock;
     
    public Worker(Socket s)
    {
        this.sock = s;
    }
    
    @Override
    public void run()
    {
        try
        {
            ObjectInputStream in;
            ObjectOutputStream out;
            try {
                in = new ObjectInputStream(sock.getInputStream());
                out = new ObjectOutputStream(sock.getOutputStream());
                
                System.out.printf(
                        "Thread %s: client prcessing\n",
                        this.toString()
                );

                var v = (IVehicle[])in.readObject();
                
                Double d = Vehicle.VehicleAnalyzer.doAvgVararg(
                    v
                );
                
                out.writeDouble(
                    d
                );
                out.flush();
                System.out.printf(
                        "Thread %s: client prcessed\n",
                        this.toString()
                );
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println(ex);
            }
        }
        finally
        {
            try {
                sock.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
