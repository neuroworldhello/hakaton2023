CREATE TABLE vote
(
    vote_id SERIAL PRIMARY KEY,
    author_id INTEGER NOT NULL REFERENCES author (author_id) ON DELETE CASCADE,
    practice_id INTEGER NOT NULL REFERENCES practice (practice_id) ON DELETE CASCADE
);