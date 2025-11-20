
CREATE TABLE users (
    userId PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    extension VARCHAR(10)
);


CREATE TABLE calls (
    idCall PRIMARY KEY,
    userId INT NOT NULL,
    date TIMESTAMP NOT NULL,
    incoming BOOLEAN NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(userId)
);
