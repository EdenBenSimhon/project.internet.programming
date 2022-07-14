package server;

import server.IHandler;

import java.io.*;
import java.util.*;

/**
 *  This class handles Matrix-related tasks
 * Adapts the functionality  of IHandler to a Matrix object
 */
public class MatrixHandler implements IHandler {
    private Matrix matrix;
    private Index sourceIndex;
    private Index destinationIndex;
    private boolean doWork;


    @Override
    public void handleClient(InputStream fromClient, OutputStream toClient) throws IOException, ClassNotFoundException {
        /*
        data is sent eventually as bytes
        read data as bytes then transform to meaningful data
        ObjectInputStream and ObjectOutputStream can read and write both primitives
        and Reference types
         */
        ObjectInputStream objectInputStream = new ObjectInputStream(fromClient);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(toClient);

        doWork = true;

        while(doWork){
            switch(objectInputStream.readObject().toString()){
                case "matrix":{
                // expect to get a 2d array. handler will create a Matrix object
                int[][] anArray = (int[][])objectInputStream.readObject();
                System.out.println("Got 2d array");
                this.matrix = new Matrix(anArray);
                this.matrix.printMatrix();
                break;
            }
                case "find all ones":{ //first mission
                    if(this.matrix!=null) {
                        Collection<Index> allIndexOneList =
                                new ArrayList<>(this.matrix.getAllOne());
                        System.out.println("get all one =" +allIndexOneList);
                        objectOutputStream.writeObject(allIndexOneList);
                    }
                    break;

                }

            case "get neighbors":{
                this.sourceIndex = (Index)objectInputStream.readObject();
                if (this.matrix!=null){
                    List<Index> neighbors =
                            new ArrayList<>(this.matrix.getNeighbors(this.sourceIndex));
                    System.out.println("my neighbors of index (1,1) is " + neighbors);
                    objectOutputStream.writeObject(neighbors);
                }
                break;
            }

            case "connected component":{
                this.sourceIndex = (Index)objectInputStream.readObject();
                this.destinationIndex= (Index)objectInputStream.readObject();
                if (this.matrix!=null){
                    MatrixAsGraph matrixAsGraph = new MatrixAsGraph(this.matrix);
                    matrixAsGraph.setSource(this.sourceIndex);
                    matrixAsGraph.setDestination(this.destinationIndex);
                    DfsVisit<Index> algorithm = new DfsVisit<>();
                    Set<Index> connectedComponent =
                            algorithm.traverse(matrixAsGraph,this.sourceIndex,this.destinationIndex);
                    System.out.println(connectedComponent);
                    objectOutputStream.writeObject(connectedComponent);
                }
                break;
            }

            case "stop":{
                doWork = false;
                break;
            }
        }
        }




    }
}
