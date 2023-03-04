import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku 
{
    private int[][] board;
    
    public Sudoku() 
    {
        board = new int[9][9];

        // This creates the completely filled in board
        for(int i = 0; i < board.length; i++)
        {
            ArrayList<Integer> popNums = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for(int j = 0; j < board.length; j++)
            {
                int randomPop = popNums.get((int) (Math.random() * popNums.size()));
                
                for(int l = 0; l < board.length; l++)
                {
                    while(board[i][l] == randomPop || board[l][i] == randomPop)
                    {
                        randomPop = popNums.get((int) (Math.random() * popNums.size()));
                    }
                }
                board[i][j] = randomPop;
                
                popNums.remove(popNums.indexOf(randomPop));
            }
        }


    }

    // Getter for win board
    public int[][] getBoard()
    {
        return this.board;
    }
    
    public static void main(String[] args)
    {
        Sudoku s = new Sudoku();

        for(int i = 0; i < s.getBoard().length; i++)
        {
            System.out.println();
            for(int j = 0; j < s.getBoard().length; j++)
            {
            System.out.print(s.getBoard()[i][j] + " ");
            }
        }
    }
}


