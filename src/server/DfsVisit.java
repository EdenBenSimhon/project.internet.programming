package server;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;

public class DfsVisit<T> implements Serializable {
    private Queue<Node<T>> workingStack;
    private Set<Node<T>> finished;

    public DfsVisit(){
        workingStack = new ArrayDeque<>(); //visited
        finished = new LinkedHashSet<>();//queue
    }

    public Set<T> traverse(@NotNull Graph<T> aGraph,T src , T dst){
        workingStack.add((aGraph.getRoot()));

        Set<Set<Node<T>>> answerSet = new LinkedHashSet<>();



        while (!workingStack.isEmpty()){
            Node<T> removed = workingStack.poll();

             // the last index
            finished.add(removed);
            //System.out.println(removed.getData());
             Collection<Node<T>> reachableNodes = aGraph.getReachableNodes(removed);
             for(Node<T> reachableNode :reachableNodes){

                 if (!finished.contains(reachableNode) &&
                 !workingStack.contains(reachableNode)){
                     workingStack.add(reachableNode);
                     System.out.println("The reachable node of Index" +removed.getData() +"is:"+reachableNode.getData());
                     if(reachableNode.getData().equals(dst)){


                         //workingStack.push(aGraph.getDes());
                         //finished.add(aGraph.getDes());
                         answerSet.add(finished);
                         for (Node<T> node: finished){
                             //System.out.println(node.getData());
                         }

                     }
//                     answerSet.add(reachableNode.getParent());
                 }

             }
        }
        Set<T> blackSet = new LinkedHashSet<>();

        for (Set<Node<T>> nodeSet : answerSet) {
            System.out.println();
            for (Node<T> node:nodeSet) {

                blackSet.add(node.getData());
            }
        }





      //  for (Node<T> node: finished)
            //if (node.getData()!=dst) {
             //   size++;


        finished.clear();
        return blackSet;
    }











}
