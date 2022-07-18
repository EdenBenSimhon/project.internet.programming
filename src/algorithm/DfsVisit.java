package algorithm;

import struct.Graph;
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











}
