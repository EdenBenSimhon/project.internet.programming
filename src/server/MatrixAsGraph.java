package server;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
1 0 0
1 1 1
0 1 1
 */
/**
 * This class uses the adapter pattern, also known as
 * wrapper/decorator/adapter.
 * It adapts a Matrix to the functionality of Graph Interface
 */
public class MatrixAsGraph implements Graph<Index>, Serializable {
    private Matrix innerMatrix;
    private Index source;

    public MatrixAsGraph(@NotNull Matrix matrix){
        this.innerMatrix = matrix;
    }

    public MatrixAsGraph(){
        this.innerMatrix = new Matrix(3,3);
        source = new Index(0,0);
    }

    public Matrix getInnerMatrix() {
        return innerMatrix;
    }

    public Index getSource() {
        return source;
    }

    public void setSource(@NotNull Index source){
        if((source.row >=0 && source.row<innerMatrix.primitiveMatrix.length)
        && (source.column >=0 &&
                source.column < innerMatrix.primitiveMatrix[0].length)){
            this.source = source;
        }

    }

    @Override
    public Node<Index> getRoot() {
        return new Node<>(source);
    }

    /**
     * A reachable node is a node that wraps a neighboring index whose value is equal to 1
     * @param aNode
     * @return
     */
    @Override
    public Collection<Node<Index>> getReachableNodes(Node<Index> aNode) {
        if (innerMatrix.getValue(aNode.getData())==1){
            List<Node<Index>> reachableNodes = new ArrayList<>();
            for(Index index:innerMatrix.getNeighbors(aNode.getData())){
                if (innerMatrix.getValue(index)==1){
                    Node<Index> singleNode = new Node<>(index,aNode);
                    reachableNodes.add(singleNode);
                }
            }
            return reachableNodes;
        }
        return null;
    }
}
