package server;

import java.io.Serializable;
import java.util.*;

/*
SOLID
Open for extension/composition but closed for modification
Prefer composition to extension
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
//        for (int[] row : primitiveMatrix) {
//            String s = Arrays.toString(row);
//            System.out.println(s);
//        }
//        System.out.println("\n");
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


    /*
    1 1 1
    0 1 1
    1 1 1
     */


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

    public int getValue(final Index index){
        return primitiveMatrix[index.row][index.column];
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

    public static void main(String[] args) {
        Matrix matrix = new Matrix(3,3);
        System.out.println(matrix);
        Index index1 = new Index(0,0);
        Index index2 = new Index(1,1);
        System.out.println(matrix.getNeighbors(index1));
        System.out.println(matrix.getNeighbors(index2));

    }

}
