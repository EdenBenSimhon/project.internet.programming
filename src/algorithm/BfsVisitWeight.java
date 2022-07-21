package algorithm;

import org.jetbrains.annotations.NotNull;
import model.Graph;
import model.Node;

import java.io.Serializable;
import java.util.*;

/** The class is bfs algorithm that find the lowest path
 * @author Eden Ben Simhon
 * @author Dan Yakobi
 *
 * @param <T>
 */

public class BfsVisitWeight<T> implements Serializable{
    private Queue<Set<Node<T>>> queue;
    private Set<Node<T>> path;
    private Set<Set<Node<T>>> allPath;

    /**
     * Constructor
     */
    public BfsVisitWeight(){
        queue = new LinkedList<>(); //queue
        path = new LinkedHashSet<>();//paths
        allPath = new LinkedHashSet<>(); //visited
    }

    /**
     * This method find the all paths with the lowest weight from the source index to the destination index
     * with bfs algorithm
     * @param aGraph
     * @param start
     * @param end
     * @return Set<Set<T>> lowestPaths
     */
    public Set<Set<T>> traverse(@NotNull Graph<T> aGraph, T start, T end){
        path.clear();
        path.add(aGraph.getRoot());
        queue.offer(path);

        while (!queue.isEmpty()) {
            path=queue.poll();
            Node<T> last= new Node<>();
            for (Node<T> temp:path) {
                last = temp;
            }
            if (last.equals(aGraph.getDes())){
                allPath.add(path);
            }

            Collection<Node<T>> reachableNodes = aGraph.getReachableNodesWeight(last);
            List<Integer> sumList = new ArrayList<>();
            for (Node<T> reachableNode : reachableNodes) {
                if(isNotVisited(reachableNode,path)) {
                    Set<Node<T>> newPath = new LinkedHashSet<>(path);
                    newPath.add(reachableNode);
                    queue.offer(newPath);
                }

            }
        }
        Set<Set<T>> lowestPaths = new LinkedHashSet<>();
        int tempSum = 0 ;
        int sum = findTheMinSum(aGraph,allPath);
        Set<T> blackSet = new LinkedHashSet<>();
        for (Set<Node<T>> path:allPath) {
            for (Node<T> node:path) {
                blackSet.add(node.getData());
                tempSum += aGraph.getValueByIndex(node);
            }
            if(sum == tempSum) {
                lowestPaths.add(new LinkedHashSet<T>((Collection<? extends T>) blackSet));
            }
            tempSum=0;
            blackSet.clear();
        }
        System.out.println("The weightiest paths is :" + lowestPaths);

        return lowestPaths;
    }

    /**
     *The method is part of the bfs algorithm ,check if the algorithm visit on this node
     * @param node
     * @param path
     * @return boolean true/false
     */
    public boolean isNotVisited(Node<T> node, Set<Node<T>> path){
        int size = path.size();
        if (path.contains(node)){
            return false;
        }
        return true;
    }

    /**
     * The method find the minimum sum from all paths
     * @param tGraph
     * @param listSet
     * @return sum
     */
    public int findTheMinSum(Graph<T> tGraph , Set<Set<Node<T>>> listSet){
        int temp = 0;
        int sum = tGraph.getTotalSum();
        for (Set<Node<T>> path:listSet) {
            for (Node<T> node : path) {
                temp +=tGraph.getValueByIndex(node);
            }
            if(temp <= sum) {
                sum = temp;
            }
            temp=0;
        }
        System.out.println("the sum is "+ sum);
        return sum;
    }


}