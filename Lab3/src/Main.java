
import Vehicle.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class Main {

    public static void main(String argv[])
            throws
            DuplicateModelNameException,
            NoSuchModelNameException,
            FileNotFoundException,
            IOException,
            ClassNotFoundException
    {
        IVehicle v = new Motocycle("Hurrington Motors", 5);
        //IVehicle v = new Automobile("Hyndai",55);

        //VehicleAnalyzer.printPriceList(v);
        System.out.println();
        // Symbol IO
        // File
        
        InputStreamReader fr = new InputStreamReader(
                new FileInputStream("obj.txt"));
        OutputStreamWriter fw = new OutputStreamWriter(
                new FileOutputStream("obj.txt"));
        
        VehicleAnalyzer.writeVehicle(v, fw);
        fw.close();
        var read = VehicleAnalyzer.readVehicle(fr);
        fr.close();
        VehicleAnalyzer.printPriceList(read);
        
        // Console
        
        VehicleAnalyzer.writeVehicle(v, 
                        new OutputStreamWriter(System.out));
        var read1 = VehicleAnalyzer.readVehicle(
                        new InputStreamReader(System.in));
        System.out.println(read.getClass().getName());
        VehicleAnalyzer.printPriceList(read1);
        
        // Byte IO
        VehicleAnalyzer.outputVehicle(v, 
                new FileOutputStream("rmp.bin"));
        IVehicle test = VehicleAnalyzer.inputVehicle(
                new FileInputStream("rmp.bin"));
        
        System.out.println(test.getClass().getName());
        VehicleAnalyzer.printPriceList(test);
        
        // Object IO
        
        var ois = new ObjectOutputStream(
                new FileOutputStream("rmp.bin"));
        ois.writeObject((Motocycle)v);
        ois.close();
        
        var ous = new ObjectInputStream(new FileInputStream("rmp.bin"));
        v = (Motocycle)ous.readObject();
        ous.close();
        
        VehicleAnalyzer.printPriceList(v);
    }
}
