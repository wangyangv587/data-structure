package datastructure.queue;

import datastructure.heap.MaxHeap;

/**
 * @author: Alex
 * @date: 2018/12/11 15:41
 * description: 堆实现优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> heap;

    public PriorityQueue(){
        heap = new MaxHeap<>();
    }

    public PriorityQueue(int capacity){
        heap = new MaxHeap<>(capacity);
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.extractMax();
    }

    @Override
    public E getFront() {
        return heap.findMax();
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
