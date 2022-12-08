
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicle;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user0
 */
public class VehicleAnalyzer {
    public static double doAvg(IVehicle v)
    {
        double sum = 0;
        var b = v.getModelsCost();
        int count = b.length;
        for (int i = 0; i < count; i++) {
            sum += b[i];
        }
        return sum / count;
    }
    
    public static void printPriceList(IVehicle v)
            throws NoSuchModelNameException
    {
        String[] s = v.getModelsTitle();
        for (String string : s) {
            System.out.println(string+"\t"+v.getModelCostByName(string));
        }
    }
    // Lab 3
    public static void outputVehicle (IVehicle v, OutputStream oout)
            throws UnsupportedEncodingException, IOException
    {
        DataOutputStream out = new DataOutputStream(oout);
        String type = v.getClass().getName();
        String mark = v.getManufacture();
        var modelNames = v.getModelsTitle();
        var modelCosts = v.getModelsCost();
        int size = modelNames.length;
        var type_bytes = type.getBytes(Charset.defaultCharset());
        var mark_bytes = mark.getBytes(Charset.defaultCharset());
        
        out.writeInt(type_bytes.length);
        out.write(type_bytes);
        out.writeInt(mark_bytes.length);
        out.write(mark_bytes);
        out.writeInt(size);
        for (int i = 0; i < size; i++)
        {
            byte[] mn_bytes = modelNames[i].getBytes();
            out.writeInt(mn_bytes.length);
            out.write(mn_bytes);
            out.writeDouble(modelCosts[i]);
        }
        out.flush();
    }
    
    public static IVehicle inputVehicle(InputStream iin)
            throws IOException, DuplicateModelNameException
    {
        DataInputStream in = new DataInputStream(iin);
        int type_size = in.readInt();
        String type = new String(
                in.readNBytes(type_size),
               Charset.defaultCharset());
        int mark_size = in.readInt();
        int size;
        String mark = new String(
                in.readNBytes(mark_size), 
               Charset.defaultCharset());
        size = in.readInt();
        
        IVehicle ret;
        switch (type)
        {
            case "Vehicle.Motocycle": ret = new Motocycle(mark, 0); break;
            case "Vehicle.Automobile": ret = new Automobile(mark, 0); break;
            default: throw new IOException(
                    "Object type does not match (\"" + type + "\"). \n");
        }
        for (int i = 0; i < size; i++)
        {
            int mn_size = in.readInt();
            byte[] mn_array = in.readNBytes(mn_size);
            String modelName = new String(mn_array, Charset.defaultCharset());
            double modelCost = in.readDouble();
            ret.addModel(modelName, modelCost);
        }
        return ret;
    }
    
    public static void writeVehicle(IVehicle v, Writer oout)
            throws IOException
    {
        PrintWriter out = new PrintWriter(oout);
        int cnt = v.getModelCount();
        out.printf("%s\n%s\n%d\n", v.getClass().getName(),v.getManufacture(),cnt);
        String[] titles = v.getModelsTitle();
        var costs = v.getModelsCost();
        for (int i = 0; i < cnt; i++)
        {
            out.printf("%s\n%f\n",titles[i],costs[i]);
        }
        out.flush();
    }
    
    public static IVehicle readVehicle (Reader iin)
            throws
                IOException,
                DuplicateModelNameException
    {
        Scanner sc = new Scanner(iin);
        sc.useDelimiter("\n");
        String cls = sc.nextLine();
        String manuf = sc.nextLine();
        IVehicle ret;
        
        try {
            Class c = Class.forName(cls);
            Constructor co = c.getConstructor(String.class, int.class);
            ret = (IVehicle)co.newInstance(manuf, 0);
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
            return null;
        }
        
        int count = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < count; i++)
        {
            String mname = sc.nextLine();
            var mcost = sc.nextDouble();
            sc.nextLine();
            
            ret.addModel(mname, mcost);
        }
        sc.close();
        return ret;
    }
    
    public static IVehicle createVehicle(String mark, int size, IVehicle base)
    {
        Class classReflect = base.getClass();
        try {
            Constructor constr = 
                    classReflect.getConstructor(String.class, int.class);
            Object ret = constr.newInstance(mark, size);
            return (IVehicle)ret;
        } catch (
            NoSuchMethodException |
            SecurityException |
            InstantiationException |
            IllegalAccessException |
            IllegalArgumentException |
            InvocationTargetException ex
        )
        {
            return null;
        }
    }
    
    public static double doAvgVararg (IVehicle ... vs)
    {
        double sum = 0;
        int cnt = 0;
        for (IVehicle v : vs)
        {
            var cs = v.getModelsCost();
            for (double c : cs)
            {
                sum += c;
                cnt++;
            }
        }
        return sum / cnt;
    }
}