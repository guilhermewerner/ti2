package app.dogs.services;

import java.util.ArrayList;
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
        return "";
    }

    public Object update(Request request, Response response) {
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

    public Object delete(Request request, Response response) {
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
}
