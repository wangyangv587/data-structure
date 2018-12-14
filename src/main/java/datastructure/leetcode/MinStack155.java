package datastructure.leetcode;

/**
 * @author: Alex
 * @date: 2018/12/13 17:42
 * description: leetcode-155. 【最小栈】
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
示例:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
 */
public class MinStack155 {

    //结点类
    public class Node{
        public int val;
        public Node next;
        public Node(int item){
            val=item;
            next=null;
        }
        public Node(){
            val=0;
            next=null;
        }
    }
    //头结点指针和初始化
    Node head;
    /** initialize your data structure here. */
    public MinStack155() {
        head=null;
    }
    //入栈方法，新入栈的结点成为head
    public void push(int x) {
        if(head==null)
            head=new Node(x);
        Node p=new Node(x);
        p.next=head;
        head=p;
    }
    //出栈方法，head指向其后继结点
    public void pop() {
        if(head==null)
            return;
        head=head.next;
    }
    //返回head的值
    public int top() {
        return head.val;
    }
    //遍历链表，同时比较结点的值
    public int getMin() {
        Node p=head;
        int minNum=Integer.MAX_VALUE;
        while(p.next!=null){
            if(p.val<minNum){
                minNum=p.val;
            }
            p=p.next;
        }
        return minNum;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */