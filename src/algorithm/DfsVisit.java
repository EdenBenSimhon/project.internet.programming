package algorithm;

import struct.Graph;
import struct.Index;
import struct.Matrix;
import struct.Node;

import java.util.*;

public class DfsVisit<T> {
    private Stack<Node<T>> workingStack;
    private Set<Node<T>> finished;

    public DfsVisit(){
        workingStack = new Stack<>();
        finished = new LinkedHashSet<>();
    }

    public Set<T> traverse(Graph<T> aGraph){
        workingStack.push(aGraph.getRoot());
        while (!workingStack.empty()){
            Node<T> removed = workingStack.pop();
            finished.add(removed);
            Collection<Node<T>> reachableNodes = aGraph.getReachableNodes(removed);
            for(Node<T> reachableNode :reachableNodes){
                if (!finished.contains(reachableNode) &&
                        !workingStack.contains(reachableNode)){
                    workingStack.push(reachableNode);
                }
            }
        }
        Set<T> blackSet = new LinkedHashSet<>();
        for (Node<T> node: finished)
            blackSet.add(node.getData());
        finished.clear();
        return blackSet;
    }
    public boolean checkIfSubmarine (Set<Index> path) {
        int sizeOfRow = 0;
        int sizeOfCol = 0;
        int maxRow = 0;
        int minRow = 1000000;
        int maxCol = 0;
        int minCol = 1000000;
        for (Index index : path) {
            int tempRow = index.getSizeOfRow();
            int tempCol = index.getSizeOfCol();
            if (tempRow > maxRow) {
                maxRow = tempRow;
            }
            if (tempRow < minRow) {
                minRow = tempRow;
            }
            if (tempCol > maxCol) {
                maxCol = tempCol;
            }
            if (tempCol < minCol) {
                minCol = tempCol;
            }
        }
        sizeOfRow = maxRow - minRow + 1;
        sizeOfCol = maxCol - minCol + 1;
        if ((path.size() == sizeOfRow * sizeOfCol)) {
            return true;
        }

        return false;

    }












}
