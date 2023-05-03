CREATE TABLE category
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL
);

CREATE TABLE team
(
    team_id SERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL
);

CREATE TABLE author
(
    author_id SERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL
);

CREATE TABLE practice
(
    practice_id   SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   TEXT         NOT NULL,
    document_link VARCHAR(255) NOT NULL,
    rating        INTEGER,
    team_id       INTEGER      NOT NULL REFERENCES team (team_id) ON DELETE CASCADE,
    category_id   INTEGER      NOT NULL REFERENCES category (category_id) ON DELETE CASCADE,
    author_id     INTEGER      NOT NULL REFERENCES author (author_id) ON DELETE CASCADE
);