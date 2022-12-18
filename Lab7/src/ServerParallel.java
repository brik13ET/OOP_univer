import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Threads.Worker;

public class ServerParallel {
    
    private final static int MAX_CLIENT = 5;
    
    private static ExecutorService tp;
    
    public static void main(String[] args) throws IOException {
        ServerSocket server;
        tp = Executors.newFixedThreadPool(MAX_CLIENT);
        
        try {
            server = new ServerSocket(8080);
            while(true) {
                Socket sock = server.accept();
                System.out.println("Connection is success");
                Worker w = new Worker(sock);
                tp.submit(w);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            tp.shutdown();
        }    
    }
}
