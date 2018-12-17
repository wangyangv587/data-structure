package datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author: Alex
 * @date: 2018/12/17 16:07
 * description:
 */
public class Main {

    public static void main(String[] args) {
        long start = System.nanoTime();
        BST<Integer> bst = new BST<>();
        ArrayList<Integer> arrayList = new ArrayList<>(100000);
        Random random = new Random();
        for(int i = 0; i < 100000; i++){
            arrayList.add(random.nextInt(10000));
        }

        //排序元素，极端情况下，BST退化成链表
        Collections.sort(arrayList);
        for(Integer i : arrayList){
            bst.add(i);
        }

        for(Integer i : arrayList){
            bst.contains(i);
        }
        long end = System.nanoTime();
        System.out.println("BST = " + (end - start) / 1000000000.0);

        long start1 = System.nanoTime();
        AVLTree<Integer> avl = new AVLTree<>();

        for(Integer i : arrayList){
            avl.add(i);
        }

        for(Integer i : arrayList){
            avl.contains(i);
        }
        //测试删除后是否是一个avl
        /*for(Integer i : arrayList){
            avl.remove(i);
            if(!avl.isBST() || !avl.isBalance()){
                throw new RuntimeException("Error");
            }
        }*/
        long end1 = System.nanoTime();
        System.out.println("AVL = " + (end1 - start1) / 1000000000.0);
    }
}
