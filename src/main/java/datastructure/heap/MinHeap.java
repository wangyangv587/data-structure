package datastructure.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author: Alex
 * @date: 2018/12/11 14:11
 * description: 用动态数据实现一个最小堆
 */
public class MinHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MinHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    public MinHeap(){
        data = new ArrayList<>();
    }

    public int getSize(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 获取指定索引所表示节点的父节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if(index == 0)
            return 0;
        return (index - 1) / 2;
    }

    /**
     * 获取指定索引所表示节点的左节点的索引
     * @param index
     * @return
     */
    private int left(int index){
        return index * 2 + 1;
    }

    /**
     * 获取指定索引所表示节点的右节点的索引
     * @param index
     * @return
     */
    private int right(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k){
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0){
            swap(k,parent(k));
            k = parent(k);
        }
    }

    private void swap(int i,int j){
        E t = data.get(i);
        data.set(i,data.get(j));
        data.set(j,t);
    }

    /**
     * 取出堆最大值
     * @return
     */
    public E extractMin(){
        E res = findMin();
        swap(0,data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);
        return res;
    }

    /**
     * 节点下浮，跟子节点中最大的节点进行比较，如果比最大的小，则交换他们的位置
     * @param k 父节点索引
     */
    private void siftDown(int k){
        int min;
        while (left(k) < data.size()){
            min = getMin(k);
            if(k != min){
                swap(k,min);
                k = min;
            }else{
                break;
            }
        }
    }

    /**
     * 获取节点的最小子节点
     * @param k 父节点索引
     * @return
     */
    private int getMin(int k){
        int left = left(k);
        int right = right(k);
        int min = k;
        if(left < data.size() && data.get(k).compareTo(data.get(left)) > 0){
            min = left;
        }

        if(right < data.size() && data.get(min).compareTo(data.get(right)) > 0){
            min = right;
        }
        return min;
    }

    private E findMin(){
        if(data.size() == 0)
            throw new RuntimeException("heap is empty");
        return data.get(0);
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        int n = 1000000;
        MinHeap<Integer> heap = new MinHeap<>(n);
        for(int i = 0; i < n; i++)
            heap.add(new Random().nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0;i < n; i++){
            arr[i] = heap.extractMin();
        }
        System.out.println();
        for(int i = 1; i < n; i ++){
            if(arr[i - 1] > arr[i]){
                throw  new RuntimeException("error");
            }
        }

        long end = System.nanoTime();

        System.out.println("test MinHeap completed：" + ((end - start)/1000000000.0) + "s");
    }
}
