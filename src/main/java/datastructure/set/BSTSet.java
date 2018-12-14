package datastructure.set;

import datastructure.tree.BST;

/**
 * @author: Alex
 * @date: 2018/12/10 15:04
 * description:
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty(E e) {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }


    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

}
