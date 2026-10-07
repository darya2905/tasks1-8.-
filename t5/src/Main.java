public class Main {
    public static void main(String[] args) {
        TreeNode a = new TreeNode("A");
        TreeNode b = new TreeNode("AB1");
        TreeNode c = new TreeNode("AB2");
        TreeNode d = new TreeNode("AB3");
        TreeNode e = new TreeNode("AB1C1");
        TreeNode f = new TreeNode("AB1C2");
        TreeNode g = new TreeNode("AB2C1");
        TreeNode h = new TreeNode("AB2C1D1");

        a.addChild(b);
        a.addChild(c);
        a.addChild(d);
        b.addChild(e);
        b.addChild(f);
        c.addChild(g);
        g.addChild(h);

        SimpleTree tree1 = new SimpleTree(a);

        System.out.println("Original:");
        tree1.print();

        SimpleTree tree2 = tree1.cloneTree();

        System.out.println("\nClone:");
        tree2.print();

    }
}