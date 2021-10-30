package app.dogs.models;

import java.sql.Date;

public class Article {
    public final int id;
    public String name;
    public String text;
    public Date date;

    public Article() {
        this(null, null, null);
    }

    public Article(String name, String text, Date date) {
        this(0, null, null, null);
    }

    public Article(int id, String name, String text, Date date) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.date = date;
    }
}
