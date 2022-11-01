
import Vehicle.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

class Main {

    public static void main(String argv[])
            throws
            DuplicateModelNameException,
            NoSuchModelNameException,
            FileNotFoundException,
            IOException
    {
        IVehicle v = new Motocycle("Hurrington Motors", 3);
        //IVehicle v = new Automobile("Hyndai",5);

        System.out.println("MAnufacter:\t" + v.getManufacture());
        System.out.println();
        
        /*
        VehicleAnalyzer.printPriceList(v);
        System.out.println();
        
        VehicleAnalyzer.writeVehicle(v, new OutputStreamWriter(System.out));
        Motocycle read = VehicleAnalyzer.readMotocycle(new InputStreamReader(System.in));
        VehicleAnalyzer.printPriceList(read);
        */
        /*
        var f = new File("rmp.bin");
        f.createNewFile();
        var fos = new FileOutputStream(f, false);
        VehicleAnalyzer.outputVehicle(v, fos);
        fos.close();
        
        var fis = new FileInputStream(f);
        IVehicle test = VehicleAnalyzer.inputAutomobile(fis);
        VehicleAnalyzer.printPriceList(test);
        */
    }
}
