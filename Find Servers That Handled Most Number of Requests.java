import java.util.*;

public class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the number of requests handled by each server
        int[] requestCount = new int[k];
        
        // TreeSet to store currently available server IDs
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        // Min-Heap to store busy servers sorted by their free_time: [free_time, server_id]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int loadTime = load[i];
            
            // 1. Free up all servers that finished their tasks before or at the current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= arrivalTime) {
                int[] freedServer = busy.poll();
                available.add(freedServer[1]);
            }
            
            // 2. If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }
            
            // 3. Find the target server starting from (i % k)
            int targetServer = i % k;
            Integer chosenServer = available.ceiling(targetServer);
            
            // If no server found >= targetServer, wrap around to the first available server
            if (chosenServer == null) {
                chosenServer = available.first();
            }
            
            // 4. Assign the request to the chosen server
            requestCount[chosenServer]++;
            available.remove(chosenServer);
            busy.offer(new int[]{arrivalTime + loadTime, chosenServer});
        }
        
        // Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        // Collect all server IDs that achieved the maximum count
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
