package app.dogs;

public class RandomId {
    public static int next() {
        return (int) (Math.random() * (1000));
    }
}
