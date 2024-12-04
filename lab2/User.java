package lab1;

public class User  {
    String fullName;
    String UserName;

    User(String fullName, String UserName) {
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