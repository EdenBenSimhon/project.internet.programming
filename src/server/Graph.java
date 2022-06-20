package server;

import java.util.Collection;

/**
 * This interface defines the common functionality required of all concrete graphs
 */
public interface Graph<T> {
    public abstract Node<T> getRoot();
    /*
    Collections in Java:
    Lists
    Sets
    Stacks
    Queues
     */
    public abstract Collection<Node<T>> getReachableNodes(Node<T> aNode);
}
