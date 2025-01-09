/**
 * FruitCosts 
 * 
 * 1. This program reads in a list of fruits and their costs from a file (args[0]).
 * 2. Then finds the two lowest cost fruits and prints their names and costs
 * along with the total cost.
 * 
 * @author Srimathi Vadivel
 * @author Sarah Benedicto
 */
public class FruitCosts {
    /**
     * Main function to execute the program
     * 
     * @param args command-line arguments, where args[0] is the file name to read from
     */
    public static void main(String[] args) {

	// Do not remove this line, it opens the file for reading.
	    StdIn.setFile(args[0]);
        int numFru = StdIn.readInt();
        double[] cost = new double[numFru];
        String[] name = new String[numFru];
 
        for(int i = 0; i < numFru; i++) {
            name[i] = StdIn.readString();
            cost[i] = StdIn.readDouble();
        }

        String minName = name[0]; 
        String minName2 = " ";
        double minCost = cost[0];
        double minCost2 = 0;

        for(int i = 0; i < numFru; i++) {
            if(minCost > cost[i]) {
                minCost2 = minCost;
                minCost = cost[i];
                minName2 = minName;
                minName = name[i];
            } else if(cost[i] < minCost2) {
                minCost2 = cost[i];
                minName2 = name[i];
            }
         }
        double total = minCost + minCost2;
        StdOut.println(minName + " " + minCost);
        StdOut.println(minName2 + " " + minCost2);
        StdOut.println("Total " + total);
    }
}
