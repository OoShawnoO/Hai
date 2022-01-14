CREATE TABLE `record`(
                         `id` INT UNSIGNED AUTO_INCREMENT,
                         `good` VARCHAR(50) NOT NULL,
                         `intro` VARCHAR(100) NOT NULL,
                         `amount` FLOAT NOT NULL,
                         `unit` VARCHAR(10) NOT NULL,
                         `date` VARCHAR(30) NOT NULL,
                         `imgsrc` VARCHAR(100) NOT NULL,
                         PRIMARY KEY ( `id` )
)

CREATE TABLE `users`(
                        `id` INT UNSIGNED AUTO_INCREMENT,
                        `username` VARCHAR(100) NOT NULL,
                        `password` VARCHAR(100) NOT NULL,
                        `permission` INT NOT NULL,
                        PRIMARY KEY (`id`)
)
