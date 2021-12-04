package app.dogs;

public class Injection {
    public static String prevent(String attr) {
        return attr.replace("'", "").replace("'\"'", "").replace(",", "").replace(";", "");
    }
}
