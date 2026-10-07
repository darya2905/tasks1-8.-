public class TreeNode {
    String data;
    TreeNode[] children;
    int childCount;

    TreeNode(String data) {
        this.data = data;
        this.children = new TreeNode[10];
        this.childCount = 0;
    }

    void addChild(TreeNode child) {
        children[childCount] = child;
        childCount++;
    }

    void addChild(String data) {
        TreeNode child = new TreeNode(data);
        children[childCount] = child;
        childCount++;
    }
}