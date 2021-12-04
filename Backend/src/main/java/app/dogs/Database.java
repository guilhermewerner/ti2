package app.dogs;

import app.dogs.models.*;
import java.sql.*;

public class Database {
    private Connection connection;

    public Database() throws Exception {
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
            st.executeUpdate("INSERT INTO article (id, name, text, date) " + "VALUES ("
                    + article.id + ", '"
                    + article.name + "', '"
                    + article.text + "', '"
                    + article.date + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public Article[] getArticles() {
        Article[] articles = new Article[0];

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM article");

            if (rs.next()) {
                rs.last();
                articles = new Article[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    articles[i] = new Article(rs.getInt("id"), rs.getString("name"), rs.getString("text"),
                            rs.getDate("date"));
                }
            }

            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return articles;
    }

    public Article getArticle(int id) {
        Article article = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM article WHERE article.id = " + id);

            if (rs.first()) {
                article = new Article(rs.getInt("id"), rs.getString("name"), rs.getString("text"), rs.getDate("date"));
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
            st.executeUpdate("INSERT INTO comment (id, user_id, article_id, content, date) " + "VALUES ("
                    + comment.id + ", '"
                    + comment.userId + "', '"
                    + comment.articleId + "', '"
                    + comment.content + "', '"
                    + comment.date + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public Comment[] getComments() {
        Comment[] comments = new Comment[0];

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM comment");
            if (rs.next()) {
                rs.last();
                comments = new Comment[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    comments[i] = new Comment(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("article_id"),
                            rs.getString("content"), rs.getDate("date"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return comments;
    }

    public Comment getComment(int id) {
        Comment comment = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM comment WHERE comment.id = " + id);
            if (rs.first()) {
                comment = new Comment(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("article_id"),
                        rs.getString("content"), rs.getDate("date"));
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
            st.executeUpdate(
                    "INSERT INTO testimonial (id, name, description, user_id, type, location, images, recommendations, date) "
                            + "VALUES ("
                            + testimonial.id + ", '"
                            + testimonial.name + "', '"
                            + testimonial.description + "', '"
                            + testimonial.userId + "', '"
                            + testimonial.type + "', '"
                            + testimonial.location + "', '"
                            + testimonial.getImages() + "', '"
                            + testimonial.getRecommendations() + "', '"
                            + testimonial.date + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public Testimonial[] getTestimonials() {
        Testimonial[] testimonials = new Testimonial[0];

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM testimonial");

            if (rs.next()) {
                rs.last();
                testimonials = new Testimonial[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    testimonials[i] = new Testimonial(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getInt("user_id"), rs.getString("type"),
                            rs.getBoolean("type"),
                            rs.getString("location"), rs.getString("images"), rs.getString("recommendations"),
                            rs.getDate("date"));
                }
            }

            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return testimonials;
    }

    public Testimonial getTestimonial(int id) {
        Testimonial testimonial = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM testimonial WHERE testimonial.id = " + id);

            if (rs.first()) {
                testimonial = new Testimonial(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                        rs.getInt("user_id"), rs.getString("type"), rs.getBoolean("type"), rs.getString("location"),
                        rs.getString("images"),
                        rs.getString("recommendations"),
                        rs.getDate("date"));
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
            String sql = "UPDATE testimonial SET name = '"
                    + testimonial.name + "', description = '"
                    + testimonial.description + "', user_id = '"
                    + testimonial.userId + "', type = '"
                    + testimonial.type.toString() + "', location = '"
                    + testimonial.location + "', images = '"
                    + testimonial.getImages() + "', recommendations = '"
                    + testimonial.getRecommendations() + "', date = '"
                    + testimonial.date + "' WHERE id = "
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

    // User

    public boolean insertUser(User user) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO public.user (id, name, password_hash, email, phone) " + "VALUES (" + user.id
                    + ", '" + user.name + "', '" + user.passwordHash + "', '" + user.email + "', '" + user.phone
                    + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public User[] getUsers() {
        User[] users = new User[0];

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM public.user");

            if (rs.next()) {
                rs.last();
                users = new User[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    users[i] = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password_hash"),
                            rs.getString("email"), rs.getString("phone"));
                }
            }

            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public User getUser(int id) {
        User user = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM public.user WHERE public.user.id = " + id);

            if (rs.first()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password_hash"),
                        rs.getString("email"), rs.getString("phone"));
            }

            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return user;
    }

    public User getUserByName(String name) {
        User user = null;

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM public.user WHERE public.user.name = " + name);

            if (rs.first()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password_hash"),
                        rs.getString("email"), rs.getString("phone"));
            }

            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return user;
    }

    public boolean updateUser(User user) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE public.user SET name = '" + user.name + "', password_hash = '" + user.passwordHash
                    + "', email = '" + user.email + "', phone = '" + user.phone + "'" + " WHERE id = " + user.id;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean deleteUser(int id) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM public.user WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }
}
