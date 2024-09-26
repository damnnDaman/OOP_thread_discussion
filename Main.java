import java.util.Scanner;

public class Main {
    // class always starts with first letter capital
      public static void main(String argvs[]) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int count = 0;

        while (num != 0) {
            int rem = num % 10; //last digit
            num = num / 10; //removed last digit for further processing
            if (rem == 4) {
                count++;
            }
        }
        System.out.println("Total number of 4s in " + num + " is " + count);
    }
   
}