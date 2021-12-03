package app.dogs.services;

import java.util.ArrayList;
import app.dogs.Database;
import app.dogs.models.Testimonial;
import app.dogs.models.TestimonialType;
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
        response.header("Content-Encoding", "UTF-8");
        response.header("Content-Type", "application/json");

        try {
            Testimonial testimonial = gson.fromJson(request.body(), Testimonial.class);

            String desc = testimonial.description;

            if (desc.contains("abandono") || desc.contains("amandonado")) {
                testimonial.type = TestimonialType.Abandonment;
            } else if (desc.contains("bateu") || desc.contains("agrediu")) {
                testimonial.type = TestimonialType.Aggression;
            } else if (desc.contains("preso") || desc.contains("acorrentado") || desc.contains("corrente")) {
                testimonial.type = TestimonialType.Chaining;
            } else if (desc.contains("higiene") || desc.contains("abertado")) {
                testimonial.type = TestimonialType.Hygiene;
            } else if (desc.contains("sol") || desc.contains("chuva") || desc.contains("relento")
                    || desc.contains("frio")) {
                testimonial.type = TestimonialType.Environment;
            } else if (desc.contains("alimento") || desc.contains("alimentar")) {
                testimonial.type = TestimonialType.Hungry;
            } else if (desc.contains("ferido") || desc.contains("doente")) {
                testimonial.type = TestimonialType.Sick;
            } else {
                testimonial.type = TestimonialType.None;
            }

            if (!db.insertTestimonial(testimonial)) {
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
            Testimonial testimonial = gson.fromJson(request.body(), Testimonial.class);

            if (!db.updateTestimonial(testimonial)) {
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
            if (!db.deleteTestimonial(id)) {
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
