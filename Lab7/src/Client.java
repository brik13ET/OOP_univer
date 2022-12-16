import Vehicle.Automobile;
import Vehicle.IVehicle;
import Vehicle.Scooter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        DataInputStream in;
        ObjectOutputStream out;
        Socket client;
        try {
             client=new Socket("localhost",8080);
             try{
                 out = new ObjectOutputStream(client.getOutputStream());
                 in = new DataInputStream(client.getInputStream());
                 IVehicle[] mas={new Automobile("",10), new Scooter("",22)};
                 out.writeObject(mas);
                 System.out.println("avg="+in.readDouble());

             }

             finally {
                 client.close();
             }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
