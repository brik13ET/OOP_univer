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

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Moped m = new Moped("🛵🛵🛵", 10);
                
        ThreadCostOutput tc = new ThreadCostOutput(m);
        ThreadTitlesOutput tt = new ThreadTitlesOutput(m);

        tc.setPriority(3);
        tt.setPriority(6);

        System.out.println("tc: " +tc.getPriority() + " tt: " + tt.getPriority());
        
        tc.start();
        tt.start();
        while(tc.isAlive() || tt.isAlive());
        // Synchonized
        
        TransportSynchronizer t = new TransportSynchronizer(System.out);
        var r1 = new RunnablePrintTitle(m, t);
        var r2 = new RunnablePrintCost(m, t);
        t.start(r1, r2);
    }
    
}
