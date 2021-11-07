package app.dogs.services;

import java.util.ArrayList;
import app.dogs.Database;
import app.dogs.models.Article;
import spark.Request;
import spark.Response;

public class ArticleService extends BaseService {
    private Database db;

    public ArticleService() {
        try {
            db = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object list(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        ArrayList<Article> articles = new ArrayList<Article>();

        for (Article a : db.getArticles()) {
            articles.add(a);
        }

        return gson.toJson(articles);
    }

    public Object get(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        Article article = db.getArticle(id);

        if (article != null) {
            return gson.toJson(article);
        } else {
            response.status(404);
            return "{ \"error\": \"Article " + id + " not found.\" }";
        }
    }

    public Object create(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        try {
            Article article = gson.fromJson(request.body(), Article.class);

            if (!db.insertArticle(article)) {
                throw new Exception("Database error");
            }

            response.status(200);
            return "";
        } catch (Exception e) {
            response.status(500);
            return "{ \"error\": \"" + e + "\" }";
        }
    }

    public Object update(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        try {
            Article article = gson.fromJson(request.body(), Article.class);

            if (!db.updateArticle(article)) {
                throw new Exception("Database error");
            }

            response.status(200);
            return "";
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
            if (!db.deleteArticle(id)) {
                throw new Exception("Database error");
            }

            response.status(200);
            return "";
        } catch (Exception e) {
            response.status(500);
            return "{ \"error\": \"" + e + "\" }";
        }
    }
}
