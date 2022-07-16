package server;

import jdk.dynalink.NoSuchDynamicMethodException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;

public class BfsVisit<T> implements Serializable {
    private Queue<Set<Node<T>>> queue;
    private Set<Node<T>> path;
    private Set<Set<Node<T>>> allPath;


    public BfsVisit(){
        queue = new LinkedList<>(); //queue
        path = new LinkedHashSet<>();//paths
        allPath = new LinkedHashSet<>(); //visited
    }
    public Set<Set<T>> traverse(@NotNull Graph<T> aGraph,T start, T end){
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

            Collection<Node<T>> reachableNodes = aGraph.getReachableNodes(last);
            for (Node<T> reachableNode : reachableNodes) {
                if(isNotVisited(reachableNode,path)) {
                    Set<Node<T>> newPath = new LinkedHashSet<>(path);
                    newPath.add(reachableNode);
                    queue.offer(newPath);
                    //paths.add(reachableNode.getParent());

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
    public boolean isNotVisited(Node<T> node, Set<Node<T>> path){
        int size = path.size();
            if (path.contains(node)){
                return false;
        }
        return true;
    }


}