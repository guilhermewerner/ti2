package app.dogs.services;

import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app.dogs.Database;
import app.dogs.models.User;
import spark.Request;
import spark.Response;

public class UserService extends BaseService {
    private Database db;

    public UserService() {
        try {
            db = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object list(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        ArrayList<User> users = new ArrayList<User>();

        for (User a : db.getUsers()) {
            users.add(a);
        }

        return gson.toJson(users);
    }

    public Object get(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        User user = db.getUser(id);

        if (user != null) {
            return gson.toJson(user);
        } else {
            response.status(404);
            return "{ \"error\": \"User " + id + " not found.\" }";
        }
    }

    public Object create(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        try {
            User user = gson.fromJson(request.body(), User.class);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.password = encoder.encode(user.password);

            if (!db.insertUser(user)) {
                throw new Exception("Database error");
            }

            response.status(200);
            return "{ \"status\": \"success\" }";
        } catch (Exception e) {
            response.status(500);
            return "{ \"error\": \"" + e + "\" }";
        }
    }

    public Object update(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        try {
            User user = gson.fromJson(request.body(), User.class);

            if (!db.updateUser(user)) {
                throw new Exception("Database error");
            }

            response.status(200);
            return "{ \"status\": \"success\" }";
        } catch (Exception e) {
            response.status(500);
            return "{ \"error\": \"" + e + "\" }";
        }
    }

    public Object delete(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));

        try {
            if (!db.deleteUser(id)) {
                throw new Exception("Database error");
            }

            response.status(200);
            return "{ \"status\": \"success\" }";
        } catch (Exception e) {
            response.status(500);
            return "{ \"error\": \"" + e + "\" }";
        }
    }
}
