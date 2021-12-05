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

    // Testimonial

    public boolean insertTestimonial(Testimonial testimonial) {
        boolean status = false;

        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    "INSERT INTO testimonial (id, name, description, user_id, type, location, images, recommendations, date) "
                            + "VALUES ("
                            + testimonial.id + ", '"
                            + Injection.prevent(testimonial.name) + "', '"
                            + Injection.prevent(testimonial.description) + "', '"
                            + testimonial.userId + "', '"
                            + testimonial.type.toString() + "', '"
                            + Injection.prevent(testimonial.location) + "', '"
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
                            rs.getBoolean("approved"),
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
                        rs.getInt("user_id"), rs.getString("type"), rs.getBoolean("approved"), rs.getString("location"),
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
                    + Injection.prevent(testimonial.name + "', description = '")
                    + Injection.prevent(testimonial.description + "', user_id = '")
                    + testimonial.userId + "', type = '"
                    + testimonial.type.toString() + "', '"
                    + Injection.prevent(testimonial.location) + "', images = '"
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
            st.executeUpdate("INSERT INTO public.user (id, name, password_hash, email, phone) " + "VALUES ("
                    + user.id + ", '"
                    + Injection.prevent(user.name) + "', '"
                    + Injection.prevent(user.password) + "', '"
                    + Injection.prevent(user.email) + "', '"
                    + Injection.prevent(user.phone) + "');");
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
            ResultSet rs = st
                    .executeQuery("SELECT * FROM public.user WHERE public.user.name = '" + Injection.prevent(name) + "';");

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
            String sql = "UPDATE public.user SET name = '"
                    + Injection.prevent(user.name + "', password_hash = '")
                    + Injection.prevent(user.password + "', email = '")
                    + Injection.prevent(user.email + "', phone = '")
                    + Injection.prevent(user.phone + "'" + " WHERE id = ")
                    + user.id;
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
