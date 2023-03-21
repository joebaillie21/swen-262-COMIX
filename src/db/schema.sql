
-- CREATE TABLE users(
--     id SERIAL PRIMARY KEY, 
--     username TEXT NOT NULL DEFAULT '',
    
-- );

CREATE TABLE comics(
    id SERIAL PRIMARY KEY, 
    series_name TEXT NOT NULL, 
    issue_num INTEGER NOT NULL, 
    full_title TEXT NOT NULL, 
    
);

CREATE TABLE publishers(
    id SERIAL PRIMARY KEY,
    comic_id INTEGER NOT NULL,
    pub_name TEXT NOT NULL
);