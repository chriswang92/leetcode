package Design;

import java.util.*;

public class HardDesignProblems {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);    // evicts key 2
        cache.put(4, 1);    // evicts key 2
        int res = cache.get(1);       // returns 1
        res = cache.get(2);       // returns 1
        //int last = cache.queue.getLast().val;
        res = cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        res = cache.get(1);       // returns -1 (not found)
        res = cache.get(3);       // returns 3
        res = cache.get(4);       // returns 4
    }
}

/**
 * 146. LRU Cache
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class Node {
    Node last;
    Node next;
    int key, val;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
        last = null;
        next = null;
    }
}
class LRUCache {
    private Deque<Node> queue;
    private HashMap<Integer, Node> map;
    private int capacity;
    public LRUCache(int capacity) {
        queue = new LinkedList<>();
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        int found = -1;
        if (map.containsKey(key)) {
            Node nodeFound = map.get(key);
            found = nodeFound.val;
            // update queue
            updateQueue(nodeFound);
        }
        return found;
    }

    public void put(int key, int value) {
        if (map.size() >= capacity && !map.containsKey(key)) {
            // When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
            int polledKey = queue.poll().key; // LRU key is always the head of queue
            map.remove(polledKey);
        }

        Node oldNode = map.get(key);
        Node newNode = oldNode == null ? new Node(key, value) : oldNode;
        // Allocate the newNode
        updateQueue(newNode);

        // Add the node into map/Update the node in map
        if (oldNode == null) {
            map.put(key, newNode);
        } else {
            oldNode.val = value;
            map.put(key, oldNode);
        }
    }

    // update queue for each action: get, put
    private void updateQueue(Node node) {
        if (node != null) {
            queue.remove(node);
            queue.addLast(node);
        }
    }
}