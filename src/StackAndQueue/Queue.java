package StackAndQueue;

/*
Pros: easy to implement
Cons:
1. Static Data structure, fixed size.
2.each index can only have once perform of enqueue, dequeue, this can be solved by circular queue(ring buffer)
 */
public class Queue {
    int[] arr;
    int size, rear, front;
    Queue(int size) {
        this.size = size;
        arr = new int[size];
        rear = -1;
        front = -1;
    }

    /* Adds an item to the queue. If the queue is full, then it is said to be an Overflow condition.
    Always insert to rear position.
    * */
    void enqueue(int item) {
        if (isFull()) {
            System.out.print("Queue Overflow");
            return;
        }
        if (front == -1)
            front = 0;
        arr[++rear] = item;
//        print();
        System.out.println(item+ " enqueued to queue");
    }
    /*
    Removes an item from the queue.
    The items are popped in the same order in which they are pushed.
    If the queue is empty, then it is said to be an Underflow condition.
    Always delete from front position.
     */
    int dequeue() {
        if (isEmpty()) {
            System.out.print("Queue Underflow");
            return 0;
        }
        return arr[front++];
    }
    /*
    Get the front item from queue.
     */
    int front() {
        return arr[front];
    }
    /*
     Get the last item from queue.
     */
    int rear() {
        return arr[rear];
    }

    boolean isEmpty() {
        return rear < 0;
    }
    boolean isFull() {
        return rear == size - 1;
    }

    void print() {
        String s = "F: ";
//        for(int i: arr) {
        for (int i=front;i<rear;i++) {
            if (arr[i]!=0)
                s+=arr[i] + " -> ";
        }
        s+=arr[rear]+" :R";
        System.out.println(s);
    }

    public static void main(String[] args) {
        Queue q = new Queue(1000);
        q.enqueue(2);
        q.enqueue(1);
        System.out.println("dequeue: " + q.dequeue());
        q.enqueue(4);
        q.enqueue(3);
        System.out.println("dequeue: " + q.dequeue());
        System.out.println("front: " + q.front());
        q.enqueue(6);
        System.out.println("rear: " + q.rear());
        q.enqueue(5);

        q.print();
    }

}
// Driver class
class Test
{
    public static void main(String[] args)
    {
        Queue queue = new Queue(1000);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        System.out.println(queue.dequeue() +
                " dequeued from queue\n");

        System.out.println("Front item is " +
                queue.front());

        System.out.println("Rear item is " +
                queue.rear());
    }
}