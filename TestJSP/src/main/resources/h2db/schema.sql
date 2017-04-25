drop table if exists reports;

CREATE TABLE reports (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  start_date DATE,
  end_date DATE,
  performer VARCHAR,
  activity VARCHAR
);
