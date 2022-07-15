package server;

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
        Set<T> blackSet = new LinkedHashSet<>();//need return Set<Set<T>>
        Set<Set<T>> shortestPaths = new LinkedHashSet<Set<T>>();
        int counter=0;
        int min =aGraph.sumOfVertex();
        System.out.println("min"+min);
        blackSet.clear();

        for (Set<Node<T>> path:allPath) {
            blackSet.clear();
            for (Node<T> node:path) {
                blackSet.add(node.getData());
                counter++;
            }
            System.out.println("\n blackset" + blackSet);
            if(counter <= min){
                min=counter;
                shortestPaths.add(blackSet);
                System.out.println("The"+shortestPaths);
                counter=0;
            }
            blackSet.clear();
        }
        System.out.println("***************"+shortestPaths+"***************");
        return shortestPaths;
    }
    public boolean isNotVisited(Node<T> node, Set<Node<T>> path){
        int size = path.size();
            if (path.contains(node)){
                return false;
        }
        return true;
    }
 /*   public Set<Set<T>> clearThePaths (Set<Set<Node<T>>> lists){
        Set<Set<T>> blackSet = new LinkedHashSet<>();
        int counter = lists.iterator().next().size();
        for (Set<Node<T>> list:lists) {
            blackSet.add((list);
        }

        return lists;
    }

*/

}