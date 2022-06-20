package server;

import java.io.Serializable;
import java.util.Objects;

public class Node<T> implements Serializable {
    private static final Long serialVersionUID = 1L;

    private T data;
    private Node<T> parent;

    public Node(T data, Node<T> parent){
        this.data = data;
        this.parent = parent;
    }

    public Node(T data){
        this(data,null);
    }

    public Node(){

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> state1 = (Node<?>) o;
        return Objects.equals(data, state1.data);
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode():0;
    }
}

