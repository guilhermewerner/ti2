package app.dogs.services;

import java.util.ArrayList;
import app.dogs.Database;
import app.dogs.models.Comment;
import spark.Request;
import spark.Response;

public class CommentService extends BaseService {
    private Database db;

    public CommentService() {
        try {
            db = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object list(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        ArrayList<Comment> comment = new ArrayList<Comment>();

        for (Comment a : db.getComments()) {
            comment.add(a);
        }

        return gson.toJson(comment);
    }

    public Object get(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        Comment article = db.getComment(id);

        if (article != null) {
            return gson.toJson(article);
        } else {
            response.status(404);
            return "{ \"error\": \"Comment " + id + " not found.\" }";
        }
    }

    public Object create(Request request, Response response) {
        return "";
    }

    public Object update(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        Comment article = db.getComment(id);

        if (article != null) {
            return gson.toJson(article);
        } else {
            response.status(404);
            return "{ \"error\": \"Comment " + id + " not found.\" }";
        }
    }

    public Object delete(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        Comment article = db.getComment(id);

        if (article != null) {
            return gson.toJson(article);
        } else {
            response.status(404);
            return "{ \"error\": \"Comment " + id + " not found.\" }";
        }
    }
}
