package LinkedList;

public class SinglyLinkedList {
    ListNode head;

    public SinglyLinkedList(int[] arr) {
        System.out.println("Make a new singly linked list using given array size="+ arr.length);
        head = new ListNode(arr[0]);
        ListNode current = head;
        // Make a new singly linked list using given array
        for (int i=1;i<arr.length;i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        display();
    }

    void display() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        ListNode curr = head;
        System.out.println();
        while (curr != null) {
            System.out.printf("%d -> ", curr.val);
            curr = curr.next;
        }
    }

    ListNode goToIndex(ListNode curr, int index) {
        int currIndex = 0;
        while (currIndex < index) {
            if (curr.next == null) {
                System.out.printf("\nFailed to reach index: %d, index out of bound", index);
                break;
            }
            curr = curr.next;
            currIndex++;
        }
        return curr;
    }

    void addFromHead(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        head = newNode;
        display();
    }

    void addFromTail(int data) {
        ListNode newNode = new ListNode(data);

        ListNode curr = head;
        // Go to tail
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = newNode;
        display();
    }

    void addInMiddle(int data, int index) {
        ListNode newNode = new ListNode(data);
        ListNode curr = head;

        // Go to the node before the node to remove
        curr = goToIndex(curr, index - 1);

        newNode.next = curr.next;

        curr.next = newNode;
        display();
    }

    void removeFromHead() {
        ListNode oldHead = head;
        head = oldHead.next;
        oldHead.next = null;
        display();
    }

    void removeFromTail() {
        ListNode curr = head;
        ListNode oneBeForeCurr = head; // this will always be one node before curr, so when curr reach the end, oneBehindCurr will be the node before it.
        // Go to tail
        while (curr.next != null) {
            if (curr != head) {
                oneBeForeCurr = oneBeForeCurr.next;
            }
            curr = curr.next;
        }
        oneBeForeCurr.next = null;
        display();
    }

    void removeFromMiddle(int index) {
        ListNode curr = head;

        // Go to the node before the node to remove
        curr = goToIndex(curr, index - 1);

        ListNode nodeToRemove = curr.next;
        curr.next = nodeToRemove.next;
        nodeToRemove.next = null;
        display();
    }

    public static void main(String[] args) {
        SinglyLinkedList l = new SinglyLinkedList(new int[]{1,3,4,5,2});
        l.addFromHead(2);
        l.addFromTail(33);
        l.addInMiddle(77,5);
        l.removeFromHead();
        l.removeFromMiddle(4);
        l.removeFromTail();
    }
}
