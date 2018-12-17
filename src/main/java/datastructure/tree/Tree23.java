package datastructure.tree;

/**
 * @author: Alex
 * @date: 2018/12/17 18:07
 * description: 2-3树
 */
public class Tree23<E> {
    private class Node{
        E[] es;
        Node left,centere,right;

        public Node(){
            es = (E[])new Object[3];
        }
    }
}
