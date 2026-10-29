import java.util.*;

class Solution {
    // Global or class-level structures for HLD
    private int[] parent, depth, heavy, head, pos, values;
    private int curPos;
    private List<Integer>[] adj;
    private SegmentTree segTree;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        // 1. Build adjacency list for the tree
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // 2. Initialize HLD arrays
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        values = new int[n];
        Arrays.fill(heavy, -1);
        curPos = 0;

        // 3. Compute subtree sizes, heavy edges, and depths
        dfsSize(0, -1, 0);

        // 4. Decompose into heavy-light chains
        dfsDecompose(0, 0);

        // 5. Initialize node values as bitmasks (a=1<<0, b=1<<1, etc.)
        for (int i = 0; i < n; i++) {
            values[pos[i]] = 1 << (s.charAt(i) - 'a');
        }

        // 6. Build the Segment Tree
        segTree = new SegmentTree(values);

        List<Boolean> ans = new ArrayList<>();

        // 7. Process queries
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int mask = 1 << (c - 'a');
                segTree.update(pos[node], mask);
            } else { // "query"
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = queryPath(u, v);
                
                // A bitmask can form a palindrome if it has at most 1 bit set.
                // (pathMask & (pathMask - 1)) == 0 checks if 0 or 1 bit is set.
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    // DFS to calculate sizes and heavy children
    private int dfsSize(int v, int p, int d) {
        parent[v] = p;
        depth[v] = d;
        int size = 1;
        int maxSubtreeSize = 0;
        for (int neighbor : adj[v]) {
            if (neighbor != p) {
                int subtreeSize = dfsSize(neighbor, v, d + 1);
                size += subtreeSize;
                if (subtreeSize > maxSubtreeSize) {
                    maxSubtreeSize = subtreeSize;
                    heavy[v] = neighbor;
                }
            }
        }
        return size;
    }

    // DFS to build HLD chains
    private void dfsDecompose(int v, int h) {
        head[v] = h;
        pos[v] = curPos++;
        if (heavy[v] != -1) {
            dfsDecompose(heavy[v], h);
        }
        for (int neighbor : adj[v]) {
            if (neighbor != parent[v] && neighbor != heavy[v]) {
                dfsDecompose(neighbor, neighbor);
            }
        }
    }

    // HLD query tracking path combinations using XOR
    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= segTree.query(pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= segTree.query(pos[u], pos[v]);
        return res;
    }

    // Standard Point-Update, Range-Query Segment Tree using XOR
    private static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int[] initialValues) {
            n = initialValues.length;
            tree = new int[4 * n];
            build(initialValues, 1, 0, n - 1);
        }

        void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = (start + end) / 2;
            build(arr, 2 * node, start, mid);
            build(arr, 2 * node + 1, mid + 1, end);
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (start <= idx && idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        int query(int L, int R) {
            return query(1, 0, n - 1, L, R);
        }

        int query(int node, int start, int end, int L, int R) {
            if (R < start || end < L) return 0;
            if (L <= start && end <= R) return tree[node];
            int mid = (start + end) / 2;
            return query(2 * node, start, mid, L, R) ^ query(2 * node + 1, mid + 1, end, L, R);
        }
    }
}
