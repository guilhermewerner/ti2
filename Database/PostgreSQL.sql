CREATE TABLE public.article (
	"id" int NOT NULL,
	"name" varchar NOT NULL,
	"text" varchar NOT NULL,
	"date" date NULL,
	CONSTRAINT article_pk PRIMARY KEY (id)
);

CREATE TABLE public."comment" (
	"id" int NOT NULL,
	"user_id" int NOT NULL,
	"article_id" int NOT NULL,
	"content" varchar NOT NULL,
	"date" date NULL,
	CONSTRAINT comment_pk PRIMARY KEY (id),
	CONSTRAINT comment_user_fk FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT comment_article_fk FOREIGN KEY (article_id) REFERENCES public.article(id) ON DELETE CASCADE ON UPDATE CASCADE
);

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
	"date" date NULL,
	CONSTRAINT testimonial_pk PRIMARY KEY (id),
	CONSTRAINT testimonial_user_fk FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE ON UPDATE CASCADE
);
