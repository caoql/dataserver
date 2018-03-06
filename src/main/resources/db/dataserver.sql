/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : dataserver

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-06 15:37:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for datasourceconfig
-- ----------------------------
DROP TABLE IF EXISTS `datasourceconfig`;
CREATE TABLE `datasourceconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(40) NOT NULL COMMENT '数据源名称',
  `jdbc_driver` varchar(60) NOT NULL COMMENT '数据库驱动',
  `jdbc_url` varchar(60) NOT NULL COMMENT '连接URL',
  `jdbc_username` varchar(60) DEFAULT NULL COMMENT '连接用户名',
  `jdbc_password` varchar(60) DEFAULT NULL COMMENT '密码',
  `is_use` char(1) DEFAULT '1' COMMENT '是否启用，1：启用，0：禁用',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datasourceconfig
-- ----------------------------
INSERT INTO `datasourceconfig` VALUES ('2', 'datasource1', 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/base', 'root', 'root', '1', '2018-03-05 14:00:29');
INSERT INTO `datasourceconfig` VALUES ('4', 'defaultDataSource', 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/dataserver', 'root', 'root', '1', '2018-03-05 23:40:25');

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `oper_name` varchar(40) NOT NULL DEFAULT '' COMMENT '文本名字',
  `oper_type` varchar(10) NOT NULL DEFAULT 'A' COMMENT '存储文本类型,A:sql,B:存储过程',
  `is_return` char(1) NOT NULL DEFAULT '0' COMMENT '存储过程是否有返回值，既out参数(1：是，0：否）',
  `sqltemplate` varchar(2500) NOT NULL DEFAULT '' COMMENT '模板',
  `text` varchar(2500) NOT NULL DEFAULT '' COMMENT '储存脚本内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(40) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updator` varchar(40) DEFAULT NULL COMMENT '更新人',
  `datasource_id` int(11) NOT NULL,
  `datasource_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_oper_name` (`oper_name`) USING BTREE COMMENT '名字是唯一的'
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation
-- ----------------------------
INSERT INTO `operation` VALUES ('3', 'bb', 'B', '0', 'call product()', 'call product()', null, '2018-03-05 23:40:38', null, '2018-03-05 17:03:43', null, '4', 'defaultDataSource');
INSERT INTO `operation` VALUES ('4', 'cc', 'B', '0', 'call product2(:age,:username)', 'call product2(#{param.age,jdbcType=NUMERIC,mode=IN},#{param.username,jdbcType=VARCHAR,mode=IN})', null, '2018-03-05 23:40:36', null, '2018-03-05 17:03:50', null, '4', 'defaultDataSource');
INSERT INTO `operation` VALUES ('5', '调用输入输出存储过程', 'B', '1', 'call test(:username,:age,:max)', 'call test(#{param.username,jdbcType=VARCHAR,mode=IN},#{param.age,jdbcType=NUMERIC,mode=IN},#{param.max,jdbcType=NUMERIC,mode=OUT})', null, '2018-03-05 23:40:35', null, '2018-03-05 17:03:56', null, '4', 'defaultDataSource');
INSERT INTO `operation` VALUES ('21', 'aa1', 'A', '0', 'select * from user where age = :age', 'select * from user where age = #{param.age}', null, '2018-03-05 23:40:34', null, '2018-03-05 17:04:02', null, '4', 'defaultDataSource');
INSERT INTO `operation` VALUES ('22', 'sql', 'A', '0', 'select * from user where username = :username and age = :age', 'select * from user where username = #{param.username} and age = #{param.age}', null, '2018-03-05 23:40:33', null, '2018-03-05 17:03:20', null, '4', 'defaultDataSource');
INSERT INTO `operation` VALUES ('24', 'tt', 'A', '0', 'select * from items', 'select * from items', null, '2018-03-05 17:04:26', null, null, null, '2', 'datasource1');

-- ----------------------------
-- Table structure for queryparameter
-- ----------------------------
DROP TABLE IF EXISTS `queryparameter`;
CREATE TABLE `queryparameter` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) NOT NULL,
  `ptype` varchar(45) DEFAULT NULL,
  `pdirection` varchar(45) DEFAULT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `FK_query_operation_idx` (`mid`),
  CONSTRAINT `FK_query_operation` FOREIGN KEY (`mid`) REFERENCES `operation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of queryparameter
-- ----------------------------
INSERT INTO `queryparameter` VALUES ('33', ':username', null, null, '22');
INSERT INTO `queryparameter` VALUES ('34', ':age', null, null, '22');
INSERT INTO `queryparameter` VALUES ('35', ':age', 'NUMERIC', 'IN', '4');
INSERT INTO `queryparameter` VALUES ('36', ':username', 'VARCHAR', 'IN', '4');
INSERT INTO `queryparameter` VALUES ('37', ':username', 'VARCHAR', 'IN', '5');
INSERT INTO `queryparameter` VALUES ('38', ':age', 'NUMERIC', 'IN', '5');
INSERT INTO `queryparameter` VALUES ('39', ':max', 'NUMERIC', 'OUT', '5');
INSERT INTO `queryparameter` VALUES ('40', ':age', '', '', '21');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'aa', '1');
INSERT INTO `teacher` VALUES ('2', 'aa', '2');
INSERT INTO `teacher` VALUES ('3', 'bb', '1');
INSERT INTO `teacher` VALUES ('4', 'bb', '3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '曹启龙', '27');
INSERT INTO `user` VALUES ('2', '曹启龙', '28');
INSERT INTO `user` VALUES ('3', '曹启龙', '29');
INSERT INTO `user` VALUES ('4', '曹启4', '27');
INSERT INTO `user` VALUES ('7', 'cao', '50');

-- ----------------------------
-- Procedure structure for product
-- ----------------------------
DROP PROCEDURE IF EXISTS `product`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `product`()
BEGIN
	SELECT
		*
	FROM
		USER;


END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for product2
-- ----------------------------
DROP PROCEDURE IF EXISTS `product2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `product2`(IN age int, IN username VARCHAR(20))
BEGIN
DECLARE v1 INT;
DECLARE v2 VARCHAR(20);
SET v1 = age;
SET v2 = username;
INSERT INTO user (age,username)
VALUES
	(v1,v2);


END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for test
-- ----------------------------
DROP PROCEDURE IF EXISTS `test`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `test`(IN username VARCHAR (20),
	IN age INT,
	OUT max INT)
    COMMENT '这是存储过程的学习'
BEGIN

DECLARE p1 VARCHAR (20);


DECLARE p2 INT;


SET p1 = username;


SET p2 = age;

INSERT INTO USER (username, age)
VALUES
	(p1, p2);

SELECT
	MAX(u.age) INTO max
FROM
	USER u;
END
;;
DELIMITER ;
