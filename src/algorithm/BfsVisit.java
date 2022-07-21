package algorithm;

import org.jetbrains.annotations.NotNull;
import model.Graph;
import model.Node;

import java.io.Serializable;
import java.util.*;

/** The class is bfs algorithm that find the shortest path
 * @author Eden Ben Simhon
 * @author Dan Yakobi
 *
 * @param <T>
 */

public class BfsVisit<T> implements Serializable {
    private Queue<Set<Node<T>>> queue;
    private Set<Node<T>> path;
    private Set<Set<Node<T>>> allPath;

    /**
     *  Constructor
     */
    public BfsVisit(){
        queue = new LinkedList<>(); //queue
        path = new LinkedHashSet<>();//paths
        allPath = new LinkedHashSet<>(); //visited
    }
    /**
     * This method find the all paths with the shortest path from the source index to the destination index
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

            Collection<Node<T>> reachableNodes = aGraph.getReachableNodesCross(last);
            for (Node<T> reachableNode : reachableNodes) {
                if(isNotVisited(reachableNode,path)) {
                    Set<Node<T>> newPath = new LinkedHashSet<>(path);
                    newPath.add(reachableNode);
                    queue.offer(newPath);
                }

            }
        }
        Set<Set<T>> shortestPaths = new LinkedHashSet<>();
        int counter=0;
        int min =aGraph.sumOfVertex();
        Set<T> blackSet = new LinkedHashSet<>();
        for (Set<Node<T>> path:allPath) {
            for (Node<T> node:path) {
                blackSet.add(node.getData());
                counter++;
            }
            if(counter <= min){
                min=counter;
                counter=0;
                shortestPaths.add(new LinkedHashSet<T>((Collection<? extends T>) blackSet));
            }
            blackSet.clear();
        }
        System.out.println("The shortest paths is :" +shortestPaths);
        return shortestPaths;
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

}