-- Creación de roles
INSERT INTO `gr169`.`role` (`id`, `description`, `name`, `soft_deleted`, `timestamp`) SELECT '1', 'Administrador General', 'ADMIN', 0, NOW() WHERE NOT EXISTS (SELECT * FROM role WHERE id = '1');
INSERT INTO `gr169`.`role` (`id`, `description`, `name`, `soft_deleted`, `timestamp`) SELECT '2', 'Usuario del Sistema', 'USER', 0, NOW() WHERE NOT EXISTS (SELECT * FROM role WHERE id = '2');

-- Inserción de 10 administradores
INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '10', 'eva@mail.com', 'Eva', 'Domínguez', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '10');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '11', 'jaime@mail.com', 'Jaime', 'Baena', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '11');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '12', 'elisabeth@mail.com', 'Elisabeth', 'Luna', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '12');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '13', 'florencio@mail.com', 'Florencio', 'Montoya', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '13');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '14', 'asuncion@mail.com', 'Asunción', 'Mejía', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '14');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '15', 'placido@mail.com', 'Plácido', 'Rovira', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '15');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '16', 'alexia@mail.com', 'Alexia', 'Vegas', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '16');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '17', 'benjamin@mail.com', 'Benjamín', 'Iniesta', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '17');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '18', 'isabel@mail.com', 'Isabel', 'Gilabert', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '18');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '19', 'jorge@mail.com', 'Jorge', 'Ocaña', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653148013-user-admin.png", 0, NOW(), 1
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '19');

-- Inserción de 10 usuarios
INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '20', 'clotilde@mail.com', 'Clotilde', 'Recio', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653249138-user-girl-1.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '20');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '21', 'adan@mail.com', 'Adán', 'Velasco', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653203067-user-boy-1.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '21');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '22', 'cintia@mail.com', 'Cintia', 'Tejera', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653269929-user-girl-2.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '22');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '23', 'julian@mail.com', 'Julián', 'Real', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653226148-user-boy-2.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '23');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '24', 'tania@mail.com', 'Tania', 'Peralta', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653249138-user-girl-1.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '24');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '25', 'arturo@mail.com', 'Arturo', 'Arce', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653203067-user-boy-1.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '25');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '26', 'gregoria@mail.com', 'Gregoria', 'Aguilera', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653269929-user-girl-2.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '26');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '27', 'gaspar@mail.com', 'Gaspar', 'Sala', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653226148-user-boy-2.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '27');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '28', 'ines@mail.com', 'Inés', 'Castro', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653249138-user-girl-1.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '28');

INSERT INTO `gr169`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `photo`, `soft_delete`,`timestamp`,`role_id`)
SELECT '29', 'alejandro@mail.com', 'Alejandro', 'Muriel', '$2a$10$Jk6NgmOL5BsqkbnWOhy5YupoIYo9hmkfufhY8flxrrr5KVayab/tS', "https://s3.us-east-1.amazonaws.com/cohorte-marzo-77238c6a/1648653203067-user-boy-1.png", 0, NOW(), 2
WHERE NOT EXISTS (SELECT * FROM users WHERE id = '29');
