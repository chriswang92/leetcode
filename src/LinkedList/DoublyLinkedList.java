package LinkedList;

public class DoublyLinkedList {
    DoublyLinkedListNode head;

    public DoublyLinkedList(int[] arr) {
        System.out.println("Make a new singly linked list using given array size="+ arr.length);
        head = new DoublyLinkedListNode(arr[0]);
        head.previous = null;
        DoublyLinkedListNode current = head;
        // Make a new doubly linked list using given array
        for (int i=0;i<arr.length-1;i++) {
            current.next = new DoublyLinkedListNode(arr[i+1]);
            current.next.previous = current;
            current = current.next;
        }
        DoublyLinkedListNode last = new DoublyLinkedListNode(arr[arr.length-1]);
        last.previous = current;
        last.next = null;
        display();
        reverseDisplayFromTail();
    }

    void display() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        DoublyLinkedListNode curr = head;
        System.out.println("\nDisplay doubly linked list:");
        while (curr != null) {
            System.out.printf("%d -> ", curr.val);
            curr = curr.next;
        }
    }

    void reverseDisplayFromTail() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        DoublyLinkedListNode curr = head;
        // Go to tail
        while (curr.next!=null) {
            curr = curr.next;
        }
        System.out.println("\nReverse display doubly linked list:");
        while (curr != null) {
            System.out.printf("%d -> ", curr.val);
            curr = curr.previous;
        }
    }

    DoublyLinkedListNode goToIndex(DoublyLinkedListNode curr, int index) {
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
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        newNode.next = head;
        head = newNode;
        display();
    }

    void addFromTail(int data) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

        DoublyLinkedListNode curr = head;
        // Go to tail
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = newNode;
        display();
    }

    void addInMiddle(int data, int index) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        DoublyLinkedListNode curr = head;

        // Go to the node before the node to remove
        curr = goToIndex(curr, index - 1);

        newNode.next = curr.next;

        curr.next = newNode;
        display();
    }

    void removeFromHead() {
        DoublyLinkedListNode oldHead = head;
        head = oldHead.next;
        oldHead.next = null;
        display();
    }

    void removeFromTail() {
        DoublyLinkedListNode curr = head;
        DoublyLinkedListNode oneBeForeCurr = head; // this will always be one node before curr, so when curr reach the end, oneBehindCurr will be the node before it.
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
        DoublyLinkedListNode curr = head;

        // Go to the node before the node to remove
        curr = goToIndex(curr, index - 1);

        DoublyLinkedListNode nodeToRemove = curr.next;
        curr.next = nodeToRemove.next;
        nodeToRemove.next = null;
        display();
    }

    public static void main(String[] args) {
        DoublyLinkedList l = new DoublyLinkedList(new int[]{1,3,4,5,2});
        l.addFromHead(2);
        l.addFromTail(33);
        l.addInMiddle(77,5);
        l.removeFromHead();
        l.removeFromMiddle(4);
        l.removeFromTail();
    }
}
