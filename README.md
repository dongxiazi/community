##码匠社区

##资料
[spring 文档](https://spring.io/guides)
[spring web](https://spring.io/guides/gs/serving-web-content/)
[es](https://elasticsearch.cn/explore)
[BootStrap](https://v3.bootcss.com/components/)
[GitHub OAuth](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)
[Thymeleaf官网](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
[Spring]
##工具
[Git](https://git-scm.com/download/win)
[visual paradigm](https://www.visual-paradigm.com/cn/)
[flyway](https://flywaydb.org/getstarted/firststeps/maven#integrating-flyway)

##脚本
```sql
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

```