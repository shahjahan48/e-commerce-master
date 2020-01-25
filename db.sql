USE `product_heaven`;

CREATE TABLE `users` (
   id BIGINT NOT NULL AUTO_INCREMENT,
   user_name VARCHAR(30) NOT NULL,
   `password` VARCHAR(100) NOT NULL,
   email_address VARCHAR(30) NOT NULL,
   created_date DATETIME NOT NULL DEFAULT NOW(),
   is_active BIT DEFAULT 0 NOT NULL,
   
   CONSTRAINT `PK_USERS` PRIMARY KEY (id),
   CONSTRAINT `UK_USERNAME` UNIQUE (user_name),
   CONSTRAINT `UK_EMAIL` UNIQUE (email_address)
);
   
/* USER_PROFILE table contains all possible roles */ 
CREATE TABLE `roles`(
   id BIGINT NOT NULL AUTO_INCREMENT,
   role_name VARCHAR(30) NOT NULL,
   role_text VARCHAR(60) CHARSET utf8mb4 NULL,
   created_date DATETIME NOT NULL DEFAULT NOW(),
   
   CONSTRAINT `PK_ROLES` PRIMARY KEY (id),
   CONSTRAINT `UK_ROLENAME` UNIQUE (role_name)
);

CREATE TABLE `user_roles` (
	id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_date DATETIME NOT NULL DEFAULT NOW(),
    
    CONSTRAINT PK_USERROLES PRIMARY KEY (id),
    CONSTRAINT FK_USERROLES_USERS FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FK_USERROLES_ROLES FOREIGN KEY (role_id) REFERENCES roles (id)
);

/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    user_name VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

/* Populate USER_PROFILE Table */
INSERT INTO roles(role_name, role_text)
VALUES ('USER', 'User'),
	('ADMIN', 'Admin'),
	('DBA', 'Database Admin');
  
  
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO users(user_name, password, email_address, is_active)
VALUES ('shahed','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'shahed@gmail.com', 1),
	('admin','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'admin@gmail.com', 1),
	('imran','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'imran@gmail.com', 1);
  
  
/* Populate JOIN Table */
INSERT INTO user_roles (user_id, role_id)
  SELECT user.id, role.id FROM users user, roles role
  where user.user_name in ('shahed', 'admin') and role.role_name IN ('ADMIN', 'USER');
 