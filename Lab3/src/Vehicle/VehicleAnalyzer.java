/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

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
    
    public static void outputVehicle (IVehicle v, OutputStream out)
            throws UnsupportedEncodingException, IOException
    {
        String type = v.getClass().getName();
        String mark = v.getManufacture();
        String[] modelNames = v.getModelsTitle();
        var modelCosts = v.getModelsCost();
        int size = modelNames.length;
        byte[] size_array = new byte[4];
        byte[] type_length_array = new byte[4];
        byte[] mark_length_array = new byte[4];
        var type_bytes = type.getBytes(Charset.defaultCharset());
        var mark_bytes = mark.getBytes(Charset.defaultCharset());
        
        for (int i = 0; i < 4; i++) {
            size_array[3-i] = 
                    (byte)(0xFF & (modelNames.length >> (8 * i)));
            mark_length_array[3-i] = 
                    (byte)(0xFF & (mark_bytes.length >> (8 * i)));
            type_length_array[3-i] = 
                    (byte)(0xff & (type_bytes.length >> (8 * i)));
        }
        out.write(type_length_array);
        out.write(type_bytes);
        out.write(mark_length_array);
        out.write(mark_bytes);
        out.write(size_array);
        for (int i = 0; i < size; i++)
        {
            byte[] mn_bytes = modelNames[i].getBytes();
            int mn_size = mn_bytes.length;
            byte[] mn_size_array = new byte[4];
            byte[] mc_array = new byte[4];
            for (int j = 0; j < 4; j++) {
                mn_size_array[3-j] = (byte)(0xff & (mn_size >> (8 * j)));
            }
            for (int j = 0; j < 4; j++) {
                mc_array[3-j] = (byte)(0xff & (modelCosts[i] >> (8 * j)));
            }
            out.write(mn_size_array);
            out.write(mn_bytes);
            out.write(mc_array);
        }
        
    }
    
    public static Motocycle inputMotocycle(InputStream in)
            throws IOException, DuplicateModelNameException
    {
        var type_size_arr = in.readNBytes(4);
        int type_size = 0;
        for (byte b : type_size_arr) {
            type_size = (type_size << 8) | b;
        }
        byte[] type_array = in.readNBytes(type_size);
        String type = new String(type_array, Charset.defaultCharset());
        byte[] mark_size_array = in.readNBytes(4);
        int mark_size = 0;
        int size = 0;

        for (byte b : mark_size_array) {
            mark_size = (mark_size << 8) | b;
        }
        byte[] mark_array = in.readNBytes(mark_size);

        byte[] size_array = in.readNBytes(4);
        for (byte b : size_array) {
            size = (size << 8) | b;
        }
        String mark = new String(mark_array, Charset.defaultCharset());
        
        if ( !type.equals("Vehicle.Motocycle"))
            throw new IOException(
                    "Object type does not match (\"" + type + "\"). \n");
       var ret = new Motocycle(mark, 0);
        for (int i = 0; i < size; i++)
        {
            var mn_size_array = in.readNBytes(4);
            int mn_size = 
                    (0xff000000 & (mn_size_array[0] << 24)) |
                    (0x00ff0000 & (mn_size_array[1] << 16)) |
                    (0x0000ff00 & (mn_size_array[2] << 8))  |
                    (0x000000ff & mn_size_array[3]);
            byte[] mn_array = in.readNBytes(mn_size);
            String modelName = new String(mn_array, Charset.defaultCharset());
            byte[] mc_array = in.readNBytes(4);
            int modelCost = 
                    (0xff000000 & (mc_array[0] << 24)) |
                    (0x00ff0000 & (mc_array[1] << 16)) |
                    (0x0000ff00 & (mc_array[2] << 8))  |
                    (0x000000ff & mc_array[3]);
            
            ret.addModel(modelName, modelCost);
        }
        return ret;
    }

    public static Automobile inputAutomobile (InputStream in)
            throws IOException, DuplicateModelNameException
    {
        var type_size_arr = in.readNBytes(4);
        int type_size = 0;
        for (byte b : type_size_arr) {
            type_size = (type_size << 8) | b;
        }
        byte[] type_array = in.readNBytes(type_size);
        String type = new String(type_array, Charset.defaultCharset());
        byte[] mark_size_array = in.readNBytes(4);
        int mark_size = 0;
        int size = 0;

        for (byte b : mark_size_array) {
            mark_size = (mark_size << 8) | b;
        }
        byte[] mark_array = in.readNBytes(mark_size);

        byte[] size_array = in.readNBytes(4);
        for (byte b : size_array) {
            size = (size << 8) | b;
        }
        String mark = new String(mark_array, Charset.defaultCharset());
        
        if ( !type.equals("Vehicle.Automobile"))
            throw new IOException("Object type does not match (\"" + type + "\"). \n");
       var ret = new Automobile(mark, 0);
        for (int i = 0; i < size; i++)
        {
            var mn_size_array = in.readNBytes(4);
            int mn_size = 
                    (0xff000000 & (mn_size_array[0] << 24)) |
                    (0x00ff0000 & (mn_size_array[1] << 16)) |
                    (0x0000ff00 & (mn_size_array[2] << 8))  |
                    (0x000000ff & mn_size_array[3]);
            byte[] mn_array = in.readNBytes(mn_size);
            String modelName = new String(mn_array, Charset.defaultCharset());
            byte[] mc_array = in.readNBytes(4);
            int modelCost = 
                    (0xff000000 & (mc_array[0] << 24)) |
                    (0x00ff0000 & (mc_array[1] << 16)) |
                    (0x0000ff00 & (mc_array[2] << 8))  |
                    (0x000000ff & mc_array[3]);
            
            ret.addModel(modelName, modelCost);
        }
        return ret;
    }
    
    public static void writeVehicle(IVehicle v, Writer out)
            throws IOException
    {
        out.write(v.getClass().getName());
        out.write('\n');
        out.write(v.getManufacture());
        out.write('\n');
        int cnt = v.getModelCount();
        out.write("" + cnt);
        out.write('\n');
        String[] titles = v.getModelsTitle();
        var costs = v.getModelsCost();
        for (int i = 0; i < cnt; i++)
        {
            out.write(titles[i]);
            out.write('\n');
            out.write("" + costs[i]);
            out.write('\n');
        }
        out.flush();
    }
    
    public static Motocycle readMotocycle (Reader in)
            throws IOException, DuplicateModelNameException
    {
        String cls = new String();
        char ch;
        while ((ch = (char) in.read()) != '\n')
        {
            cls += ch;
        }
        if (!Motocycle.class.getName().equals(cls))
            throw new IOException(
                    "Object type does not match (\"" + cls + "\"). \n");
        String manuf = "";
        while ((ch = (char) in.read()) != '\n')
        {
            manuf += ch;
        }
        var ret = new Motocycle(manuf,0);
        String str_count = "";
        while ((ch = (char) in.read()) != '\n')
        {
            str_count += ch;
        }
        int count = Integer.parseInt(str_count);
        for (int i = 0; i < count; i++)
        {
            String mname = new String();
            String mcost_str = new String();
            
            while ((ch = (char) in.read()) != '\n')
                mname += ch;
            
            while ((ch = (char) in.read()) != '\n')
                mcost_str += ch;
            ret.addModel(mname, Integer.parseInt(mcost_str));
        }
        return ret;
    }
    
    
    
}
