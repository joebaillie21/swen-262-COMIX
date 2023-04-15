-- + publisher
-- + seriesTitle
-- + volumeNumber
-- +issueNumber
-- + publicationDate
-- + Author
-- + principleCharacters
-- + description
-- + value
-- + grade

CREATE TABLE comics(
  id SERIAL PRIMARY KEY,
  series_title TEXT NOT NULL,
  volume_number TEXT NOT NULL,
  issue_number TEXT NOT NULL,
  publication_date DATE,
  author TEXT,
  publisher_id INT,
  principle_character TEXT, 
  description TEXT,
  value FLOAT, 
  grade INT
);

CREATE TABLE publishers(
  id SERIAL PRIMARY KEY, 
  name TEXT NOT NULL
  );

CREATE TABLE users(
  id SERIAL PRIMARY KEY, 
  collection JSON
  );