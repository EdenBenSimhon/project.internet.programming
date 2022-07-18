package struct;

import java.io.Serializable;
import java.util.Objects;

// POJO - Plain Old Java Object
public class Index implements Serializable {
    int row, column;

    public Index(final int row, final int column) {
        this.row=row;
        this.column=column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return row == index.row &&
                column == index.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "("+row +
                "," + column +
                ')';
    }

    public static void main(String[] args) {
        Index index = new Index(2,3);
        System.out.println(index);
    }
}
