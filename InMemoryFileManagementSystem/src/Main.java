public class Main {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        System.out.println("ls(\"/\"): " + fs.ls("/"));

        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");

        System.out.println("ls(\"/\"): " + fs.ls("/"));
        System.out.println("ls(\"/a/b/c/d\"): " + fs.ls("/a/b/c/d"));
        System.out.println("read(\"/a/b/c/d\"): " + fs.readContentFromFile("/a/b/c/d"));

        fs.addContentToFile("/a/b/c/d", " world");
        System.out.println("read after append: " + fs.readContentFromFile("/a/b/c/d"));
        System.out.println("ls(\"/a/b/c\"): " + fs.ls("/a/b/c"));
    }
}
