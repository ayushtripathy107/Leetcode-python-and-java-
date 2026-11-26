class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }

        // If the loop ends on Bob's turn, Bob cannot move, so Alice wins.
        return !isAliceTurn;
    }
}
