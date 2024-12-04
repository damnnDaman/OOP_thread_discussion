package lab4;

public class TextPost extends Post {
    private String content;

    // constructor
    public TextPost(int post_id, String title, String content, User userReference)
            throws IllegalArgumentException {
        super(post_id, title, userReference);
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Post #" + getPostId() + "\n"
                + "Created By: " + getUser().getFullName() + " (@"
                + getUser().getUserName() + ")\n"
                + "Title: " + getTitle() + "\n" + content + "\n";
    }
}