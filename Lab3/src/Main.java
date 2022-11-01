
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
        IVehicle v = new Motocycle("Hurrington Motors", 42);
        //IVehicle v = new Automobile("Hyndai",55);

        //VehicleAnalyzer.printPriceList(v);
        System.out.println();
        /*
        VehicleAnalyzer.writeVehicle(v,
                new PrintWriter(System.out));
        var read = VehicleAnalyzer.readVehicle(
                new BufferedReader(
                        new InputStreamReader(System.in)));
        System.out.println(read.getClass().getName());
        VehicleAnalyzer.printPriceList(read);
        */
        /*
        FileOutputStream fos = new FileOutputStream("rmp.bin");
        var dos = new DataOutputStream(fos);
        VehicleAnalyzer.outputVehicle(v, dos);
        fos.close();
        
        FileInputStream fis = new FileInputStream("rmp.bin");
        var dis = new DataInputStream(fis);
        IVehicle test = VehicleAnalyzer.inputVehicle(dis);
        fis.close();
        System.out.println(test.getClass().getName());
        VehicleAnalyzer.printPriceList(test);
        */
        var ois = new ObjectOutputStream(new FileOutputStream("rmp.bin"));
        ois.writeObject((Motocycle)v);
        ois.close();
        var ous = new ObjectInputStream(new FileInputStream("rmp.bin"));
        v = (Motocycle)ous.readObject();
        ous.close();
        
        VehicleAnalyzer.printPriceList(v);
        
    }
}
