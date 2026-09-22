import java.util.Arrays;

class Solution {
    class Node {
        int prod;
        int[] remain;

        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;
        tree = new Node[4 * n];
        
        // Build the initial segment tree
        build(nums, 0, 0, n - 1);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Persistently update the value at the specified index
            update(0, 0, n - 1, index, value);

            // 2. Query the subarray from 'start' to the end of the array
            Node queryResult = query(0, 0, n - 1, start, n - 1);

            // 3. Store the count of ways that result in a remainder of x
            result[i] = queryResult.remain[x];
        }

        return result;
    }

    private void build(int[] nums, int node, int start, int end) {
        tree[node] = new Node(k);
        if (start == end) {
            int valMod = nums[start] % k;
            tree[node].prod = valMod;
            tree[node].remain[valMod] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(nums, 2 * node + 1, start, mid);
        build(nums, 2 * node + 2, mid + 1, end);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int valMod = val % k;
            Arrays.fill(tree[node].remain, 0);
            tree[node].prod = valMod;
            tree[node].remain[valMod] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        if (r <= mid) {
            return query(2 * node + 1, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 2, mid + 1, end, l, r);
        }
        Node leftNode = query(2 * node + 1, start, mid, l, r);
        Node rightNode = query(2 * node + 2, mid + 1, end, l, r);
        return merge(leftNode, rightNode);
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node(k);
        parent.prod = (left.prod * right.prod) % k;

        // Prefixes entirely within the left child
        for (int i = 0; i < k; i++) {
            parent.remain[i] += left.remain[i];
        }

        // Prefixes spanning across both children
        for (int i = 0; i < k; i++) {
            if (right.remain[i] > 0) {
                int combinedMod = (left.prod * i) % k;
                parent.remain[combinedMod] += right.remain[i];
            }
        }

        return parent;
    }
}
