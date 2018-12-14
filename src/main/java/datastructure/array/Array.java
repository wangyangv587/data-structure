package datastructure.array;

/**
 * @author: Alex
 * @date: 2018/11/29 11:16
 * description:
 */
public class Array {

    private int[] data;
    private int size;

    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }
}
