package app.dogs.models;

import java.sql.Date;

public class Testimonial {
    public final int id;
    public String name;
    public String description;
    public int userId;
    public TestimonialType type;
    public String location;
    public String[] images;
    public Date date;

    public Testimonial() {
        this(null, null, 0, TestimonialType.None, null, null, null);
    }

    public Testimonial(String name, String description, int userId, TestimonialType type, String location,
            String[] images, Date date) {
        this(0, name, description, userId, type, location, images, date);
    }

    public Testimonial(int id, String name, String description, int userId, TestimonialType type, String location,
            String[] images, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.type = type;
        this.location = location;
        this.images = images;
        this.date = date;
    }
}
