package Entity;

// One cache entry; prev/next enable O(1) unlink in the DLL.
public class Node {
    public final String key;
    public int value;
    public Node prev;
    public Node next;

    public Node(String key, int value) {
        this.key = key;
        this.value = value;
    }
}
