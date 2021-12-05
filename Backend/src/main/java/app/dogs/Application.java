package app.dogs;

import static spark.Spark.*;
import app.dogs.services.*;

public class Application {
    private static AuthService authService = new AuthService();
    private static TestimonialService testimonialService = new TestimonialService();
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        staticFiles.location("/");

        port(80);

        options("/*", (request, response) -> {
            response.header("Access-Control-Allow-Headers", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });

        post("/login", "application/json", (request, response) -> authService.login(request, response));
        post("/register", "application/json", (request, response) -> userService.create(request, response));

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
