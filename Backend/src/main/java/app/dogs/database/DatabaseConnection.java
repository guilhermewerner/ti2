package app.dogs.database;

import app.dogs.models.*;
import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() throws Exception {
        String host = "localhost";
        int port = 5432;
        String user = "postgres";
        String pass = "1234";

        String url = "jdbc:postgresql://" + host + ":" + port + "/postgres";
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection(url, user, pass);
    }

    public boolean close() {
        boolean status = false;

        try {
            connection.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return status;
    }

    // Article

    public boolean insertArticle(Article article) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO article (id, name, text, date) " + "VALUES (" + article.id + ", '"
                    + article.name + "', '" + article.text + "', '" + article.date + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public Article[] getArticles() {
        Article[] article = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM article");
            if (rs.next()) {
                rs.last();
                article = new Article[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    article[i] = new Article(rs.getInt("id"), rs.getString("name"), rs.getString("text"),
                            rs.getTimestamp("date"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return article;
    }

    public Article getArticle(int id) {
        Article article = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM article WHERE article.id = " + id);

            if (rs.first()) {
                article = new Article(rs.getInt("id"), rs.getString("name"), rs.getString("text"),
                        rs.getTimestamp("date"));
            }

            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return article;
    }

    public boolean updateArticle(Article article) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE article SET name = '" + article.name + "', text = '" + article.text + "', date = '"
                    + article.date + "'" + " WHERE id = " + article.id;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean deleteArticle(int id) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM article WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    // Comment

    public boolean insertComment(Comment comment) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO comment (id, userId, articleId, content, date) " + "VALUES (" + comment.id
                    + ", '" + comment.userId + "', '" + comment.articleId + "', '" + comment.content + "', '"
                    + comment.date + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public Comment[] getComments() {
        Comment[] comment = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM comment");
            if (rs.next()) {
                rs.last();
                comment = new Comment[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    comment[i] = new Comment(rs.getInt("id"), rs.getInt("userId"), rs.getInt("articleId"),
                            rs.getString("content"), rs.getTimestamp("date"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return comment;
    }

    public Comment getComment(int id) {
        Comment comment = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM comment WHERE comment.id = " + id);
            if (rs.first()) {
                comment = new Comment(rs.getInt("id"), rs.getInt("userId"), rs.getInt("articleId"),
                        rs.getString("content"), rs.getTimestamp("date"));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return comment;
    }

    public boolean updateComment(Comment comment) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE comment SET content = '" + comment.content + "', date = '" + comment.date + "'"
                    + " WHERE id = " + comment.id;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean deleteComment(int id) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM comment WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    // Testimonial

    public boolean insertTestimonial(Testimonial testimonial) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO testimonial (id, name, description, type, location, images, date) "
                    + "VALUES (" + testimonial.id + ", '" + testimonial.name + "', '" + testimonial.description + "', '"
                    + testimonial.userId + "', '" + testimonial.type + "', '" + testimonial.location + "', '"
                    + testimonial.images + "', '" + testimonial.date + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public Testimonial[] getTestimonials() {
        Testimonial[] testimonial = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM testimonial");

            if (rs.next()) {
                rs.last();
                testimonial = new Testimonial[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    testimonial[i] = new Testimonial(rs.getInt("id"), rs.getString("userId"), rs.getString("articleId"),
                            rs.getInt("content"), rs.getString("date"), rs.getString("date"), rs.getString("date"),
                            rs.getTimestamp("date"));
                }
            }

            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return testimonial;
    }

    public Testimonial getTestimonial(int id) {
        Testimonial testimonial = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM testimonial WHERE testimonial.id = " + id);

            if (rs.first()) {
                testimonial = new Testimonial(rs.getInt("id"), rs.getString("userId"), rs.getString("articleId"),
                        rs.getInt("content"), rs.getString("date"), rs.getString("date"), rs.getString("date"),
                        rs.getTimestamp("date"));
            }

            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return testimonial;
    }

    public boolean updateTestimonial(Testimonial testimonial) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE testimonial SET name = '" + testimonial.name + "', description = '"
                    + testimonial.description + "', userId = '" + testimonial.userId + "', type = '"
                    + testimonial.type.toString() + "', location = '" + testimonial.location + "', images = '"
                    + testimonial.getImages() + "', date = '" + testimonial.date + "'" + " WHERE id = "
                    + testimonial.id;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean deleteTestimonial(int id) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM testimonial WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }
}
