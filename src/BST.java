import java.util.ArrayList;
public class BST<K extends Comparable<K>, V> {
    private Node root; // first node
    private int size = 0; // size of tree
    private class Node {
        private K key;
        private V val;
        private Node left; // left child of node
        private Node right; // right child of node
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
        @Override
        public String toString() {
            return "{key: " + this.key + " value: " + this.val + "}";
        }
    }
    /**
     * getSize - method returns size of tree
     * @return int
     */
    public int getSize() {
        return size;
    }
    /**
     * put - method takes value and puts it with key in tree
     * @param key - key to put within
     * @param val - value to put
     */
    public void put(K key, V val) {
        this.root = insertNode(root, key, val);
        size++;
    }
    /**
     * insertNode - method with recursive checking insert new node in tree
     * @param node - node to check
     * @param key - key to put within
     * @param val - value to put
     * @return Node
     */
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
    /**
     * get - method takes a value from tree with key
     * @param key - key to take value within
     * @return V
     */
    public V get(K key) {
        Node node = getTreeNode(root, key);
        return (node.equals(null) ? null : node.val);
    }
    /**
     * getTreeNode - method with recursive checking takes Node with specific key
     * @param node - node to check
     * @param key - key to get node within
     * @return Node
     */
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
    /**
     * delete - method delete Node with specific key
     * @param key - key to find and delete node within
     */
    public void delete(K key) {
        this.root = deleteNode(root, key);
        size--;
    }
    /**
     * deleteNode - method delete node with recursion
     * @param node - given node to check
     * @param key - key to delete node within
     * @return Node
     */
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
    /**
     * findMinimumNode - finds minimum node from given
     * @param node - given node to check
     * @return Node
     */
    private Node findMinimumNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    /**
     * iterator - This method returns an iterator that allows iterating over the binary search tree in an inorder traversal.
     * @return Iterable<Node>
     */
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
    public int height() {
        return heightOfTree(root) - 1; // -1 because teacher said that we don't need to sum root
    }
    private int heightOfTree(Node node) {
        if (node == null) { // base case
            return 0;
        } else {
            int leftHeight = heightOfTree(node.left);
            int rightHeight = heightOfTree(node.right);
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }

    }
}
