package app.dogs.models;

import app.dogs.RandomId;
import java.time.*;
import java.sql.*;

public class Testimonial {
    public final int id;
    public String name;
    public String description;
    public int userId;
    public TestimonialType type;
    public String location;
    public String[] images;
    public LocalDateTime date;

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
    public Testimonial(String name, String description, int userId, TestimonialType type, String location,
            String[] images) {
        this(RandomId.next(), name, description, userId, type, location, images, LocalDateTime.now());
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
    public Testimonial(int id, String name, String description, int userId, String type, String location, String images,
            Timestamp date) {
        this(id, name, description, userId, TestimonialType.valueOf(type), location, images.split("</row/>"),
                date.toLocalDateTime());
    }

    public Testimonial(int id, String name, String description, int userId, TestimonialType type, String location,
            String[] images, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.type = type;
        this.location = location;
        this.images = images;
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
}
