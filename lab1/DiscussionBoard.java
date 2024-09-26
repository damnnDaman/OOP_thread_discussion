package lab1;

import java.util.Scanner;

public class DiscussionBoard {

    public static void main(String[] args) {


        String[] name = new String[10];
        String[] message = new String[10];

        /**
         Single array that contans name and message
         both with a "Says:" string in between
         */
        String[] User = new String[10];

        /**
         nameAndMessage is used to get a return value as a string from method 1
         and appending that string to our array called User;
         */
        String nameAndMessage;

        /**
         keeping track of the size of the array using variable counter
         */
        int counter = 0;

        /**
         importing methods from MethodsFor file through object;
         */
        MethodsFor method = new MethodsFor();

        /**
         access to the keyboard's input
         */
        Scanner input = new Scanner(System.in);
        int userInput = 0;


        while (userInput != 9) {
            System.out.println("\n***************");
            System.out.println("(1) Post new message");
            System.out.println("(2) Print all posts");
            System.out.println("(3) Print all posts in reverse order");
            System.out.println("(4) Print number of posts entered so far");
            System.out.println("(5) Print all posts from a user");
            System.out.println("(6) Print the number of vowels across all posts");
            System.out.println("(7) Perform a search of posts containing a given word (case sensitive)");
            System.out.println("(8) Perform a search of posts containing a given word (case insensitive)");
            System.out.println("(9) End Program");
            System.out.println("***************\n");
            System.out.print("\nEnter the number: --> ");
            userInput = input.nextInt();

            if (userInput >= 1 && userInput <= 9) {
                switch (userInput) {
                    case 1:
                        /**
                         if counter or length of the discussion boards array
                         is greater than to 10 then, user is not allowed to
                         add any post to teh system.
                         */
                        if (counter == 10) {
                            System.out.println("---Exceeded the limit of 10---");
                            break;
                        } else {
                            nameAndMessage = method.postmsg(name[counter], message[counter]);
                            User[counter] = nameAndMessage;
                            counter++;
                        }
                        break;

                    case 2:
                        method.printAll(User);
                        break;

                    case 3:
                        method.printAllInReverse(User, counter);
                        break;

                    case 4:
                        System.out.println("\nThe number of posts entered so far...\n" + counter);
                        break;

                    case 5:
                        boolean found = method.postFromUser(User, counter);
                        if (found == false) {
                            System.out.println("\nNo post found.\n");
                        }
                        break;

                    case 6:
                        int vowel = method.printVowels(User, counter);
                        System.out.println("\nTotal number of vowels across all post...\n" + vowel);
                        break;

                    case 7:
                        method.postSearchSensitive(User, counter);
                        break;

                    case 8:
                        method.postSearchInsensitive(User, counter);
                        break;

                    case 9:
                        System.out.println("\n\nEnding the program...\nThank you!\n\n");
                        break;
                }

            } else {
                System.out.println("\n---Please enter a number between 1 and 9---\n");
            }

       }

    }
}

//---------END OF THE PROGRAM-------