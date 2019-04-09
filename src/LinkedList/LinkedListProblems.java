package LinkedList;

import java.util.LinkedList;
import java.util.List;
//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) {
        val = x;
    }
}
public class LinkedListProblems {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        reverseSingleLinkedList(node);
    }
    // reverse single linked list - recursively
    public static ListNode reverseSingleLinkedList_Recursive(ListNode head) {
        if (head == null)
            return null;
       
    }
    // reverse single linked list - iteratively
    public static ListNode reverseSingleLinkedList(ListNode head) {
        if (head == null)
            return null;
        List<ListNode> list = new LinkedList<>();
        ListNode curr = head;
        list.add(curr);
        while (curr.next != null) {
            curr = curr.next;
            list.add(curr);
        }
        ListNode newHead = list.get(list.size() - 1);
        curr = newHead;
        for (int i=list.size()-2;i>=0;i--) {
            curr.next = list.get(i);
            curr = curr.next;
        }
        curr.next = null;
        return newHead;
    }
}
