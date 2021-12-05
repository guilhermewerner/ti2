package app.dogs.models;

import app.dogs.RandomId;

public class User {
    public int id;
    public String name;
    public String password;
    public String email;
    public String phone;

    /**
     * Constructor for use when creating a new user on the database.
     *
     * @param name
     * @param password
     * @param email
     * @param phone
     */
    public User(String name, String password, String email, String phone) {
        this(RandomId.next(), name, password, email, phone);
    }

    /**
     * Constructor for use when fetching user data from the database
     *
     * @param name
     * @param password
     * @param email
     * @param phone
     */
    public User(int id, String name, String password, String email, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
