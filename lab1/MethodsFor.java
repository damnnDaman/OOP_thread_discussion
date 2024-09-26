package lab1;

import java.util.Scanner;
import java.util.Arrays;
import java.util.StringTokenizer;


public class MethodsFor {
    public Scanner input = new Scanner(System.in);


    // METHOD 1
    public String postmsg(String name, String message) {
        System.out.println("Post a message...");
        System.out.print("Your name: ");
        name = input.nextLine();

        System.out.print("Message: ");
        message = input.nextLine();

        System.out.println("\nPOSTED!!");
        return (name + " Says: " + message);
    }

    // METHOD 2
    public void printAll(String[] User) {
        int i = 0;
        System.out.println("\nPrinting all posts...\n");
        while (User[i] != null) {
            System.out.println(User[i]);
            i++;
            if (i >= 10) {
                break;
            }
        }
    }

    //METHOD 3
    public void printAllInReverse(String[] User, int counter) {
        System.out.println("\nPrinting all posts in reverse order...\n");

        for (int i = counter - 1; i >= 0; i--) {
            System.out.println(User[i]);
        }
    }

    //METHOD 5
    public boolean postFromUser(String[] User, int counter) {
        System.out.println("\nPrinting all posts from a user...\n");
        boolean found = false;
        System.out.print("Enter a name: ");
        String userName = input.nextLine();

        for (int i = 0; i < counter; i++) {
            if (User[i].split(" Says: ")[0].equals(userName)) {
                System.out.println(User[i].split(" Says: ")[1]);
                found = true;
            }
        }
        return found;
    }

    //METHOD 6
    public int printVowels(String[] User, int counter) {
        int vowelsCounter = 0;
        for (int i = 0; i < counter; i++) {
            /**
             Outer loop to iterate through
             numer of entries we have for discussion board.

             Inner loop iterates on the number of char(s) in the message
             */
            String message = User[i].split(" Says: ")[1];
            for (int j = 0; j < (message.length()); j++) {
                if (message.charAt(j) == 'a' ||
                        message.charAt(j) == 'e' ||
                        message.charAt(j) == 'i' ||
                        message.charAt(j) == 'o' ||
                        message.charAt(j) == 'u') {
                    vowelsCounter++;
                }
            }
        }
        return vowelsCounter;
    }

    //METHOD 7
    public void postSearchSensitive(String[] User, int counter) {
        System.out.print("Searching for (Case Sensitive): ");
        String word = input.nextLine();
        String post;
        String message;

        for (int i = 0; i < counter; i++) {
            post = User[i].split(" Says: ")[1];

            /**
             post stores the actual message
             */

            /**
             using tokenizer to iterate over each
             word in the message and
             using .contains() to find if
             the substring is contained in any word
             from the message.
             */

            StringTokenizer tokens = new StringTokenizer(post, " ");
            while (tokens.hasMoreTokens()) {
                message = tokens.nextToken();
                if (message.contains(word)) {
                    System.out.println(post);
                    break;  // break if it gets the searched word.


                }
            }
        }


    }


    //METHOD 8
    public void postSearchInsensitive(String[] User, int counter) {

        System.out.print("Searching for (Case Insensitive): ");
        String word = input.nextLine();
        String post;
        String message;

        /**
        same logic as previous question.
        */
        for (int i = 0; i < counter; i++) {

            post = User[i].split(" Says: ")[1];
            StringTokenizer tokens = new StringTokenizer(post, " ");
            while (tokens.hasMoreTokens()) {
                message = tokens.nextToken();
                if (message.toLowerCase().contains(word.toLowerCase())) {
                    System.out.println(post);
                    break;


                }
            }
        }


    }

}

//---------END OF THE PROGRAM-------