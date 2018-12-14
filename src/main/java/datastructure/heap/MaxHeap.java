package datastructure.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author: Alex
 * @date: 2018/12/11 10:26
 * description: 用动态数据实现一个最大堆
 */
public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MaxHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    /**
     * 数组转换为最大堆
     * @param arr
     */
    public MaxHeap(E[] arr){
        data = new ArrayList<>(arr.length);
        for(E i : arr){
            data.add(i);
        }
        for(int i = parent(data.size() - 1); i >= 0; i--){
            siftDown(i);
        }

    }

    public MaxHeap(){
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

    /**
     * 往堆中插入一个元素
     * @param e
     */
    public void add(E e){
        data.add(e);
        siftUp(data.size() - 1);
    }

    /**
     * 节点上浮，跟节点的父节点进行比较，如果比父节点大，则跟父节点交换位置，如此循环
     * @param k
     */
    private void siftUp(int k){
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            swap(parent(k),k);
            k = parent(k);
        }
    }

    /**
     * 交换元素位置
     * @param i
     * @param j
     */
    private void swap(int i, int j){
        E t = data.get(i);
        data.set(i,data.get(j));
        data.set(j,t);
    }

    /**
     * 取出堆最大值
     * @return
     */
    public E extractMax(){
        E res = findMax();
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
        int max;
        while (left(k) < data.size()){
            max = getMax(k);
            if(k != max){
                swap(k,max);
                k = max;
            }else{
                break;
            }
        }
    }

    /**
     * 获取节点的最大子节点
     * @param k 父节点索引
     * @return
     */
    private int getMax(int k){
        int left = left(k);
        int right = right(k);
        int max = k;
        if(left < data.size() && data.get(k).compareTo(data.get(left)) < 0){
            max = left;
        }

        if(right < data.size() && data.get(max).compareTo(data.get(right)) < 0){
            max = right;
        }
        return max;
    }

    public E findMax(){
        if(data.size() == 0)
            throw new RuntimeException("heap is empty");
        return data.get(0);
    }

    /**
     * 取出最大值并插入一个新值
     * @param e
     * @return
     */
    public E replace(E e){
        E res = findMax();
        data.set(0,e);
        siftDown(0);
        return res;
    }

    /**
     * 最后一个叶子节点的父节点
     * @return
     */
    public int getLast(){
        return parent(data.size() - 1);

    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for(int i = 0; i < n; i++){
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        testHeap(arr,true);
        testHeap(arr,false);
    }

    public static void testHeap(Integer[] arrs,boolean isHeap){
        long start = System.nanoTime();
        MaxHeap<Integer> heap;
        if(!isHeap){
             heap = new MaxHeap<>(arrs.length);
            for(Integer e : arrs)
                heap.add(e);
        }else{
            heap = new MaxHeap<>(arrs);
        }
            int[] arr = new int[arrs.length];
            for(int i = 0;i < arrs.length; i++){
                arr[i] = heap.extractMax();
            }
            for(int i = 1; i < arrs.length; i ++){
                if(arr[i - 1] < arr[i]){
                    throw  new RuntimeException("error");
                }
            }

        long end = System.nanoTime();
        System.out.println(isHeap + "，test MaxHeap completed：" + ((end - start)/1000000000.0) + "s");
    }

}
