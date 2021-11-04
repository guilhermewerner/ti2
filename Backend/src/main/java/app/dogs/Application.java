package app.dogs;

import static spark.Spark.*;
import app.dogs.services.*;

public class Application {
    private static ArticleService articleService = new ArticleService();
    private static CommentService commentService = new CommentService();
    private static TestimonialService testimonialService = new TestimonialService();
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        staticFiles.location("/");

        port(5555);

        get("/articles", (request, response) -> articleService.list(request, response));
        get("/articles/:id", (request, response) -> articleService.get(request, response));
        post("/articles", (request, response) -> articleService.create(request, response));
        put("/articles/:id", (request, response) -> articleService.update(request, response));
        delete("/articles/:id", (request, response) -> articleService.delete(request, response));

        get("/comments", (request, response) -> commentService.list(request, response));
        get("/comments/:id", (request, response) -> commentService.get(request, response));
        post("/comments", (request, response) -> commentService.create(request, response));
        put("/comments/:id", (request, response) -> commentService.update(request, response));
        delete("/comments/:id", (request, response) -> commentService.delete(request, response));

        get("/testimonials", (request, response) -> testimonialService.list(request, response));
        get("/testimonials/:id", (request, response) -> testimonialService.get(request, response));
        post("/testimonials", (request, response) -> testimonialService.create(request, response));
        put("/testimonials/:id", (request, response) -> testimonialService.update(request, response));
        delete("/testimonials/:id", (request, response) -> testimonialService.delete(request, response));

        get("/users", (request, response) -> userService.list(request, response));
        get("/users/:id", (request, response) -> userService.get(request, response));
        post("/users", (request, response) -> userService.create(request, response));
        put("/users/:id", (request, response) -> userService.update(request, response));
        delete("/users/:id", (request, response) -> userService.delete(request, response));
    }
}
