package LinkedList;

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
        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        //ListNode newNode = reverseSingleLinkedList_Recursive(node);
        //deleteNode(nodeToDelete);

        System.out.println(printNode(mergeTwoLists(node, node2)));
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while (l1.next != null && l2.next != null) {
            if (l1.val < l2.val) {
                curr = l1;
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr = l2;
                dummyHead.next = curr;
                l2 = l2.next;
            }
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
