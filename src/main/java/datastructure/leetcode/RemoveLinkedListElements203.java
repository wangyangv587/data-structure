package datastructure.leetcode;

/**
 * @author: Alex
 * @date: 2018/12/13 17:32
 * description: leetcode-203. 【移除链表元素】
 * 删除链表中等于给定值 val 的所有节点。
示例:
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
 */
public class RemoveLinkedListElements203 {

    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val){
            head = head.next;
        }
        if(head == null)
            return null;

        ListNode prev = head;
        while (prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else{
                prev = prev.next;
            }
        }
        return head;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        public ListNode(int[] arr){
            if(arr == null || arr.length == 0){
                throw new RuntimeException("arr is empty");
            }
            ListNode cur = this;
            cur.val = arr[0];
            for(int i = 1; i < arr.length; i++){
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");
            ListNode cur = this;
            while (cur != null){
                sb.append(cur.val + ",");
                cur = cur.next;
            }
            return sb.toString();
        }
    }
}
