package app.dogs.services;

import java.util.ArrayList;
import app.dogs.Database;
import app.dogs.models.Testimonial;
import spark.Request;
import spark.Response;

public class TestimonialService extends BaseService {
    private Database db;

    public TestimonialService() {
        try {
            db = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object list(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        ArrayList<Testimonial> testimonials = new ArrayList<Testimonial>();

        for (Testimonial a : db.getTestimonials()) {
            testimonials.add(a);
        }

        return gson.toJson(testimonials);
    }

    public Object get(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        Testimonial testimonial = db.getTestimonial(id);

        if (testimonial != null) {
            return gson.toJson(testimonial);
        } else {
            response.status(404);
            return "{ \"error\": \"Testimonial " + id + " not found.\" }";
        }
    }

    public Object create(Request request, Response response) {
        return "";
    }

    public Object update(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        Testimonial testimonial = db.getTestimonial(id);

        if (testimonial != null) {
            return gson.toJson(testimonial);
        } else {
            response.status(404);
            return "{ \"error\": \"Testimonial " + id + " not found.\" }";
        }
    }

    public Object delete(Request request, Response response) {
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        int id = Integer.parseInt(request.params(":id"));
        Testimonial testimonial = db.getTestimonial(id);

        if (testimonial != null) {
            return gson.toJson(testimonial);
        } else {
            response.status(404);
            return "{ \"error\": \"Testimonial " + id + " not found.\" }";
        }
    }
}
