package app.dogs.services;

import java.util.ArrayList;
import app.dogs.AzureClient;
import app.dogs.Recomendations;
import app.dogs.Database;
import app.dogs.models.Testimonial;
import app.dogs.models.TestimonialType;
import spark.Request;
import spark.Response;

public class TestimonialService extends BaseService {
    private Database db;
    private AzureClient azure;

    public TestimonialService() {
        this.azure = new AzureClient();

        try {
            this.db = new Database();
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

            String matches = azure.extract(testimonial.description);

            if (matches.contains("abandono") || matches.contains("amandonado")) {
                testimonial.type = TestimonialType.Abandonment;
                testimonial.recommendations = Recomendations.abandonment();
            } else if (matches.contains("bateu") || matches.contains("agrediu")) {
                testimonial.type = TestimonialType.Aggression;
                testimonial.recommendations = Recomendations.aggression();
            } else if (matches.contains("preso") || matches.contains("acorrentado") || matches.contains("corrente")) {
                testimonial.type = TestimonialType.Chaining;
                testimonial.recommendations = Recomendations.chaining();
            } else if (matches.contains("higiene") || matches.contains("abertado")) {
                testimonial.type = TestimonialType.Hygiene;
                testimonial.recommendations = Recomendations.hygiene();
            } else if (matches.contains("sol") || matches.contains("chuva") || matches.contains("relento")
                    || matches.contains("frio")) {
                testimonial.type = TestimonialType.Environment;
                testimonial.recommendations = Recomendations.environment();
            } else if (matches.contains("alimento") || matches.contains("comida") || matches.contains("alimentar")
                    || matches.contains("fome")) {
                testimonial.type = TestimonialType.Hungry;
                testimonial.recommendations = Recomendations.hungry();
            } else if (matches.contains("ferido") || matches.contains("doente")) {
                testimonial.type = TestimonialType.Sick;
                testimonial.recommendations = Recomendations.sick();
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
            e.printStackTrace();
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
