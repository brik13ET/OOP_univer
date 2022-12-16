import Vehicle.IVehicle;
import Vehicle.VehicleAnalyzer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSerial {
    public static void main(String[] args) {
        ObjectInputStream in;
        DataOutputStream out;
        ServerSocket server;
        Socket client;
        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            try {
                client = server.accept();
                System.out.println("Connection is success");
                try {
                    out = new DataOutputStream(client.getOutputStream());
                    in = new ObjectInputStream(client.getInputStream());
                    IVehicle[] mas = (IVehicle[]) in.readObject();
                    System.out.println("recv");
                    out.writeDouble(VehicleAnalyzer.doAvgVararg(mas));
                    System.out.println("transmit");
                }
                finally {
                    client.close();
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
