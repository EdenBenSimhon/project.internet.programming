package server;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        int[][] myArray = {
                {1,1,0},
                {0,1,1},
                {0,1,1}
        };

        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(new Matrix(myArray));
        System.out.println(matrixAsGraph.getInnerMatrix());
        matrixAsGraph.setSource(new Index(0,0));
        DfsVisit<Index> algorithm = new DfsVisit<>();
        Set<Index> connectedComponent = algorithm.traverse(matrixAsGraph);
        System.out.println(connectedComponent);



    }
}
