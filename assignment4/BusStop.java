/*
 * Write your program inside the main method to find the order
 * which the bus the student needs to take will arrive
 * according to the assignemnt description. 
 *
 * To compile:
 *        javac BusStop.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class BusStop {

    public static void main(String[] args) {
        // WRITE YOUR CODE HERE
        int len = args.length;
        int index = -1;
        char last = args[len - 1].charAt(0);
        char[] list = new char[len];
        for(int i = 0; i < len - 1; i++) {
            list[i] = args[i].charAt(0);
        }
        for(int i = 0; i < list.length; i++) {
            if(list[i] == last) {
                index = i + 1;
                break;
            }
        }
        System.out.println(index);
    }
}
