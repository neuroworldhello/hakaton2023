CREATE TABLE comment
(
    comment_id  SERIAL PRIMARY KEY,
    text        TEXT      NOT NULL,
    practice_id INTEGER   NOT NULL REFERENCES practice (practice_id) ON DELETE CASCADE,
    author_id   INTEGER   NOT NULL REFERENCES author (author_id) ON DELETE CASCADE,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);
