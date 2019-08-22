CREATE TABLE `question` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(50) NULL,
	`description` TEXT NULL,
	`gmt_create` BIGINT(20) NULL,
	`gmt_modified` BIGINT(20) NULL,
	`creator` INT(11) NULL DEFAULT '0',
	`comment_count` INT(11) NULL DEFAULT '0' COMMENT '评论数',
	`view_count` INT(11) NULL DEFAULT '0' COMMENT '阅读数',
	`like_count` INT(11) NULL DEFAULT '0' COMMENT '点赞数 ',
	`tag` VARCHAR(256) NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
