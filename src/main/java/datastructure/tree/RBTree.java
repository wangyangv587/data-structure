package datastructure.tree;

import java.util.*;

/**
 * @author: Alex
 * @date: 2018/12/7 11:45
 * description: 红黑树
 */
public class RBTree<E extends Comparable<E>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        E e;
        Node left, right;
        boolean color;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树中添加一个元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
        root.color = BLACK;
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        } else if(node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        }

        //判断是否需要左旋转
        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        //判断是否需要右旋转
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        //判断是否需要颜色翻转
        if(isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        return node;//pride and prejudice
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

        y.right = x.left;
        x.left = y;

        x.color = y.color;
        y.color = RED;
        return x;
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

        y.left = x.right;
        x.right = y;

        x.color = y.color;
        y.color = RED;

        return x;
    }

    private boolean isRed(Node node){
        if(node == null){
            return BLACK;
        }
        return node.color;
    }

    private void flipColor(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 非递归实现二分搜索树的前序遍历
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
     * 判断二分搜索树中是否存在某个元素
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
     * 递归实现二分搜索树的前序遍历
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
     * 递归实现二分搜索树的中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println("node = " + node.e);
        inOrder(node.right);
    }

    /**
     * 递归实现二分搜索树的后序遍历
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
     * 非递归实现二分搜索树的层次遍历
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
     * 获取二分搜索树中最小的元素
     * @return
     */
    public E mininum(){
        if(size == 0){
            throw new RuntimeException("RBTree is empty");
        }
        return mininum(root).e;
    }

    private Node mininum(Node node){

        if(node.left == null)
            return node;
        return mininum(node.left);
    }

    /**
     * 获取二分搜索树中最大的元素
     * @return
     */
    public E maxnum(){
        if(isEmpty()){
            throw new RuntimeException("RBTree is empty");
        }
        return maxnum(root).e;
    }

    private Node maxnum(Node node){

        if(node.right == null)
            return node;
        return maxnum(node.right);
    }

    /**
     * 删除二分搜索树最小元素
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
     * 删除二分搜索树最小元素
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
     * 删除二分搜索树中的任意元素
     * @param e
     */
    public void remove(E e){
        remove(root,e);
    }

    private Node remove(Node node,E e){
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else{
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            //左右节点皆不为空，选取右子树最小节点作为这个删除节点的后继节点，也可用左子树的最大值
            Node succ = mininum(node.right);
            succ.right = removeMin(node.right);
            succ.left = node.left;
            node.left = node.right = null;
            return succ;
        }
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
     * 获取二分搜索树的最大深度
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
        RBTree<Integer> RBTree = new RBTree<>();
        for (int i = 0; i < 1000; i++)
            RBTree.add(new Random().nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>(200);
        while (!RBTree.isEmpty()) {
            nums.add(RBTree.removeMin());
        }
        System.out.println("nums = " + nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                throw new RuntimeException("Error");
            }
        }

    }//binary search tree
}
