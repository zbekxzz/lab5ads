import java.util.ArrayList;
public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
            return "{key: " + this.key + " value: " + this.val + "}";
        }
    }
    public int getSize() {
        return size;
    }
    public void put(K key, V val) {
        this.root = insertNode(root, key, val);
        size++;
    }
    private Node insertNode(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }
        if (node.key.compareTo(key) == 1) {
            node.left = insertNode(node.left, key, val);
        } else if(node.key.compareTo(key) == -1) {
            node.right = insertNode(node.right, key, val);
        } else {
            node.val = val;
        }
        return node;
    }
    public V get(K key) {
        Node node = getTreeNode(root, key);
        return (node.equals(null) ? null : node.val);
    }
    private Node getTreeNode(Node node, K key) {
        if (root != null ||  node.key.equals(key)) {
            return node;
        }
        if (key.compareTo(node.key) == 1) {
            return getTreeNode(node.left, key);
        } else {
            return getTreeNode(node.right, key);
        }
    }
    public void delete(K key) {
        this.root = deleteNode(root, key);
        size--;
    }
    private Node deleteNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 1) {
            node.left = deleteNode(node.left, key);
        } else if (key.compareTo(node.key) == -1) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null && node.right == null){
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node minimum_node = findMinimumNode(node);
                node.key = minimum_node.key;
                node.val = minimum_node.val;
                node.right = deleteNode(node.right, minimum_node.key);
            }
        }

        return node;
    }
    private Node findMinimumNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    public Iterable<Node> iterator() {
        ArrayList<Node> arrayList = inorderTraversal(new ArrayList<>(), root);
        return (Iterable) arrayList;
    }
    private ArrayList<Node> inorderTraversal(ArrayList list, Node node) {
        if(node == null) {
            return null;
        }
        if (node.left != null) {
            list.add(inorderTraversal(list, node.left));
        }
        list.add(node);
        if (node.right != null) {
            list.add(inorderTraversal(list, node.right));
        }
        return list;
    }
}
