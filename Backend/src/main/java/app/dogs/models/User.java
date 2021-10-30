package app.dogs.models;

public class User {
    public final int id;
    public String name;
    public String paswordHash;
    public String email;
    public String phone;

    public User() {
        this(null, null, null, null);
    }

    public User(String name, String paswordHash, String email, String phone) {
        this(0, name, paswordHash, email, phone);
    }

    public User(int id, String name, String paswordHash, String email, String phone) {
        this.id = id;
        this.name = name;
        this.paswordHash = paswordHash;
        this.email = email;
        this.phone = phone;
    }
}
