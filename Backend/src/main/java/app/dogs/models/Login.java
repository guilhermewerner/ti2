package app.dogs.models;

public class Login {
    public String name;
    public String passwordHash;

    /**
     * @param name
     * @param passwordHash
     */
    public Login(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }
}
