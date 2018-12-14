package datastructure.set;

/**
 * @author: Alex
 * @date: 2018/12/10 15:01
 * description:
 */
public interface Set<E> {

    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty(E e);
}
