package Team_2.entities;

public class User {
    private long id;
    private String fullName;

    public User() {
    }

    ;

    public User(String fullName) {
        this.fullName = fullName;
    }

    ;

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
