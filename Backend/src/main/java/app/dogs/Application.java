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

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");

            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "*");
            response.type("application/json");
        });

        get("/articles", "application/json", (request, response) -> articleService.list(request, response));
        get("/articles/:id", "application/json", (request, response) -> articleService.get(request, response));
        post("/articles", "application/json", (request, response) -> articleService.create(request, response));
        put("/articles/:id", "application/json", (request, response) -> articleService.update(request, response));
        delete("/articles/:id", "application/json", (request, response) -> articleService.delete(request, response));

        get("/comments", "application/json", (request, response) -> commentService.list(request, response));
        get("/comments/:id", "application/json", (request, response) -> commentService.get(request, response));
        post("/comments", "application/json", (request, response) -> commentService.create(request, response));
        put("/comments/:id", "application/json", (request, response) -> commentService.update(request, response));
        delete("/comments/:id", "application/json", (request, response) -> commentService.delete(request, response));

        get("/testimonials", "application/json", (request, response) -> testimonialService.list(request, response));
        get("/testimonials/:id", "application/json", (request, response) -> testimonialService.get(request, response));
        post("/testimonials", "application/json", (request, response) -> testimonialService.create(request, response));
        put("/testimonials/:id", "application/json", (request, response) -> testimonialService.update(request, response));
        delete("/testimonials/:id", "application/json", (request, response) -> testimonialService.delete(request, response));

        get("/users", "application/json", (request, response) -> userService.list(request, response));
        get("/users/:id", "application/json", (request, response) -> userService.get(request, response));
        post("/users", "application/json", (request, response) -> userService.create(request, response));
        put("/users/:id", "application/json", (request, response) -> userService.update(request, response));
        delete("/users/:id", "application/json", (request, response) -> userService.delete(request, response));
    }
}
