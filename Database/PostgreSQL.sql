CREATE TABLE public.user (
	"id" int NOT NULL,
	"name" varchar NOT NULL,
	"password_hash" varchar NOT NULL,
	"email" varchar NULL,
	"phone" varchar NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE public.testimonial (
	"id" int NOT NULL,
	"name" varchar NOT NULL,
	"description" varchar NOT NULL,
	"user_id" int NOT NULL,
	"type" varchar NOT NULL,
	"location" varchar NULL,
	"images" varchar NULL,
	"recommendations" varchar NULL,
	"date" date NULL,
	CONSTRAINT testimonial_pk PRIMARY KEY (id),
	CONSTRAINT testimonial_user_fk FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE ON UPDATE CASCADE
);
