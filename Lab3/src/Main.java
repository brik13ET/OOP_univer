
import Vehicle.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        //IVehicle v = new Automobile("Hyndai",5);

        //VehicleAnalyzer.printPriceList(v);
        System.out.println();
        // Symbol IO
        // File
        
        System.out.println("Символьные запись и чтение в файл");
        var fr = new FileReader("obj.txt");
        var fw = new FileWriter("obj.txt");
        
        VehicleAnalyzer.writeVehicle(v, fw);
        fw.close();
        var read = VehicleAnalyzer.readVehicle(fr);
        fr.close();
        VehicleAnalyzer.printPriceList(read);
        
        // Console
        System.out.println("Символьные запись и чтение в консоль");

        VehicleAnalyzer.writeVehicle(v, 
                        new OutputStreamWriter(System.out));
        var read1 = VehicleAnalyzer.readVehicle(
                        new InputStreamReader(System.in));
        System.out.println(read.getClass().getName());
        VehicleAnalyzer.printPriceList(read1);
        
        System.out.println("Битовые запись и чтение в файл");

        // Byte IO
        VehicleAnalyzer.outputVehicle(v, 
                new FileOutputStream("rmp.bin"));
        IVehicle test = VehicleAnalyzer.inputVehicle(
                new FileInputStream("rmp.bin"));
        
        System.out.println(test.getClass().getName());
        VehicleAnalyzer.printPriceList(test);
        
        
        System.out.println("Битовые запись и чтение в файл (ObjectStream)");
        
        // Object IO
        
        var ois = new ObjectOutputStream(new FileOutputStream("rmp.bin"));
        ois.writeObject(v);
        ois.close();
        
        var ous = new ObjectInputStream(new FileInputStream("rmp.bin"));
        v = (IVehicle)ous.readObject();
        ous.close();
        
        VehicleAnalyzer.printPriceList(v);
        
    }
}
