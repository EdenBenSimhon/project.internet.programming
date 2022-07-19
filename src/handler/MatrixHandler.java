package handler;

import algorithm.BfsVisit;
import algorithm.BfsVisitWeight;
import algorithm.DfsVisit;
import struct.Index;
import struct.Matrix;
import struct.MatrixAsGraph;

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

        while(doWork) {
            switch (objectInputStream.readObject().toString()) {
                case "matrix": {
                    // expect to get a 2d array. handler will create a Matrix object
                    int[][] anArray = (int[][]) objectInputStream.readObject();
                    System.out.println("Got 2d array");
                    this.matrix = new Matrix(anArray);
                    this.matrix.printMatrix();
                    break;
                }
                case "find all ones": { //first mission
                    if (this.matrix != null) {
                        Collection<Index> allIndexOneList =
                                new ArrayList<>(this.matrix.getAllOne());
                        //System.out.println("get all one =" + allIndexOneList);
                        objectOutputStream.writeObject(allIndexOneList);
                    }
                    break;

                }
                case "get neighbors": {
                    this.sourceIndex = (Index) objectInputStream.readObject();
                    if (this.matrix != null) {
                        List<Index> neighbors =
                                new ArrayList<>(this.matrix.getNeighborsCross(this.sourceIndex));
                        System.out.println("my neighbors of index (1,1) is " + neighbors);
                        objectOutputStream.writeObject(neighbors);
                    }
                    break;
                }
                case "The shortest path": {
                    this.sourceIndex = (Index) objectInputStream.readObject();
                    this.destinationIndex = (Index) objectInputStream.readObject();
                    if (this.matrix != null) {
                        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(this.matrix);
                        matrixAsGraph.setSource(this.sourceIndex);
                        matrixAsGraph.setDestination(this.destinationIndex);
                        //DfsVisit<Index> algorithm = new DfsVisit<>();
                        //Set<Index> connectedComponent =
                        //      algorithm.traverse(matrixAsGraph,this.sourceIndex,this.destinationIndex);
                        //System.out.println(connectedComponent);
                        BfsVisit<Index> algorithm = new BfsVisit<>();
                        Set<Set<Index>> connectedComponent =
                                algorithm.traverse(matrixAsGraph, this.sourceIndex, this.destinationIndex);
                        objectOutputStream.writeObject(connectedComponent);
                    }
                    break;
                }
                case "weight":{
                    //this.sourceIndex = (Index) objectInputStream.readObject();
                    ///this.destinationIndex = (Index) objectInputStream.readObject();
                    if (this.matrix != null) {
                        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(this.matrix);
                        matrixAsGraph.setSource(this.sourceIndex);
                        matrixAsGraph.setDestination(this.destinationIndex);
                        BfsVisitWeight<Index> algorithm = new BfsVisitWeight<>();
                        Set<Set<Index>> lowestWeight =
                                algorithm.traverse(matrixAsGraph, this.sourceIndex, this.destinationIndex);
                        objectOutputStream.writeObject(lowestWeight);
                    }
                    break;
                }
                case "submarine": {
                    if (this.matrix != null) {
                        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(this.matrix);
                        DfsVisit<Index> algorithm = new DfsVisit<>();
                        Collection<Index> allIndexOneList = new ArrayList<>(this.matrix.getAllOne());
                        Set<Set<Index>> candidateBeSubmarine = new LinkedHashSet<>();
                        for (Index index : allIndexOneList) {
                            matrixAsGraph.setSource(index);
                            Set<Index> path = algorithm.traverse(matrixAsGraph);
                            candidateBeSubmarine.add(path);
                        }
                        boolean isCorrect = true;
                        for (Set<Index> path:candidateBeSubmarine) {
                            if(!algorithm.checkIfSubmarine(path) || path.size() == 1){

                               isCorrect= false;
                               candidateBeSubmarine.removeAll(path);
                            }
                        }
                        if ((isCorrect==true)){
                            objectOutputStream.writeObject(candidateBeSubmarine);

                        }
                        else {
                            Set<Set<Index>> candidateBeSubmarine1 = new LinkedHashSet<>();
                            objectOutputStream.writeObject(candidateBeSubmarine1);
                        }

                    }
                    break;
                }
                case "stop": {
                    doWork = false;
                    break;
                }
            }
        }
    }
}
