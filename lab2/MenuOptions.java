package lab1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuOptions {
    public static Scanner input = new Scanner(System.in);

    public static void createUser(ArrayList<User> UserList) {
        System.out.print("Enter your Full Name: ");
        String fullName = input.nextLine();

        System.out.print("Enter your Username: ");
        String username = input.nextLine().toLowerCase();

        // if user doesnt enter username
        if (username.equals("")) {
            username = fullName.split(" ")[0].toLowerCase();
            System.out.println("\nUsername created as: " + username);
        }

        // if username already registered with Discussion Board
        // deny the request
        for (User current_user : UserList) {
            if (current_user.getUserName().equals(username)) {
                System.out.println("This username already exists\n\n**REQUEST "
                        + "DENIED**\nPick a different username");
                return;
            }
        }

        // otherwise add the user to UserList ArrayList<>
        User user = new User(fullName.toLowerCase(), username.toLowerCase());
        UserList.add(user);
    }

    public static void createPost(
            ArrayList<Post> PostList, ArrayList<User> UserList) {
        System.out.print("Enter your username: ");
        String username = input.nextLine();
        boolean registered = false;
        int counter = 0;
        for (User current_user : UserList) {
            if (current_user.getUserName().equals(username)) {
                registered = true;
                break;
            }
            counter++;
        }

        if (registered) {
            System.out.println("***You are already registered on Discussion "
                    + "Board***\nLets add your post under your username (@"
                    + username + ")");
            System.out.print("Enter title for the post: ");
            String title = input.nextLine();
            System.out.print("Enter Content: ");
            String content = input.nextLine();
            // System.out.println(UserList.get(counter).toString());
            Post post = new Post(title, content, UserList.get(counter));
            PostList.add(post);

        } else {
            System.out.println("**Permission Denied**\nYou need to register first");
        }
    }

    public static void printAllPost(ArrayList<Post> PostList) {
        if (PostList.size() != 0) {
            for (int i = 0; i < PostList.size(); i++) {
                System.out.println(
                        "\nCreated By: " + PostList.get(i).userReference.getFullName()
                                + " (@" + PostList.get(i).userReference.getUserName() + ")");
                System.out.println("Title: " + PostList.get(i).title);
                System.out.println(PostList.get(i).content);
            }
        }

        else {
            System.out.println("There is no post yet!");
        }
    }

    public static void PostByUsername(ArrayList<Post> PostList) {
        System.out.print("Enter username to find the post: ");
        String username = input.nextLine();
        boolean gotIt = false;
        if (PostList.size() != 0) {
            for (int i = 0; i < PostList.size(); i++) {
                if (username.equalsIgnoreCase(
                        PostList.get(i).userReference.getUserName())) {
                    System.out.println(
                            "Created By: " + PostList.get(i).userReference.getFullName()
                                    + "(@" + PostList.get(i).userReference.getUserName() + ")");
                    System.out.println("Title: " + PostList.get(i).title);
                    System.out.println(PostList.get(i).content);
                    gotIt = true;
                }
            }
        }

        else {
            System.out.println("There is no post yet!");
        }
        if (!gotIt) {
            System.out.println("\nNo Post Found");
        }
    }

    public static void PostByKeyword(ArrayList<Post> PostList) {
        System.out.print("Enter keyword to find the post: ");
        String keyword = input.nextLine().toLowerCase();
        boolean gotIt = false;

        if (PostList.size() != 0) {
            for (Post post : PostList) {
                String[] title = post.getTitle().toLowerCase().split("[ ]+");
                String[] content = post.getContent().toLowerCase().split("[ ]+");
                if (Arrays.asList(title).contains(keyword)
                        || Arrays.asList(content).contains(keyword)) {
                    System.out.println("Created By: " + post.userReference.getFullName()
                            + "(@" + post.userReference.getUserName() + ")");
                    System.out.println("Title: " + post.title);
                    System.out.println(post.content);
                    gotIt = true;
                }
            }

        }

        else {
            System.out.println("There is no post yet!");
        }
        if (!gotIt) {
            System.out.println("\nNo Post Found");
        }
    }
}

//---------END OF THE PROGRAM-------