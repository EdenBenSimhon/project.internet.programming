package client;

import model.Index;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

/**
 * The class create client socket and send request to InternetServer
 */
public class Client {

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Socket clientSocket = new Socket("127.0.0.1",8010);
            System.out.println("Socket created");
            ObjectOutputStream toServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(clientSocket.getInputStream());
            boolean doWork = true;
            while (doWork){
                System.out.print("\nPlease enter the task:\n" +
                        "1. Find all the ones in the matrix (write: find all ones)\n" +
                        "2. Finding the shortest path from a source index to a target index (write: The shortest path)\n" +
                        "3. The submarine game (write: submarine)\n" +
                        "4. Finding the easiest route (write: weight)\n" +
                        "5. To disconnect from the server (write: stop)\n");
                Scanner sc= new Scanner(System.in);
                String input= sc.nextLine();              //reads string
                System.out.println("\nYour choice is:" + input);
                int[][] sourceArray = {
                        {1,1,0,0,0},
                        {1,0,0,0,0},
                        {1,0,0,0,0}
                };
                toServer.writeObject("matrix");
                toServer.writeObject(sourceArray);
                Index sourceIndex = new Index(0,0);
                Index destinationIndex = new Index(2,2);
                switch (input) {
                    case "find all ones": {
                        toServer.writeObject("find all ones");
                        List<Index> allOne = new ArrayList<Index>((List<Index>) fromServer.readObject());
                        System.out.println("########################");
                        System.out.println("All indexes that contain one: " + allOne);
                        System.out.println("########################");
                        break;
                    }
                    case "The shortest path": {
                        toServer.writeObject("The shortest path");
                        toServer.writeObject(sourceIndex); //source
                        toServer.writeObject(destinationIndex); //destination
                        toServer.writeObject(sourceArray);
                        Set<Set<Index>> connectedComponent = new LinkedHashSet<Set<Index>>((Set<Set<Index>>) fromServer.readObject());
                        System.out.println("########################");
                        System.out.println("The shortest paths form: " + sourceIndex + " to " + destinationIndex + " is :" + connectedComponent);
                        System.out.println("########################");
                        break;
                    }
                    case "submarine": {
                        toServer.writeObject("submarine");
                        Set<Set<Index>> submarines = new LinkedHashSet<Set<Index>>((Set<Set<Index>>) fromServer.readObject());
                        //need fix the result
                        if(submarines.size()==0){
                            System.out.println("########################");
                            System.out.println("According to the rules of the game the input is not correct");
                            System.out.println("########################");
                        }
                        else {
                            System.out.println("########################");
                            System.out.println("\nsubmarine:" + submarines + "\n The size is : " + submarines.size());
                            System.out.println("########################");
                        }
                        break;
                    }
                    case "weight": {
                        int[][] sourceArray1 = {
                                {500, 100, 100},
                                {1000, 0, 100},
                                {500, 100, 10}
                        };
                        toServer.writeObject("matrix");
                        toServer.writeObject(sourceArray1);
                        toServer.writeObject("weight");
                        toServer.writeObject(sourceIndex); //source
                        toServer.writeObject(destinationIndex);
                        Set<Set<Index>> lowestWeight = new LinkedHashSet<Set<Index>>((Set<Set<Index>>) fromServer.readObject());
                        System.out.print("\nThe weightiest paths from " + sourceIndex + " to " + destinationIndex + " is :" + lowestWeight);

                        break;
                    }
                    case "stop": {
                        doWork=false;
                        toServer.writeObject("stop");
                        break;
                    }
                }
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
