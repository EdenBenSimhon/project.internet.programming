package algorithm;

import org.jetbrains.annotations.NotNull;
import struct.Graph;
import struct.Node;

import java.io.Serializable;
import java.util.*;

public class BfsVisitWeight<T> implements Serializable {
    private Queue<Set<Node<T>>> queue;
    private Set<Node<T>> path;
    private Set<Set<Node<T>>> allPath;


    public BfsVisitWeight(){
        queue = new LinkedList<>(); //queue
        path = new LinkedHashSet<>();//paths
        allPath = new LinkedHashSet<>(); //visited
    }
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
        Set<Set<T>> shortestPaths = new LinkedHashSet<>();
        int tempSum = 0 ;
        int sum = findTheMinSum(aGraph,allPath);
        Set<T> blackSet = new LinkedHashSet<>();
        for (Set<Node<T>> path:allPath) {
            for (Node<T> node:path) {
                blackSet.add(node.getData());
                tempSum += aGraph.getValueByIndex(node);
            }
            if(sum == tempSum) {
                shortestPaths.add(new LinkedHashSet<T>((Collection<? extends T>) blackSet));
            }
            tempSum=0;
            blackSet.clear();
        }
        System.out.println("The weightiest paths is :" + shortestPaths);

        return shortestPaths;
    }

    public boolean isNotVisited(Node<T> node, Set<Node<T>> path){
        int size = path.size();
        if (path.contains(node)){
            return false;
        }
        return true;
    }
    //need change the function to find the min on matrix
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