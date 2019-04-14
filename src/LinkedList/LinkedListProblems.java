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
        ListNode newNode = reverseSingleLinkedList_Recursive(node);
        System.out.println(printNode(newNode));
    }
    // reverse single linked list - recursively
    public static ListNode reverseSingleLinkedList_Recursive(ListNode head) {
        // head is null or last node or only one node
        if(head == null || head.next == null) {
            return head;
        }

        // reverse the rest
        ListNode newHeadNode = reverseSingleLinkedList_Recursive(head.next);

        // change references for middle chain
        head.next.next = head;
        head.next = null;

        // send back new head node in every recursion
        return newHeadNode;
    }
    // iterative solution 2
    public static ListNode reverseList_Iterative(ListNode head) {
        //Iterative solution.
        ListNode last = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = last;
            last = head;
            head = next;
        }
        return last;

    }
    static String printNode(ListNode head) {
        String ret = "" + head.val;
        while (head.next != null) {
            head = head.next;
            ret += " -> " + head.val;
        }
        return ret;
    }
}
