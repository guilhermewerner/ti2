package app.dogs.models;

import app.dogs.RandomId;
import java.sql.*;
import java.time.*;

public class Article {
    public int id;
    public String name;
    public String text;
    public LocalDate date;

    /**
     * Constructor for use when creating a new article on the database.
     *
     * @param name
     * @param text
     */
    public Article(String name, String text) {
        this(RandomId.next(), name, text, LocalDate.now());
    }

    /**
     * Constructor for use when fetching a article from the database
     *
     * @param id
     * @param name
     * @param text
     * @param date
     */
    public Article(int id, String name, String text, Date date) {
        this(id, name, text, date.toLocalDate());
    }

    public Article(int id, String name, String text, LocalDate date) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.date = date;
    }
}
