package Entity;

// Recency list: head side = MRU, tail side = LRU. All ops O(1).
public class DoublyLinkedList {
    private final Node head; // dummy
    private final Node tail; // dummy

    public DoublyLinkedList() {
        head = new Node("", -1);
        tail = new Node("", -1);
        head.next = tail;
        tail.prev = head;
    }

    // Place node as most recently used.
    public void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Unlink node from wherever it sits.
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // On get/update: refresh recency.
    public void moveToFront(Node node) {
        remove(node);
        addToFront(node);
    }

    // Evict least recently used (node just before tail).
    public Node removeLRU() {
        Node lru = tail.prev;
        if (lru == head) return null;
        remove(lru);
        return lru;
    }
}
