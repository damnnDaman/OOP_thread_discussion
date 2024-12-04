package lab1;

public class Post {
    String title;
    String content;
    User userReference;

    Post(String title, String content, User userReference) {
        this.title = title;
        this.content = content;
        this.userReference = userReference;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}