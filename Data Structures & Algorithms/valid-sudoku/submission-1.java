class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9]; 
        int[] cols = new int[9]; 
        int[] boxes = new int[9]; 

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char cell = board[r][c]; 

                if (cell == '.') {
                    continue; 
                }

                int bit = 1 << (cell - '1'); // 5 -> (decimal: 16)
                int box = (r / 3) * 3 + (c / 3); // (6, 6) -> (6 / 2) * 3 + (6 / 2) -> box 8 

                if ((rows[r] & bit) != 0 // bitwise & 
                    || (cols[c] & bit) != 0
                    || (boxes[box] & bit) != 0) {
                        return false;
                    }

                rows[r] |= bit; // rows[5] -> 000010000
                cols[c] |= bit; // cols[5] -> 000010000
                boxes[box] |= bit; // boxes[8] -> 000010000

                
            }
        }
        return true;
    }
}
