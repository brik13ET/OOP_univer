/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author user0
 */
import Vehicle.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args.length <= 3)
        {
            System.out.println(
                    "Too less arguments\n"+
                    "Params:\n\t fullClassName methodName param1 param2\n"
            );
            return;
        }
        
        System.out.println(
                "fullClassName: "+args[0]+"\n"+
                "methodName: "+args[1]+"\n"+
                "param1: "+args[2]+"\n"+
                "param2: "+args[3]+"\n"
        );
        
        try {
            
            Class c = Class.forName(args[0]);
            Constructor co = c.getConstructor(String.class, int.class);
            Method mc = c.getMethod(args[1], String.class, double.class);
            
            Object a = co.newInstance("🚑", 4);
            mc.invoke(a, args[2], Integer.valueOf(args[3]));
            
            System.out.printf(
                    "Результат:\n%s\n%s\n",
                    a.getClass().getCanonicalName(),
                    a.toString()
            );
            
        } catch (
            InstantiationException |
            IllegalAccessException |
            IllegalArgumentException |
            ClassNotFoundException |
            NoSuchMethodException |
            InvocationTargetException ex
        )
        {
            System.err.println(ex);
            return;
        }
        
        IVehicle v1 = new Moped("🚗", 0);
        IVehicle v2 = new Quadricycle("🏍", 0);
        IVehicle v3 = new Scooter("🏍", 0);
        
        try {
            v1.addModel("🚀", 100_000);
            v2.addModel("🛰", 300_000);
            v3.addModel("🌙", 600_000);
        } catch (DuplicateModelNameException ex) {
            System.err.println(ex);
            return;
        }
        
        try {
            VehicleAnalyzer.printPriceList(v1);
            VehicleAnalyzer.printPriceList(v2);
            VehicleAnalyzer.printPriceList(v3);
        } catch (NoSuchModelNameException ex) {
            System.err.println(ex);
            return;
        }
        
        System.out.println(
                "Vararg avg: " + VehicleAnalyzer.doAvgVararg(v1, v2, v3)
        );
        
        // v1 Moped
        // v2 Quadricycle
        // v3 Scooter
        
        var v4 = VehicleAnalyzer.createVehicle("костыль и велосипед", 1, v3);
        try {
            v4.addModel("⭐⭐⭐", 123_456);
        } catch (DuplicateModelNameException ex) {
            System.err.println(ex);
            return;            
        }
        
        System.out.println(v4.getClass().getCanonicalName());
        System.out.println(v4);
        
        try {
            var oout = new PrintWriter(System.out);
            VehicleAnalyzer.writeVehicle(v4, oout);
            var v5 = VehicleAnalyzer.readVehicle(
                    new InputStreamReader(System.in)
            );
            System.out.println("\n" + v5);
        } catch (
                IOException |
                DuplicateModelNameException ex
        ) {
            System.err.println(ex);
            return;
        }
    }   
}