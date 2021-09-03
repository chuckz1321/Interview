package com.chuck.tree;

public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        TreeNode tmpLeft = null;
        TreeNode tmpRight = null;
        if (root == null || (root.left == null && root.right == null)) return root;
        if (root.left != null) {
            tmpLeft = invertTree(root.left);
        }
        if (root.right != null) {
            tmpRight = invertTree(root.right);
        }
        root.left = tmpRight;
        root.right = tmpLeft;
        return root;
    }


    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = left;
        root.left = null;
        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = right;
    }

    int i = 0;
    public int kthSmallest(TreeNode root, int k) {
        findK(root, k);
        return res;
    }

    int res = 0;
    private void findK(TreeNode root, int k) {
        if (root == null) return;
        findK(root.left, k);
        i++;
        if (k == i) {
            res = root.val;
            return;
        }
        findK(root.right, k);
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            root.val = findRightMin(root.right);
            root.right = deleteNode(root.right, root.val);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    public int findRightMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node.val;
    }


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode ro = new TreeNode(1, new TreeNode(2,null,null), new TreeNode(3, null, null));
        InvertTree invertTree = new InvertTree();
        invertTree.invertTree(ro);
    }


    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) return false;
        pre = root.val;
        return isValidBST(root.right);
    }

    int memo[][];
    public int numTrees(int n) {
        memo = new int[n+1][n+1];
        return count(1,n);
    }

    int rr = 0;
    private int count(int l, int r) {
        if (l > r) return 1;

        if (memo[l][r] > 0) return memo[l][r];

        for (int j = l; j <= r; j++) {
            rr = rr + count(l, i -1) * count(i+1, r);
        }
        memo[l][r] = rr;
        return rr;
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}