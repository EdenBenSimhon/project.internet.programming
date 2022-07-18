package server;

import java.util.Collection;

/**
 * This interface defines the common functionality required of all concrete graphs
 */
public interface Graph<T> {
    public abstract Node<T> getRoot();
    public abstract Node<T> getDes();
    public abstract int sumOfVertex();
    public abstract int getValueByIndex(Node<T> aNode);
    public abstract int getTotalSum();



    /*
    Collections in Java:
    Lists
    Sets
    Stacks
    Queues
     */
    public abstract Collection<Node<T>> getReachableNodes(Node<T> aNode);
    public abstract Collection<Node<T>> getReachableNodesCross(Node<T> aNode);
    public abstract Collection<Node<T>> getReachableNodesOnlyCross(Node<T> aNode);
    public abstract Collection<Node<T>> getReachableNodesWeight(Node<T> aNode);


}
