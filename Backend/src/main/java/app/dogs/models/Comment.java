package app.dogs.models;

import app.dogs.RandomId;
import java.time.*;
import java.sql.*;

public class Comment {
    public final int id;
    public int userId;
    public int articleId;
    public String content;
    public LocalDateTime date;

    /**
     * Constructor for use when creating a new comment on the database.
     *
     * @param userId
     * @param articleId
     * @param content
     */
    public Comment(int userId, int articleId, String content) {
        this(RandomId.next(), userId, articleId, content, LocalDateTime.now());
    }

    /**
     * Constructor for use when fetching a comment from the database
     *
     * @param id
     * @param userId
     * @param articleId
     * @param content
     * @param date
     */
    public Comment(int id, int userId, int articleId, String content, Timestamp date) {
        this(id, userId, articleId, content, date.toLocalDateTime());
    }

    public Comment(int id, int userId, int articleId, String content, LocalDateTime date) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
        this.date = date;
    }
}
