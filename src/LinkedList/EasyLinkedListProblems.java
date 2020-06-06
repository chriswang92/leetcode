package LinkedList;

import java.util.HashSet;
import java.util.*;

public class EasyLinkedListProblems {

    public static void main(String[] args) {
        ListNode commonNode = new ListNode(33);
        commonNode.next = new ListNode(22);
        commonNode.next.next = new ListNode(11);
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        //node.next.next.next = commonNode;
        /*
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(3);
        node.next.next.next.next.next = new ListNode(1);
        */
        ListNode node2 = new ListNode(4);
        node2.next = new ListNode(5);
        node2.next.next = new ListNode(6);
        node2.next.next.next = new ListNode(7);
        //node2.next.next.next = commonNode;
        //ListNode newNode = reverseSingleLinkedList_Recursive(node);
        //deleteNode(nodeToDelete);
        //System.out.println(printNode(mergeTwoLists_recursion(node, node2)));
        //hasCycle2(node);
        //System.out.println(isPalindrome2(node));
        System.out.println(printNode(getIntersectionNode(node, node2)));
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        // the 2+ iterations will finally end with either both null or same Node to break the loop
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        List<Integer> l1 = new LinkedList<>(), l2 = new LinkedList<>();;
        List<ListNode> nodeList1 = new LinkedList<>();
        ListNode curr = headA, res = null;
        while (curr != null) {
            l1.add(curr.val);
            nodeList1.add(curr);
            curr = curr.next;
        }
        curr = headB;
        while (curr != null) {
            if (nodeList1.contains(curr)) {
                res = curr;
                break;
            }
            curr = curr.next;
        }

        return res;
    }
    /* Function to check if given linked list is
      palindrome or not */
    static ListNode head;  // head of list
    static ListNode slow_ptr, fast_ptr,second_half;
    static boolean isPalindrome2(ListNode head)
    {
        slow_ptr = head; fast_ptr = head;
        ListNode prev_of_slow_ptr = head;
        ListNode midnode = null;  // To handle odd size list
        boolean res = true; // initialize result

        if (head != null && head.next != null)
        {
            /* Get the middle of the list. Move slow_ptr by 1
               and fast_ptrr by 2, slow_ptr will have the middle
               node */
            while (fast_ptr != null && fast_ptr.next != null)
            {
                fast_ptr = fast_ptr.next.next;

                /*We need previous of the slow_ptr for
                  linked lists  with odd elements */
                prev_of_slow_ptr = slow_ptr;
                slow_ptr = slow_ptr.next;
            }

            /* fast_ptr would become NULL when there are even elements
               in the list and not NULL for odd elements. We need to skip
               the middle node for odd case and store it somewhere so that
               we can restore the original list */
            if (fast_ptr != null)
            {
                midnode = slow_ptr;
                slow_ptr = slow_ptr.next;
            }

            // Now reverse the second half and compare it with first half
            second_half = slow_ptr;
            prev_of_slow_ptr.next = null; // NULL terminate first half
            reverse();  // Reverse the second half
            res = compareLists2(head, second_half); // compare

            /* Construct the original list back */
            reverse(); // Reverse the second half again

            if (midnode != null)
            {
                // If there was a mid node (odd size case) which
                // was not part of either first half or second half.
                prev_of_slow_ptr.next = midnode;
                midnode.next = second_half;
            } else
                prev_of_slow_ptr.next = second_half;
        }
        return res;
    }

    /* Function to reverse the linked list  Note that this
       function may change the head */
    static void reverse()
    {
        ListNode prev = null;
        ListNode current = second_half;
        ListNode next;
        while (current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        second_half = prev;
    }

    /* Function to check if two input lists have same data*/
    static boolean compareLists2(ListNode head1, ListNode head2)
    {
        ListNode temp1 = head1;
        ListNode temp2 = head2;

        while (temp1 != null && temp2 != null)
        {
            if (temp1.val == temp2.val)
            {
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else
                return false;
        }

        /* Both are empty reurn 1*/
        if (temp1 == null && temp2 == null)
            return true;

        /* Will reach here when one is NULL
           and other is not */
        return false;
    }

    public static boolean isPalindrome(ListNode head) {
        boolean res = true;
        if (head != null && head.next != null) {
            // start
            /* Get the middle of the list. Move slow_ptr by 1
               and fast_ptrr by 2, slow_ptr will have the middle
               node */
            ListNode slow = head, fast = head, prev_of_slow = head, midNode = null, secondHalfHead = null;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                prev_of_slow = slow;
                slow = slow.next;
            }
            /* fast pointer would become NULL when there are even elements in the list, and NOT NULL for odd elements.
               We need to skip the middle node for odd case and store it somewhere so that we can restore the original
               list
            * */
            if (fast != null) {
                midNode = slow;
                slow = slow.next;
            }

            // Now reverse the second half and compare it with the first half
            secondHalfHead = slow;
            prev_of_slow.next = null; // NULL terminate first half
            ListNode newHead = reverseSingleLinkedList_Recursive(secondHalfHead);
            res = compareLists(head, newHead);
        }
        return res;
    }
    static boolean compareLists(ListNode head1, ListNode head2) {
        // compare each element in both list
        while (head1 != null && head2 != null) {
            if (head1.val != head2.val)
                return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        // both reach end of list
        if (head1 == null && head2 == null) {
            return true;
        }
        // will reach here when one reaches end of list and the other does not
        return false;
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
        if(head == null)
            return "null node";
        String ret = "" + head.val;
        while (head.next != null) {
            head = head.next;
            ret += " -> " + head.val;
        }
        return ret;
    }
}
