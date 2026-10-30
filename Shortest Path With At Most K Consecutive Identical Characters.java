import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Adjacency list: u -> list of {v, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        // Priority Queue stores: {accumulated_weight, node, consecutive_count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        
        // dist[u][cnt] = min weight to reach node u with consecutive count cnt
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Start at node 0
        int startNode = 0;
        int startCnt = 1;
        pq.offer(new long[]{0, startNode, startCnt});
        dist[startNode][startCnt] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long w = curr[0];
            int u = (int) curr[1];
            int cnt = (int) curr[2];

            if (u == n - 1) {
                return (int) w;
            }

            if (w > dist[u][cnt]) {
                continue;
            }

            char charU = labels.charAt(u);

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int weight = edge[1];
                char charV = labels.charAt(v);

                int nextCnt = (charV == charU) ? cnt + 1 : 1;

                if (nextCnt <= k) {
                    if (w + weight < dist[v][nextCnt]) {
                        dist[v][nextCnt] = w + weight;
                        pq.offer(new long[]{w + weight, v, nextCnt});
                    }
                }
            }
        }

        return -1;
    }
}
