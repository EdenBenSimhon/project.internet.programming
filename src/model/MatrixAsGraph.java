package model;

import algorithm.DfsVisit;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class uses the adapter pattern, also known as
 * wrapper/decorator/adapter.
 * It adapts a Matrix to the functionality of Graph Interface
 */
public class MatrixAsGraph extends DfsVisit implements Graph<Index>, Serializable {
    private Matrix innerMatrix;
    private Index source;
    private Index destination;

    /**
     * Constructor
     * @param matrix
     */
    public MatrixAsGraph(@NotNull Matrix matrix) {
        this.innerMatrix = matrix;
    }

    /**
     * Constructor
     */
    public MatrixAsGraph() {
        this.innerMatrix = new Matrix(3, 3);
        source = new Index(0, 0);
    }

    public Matrix getInnerMatrix() {
        return innerMatrix;
    }

    public Index getSource() {
        return source;
    }

    public void setSource(@NotNull Index source) {
        if ((source.row >= 0 && source.row < innerMatrix.primitiveMatrix.length)
                && (source.column >= 0 &&
                source.column < innerMatrix.primitiveMatrix[0].length)) {
            this.source = source;
        }
    }

    public Index getDestination() {
        return destination;
    }

    public void setDestination(@NotNull Index destination) {
        if ((destination.row >= 0 && destination.row < innerMatrix.primitiveMatrix.length)
                && (destination.column >= 0 &&
                destination.column < innerMatrix.primitiveMatrix[0].length)) {
            this.destination = destination;
        }
    }


    @Override
    public Node<Index> getRoot() {
        return new Node<>(source);
    }

    @Override
    public Node<Index> getDes() {
        return new Node<>(destination);
    }

    /**
     * This method count the vertex
     */
    @Override
    public int sumOfVertex() {
        int row = innerMatrix.primitiveMatrix.length;
        int col = innerMatrix.primitiveMatrix[0].length;
        int size = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                size++;
            }
        }
        return size;
    }

    /**
     * A reachable node is a node that wraps a neighboring index whose value is equal to 1 ,
     * move up , down , left ,right
     *
     * @param aNode
     * @return Collection
     */
    @Override
    public Collection<Node<Index>> getReachableNodes(Node<Index> aNode) {
        if (innerMatrix.getValue(aNode.getData()) == 1) {
            List<Node<Index>> reachableNodes = new ArrayList<>();
            for (Index index : innerMatrix.getNeighbors(aNode.getData())) {
                if (innerMatrix.getValue(index) == 1) {
                    Node<Index> singleNode = new Node<>(index, aNode);
                    reachableNodes.add(singleNode);
                }
            }
            return reachableNodes;
        }
        return null;
    }
    /**
     * A reachable node is a node that wraps a neighboring index whose value is equal to 1 ,
     * move : up , down , left ,right and cross
     *
     * @param aNode
     * @return Collection
     */
    public Collection<Node<Index>> getReachableNodesCross(Node<Index> aNode) {
        if (innerMatrix.getValue(aNode.getData()) == 1) {
            List<Node<Index>> reachableNodes = new ArrayList<>();
            for (Index index : innerMatrix.getNeighborsCross(aNode.getData())) {
                if (innerMatrix.getValue(index) == 1) {
                    Node<Index> singleNode = new Node<>(index, aNode);
                    reachableNodes.add(singleNode);
                }
            }
            return reachableNodes;
        }
        return null;
    }

    /**
     * A reachable node is a node that wraps a neighboring index whose value is equal to 1 ,
     * move : cross
     *
     * @param aNode
     * @return Collection
     */
    public Collection<Node<Index>> getReachableNodesOnlyCross(Node<Index> aNode) {
        if (innerMatrix.getValue(aNode.getData()) == 1) {
            List<Node<Index>> reachableNodes = new ArrayList<>();
            for (Index index : innerMatrix.getNeighborsOnlyCross(aNode.getData())) {
                if (innerMatrix.getValue(index) == 1) {
                    Node<Index> singleNode = new Node<>(index, aNode);
                    reachableNodes.add(singleNode);
                }
            }
            return reachableNodes;
        }
        return null;
    }

    /**
     * An accessible node is a node that encloses a neighboring index and summarizes it
     * * move : up , down , left ,right and cross
     *
     * @param aNode
     * @return Collection
     */
    public Collection<Node<Index>> getReachableNodesWeight(Node<Index> aNode) {
            List<Node<Index>> reachableNodes = new ArrayList<>();
            for (Index index : innerMatrix.getNeighborsCross(aNode.getData())) {
                    Node<Index> singleNode = new Node<>(index, aNode);
                    reachableNodes.add(singleNode);

            }
            return reachableNodes;

    }

    /**
     * This method calculate the total sum of the matrix
     * @return sum
     */
    public int getTotalSum(){
        int sum = 0 ;
        int row = innerMatrix.primitiveMatrix.length;
        int col = innerMatrix.primitiveMatrix[0].length;
        for (int i = 0; i <row; i++) {
            for (int j = 0; j < col; j++) {
                Index index = new Index(i,j);
                sum += getInnerMatrix().getValue(index);
            }
        }
        return sum;
    }

    /**
     * This method check the value by the index
     * @param aNode
     * @return
     */
    public int getValueByIndex (Node<Index> aNode){
        return innerMatrix.getValue(aNode.getData());
    }


}
