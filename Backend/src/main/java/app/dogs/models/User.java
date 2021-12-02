package app.dogs.models;

import app.dogs.RandomId;

public class User {
    public int id;
    public String name;
    public String passwordHash;
    public String email;
    public String phone;

    /**
     * Constructor for use when creating a new user on the database.
     *
     * @param name
     * @param passwordHash
     * @param email
     * @param phone
     */
    public User(String name, String passwordHash, String email, String phone) {
        this(RandomId.next(), name, passwordHash, email, phone);
    }

    /**
     * Constructor for use when fetching user data from the database
     *
     * @param name
     * @param passwordHash
     * @param email
     * @param phone
     */
    public User(int id, String name, String passwordHash, String email, String phone) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phone = phone;
    }
}
