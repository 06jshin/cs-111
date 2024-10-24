public class Palindrome {
    public static void main(String[] args) {
        int int1 = Integer.parseInt(args[0]);
        int int2 = Integer.parseInt(args[1]);
        int int3 = Integer.parseInt(args[2]);
        int int4 = Integer.parseInt(args[3]);
        int int5 = Integer.parseInt(args[4]);
        int int6 = Integer.parseInt(args[5]);
        boolean check1 = int1 == int6;
        boolean check2 = int2 == int5;
        boolean check3 = int3 == int4;
        System.out.println(check1 && check2 && check3);
    }
}

