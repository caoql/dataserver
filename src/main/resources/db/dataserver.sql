/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : dataserver

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-02 16:46:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `oper_name` varchar(40) NOT NULL DEFAULT '' COMMENT '文本名字',
  `oper_type` varchar(10) NOT NULL DEFAULT 'A' COMMENT '存储文本类型,A:sql,B:存储过程',
  `is_return` char(1) NOT NULL DEFAULT '0' COMMENT '存储过程是否有返回值，既out参数(1：是，0：否）',
  `text` varchar(2500) NOT NULL DEFAULT '' COMMENT '储存脚本内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(40) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updator` varchar(40) DEFAULT NULL COMMENT '更新人',
  `sqltemplate` varchar(2500) NOT NULL DEFAULT '' COMMENT '模板',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_oper_name` (`oper_name`) USING BTREE COMMENT '名字是唯一的'
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation
-- ----------------------------
INSERT INTO `operation` VALUES ('3', 'bb', 'B', '0', 'call product()', null, '2018-03-02 15:58:12', null, '2018-03-02 15:58:13', null, 'call product()');
INSERT INTO `operation` VALUES ('4', 'cc', 'B', '0', 'call product2(#{param.age,jdbcType=NUMERIC,mode=IN},#{param.username,jdbcType=VARCHAR,mode=IN})', null, '2018-03-02 16:10:51', null, '2018-03-02 16:10:52', null, 'call product2(:age,:username)');
INSERT INTO `operation` VALUES ('5', '调用输入输出存储过程', 'B', '1', 'call test(#{param.username,jdbcType=VARCHAR,mode=IN},#{param.age,jdbcType=NUMERIC,mode=IN},#{param.max,jdbcType=NUMERIC,mode=OUT})', null, '2018-03-02 16:14:08', null, '2018-03-02 16:14:09', null, 'call test(:username,:age,:max)');
INSERT INTO `operation` VALUES ('21', 'aa1', 'A', '0', 'select * from user where age = #{param.age}', null, '2018-03-02 14:02:15', null, null, null, 'select * from user where age = :age');
INSERT INTO `operation` VALUES ('22', 'sql', 'A', '0', 'select * from user where username = #{param.username} and age = #{param.age}', null, '2018-03-02 16:16:38', null, '2018-03-02 16:16:38', null, 'select * from user where username = :username and age = :age');

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of queryparameter
-- ----------------------------
INSERT INTO `queryparameter` VALUES ('2', ':age', '', '', '21');
INSERT INTO `queryparameter` VALUES ('26', ':age', 'NUMERIC', 'IN', '4');
INSERT INTO `queryparameter` VALUES ('27', ':username', 'VARCHAR', 'IN', '4');
INSERT INTO `queryparameter` VALUES ('28', ':username', 'VARCHAR', 'IN', '5');
INSERT INTO `queryparameter` VALUES ('29', ':age', 'NUMERIC', 'IN', '5');
INSERT INTO `queryparameter` VALUES ('30', ':max', 'NUMERIC', 'OUT', '5');
INSERT INTO `queryparameter` VALUES ('31', ':username', null, null, '22');
INSERT INTO `queryparameter` VALUES ('32', ':age', null, null, '22');

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '曹启龙', '27');
INSERT INTO `user` VALUES ('2', '曹启龙', '28');
INSERT INTO `user` VALUES ('3', '曹启龙', '29');
INSERT INTO `user` VALUES ('4', '曹启4', '27');
INSERT INTO `user` VALUES ('18', 'CAOQL', '33');
INSERT INTO `user` VALUES ('19', '天天', '38');
INSERT INTO `user` VALUES ('65', 'cao2', '67');
INSERT INTO `user` VALUES ('66', '大王', '27');

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
