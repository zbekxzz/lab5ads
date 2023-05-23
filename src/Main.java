import java.util.Iterator;
public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST();
        bst.put(5, "val " + 5);
        bst.put(3, "val " + 3);
        bst.put(8, "val " + 8);
        bst.put(4, "val " + 4);
        bst.put(1, "val " + 1);
        bst.put(7, "val " + 7);
        bst.put(9, "val " + 9);
        System.out.println(bst.height());
        /**
         *           5
         *       3       8
         *     1   4   7   9
         *
         *  HEIGHT - 2
         */
    }
}
