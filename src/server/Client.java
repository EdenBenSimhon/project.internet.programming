package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Socket clientSocket = new Socket("127.0.0.1",8010);
            System.out.println("Socket created");

            ObjectOutputStream toServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(clientSocket.getInputStream());

            int[][] sourceArray = {
                    {1,0,0},
                    {1,0,1},
                    {1,1,1}
            };

            toServer.writeObject("matrix");
            toServer.writeObject(sourceArray);

            toServer.writeObject("find all ones");

            List<Index> allOne = new ArrayList<Index>((List<Index>)fromServer.readObject());
            System.out.println("all one Index "+ allOne);


            Index index1 = new Index(0,0);
            Index index2 = new Index(1,1);

            toServer.writeObject("get neighbors");
            toServer.writeObject(index2);

            List<Index> neighbors = new ArrayList<Index>((List<Index>)fromServer.readObject());
            System.out.println("Neighbors of " + index2 + ": " + neighbors);

            toServer.writeObject("connected component");
            toServer.writeObject(index1); //source
            toServer.writeObject(index2); //destination

            Set<Index> connectedComponent = new LinkedHashSet<Index>((Set<Index>)fromServer.readObject());
            System.out.println("The shortest routes from " + index1 + " to "+ index2+" is :"+ connectedComponent);
            toServer.writeObject("stop");




        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
