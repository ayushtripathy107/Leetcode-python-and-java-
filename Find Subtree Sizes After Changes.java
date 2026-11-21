import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        
        // 1. Build initial adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        // 2. First DFS to determine new parents simultaneously
        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        
        dfsFindParents(0, adj, s, lastSeen, newParent);

        // 3. Build updated tree using newParent
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        // 4. Second DFS to compute subtree sizes
        int[] answer = new int[n];
        dfsCountSizes(0, newAdj, answer);

        return answer;
    }

    private void dfsFindParents(int u, List<List<Integer>> adj, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj.get(u)) {
            dfsFindParents(v, adj, s, lastSeen, newParent);
        }
        // Backtrack to restore state for other branches
        lastSeen[charIdx] = prevAncestor;
    }

    private int dfsCountSizes(int u, List<List<Integer>> newAdj, int[] answer) {
        int size = 1;
        for (int v : newAdj.get(u)) {
            size += dfsCountSizes(v, newAdj, answer);
        }
        answer[u] = size;
        return size;
    }
}
