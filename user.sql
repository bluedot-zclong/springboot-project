/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2019-02-05 19:13:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `effective_from` datetime DEFAULT NULL COMMENT '开始时间',
  `effective_to` datetime DEFAULT NULL COMMENT '结束时间',
  `own_sign` varchar(16) DEFAULT NULL COMMENT '环境标识：dev线下；daily日常；staging预发；prod线上',
  `region_sign` varchar(16) DEFAULT NULL COMMENT '区域标识：jx江西',
  `is_delete` char(1) NOT NULL COMMENT '逻辑删标识：Y正常、N删除',
  `db_remark` varchar(1024) DEFAULT NULL COMMENT '逻辑删除说明',
  PRIMARY KEY (`id`),
  KEY `index` (`id`,`password`) USING BTREE,
  KEY `index_age` (`password`) USING BTREE,
  KEY `index_id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
