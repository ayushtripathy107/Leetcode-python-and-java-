import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // freeServers: stores available servers as int[]{weight, index}
        // Sorted by weight asc, then index asc
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // busyServers: stores busy servers as int[]{free_time, weight, index}
        // Sorted by free_time asc, then weight asc, then index asc
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        // Initialize all servers as free
        for (int i = 0; i < n; i++) {
            freeServers.add(new int[]{servers[i], i});
        }

        int time = 0;
        for (int j = 0; j < m; j++) {
            // The current task j becomes available at time = max(time, j)
            // This fast-forwards time if all servers are busy and we must wait
            time = Math.max(time, j);

            // If no servers are available, jump time to when the earliest busy server frees up
            if (freeServers.isEmpty()) {
                time = Math.max(time, busyServers.peek()[0]);
            }

            // Release all servers that have finished their tasks by the current time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                int[] server = busyServers.poll();
                freeServers.add(new int[]{server[1], server[2]});
            }

            // Assign the best available server to task j
            int[] bestServer = freeServers.poll();
            ans[j] = bestServer[1];

            // Put the server into the busy queue with its new release time
            busyServers.add(new int[]{time + tasks[j], bestServer[0], bestServer[1]});
        }

        return ans;
    }
}
