package com.chuck.listnode;

class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ListNodeClass {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode successor = null;
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode kth = head;
        for (int i = 0; i < k; i++) {
            if (kth == null) return head;
            kth = kth.next;
        }
        ListNode newHead = reverseFromTo(head, kth);
        head.next = reverseKGroup(kth, k);
        return newHead;
    }

    public ListNode reverseFromTo(ListNode head, ListNode dest) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        while (cur != dest) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-200, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNodeClass listNodeClass = new ListNodeClass();
        listNodeClass.reverseKGroup(head, 2);
    }
}
