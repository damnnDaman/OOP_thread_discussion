package lab5;

public class Post {
    private int postId;
    private String title;
    public User userReference;

    //constructor
    public Post( int postId, String title, User userReference) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Empty Title.");
        }
        this.postId = postId;
        this.title = title;
        this.userReference = userReference;
    }


    public int getPostId() {
        return postId;
    }
    public User getUser() {
        return userReference;
    }

    public String getTitle() {
        return title;
    }

    public User getUserReference() {
        return userReference;
    }

}