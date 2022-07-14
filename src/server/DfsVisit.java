package server;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class DfsVisit<T> implements Serializable {
    private Stack<Node<T>> workingStack;
    private Set<Node<T>> finished;

    public DfsVisit(){
        workingStack = new Stack<>(); //visited
        finished = new LinkedHashSet<>();//queue
    }

    public Set<T> traverse(@NotNull Graph<T> aGraph,T src , T dst){
        workingStack.push((aGraph.getRoot()));
        boolean isFound = false;
        while (!workingStack.empty() && !isFound){
            Node<T> removed = workingStack.pop();


             // the last index
            finished.add(removed);
            System.out.println(removed.getData());
             Collection<Node<T>> reachableNodes = aGraph.getReachableNodes(removed);
             Set<Set<Node<T>>> answerSet = new LinkedHashSet<>();
             for(Node<T> reachableNode :reachableNodes){

                 if (!finished.contains(reachableNode) &&
                 !workingStack.contains(reachableNode)){
                     workingStack.push(reachableNode);
                     //System.out.println(dst+"rech"+reachableNode.getData());
                     if(reachableNode.getData().equals(dst)){
                         //workingStack.push(aGraph.getDes());
                         finished.add(aGraph.getDes());
                         isFound=true;
                         break;
                     }
                 }

             }
        }
        Set<T> blackSet = new LinkedHashSet<>();
        int size = 0;

        for (Node<T> node: finished)
            //if (node.getData()!=dst) {
             //   size++;
                blackSet.add(node.getData());
           // }
        finished.clear();
        return blackSet;
    }











}
