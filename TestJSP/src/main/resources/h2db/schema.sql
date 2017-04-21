drop table if exists user_user_role;
drop table if exists shopping_cart_answers;
drop table if exists users;
drop table if exists user_roles;
drop table if exists products;

drop table if exists reports;

CREATE TABLE reports (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  start_date DATE,
  end_date DATE,
  performer VARCHAR,
  activity VARCHAR
);
