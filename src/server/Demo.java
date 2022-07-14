package server;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        int[][] myArray = {
                {1,0,0},
                {1,1,0},
                {1,1,0}
        };

        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(new Matrix(myArray));
        System.out.println(matrixAsGraph.getInnerMatrix());
        matrixAsGraph.setSource(new Index(0,0));
        matrixAsGraph.setDestination(new Index(1,1));
        DfsVisit<Index> algorithm = new DfsVisit<>();
        Set<Index> connectedComponent = algorithm.traverse(matrixAsGraph,matrixAsGraph.getSource(),matrixAsGraph.getDestination());
        System.out.println(connectedComponent);



    }
}
