class Solution {
    // Number of days in each month for a non-leap year
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Convert all dates to absolute days of the year
        int startA = dateToDayOfYear(arriveAlice);
        int endA = dateToDayOfYear(leaveAlice);
        int startB = dateToDayOfYear(arriveBob);
        int endB = dateToDayOfYear(leaveBob);

        // Find the intersection of the two date ranges
        int latestArrival = Math.max(startA, startB);
        int earliestLeave = Math.min(endA, endB);

        // Calculate overlap days
        if (latestArrival <= earliestLeave) {
            return earliestLeave - latestArrival + 1;
        }
        
        return 0;
    }

    // Helper method to convert "MM-DD" string to day of the year (1-365)
    private int dateToDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int dayCount = 0;
        for (int i = 0; i < month - 1; i++) {
            dayCount += daysInMonth[i];
        }
        
        return dayCount + day;
    }
}
