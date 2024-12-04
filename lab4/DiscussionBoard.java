/**

 * Name--> Daman Kumar
 * Student ID --> 1306900
 * Compile --> javac lab4/*.java
 
 * Run --> java lab4/*.java

 */

package lab4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DiscussionBoard {
    public static void main(String[] args) {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<User> UserList = new ArrayList<>();
        HashMap<String, ArrayList<Integer>> userPosts = new HashMap<>();
        //         int post_id = 0;
        /**
         importing methods from MethodsFor file through object;
         */
        //    MenuOptions method = new MenuOptions();

        /**
         access to the keyboard's input
         */
        Scanner input = new Scanner(System.in);
        int userInput = 0;

        do {
            System.out.println("\n***************");
            System.out.println("(1) Create new user ");
            System.out.println("(2) Create new post");
            System.out.println("(3) View all posts");
            System.out.println("(4) View all posts with a given username ");
            System.out.println("(5) End Program");
            System.out.println("***************\n");
            // System.out.print("\nEnter the number: --> ");
            userInput = MenuOptions.getIntInput("Enter your choice: ");

            if (userInput >= 1 && userInput <= 5) {
                switch (userInput) {
                    case 1:
                        System.out.println("****Creating a new user****");
                        MenuOptions.createUser(userPosts, posts, UserList);
                        break;
                    case 2:
                        MenuOptions.createPost(userPosts, posts, UserList);
                        break;
                    case 3:
                        MenuOptions.printAllPost(userPosts, posts);
                        break;
                    case 4:
                        MenuOptions.PostByUsername(userPosts, posts);
                        break;
                    case 5:
                        System.out.println("\n\nEnding the program...\nThank you!\n\n");
                        break;
                }
            } else {
                System.out.println("---Please enter a number between 1 and 5---\n");
            }
        } while (userInput != 5);
    }
}

//---------END OF THE PROGRAM-------