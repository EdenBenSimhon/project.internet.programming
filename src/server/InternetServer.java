package server;

import handler.IHandler;
import handler.MatrixHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InternetServer {

    private final int port;
    private boolean stopServer; // TODO: transparency between threads
    private ThreadPoolExecutor clientsPool; // handle multiple clients concurrently

    private IHandler requestHandler;

    public InternetServer(int port){
        this.port = port;
        this.clientsPool = null;
        this.requestHandler = null;
        this.stopServer = false; // if server should handle clients' requests
    }

    public void supportClients(IHandler concreteHandler){
        this.requestHandler = concreteHandler;

        Runnable clientHandling = ()->{
            this.clientsPool =
                    new ThreadPoolExecutor(
                            10,15,200, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<>()); //all client
            try {
                ServerSocket serverSocket = new ServerSocket(this.port,50);
                while (!stopServer){
                    // listen + accept (phases 3+4), are done by accept method

                    Socket clientToServerConnection = serverSocket.accept();
                    Runnable specificClientHandling = ()->{
                         System.out.println("Server: Handling a client in " + Thread.currentThread().getName() +
                                 " Thread");
                         try {
                             this.requestHandler.handleClient(clientToServerConnection.getInputStream()
                                     ,clientToServerConnection.getOutputStream());
                         } catch (IOException | ClassNotFoundException ioException) {
                             ioException.printStackTrace();
                         }
                         // we stopped handling the specific client
                         try {
                             clientToServerConnection.getOutputStream().close();
                             clientToServerConnection.getInputStream().close();
                             clientToServerConnection.close();
                         } catch (IOException ioException) {
                             ioException.printStackTrace();
                         }
                     };
                     clientsPool.execute(specificClientHandling);
                }
                serverSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        new Thread(clientHandling).start();
    }

    public void stop(){
        if (!stopServer){
            stopServer = true;
            if (clientsPool!=null)
                clientsPool.shutdown();
        }

    }

    public static void main(String[] args) {
        InternetServer server = new InternetServer(8010);
        server.supportClients(new MatrixHandler());
    }

}
