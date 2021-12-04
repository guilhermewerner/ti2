package app.dogs.models;

import app.dogs.RandomId;
import java.time.*;
import java.sql.*;

public class Testimonial {
    public int id;
    public String name;
    public String description;
    public int userId;
    public TestimonialType type;
    public boolean approved;
    public String location;
    public String[] images;
    public String[] recommendations;
    public LocalDate date;

    /**
     * Constructor for use when creating a new testimonial on the database.
     *
     * @param name
     * @param description
     * @param userId
     * @param type
     * @param location
     * @param images
     */
    public Testimonial(String name, String description, int userId, TestimonialType type, boolean approved,
            String location,
            String[] images, String[] recommendations) {
        this(RandomId.next(), name, description, userId, type, approved, location, images, recommendations,
                LocalDate.now());
    }

    /**
     * Constructor for use when fetching a testimonial from the database
     *
     * @param id
     * @param name
     * @param description
     * @param userId
     * @param type
     * @param location
     * @param images
     * @param date
     */
    public Testimonial(int id, String name, String description, int userId, String type, boolean approved,
            String location, String images,
            String recommendations,
            Date date) {
        this(id, name, description, userId, TestimonialType.valueOf(type), approved, location, images.split("</row/>"),
                recommendations.split("</row/>"),
                date.toLocalDate());
    }

    public Testimonial(int id, String name, String description, int userId, TestimonialType type, boolean approved,
            String location,
            String[] images, String[] recommendations, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.type = type;
        this.type = type;
        this.approved = approved;
        this.images = images;
        this.recommendations = recommendations;
        this.date = date;
    }

    public String getImages() {
        String result = "";

        for (int i = 0; i < images.length; i++) {
            if (i != images.length) {
                result += images[i] + "</row/>";
            } else {
                result += images[i];
            }
        }

        return result;
    }

    public String getRecommendations() {
        String result = "";

        for (int i = 0; i < recommendations.length; i++) {
            if (i != recommendations.length) {
                result += recommendations[i] + "</row/>";
            } else {
                result += recommendations[i];
            }
        }

        return result;
    }
}
