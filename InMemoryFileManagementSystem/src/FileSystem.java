import java.util.ArrayList;
import java.util.List;

class FileSystem {
    private final Directory root = new Directory("");

    List<String> ls(String path) {
        Node node = traverse(path);
        if (node instanceof FileNode) {
            return List.of(node.name);
        }
        return new ArrayList<>(((Directory) node).children.keySet());
    }

    void mkdir(String path) {
        createDirectories(path);
    }

    void addContentToFile(String filePath, String content) {
        int lastSlash = filePath.lastIndexOf('/');
        String fileName = filePath.substring(lastSlash + 1);
        Directory parent = lastSlash == 0
                ? root
                : createDirectories(filePath.substring(0, lastSlash));

        Node existing = parent.children.get(fileName);
        if (existing instanceof FileNode file) {
            file.content.append(content);
        } else if (existing == null) {
            FileNode file = new FileNode(fileName);
            file.content.append(content);
            parent.children.put(fileName, file);
        }
    }

    String readContentFromFile(String filePath) {
        Node node = traverse(filePath);
        return ((FileNode) node).content.toString();
    }

    private Node traverse(String path) {
        if ("/".equals(path)) {
            return root;
        }

        Node current = root;
        for (String part : path.split("/")) {
            if (part.isEmpty()) {
                continue;
            }
            current = ((Directory) current).children.get(part);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    private Directory createDirectories(String path) {
        if ("/".equals(path)) {
            return root;
        }

        Directory current = root;
        for (String part : path.split("/")) {
            if (part.isEmpty()) {
                continue;
            }
            Node child = current.children.get(part);
            if (child instanceof Directory directory) {
                current = directory;
            } else if (child == null) {
                Directory directory = new Directory(part);
                current.children.put(part, directory);
                current = directory;
            }
        }
        return current;
    }
}
