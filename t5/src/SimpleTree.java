public class SimpleTree {
    TreeNode root;

    SimpleTree() {
       root = null;
    }

    SimpleTree(TreeNode root) {
        this.root = root;
    }

    SimpleTree cloneTree() {
        if (root == null) {
            return new SimpleTree();
        }
        TreeNode newRoot = cloneNode(root);
        return new SimpleTree(newRoot);
    }

    TreeNode cloneNode(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode copy = new TreeNode(node.data);

        for (int i = 0; i < node.childCount; i++) {
            TreeNode childCopy = cloneNode(node.children[i]);
            copy.addChild(childCopy);
        }

        return copy;
    }

    void print() {
        printNode(root, "");
    }

    void printNode(TreeNode node, String indent) {
        if (node == null) {
            return;
        }

        System.out.println(indent + node.data);

        for (int i = 0; i < node.childCount; i++) {
            printNode(node.children[i], indent + " ");
            }
   }
}