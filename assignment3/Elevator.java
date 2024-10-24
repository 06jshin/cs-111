
/*************************************************************************
 *  Compilation:  javac Elevator.java
 *  Execution:    java Elevator 'number of floors' 'floor requests' 'number of restricted floors' 'optional passcode'
 *
 *  @author Pooja Kedia
 *  @author Vidushi Jindal
 *
 *************************************************************************/
public class Elevator {
    
    public static void main ( String[] args ) {

	// WRITE YOUR CODE HERE
    int floors = Integer.parseInt(args[0]);
    int requests = Integer.parseInt(args[1]);
    int restrictFl = Integer.parseInt(args[2]);
    int passcode;
    int el1 = 1;
    int el2 = 1;
    while(requests > 0) {
        int current = requests % 10;
        int dis1 = Math.abs(current - el1); 
        int dis2 = Math.abs(current - el2);
        if(dis2 < dis1) {
            System.out.println("2" + " " + current);
            el2 = current;
        } else {
            System.out.println("1" + " " + current);
            el1 = current;
        }
        if(current > floors - restrictFl) {
            passcode = Integer.parseInt(args[3]);
            if(passcode % floors == current || (passcode % floors == 0 && current == floors)) {
                System.out.println("Granted");
            } else {
                System.out.println("Denied");
            }
        }
        requests /= 10;
    } 
}   
}