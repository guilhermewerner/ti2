package app.dogs.models;

import app.dogs.RandomId;

public class User {
    public final int id;
    public String name;
    public String paswordHash;
    public String email;
    public String phone;

    /**
     * Constructor for use when creating a new user on the database.
     *
     * @param name
     * @param paswordHash
     * @param email
     * @param phone
     */
    public User(String name, String paswordHash, String email, String phone) {
        this(RandomId.next(), name, paswordHash, email, phone);
    }

    /**
     * Constructor for use when fetching user data from the database
     *
     * @param name
     * @param paswordHash
     * @param email
     * @param phone
     */
    public User(int id, String name, String paswordHash, String email, String phone) {
        this.id = id;
        this.name = name;
        this.paswordHash = paswordHash;
        this.email = email;
        this.phone = phone;
    }
}
