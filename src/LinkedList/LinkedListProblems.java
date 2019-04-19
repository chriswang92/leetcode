package LinkedList;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) {
        val = x;
    }
}

/* Link list node */
class Node
{
    int data;
    Node next;
    Node(int d) {data = d;
        next = null;}
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
        while (true) {
            if (l1 == null) {
                curr.next = l2;
                break;
            }
            if (l2 == null) {
                curr.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        return dummyHead.next;
    }
    static ListNode merge2(ListNode headA, ListNode headB) {
           /* a dummy first node to
       hang the result on */
        ListNode dummyNode = new ListNode(0);

    /* tail points to the
    last result node */
        ListNode tail = dummyNode;
        while(true)
        {

        /* if either list runs out,
        use the other list */
            if(headA == null)
            {
                tail.next = headB;
                break;
            }
            if(headB == null)
            {
                tail.next = headA;
                break;
            }

        /* Compare the data of the two
        lists whichever lists' data is
        smaller, append it into tail and
        advance the head to the next Node
        */
            if(headA.val <= headB.val)
            {
                tail.next = headA;
                headA = headA.next;
            }
            else
            {
                tail.next = headB;
                headB = headB.next;
            }

            /* Advance the tail */
            tail = tail.next;
        }
        return dummyNode.next;
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
