package algorithm;

import model.Graph;
import model.Index;
import model.Node;

import java.util.*;

/** The class is dfs algorithm
 * @param <T>
 */
public class DfsVisit<T> {
    private Stack<Node<T>> workingStack;
    private Set<Node<T>> finished;

    /**
     * constructor
     */
    public DfsVisit(){
        workingStack = new Stack<>();
        finished = new LinkedHashSet<>();
    }

    /**
     *This method find the longest path
     * @param aGraph
     * @return
     */
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

    /**
     * The method check if the path is submarine using algorithm for finding sub-matrices
     * @param path
     * @return boolean true/false
     */
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
