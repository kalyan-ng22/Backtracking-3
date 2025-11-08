// Time Complexity : O(m*n*3^(L)) where L is the length of word
// Space Complexity : O(L)
// Did this code successfully run on Leetcode : Yes
// Approach : Performed DFW with backtracking to get to the solution. We start performing DFS when we encounter first char in word while
// parsing the matrix. We mark the visited chars as # and continue exploring neighbors. When the next char si not found we backtrack and
// unmark the visited node so that it can be visited again. Continuw until we reach the end of the word length.

class Solution {
    int[][] directions;
    int m;
    int n;

    public boolean exist(char[][] board, String word) {
        this.directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };// directions array
        this.m = board.length;
        this.n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {//when first char is word is found
                    if (helper(board, word, i, j, 0)) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    private boolean helper(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) { //base case
            return true;
        }
        if (i < 0 || j < 0 || i == m || j == n || board[i][j] == '#') { //bounds and visited condition check
            return false;
        }

        if (board[i][j] == word.charAt(index)) { //match found
            board[i][j] = '#';// marks as visited
            for (int[] dir : directions) {
                int nr = i + dir[0]; // neighbour row
                int nc = j + dir[1]; // neighbour column
                if (helper(board, word, nr, nc, index + 1)) {
                    return true;
                }
            }
            board[i][j] = word.charAt(index); //backtrack
        }
        return false;

    }
}