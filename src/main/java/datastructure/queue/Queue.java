package datastructure.queue;

/**
 * @author: Alex
 * @date: 2018/12/11 10:03
 * description:
 */
public interface Queue<E> {

    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
