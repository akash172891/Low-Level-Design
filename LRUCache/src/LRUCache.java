import Entity.DoublyLinkedList;
import Entity.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Fixed-capacity LRU Cache.
 * Logic: HashMap for O(1) lookup + DLL for O(1) recency / eviction.
 *
 * put(new) when full → remove LRU, then insert as MRU
 * get(key) / put(existing) → move that key to MRU
 */
public class LRUCache {
    private final int capacity;
    private final Map<String, Node> map;
    private final DoublyLinkedList list;

    public LRUCache(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList();
    }

    // O(1): miss → -1; hit → move to front, return value.
    public int get(String key) {
        Node node = map.get(key);
        if (node == null) return -1;
        list.moveToFront(node);
        return node.value;
    }

    // O(1): update existing OR insert new (evict LRU if at capacity).
    public void put(String key, int value) {
        Node existing = map.get(key);
        if (existing != null) {
            existing.value = value;
            list.moveToFront(existing);
            return;
        }

        if (map.size() == capacity) {
            Node lru = list.removeLRU();
            map.remove(lru.key);
            System.out.println("  [evict] " + lru.key);
        }

        Node node = new Node(key, value);
        list.addToFront(node);
        map.put(key, node);
    }

    public int size() { return map.size(); }

    public void printState(String label) {
        System.out.println(label + " → keys=" + map.keySet() + " size=" + size() + "/" + capacity);
    }
}
