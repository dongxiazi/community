CREATE TABLE `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`account_id` VARCHAR(100) NULL,
	`name` VARCHAR(50) NOT NULL,
	`token` CHAR(36) NOT NULL,
	`gmt_create` BIGINT(20) NOT NULL,
	`gmt_modified` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;