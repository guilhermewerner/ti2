package app.dogs.models;

import java.sql.Date;

public class Comment {
    public final int id;
    public int userId;
    public int articleId;
    public String content;
    public Date date;

    public Comment() {
        this(0, 0, null, null);
    }

    public Comment(int userId, int articleId, String content, Date date) {
        this(0, userId, articleId, content, date);
    }

    public Comment(int id, int userId, int articleId, String content, Date date) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
        this.content = content;
        this.date = date;
    }
}
