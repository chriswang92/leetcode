package LinkedList;

import java.util.HashSet;

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
        node.next.next = new ListNode(4);
        node.next.next.next = new ListNode(5);
        node.next.next.next.next = node.next;
        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        //ListNode newNode = reverseSingleLinkedList_Recursive(node);
        //deleteNode(nodeToDelete);

        //System.out.println(printNode(mergeTwoLists_recursion(node, node2)));
        hasCycle2(node);
    }
    // hasCycle by two pointers
    public static boolean hasCycle2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;

    }
    // hasCycle by hashset
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.contains(head)) {
                set.add(head);
            } else {
                return true;
            }
            head = head.next;
        }
        return false;
    }
    public static ListNode mergeTwoLists_recursion(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists_recursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_recursion(l1, l2.next);
            return l2;
        }
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /* a dummy first node to
        hang the result on */
        ListNode dummyHead = new ListNode(0);
        /* tail points to the
        last result node */
        ListNode curr = dummyHead;
        while (true) {
            /* if either list runs out,
            use the other list */
            if (l1 == null) {
                curr.next = l2;
                break;
            }
            if (l2 == null) {
                curr.next = l1;
                break;
            }
             /* Compare the data of the two
            lists whichever lists' data is
            smaller, append it into tail and
            advance the head to the next Node
            */
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            /* Advance the tail */
            curr = curr.next;
        }
        return dummyHead.next;
    }
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    // reverse single linked list - recursively
    public static ListNode reverseSingleLinkedList_Recursive(ListNode head) {
        // head is null, or last node or only one node
        if (head == null || head.next == null)
            return head;

        // remember the new head
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
