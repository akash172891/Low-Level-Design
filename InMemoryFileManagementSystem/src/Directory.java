import java.util.TreeMap;

class Directory extends Node {
    final TreeMap<String, Node> children = new TreeMap<>();

    Directory(String name) {
        super(name);
    }
}
