CREATE TABLE userfs1 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);
INSERT INTO userfs1 (name, email) VALUES ('User1', 'user1@mail.com');
INSERT INTO userfs1 (name, email) VALUES ('User2', 'user2@mail.com');
INSERT INTO userfs1 (name, email) VALUES ('User3', 'user3@mail.com');
INSERT INTO userfs1 (name, email) VALUES ('User4', 'user4@mail.com');
INSERT INTO userfs1 (name, email) VALUES ('User5', 'user5@mail.com');
-- Add up to 200+ users (or use a script for bulk insert)
