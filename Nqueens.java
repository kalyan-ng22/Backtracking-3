// Time Complexity : O(n!)
// Space Complexity : O(n*n) for board + 0(n) recursive stack
// Did this code successfully run on Leetcode : Yes
// Approach : We start from 1st row 1st col by inserting queen and explore the next row's all columns if we can insert a queen by checking
// the column, upper left diagonal and upper right diagonal for an existing queen. We repeat until we complete the board, construct
// the result and return.

class Solution {
    List<List<String>> result;

    public List<List<String>> solveNQueens(int n) {
        this.result = new ArrayList<>();
        boolean[][] arr = new boolean[n][n];//create a chess board 2d array
        helper(arr, 0, n);
        return result;
    }

    public void helper(boolean[][] arr, int row, int n) {
        //base case
        if (row == arr.length) { //when all row are parsed
            List<String> li = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == true) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }

                }
                li.add(sb.toString());
            }
            result.add(li);

        }

        for (int c = 0; c < n; c++) {
            if (isSafe(arr, row, c)) { //checking if safe to add
                arr[row][c] = true;
                helper(arr, row + 1, n);
                arr[row][c] = false; //backtrack
            }
        }

    }

    private boolean isSafe(boolean[][] arr, int row, int col) {
        for (int i = row - 1; i >= 0; i--) { //column check
            if (arr[i][col]) {
                return false;
            }
        }
        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) { //upper left diagonal check
            if (arr[i][j]) {
                return false;
            }
            i--;
            j--;
        }
        int a = row;
        int b = col;
        while (a >= 0 && b < arr.length) { //upper right diagonal check
            if (arr[a][b]) {
                return false;
            }
            a--;
            b++;
        }
        return true;
    }
}