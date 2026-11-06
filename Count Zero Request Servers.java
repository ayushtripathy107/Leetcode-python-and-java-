import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        
        // 1. Sort the logs chronologically by their timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 2. Store queries with their original indices and sort them by query time
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i]; // query time
            sortedQueries[i][1] = i;          // original index
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 3. Sliding window pointers and frequency tracker
        int[] serverCounts = new int[n + 1]; // 1-indexed based on server IDs
        int activeServers = 0;
        int left = 0;
        int right = 0;
        
        for (int i = 0; i < m; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            // Expand the window on the right: include all requests up to queryTime
            while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                if (serverCounts[serverId] == 0) {
                    activeServers++;
                }
                serverCounts[serverId]++;
                right++;
            }
            
            // Shrink the window from the left: remove all requests before (queryTime - x)
            while (left < logs.length && logs[left][1] < queryTime - x) {
                int serverId = logs[left][0];
                serverCounts[serverId]--;
                if (serverCounts[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // The number of servers that received 0 requests is (Total - Active)
            ans[originalIndex] = n - activeServers;
        }
        
        return ans;
    }
}
