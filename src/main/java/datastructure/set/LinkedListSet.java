package datastructure.set;

import java.util.LinkedList;

/**
 * @author: Alex
 * @date: 2018/12/10 15:19
 * description:
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty(E e) {
        return list.isEmpty();
    }

    @Override
    public void add(E e) {
        if(!contains(e)){
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

}
