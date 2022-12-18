import Vehicle.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {

    public static void main(String[] args) {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket client;
        Random rnd = new Random();
        try {
            client=new Socket("localhost",8080);
            try{
                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream(client.getInputStream());
                IVehicle[] mas=
                {
                    new Automobile("",rnd.nextInt(30)),
                    new Scooter("",rnd.nextInt(30)),
                    new Moped("", rnd.nextInt(30)),
                    new Quadricycle("", rnd.nextInt(30))
                };
                out.writeObject(mas);
                System.out.println("avg:\t"+in.readDouble() + "\nlocal: " + VehicleAnalyzer.doAvgVararg(mas));

            }

            finally {
                client.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
