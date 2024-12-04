/**

 * Name--> Daman Kumar
 * Student ID --> 1306900
 * Compile --> javac lab1/DiscussionBoard.java lab1/MenuOptions.java lab1/User.java lab1/Post.java
 * Run --> java lab1/DiscussionBoard.java lab1/MenuOptions.java lab1/User.java lab1/Post.java

  */

package lab1;
import java.util.ArrayList;
import java.util.Scanner;

public class DiscussionBoard {
    public static void main(String[] args) {
        ArrayList<User> UserList = new ArrayList<>();
        ArrayList<Post> PostList = new ArrayList<>();

        /**
         importing methods from MethodsFor file through object;
         */
        MenuOptions method = new MenuOptions();

        /**
         access to the keyboard's input
         */
        Scanner input = new Scanner(System.in);
        int userInput = 0;

        while (userInput != 6) {
            System.out.println("\n***************");
            System.out.println("(1) Create new user ");
            System.out.println("(2) Create new post");
            System.out.println("(3) View all posts");
            System.out.println("(4) View all posts with a given username");
            System.out.println("(5) View all posts with a given keyword");
            System.out.println("(6) End Program");
            System.out.println("***************\n");
            System.out.print("\nEnter the number: --> ");
            userInput = input.nextInt();

            if (userInput >= 1 && userInput <= 6) {
                switch (userInput) {
                    case 1:
                        System.out.println("****Creating a new user****");
                        MenuOptions.createUser(UserList);
                        break;
                    case 2:
                        MenuOptions.createPost(PostList, UserList);
                        break;
                    case 3:
                        MenuOptions.printAllPost(PostList);
                        break;
                    case 4:
                        MenuOptions.PostByUsername(PostList);
                        break;
                    case 5:
                        MenuOptions.PostByKeyword(PostList);
                        break;
                    case 6:
                        System.out.println("\n\nEnding the program...\nThank you!\n\n");
                        break;
                }
            } else {
                System.out.println("---Please enter a number between 1 and 6---\n");
            }
        }
    }
}

//---------END OF THE PROGRAM-------