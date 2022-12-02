/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author user0
 */
import Vehicle.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException
    {
        if (args.length <= 3)
        {
            System.out.println(
                    "Too less arguments\n"+
                    "Params:\n\t fullClassName methodName param1 param2\n"
            );
            if (args.length == 2 && args[1] == "?")
            {
                try {
                    Class c = Class.forName(args[0]);
                    Method[] ms = c.getMethods();
                    for (int i = 0; i < ms.length; i++) {
                        System.out.println(ms[i]);
                    }
                } catch (ClassNotFoundException ex) {
                    System.err.println(ex);
                }
            }
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
            
            System.out.println("Результат:\n" + a.toString());
            
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + args[0] + "not found.\n");
        } catch (NoSuchMethodException ex) {
            System.err.println(ex);
        }
        
        IVehicle v1 = new Automobile("🚗", 10);
        IVehicle v2 = new Quadricycle("🏍", 10);
        IVehicle v3 = new Motocycle("🏍", 10);
        
        System.out.println("Vararg avg: " + VehicleAnalyzer.doAvgVararg(v1, v2, v3));
    }
    
}
