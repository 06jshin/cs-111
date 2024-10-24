
/**
 * Compilation: javac EgyptianPyramid.java
 * Execution:   java EgyptianPyramid 'size of grid' 'number of initial blocks'
 * 
 * @author Ayla Muminovic
 * @author Kushi Sharma
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 */
public class EgyptianPyramid {
    
    public static void main(String[] args) {

	// WRITE YOUR CODE HERE
        int size = Integer.parseInt(args[0]);
        int num = Integer.parseInt(args[1]);
        char[][] grid = new char[size][size];
        int count = 0;
        
        for(int row = grid.length-1; row >= 0; row--) {
            for(int col = 0; col < grid[row].length; col++) {
                if((num > 0 && count == 0) || ((num > 0) && (col > count - 1)  && (col < (size - count)))) {
                    grid[row][col] = 'X';
                    num--;
                } else  {
                    grid[row][col] = '=';
                }
            }
            count++;
        }
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col]);
        }
        System.out.println();
    }
    System.out.print(num + " Bricks Remaining");
}
}
