package lab4;
import java.io.*;
import java.util.*;

public class MenuOptions {
    public static Scanner input = new Scanner(System.in);
    public static int post_id = 0;

    public static void createUser(HashMap<String, ArrayList<Integer>> userPosts,
                                  ArrayList<Post> posts, ArrayList<User> UserList) {
        String fullName;

        while (true) {
            try {
                System.out.print("Enter your Full Name: ");
                fullName = input.nextLine().toLowerCase().trim();

                // Check if fullName contains any digits
                if (fullName.matches(".*\\d.*") || fullName.isEmpty()) {
                    throw new IllegalArgumentException("Invalid fullname!!!.");
                }

                // If no exception is thrown, break the loop
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.");
            }
        }

        System.out.print("Enter your Username: ");
        String username = input.nextLine().toLowerCase().trim();

        // if user doesnt enter username
        if (username.equals("")) {
            username = fullName.split(" ")[0].toLowerCase();
            System.out.println("\nUsername created as: " + username);
        }

        //         if username already registered with Discussion Board
        //         deny the request
        //        User onUser; // user on which we working
        for (User current_user : UserList) {
            if (current_user.getUserName().equals(username)) {
                System.out.println("This username already exists\n\n**REQUEST "
                        + "DENIED**\nPick a different username");

                return;
            }
        }

        //        System.out.println("checking username's existence");

        // otherwise add the user to UserList ArrayList<>
        try {
            //            System.out.println("added");
            User user = new User(fullName, username);
            UserList.add(user);
            userPosts.put(username, null);

            System.out.println("User created: " + user.getFullName() + " (@"
                    + user.getUserName() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createPost(HashMap<String, ArrayList<Integer>> userPosts,
                                  ArrayList<Post> posts, ArrayList<User> UserList) {
        System.out.print("Enter your username: ");
        String username = input.nextLine().toLowerCase().trim();

        boolean registered = false;
        boolean titleFound = false;
        boolean contentFound = false;
        String content="";
        String title = "";
        int counter = 0;


        for (User current_user : UserList) {
            if (current_user.getUserName().equals(username)) {
                registered = true;
                break;
            }
            counter++;
        }
     if (registered)  {
         System.out.println("***You are already registered on Discussion "+ "Board***\nLets add your post under your username (@" + username+")");



            while (!titleFound || !contentFound) {

                    System.out.print("Enter title for the post: ");
                    title = input.nextLine().trim();
                    System.out.print("Enter Content: ");
                    content = input.nextLine().trim();

                    // Check if the title is not empty
                    if ((!title.isEmpty() || !title.equals("")) && (!content.isEmpty() || !content.equals(""))) {
                        titleFound =contentFound= true; //set to true when both are found
                    } else {
                        if (title.isEmpty() || title.equals("")) {
                            System.out.println("Title cannot be empty. Please try again.");
                            while(!titleFound){
                            System.out.print("Enter title for the post: ");
                            title = input.nextLine().trim();
                            if(!title.isEmpty()){
                                titleFound=true;
                            }
                        }}
                        if (content.isEmpty() || content.equals("")) {
                            System.out.println("Content cannot be empty. Please try again.");
                            while(!contentFound) {
                            System.out.print("Enter Content: ");
                            content = input.nextLine().trim();
                            if (!content.isEmpty()) {
                                contentFound=true;
                            }
                        }}

                    }

            }


            //            System.out.println(counter);
            //                 System.out.println(UserList.get(counter-1));
            //            System.out.println("thisis okay");
            //                System.out.println(post_id);


                        try {
                Post post =
                        new TextPost(++post_id, title, content, UserList.get(counter));
                posts.add(post);
                //            userPosts.put(userCheck, post.add(++post_id));

                userPosts.putIfAbsent(username, new ArrayList<>());
                //      System.out.println("this is throwing error!!!");
                userPosts.get(username).add(post.getPostId());

                System.out.println("Post created successfully.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            }
        } else {
            System.out.println("**Permission Denied**\nYou need to register first");
        }
    }

    public static void printAllPost(
            HashMap<String, ArrayList<Integer>> userPosts, ArrayList<Post> posts) {
        boolean printSomething = false;

        // Loop through each user in userPosts
        for (String username : userPosts.keySet()) {
            ArrayList<Integer> indices = userPosts.get(username);

            // Skip users with no posts
            if (indices == null || indices.isEmpty()) {
                continue;
            } else {

                // For each post ID of the user, find the matching post
                for (int index : indices) {
                    System.out.println(posts.get(index-1).toString());
                    printSomething = true;
                }
            }
        }
        // If no posts were printed, output the message
        if (!printSomething) {
            System.out.println("No posts present!");
        }
    }

    public static int getIntInput(String prompt) {
        int in = -1;
        while (true) {
            System.out.print(prompt);
            try {
                in = Integer.parseInt(input.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!!!");
            }
        }
        return in;
    }


    public static void PostByUsername(
            HashMap<String, ArrayList<Integer>> userPosts, ArrayList<Post> posts) {
        System.out.print("Enter username to view post: ");
        String username = input.nextLine().trim().toLowerCase();

        ArrayList<Integer> indices = userPosts.get(username);
        if (indices == null || indices.isEmpty()) {
            System.out.println("No posts found for user @" + username);
        } else {
            for (int index : indices) {
                System.out.println(posts.get(index - 1).toString());
            }
        }
    }

    // old code doesnt want to delete it

  /*    if (PostList.size() != 0) {
          for (int i = 0; i < PostList.size(); i++) {
              if (username.equalsIgnoreCase(
                      PostList.get(i).userReference.getUserName())) {
                  System.out.println(
                          "Created By: " +
 PostList.get(i).userReference.getFullName()
                                  + "(@" +
 PostList.get(i).userReference.getUserName() + ")"); System.out.println("Title:
 " + PostList.get(i).title); System.out.println(PostList.get(i).content); gotIt
 = true;
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
                  System.out.println("Created By: " +
 post.userReference.getFullName()
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
  */
}

//---------END OF THE PROGRAM-------