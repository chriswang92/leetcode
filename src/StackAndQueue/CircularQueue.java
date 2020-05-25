package StackAndQueue;

/*
Circular Queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle
and the last position is connected back to the first position to make a circle. It is also called ‘Ring Buffer’.

Operations on Circular Queue:

1.Front: Get the front item from queue.
2.Rear: Get the last item from queue.
3.enQueue(value) This function is used to insert an element into the circular queue.
In a circular queue, the new element is always inserted at Rear position.
Steps:
    1.Check whether queue is Full – Check ((rear == SIZE-1 && front == 0) || (rear == front-1)).
    2.If it is full then display Queue is full.
      If queue is not full then, check if (rear == SIZE – 1 && front != 0) if it is true then set rear=0 and insert element.

4.deQueue() This function is used to delete an element from the circular queue.
In a circular queue, the element is always deleted from front position.
Steps:
    1.Check whether queue is Empty means check (front==-1).
    2.If it is empty then display Queue is empty. If queue is not empty then step 3
    3.Check if (front==rear) if it is true then set front=rear= -1 else check if (front==size-1), if it is true then set front=0 and return the element.
 */
public class CircularQueue {
    int[] arr;
    int front, rear, SIZE;
    CircularQueue(int size) {
        arr = new int[size];
        front = rear = -1;
        SIZE = size;
    }
    boolean isFull() {
        return (rear == SIZE - 1 && front == 0) // first full case
                || rear == front - 1;
    }
    boolean isEmpty() {
        return front == -1;
    }
    void enQueue(int value) {
        if (isFull()) {
            System.out.print("\nQueue is full");
            return;
        }
        System.out.printf("\nEnqueue: %d", value);
        // insert first element
        if (front == -1) {
            front = rear = 0;
            arr[rear] = value;
        }
        // When rear is at last index, but front is not at 0 index, which means there was dequeues from front happened
        else if (rear == SIZE - 1 && front != 0) {
            rear = 0;
            arr[rear] = value;
        } else {
            // regular case
            arr[++rear] = value;
        }
    }
    int deQueue() {
        if (isEmpty()) {
            System.out.print("\nQueue is empty");
            return Integer.MIN_VALUE;
        }
        int data = arr[front];
        arr[front] = -1;
        // Only element in queue
        if (front == rear)
            front = rear = -1;
        /*
         A number of SIZE - 1 elements were dequeued, and caused front is in the last index.
         Increment its index by set it to 0
         */
        else if (front == SIZE - 1)
            front = 0;
        // regular increment
        else
            front ++;
        return data;
    }
    void displayQueue() {
        if (isEmpty()) {
            System.out.print("\nQueue is empty");
            return;
        }
        System.out.print("\nElements in Circular Queue are: ");
        if (rear >= front) {
            for (int i=front; i<=rear;i++)
                System.out.printf("%d ", arr[i]);
        } else {
            for (int i=front; i<SIZE;i++)
                System.out.printf("%d ", arr[i]);
            for (int i=0; i<=rear;i++)
                System.out.printf("%d ", arr[i]);
        }
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(5);

        // Inserting elements in Circular Queue
        q.enQueue(14);
        q.enQueue(22);
        q.enQueue(13);
        q.enQueue(-6);
        // Display elements present in Circular Queue
        q.displayQueue();

        // Deleting elements from Circular Queue
        System.out.printf("\nDeleted value = %d", q.deQueue());
        System.out.printf("\nDeleted value = %d", q.deQueue());

        q.displayQueue();

        q.enQueue(9);
        q.enQueue(20);
        q.enQueue(5);

        q.displayQueue();

        q.enQueue(20); // display queue is full, can not enqueue

        q.displayQueue();
    }
}
