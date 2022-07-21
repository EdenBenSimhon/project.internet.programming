package model;

import java.io.Serializable;
import java.util.*;

/**
 * Matrix model build by int[][]
 */
public class Matrix implements Serializable {
    int[][] primitiveMatrix;


    public Matrix(int[][] oArray){
        List<int[]> list = new ArrayList<>();
        for (int[] row : oArray) {
            int[] clone = row.clone();
            list.add(clone);
        }
        primitiveMatrix = list.toArray(new int[0][]);
    }
    public Matrix(int numOfRows,int numOfColumns) {
        Random r = new Random();
        primitiveMatrix = new int[numOfRows][numOfColumns];
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                primitiveMatrix[i][j] = r.nextInt(2);
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : primitiveMatrix) {
            stringBuilder.append(Arrays.toString(row));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    public Collection<Index> getAllOne(){
        Collection<Index> list = new ArrayList<>();
        int lengthRow = primitiveMatrix.length;
        int lengthCol = primitiveMatrix[0].length;
        for (int i = 0; i < lengthRow; i++) {
            for (int j = 0; j < lengthCol; j++) {
                if (primitiveMatrix[i][j] == 1) {
                    list.add(new Index(i, j));
                }
            }
        }
        return list;
    }
    public Collection<Index> getNeighborsOnlyCross(final Index index){
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try{
            extracted = primitiveMatrix[index.row+1][index.column+1];
            list.add(new Index(index.row+1,index.column+1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row+1][index.column-1];
            list.add(new Index(index.row+1,index.column-1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row-1][index.column+1];
            list.add(new Index(index.row-1,index.column+1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row-1][index.column-1];
            list.add(new Index(index.row-1,index.column-1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        return list;
    }
    public Collection<Index> getNeighborsCross(final Index index){
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try{
            extracted = primitiveMatrix[index.row+1][index.column];
            list.add(new Index(index.row+1,index.column));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row][index.column+1];
            list.add(new Index(index.row,index.column+1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row-1][index.column];
            list.add(new Index(index.row-1,index.column));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row][index.column-1];
            list.add(new Index(index.row,index.column-1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row + 1][index.column +1]; //move from (0,0) to (1,1)
            list.add(new Index(index.row +1, index.column+1 ));

        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row - 1][index.column -1]; //move from (0,0) to (1,1)
            list.add(new Index(index.row -1, index.column-1 ));

        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row - 1][index.column +1];
            list.add(new Index(index.row -1, index.column + 1 ));

        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row + 1][index.column -1];
            list.add(new Index(index.row +1, index.column - 1 ));

        }catch (ArrayIndexOutOfBoundsException ignored){}
        return list;
    }
    public Collection<Index> getNeighbors(final Index index){
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try{
            extracted = primitiveMatrix[index.row+1][index.column];
            list.add(new Index(index.row+1,index.column));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row][index.column+1];
            list.add(new Index(index.row,index.column+1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row-1][index.column];
            list.add(new Index(index.row-1,index.column));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        try{
            extracted = primitiveMatrix[index.row][index.column-1];
            list.add(new Index(index.row,index.column-1));
        }catch (ArrayIndexOutOfBoundsException ignored){}
        return list;
    }
    public int getValue(final Index index){
        return primitiveMatrix[index.row][index.column];
    }
    public void setValue(final Index index,int data){
         primitiveMatrix[index.row][index.column]=data;
    }
    public void printMatrix(){
        for (int[] row : primitiveMatrix) {
            String s = Arrays.toString(row);
            System.out.println(s);
        }
    }
    public final int[][] getPrimitiveMatrix() {
        return primitiveMatrix;
    }

}
