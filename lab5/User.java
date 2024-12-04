package lab5;

public class User  {
    private String fullName;
    private String UserName;

    public User(String fullName, String UserName) {
        this.fullName = fullName;
        this.UserName = UserName;
    }
    public String getFullName (){
        return fullName;
    }
    public String getUserName(){
        return UserName;
    }
    public String toString() {
        return fullName + " " + UserName;
    }
}