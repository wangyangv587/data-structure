package datastructure.tree;

import java.util.*;

/**
 * @author: Alex
 * @date: 2018/12/7 11:45
 * description: AVL
 */
public class AVLTree<E extends Comparable<E>> {

    private class Node {
        E e;
        int height;
        Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    /**
     * 获取节点的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 向AVL中添加一个元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            node = new Node(e);
            size++;
            return node;
        }
        if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        } else if(node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        }
        //更新height
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        //计算平衡因子
        int balance = getBalanceFactor(node);

        //AVL自平衡机制
        if(balance > 1 && getBalanceFactor(node.left) >= 0){ //表示左边子树的高度比右边子树的高度大于1，开始自平衡机制-右旋转 LL
            return rightRotate(node);
        }
        if(balance < -1 && getBalanceFactor(node.right) <= 0){ //表示右边子树的高度比左边子树的高度大于1，开始自平衡机制-左旋转 RR
            return leftRotate(node);
        }
        if(balance > 1 && getBalanceFactor(node.left) < 0){ //表示左边子树的高度比右边子树的高度大于1，开始自平衡机制-右旋转 LR
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balance < -1 && getBalanceFactor(node.right) > 0){ //表示右边子树的高度比左边子树的高度大于1，开始自平衡机制-左旋转 RL
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;//pride and prejudice
    }

    /**
     * 对节点y进行右旋转操作，返回旋转后新的结果x
     *         y                              x
     *      /   \                          /    \
     *     x    t4                        z      y
     *    / \               ---->        / \    / \
     *   z   t3                         t1 t2  t3 t4
     *  / \
     * t1  t2
     * @param y
     * @return
     */
    private Node rightRotate(Node y){
        Node x = y.left;
        Node t3 = x.right;

        //向右旋转
        x.right = y;
        y.left = t3;

        //更新height TODO
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 对节点y进行左旋转操作，返回旋转后新的结果x
     *         y                              x
     *      /   \                          /    \
     *     t1    x                        y      z
     *          / \            ---->     / \    / \
     *       t2   z                     t1 t2  t3 t4
     *          /  \
     *         t3  t4
     * @param y
     * @return
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t3 = x.left;

        //向右旋转
        x.left = y;
        y.right = t3;

        //更新height TODO
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 非递归实现AVL的前序遍历
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println("cur.e = " + cur.e);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    /**
     * 判断AVL中是否存在某个元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {

        if (node == null)
            return false;

        if (node.e.compareTo(e) == 0)
            return true;
        else if (node.e.compareTo(e) < 0)
            return contains(node.right, e);
        else
            return contains(node.left, e);
    }

    /**
     * 递归实现AVL的前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println("node = " + node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 判断是否是一个AVL，中序遍历，是从大到小排序的
     * @return
     */
    public boolean isBST(){
        ArrayList<E> array = new ArrayList<>();
        inOrder(root,array);
        for(int i = 1; i < array.size(); i++){
            if(array.get(i - 1).compareTo(array.get(i)) < 1){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是满足平衡二叉树，即所有平衡因子是否大于1
     */
    public boolean isBalance(){
        return isBalance(root);
    }

    private boolean isBalance(Node node){
        if(node == null){
            return true;
        }
        int balance = getBalanceFactor(node);
        if(Math.abs(balance) > 1){
            return false;
        }
        return isBalance(node.left) && isBalance(node.right);
    }

    private void inOrder(Node node,ArrayList<E> array){
        if(node == null){
            return;
        }
        inOrder(node.left);
        array.add(node.e);
        inOrder(node.right);
    }

    /**
     * 递归实现AVL的中序遍历，输出元素按从大到小排序
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        //System.out.println("node = " + node.e);
        inOrder(node.right);
    }

    /**
     * 递归实现AVL的后序遍历
     */
    public void nextOrder() {
        nextOrder(root);
    }

    private void nextOrder(Node node) {
        if (node == null)
            return;
        nextOrder(node.left);
        nextOrder(node.right);
        System.out.println("node = " + node.e);
    }

    /**
     * 非递归实现AVL的层次遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println("cur.e = " + cur.e);
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * 获取AVL中最小的元素
     * @return
     */
    public E mininum(){
        if(size == 0){
            throw new RuntimeException("BST is empty");
        }
        return mininum(root).e;
    }

    private Node mininum(Node node){

        if(node.left == null)
            return node;
        return mininum(node.left);
    }

    /**
     * 获取AVL中最大的元素
     * @return
     */
    public E maxnum(){
        if(isEmpty()){
            throw new RuntimeException("BST is empty");
        }
        return maxnum(root).e;
    }

    private Node maxnum(Node node){

        if(node.right == null)
            return node;
        return maxnum(node.right);
    }

    /**
     * 删除AVL最小元素
     * @return
     */
    public E removeMin(){
        E e = mininum();
        root = removeMin(root);
        return e;
    }

    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除AVL最小元素
     * @return
     */
    public E removeMax(){
        E e = maxnum();
        root = removeMax(root);
        return e;
    }

    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除AVL中的任意元素
     * @param e
     */
    public void remove(E e){
        remove(root,e);
    }

    private Node remove(Node node,E e){
        if(node == null){
            return null;
        }
        Node retNode;
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            retNode = node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            retNode = node;
        }else{
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }else if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }else {
                //左右节点皆不为空，选取右子树最小节点作为这个删除节点的后继节点，也可用左子树的最大值
                Node succ = mininum(node.right);
                succ.right = remove(node.right, succ.e);
                succ.left = node.left;
                node.left = node.right = null;
                retNode = succ;
            }
        }
        if(retNode == null){
            return null;
        }
        //更新height
        retNode.height = Math.max(getHeight(retNode.left),getHeight(retNode.right)) + 1;
        //计算平衡因子
        int balance = getBalanceFactor(retNode);

        //AVL自平衡机制
        if(balance > 1 && getBalanceFactor(retNode.left) >= 0){ //表示左边子树的高度比右边子树的高度大于1，开始自平衡机制-右旋转 LL
            return rightRotate(retNode);
        }
        if(balance < -1 && getBalanceFactor(retNode.right) <= 0){ //表示右边子树的高度比左边子树的高度大于1，开始自平衡机制-左旋转 RR
            return leftRotate(retNode);
        }
        if(balance > 1 && getBalanceFactor(retNode.left) < 0){ //表示左边子树的高度比右边子树的高度大于1，开始自平衡机制-右旋转 LR
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        if(balance < -1 && getBalanceFactor(retNode.right) > 0){ //表示右边子树的高度比左边子树的高度大于1，开始自平衡机制-左旋转 RL
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    /**
     * 比e小的最大元素
     * @return
     */
    public E floor(E e){
        return null;
    }

    /**
     * 比E大的最小元素
     * @param e
     * @return
     */
    public E ceil(E e){
        return null;
    }

    /**
     * 获取E排第几名
     * @return
     */
    public int rank(E e){
        return 0;
    }

    /**
     * 获取排名为rank的元素
     * @return
     */
    public E[] select(int rank){
        Object[] objects = {};
        E[] es = (E[])objects;
        return es;
    }

    /**
     * 获取AVL的最大深度
     * @return
     */
    public int depth(){
        return depth(root);
    }

    private int depth(Node node){
        if(root == null){
            return 0;
        }
        return Math.max(depth(node.left),depth(node.right)) + 1;
    }


    ///////////////
    //     5     //
    //    / \    //
    //   3   6   //
    //  / \   \  //
    // 2   4   8 //
    ////////////////
    public static void main(String[] args) {
        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < 1000; i++)
            bst.add(new Random().nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>(200);
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }
        System.out.println("nums = " + nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                throw new RuntimeException("Error");
            }
        }

    }//binary search tree
}
