DROP DATABASE springmvc;
CREATE DATABASE IF NOT EXISTS springmvc DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;--大小写不敏感
CREATE DATABASE IF NOT EXISTS springmvc DEFAULT CHARACTER SET utf8 COLLATE utf8_general_cs;--大小写敏感
SHOW DATABASES;

--选择数据库
USE springmvc;

--刪除表
DROP TABLE user;

--創建表
CREATE TABLE user(
id INT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
name VARCHAR(20) COMMENT '姓名',
sex VARCHAR(2) COMMENT '性别',
password VARCHAR(20) COMMENT '密码',
birthday DATE COMMENT '生日',
PRIMARY KEY(id),
INDEX day_idx (birthday)
)ENGINE = INNODB AUTO_INCREMENT = 100 DEFAULT CHARSET = utf8;

--COMMENT '字符串' //注释
--PRIMARY KEY(id)//主键
--auto_increment 1//自增，从1开始
--engine = innodb //引擎
--charset = utf8 //编码
--INDEX day_idx (birthday)//给'birthday'列创建day_idx名的索引
--CREATE INDEX 索引名ON 表名(字段名)也可以创建索引
--删除索引：DORP INDEX 索引名称 ON 表名
--唯一索引：CREATE UNIQUE INDEX 索引名称 ON 表名(字段名); 不可以重复值，但可以有空值
--组合索引：CREATE INDEX 索引名称 ON 表名(字段一, 字段二, 字段三);
--全文索引：ALTER TABLE tablename ADD FULLTEXT(column1, column2)
--查看表结构：show create table user;
--查看表中索引：show index from user;