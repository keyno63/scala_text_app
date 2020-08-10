# picture_properties and converted_pictures schema

# --- !Ups
CREATE TABLE "picture_properties" (
"picture_id" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
"status" VARCHAR NOT NULL,
"twitter_id" BIGINT NOT NULL,
"file_name" VARCHAR NOT NULL,
"content_type" VARCHAR NOT NULL,
"overlay_text" VARCHAR NOT NULL,
"overlay_text_size" INT NOT NULL,
"created_time" TIMESTAMP NOT NULL);
CREATE INDEX ON "picture_properties" ("twitter_id");
CREATE INDEX ON "picture_properties" ("created_time");

CREATE TABLE "converted_pictures" (
"picture_id" BIGINT NOT NULL PRIMARY KEY,
"binary" BLOB NOT NULL);

# --- !Downs
DROP TABLE "picture_properties";
DROP TABLE "converted_pictures";