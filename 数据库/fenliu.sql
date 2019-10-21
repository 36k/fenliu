/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : fenliu

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 23/06/2019 09:20:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aspiration
-- ----------------------------
DROP TABLE IF EXISTS `aspiration`;
CREATE TABLE `aspiration`  (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `aspiration_major` int(11) NULL DEFAULT NULL,
  `aspiration_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`object_id`) USING BTREE,
  INDEX `FKfy8thvw00qbtlkxf4nftq0ud1`(`aspiration_major`) USING BTREE,
  INDEX `FK5jgadrwmme2630nmpg1w3nxjj`(`aspiration_user`) USING BTREE,
  CONSTRAINT `FK5jgadrwmme2630nmpg1w3nxjj` FOREIGN KEY (`aspiration_user`) REFERENCES `user` (`object_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfy8thvw00qbtlkxf4nftq0ud1` FOREIGN KEY (`aspiration_major`) REFERENCES `major` (`object_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aspiration
-- ----------------------------
INSERT INTO `aspiration` VALUES (47, 1, 1, 1);
INSERT INTO `aspiration` VALUES (48, 2, 2, 1);
INSERT INTO `aspiration` VALUES (49, 3, 3, 1);

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `college_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`object_id`) USING BTREE,
  UNIQUE INDEX `UK_79reucg4dknkcctnijpw6axmi`(`college_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, 'dqyxx', '电气与信息');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_college` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`object_id`) USING BTREE,
  UNIQUE INDEX `UK_21ujm2eubctgc9x652n0esnwa`(`department_id`) USING BTREE,
  INDEX `FK6je19aueow7lppnph34vy03ev`(`department_college`) USING BTREE,
  CONSTRAINT `FK6je19aueow7lppnph34vy03ev` FOREIGN KEY (`department_college`) REFERENCES `college` (`object_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 'cs', '计算机科学与技术', 1);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `major_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major_department` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`object_id`) USING BTREE,
  UNIQUE INDEX `UK_lf3ilcqm3ruau0tlugjp7c32j`(`major_id`) USING BTREE,
  INDEX `FKfgxtcxmm3nw7exyi05a53r459`(`major_department`) USING BTREE,
  CONSTRAINT `FKfgxtcxmm3nw7exyi05a53r459` FOREIGN KEY (`major_department`) REFERENCES `department` (`object_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, 'ui', 'UI设计师', 1);
INSERT INTO `major` VALUES (2, 'front', '前端', 1);
INSERT INTO `major` VALUES (3, 'soft', '软件工程师', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`object_id`) USING BTREE,
  UNIQUE INDEX `UK_nvt31tls2ym5so7gx2s6gopi0`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'STUDENT', '学生');
INSERT INTO `role` VALUES (2, 'ADMIN', '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `object_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` double NOT NULL,
  `subscribe` bit(1) NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_department` int(11) NULL DEFAULT NULL,
  `user_major` int(11) NULL DEFAULT NULL,
  `user_role` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`object_id`) USING BTREE,
  UNIQUE INDEX `UK_a3imlf41l37utmxiquukk8ajc`(`user_id`) USING BTREE,
  INDEX `FKbgnpw5erbnf2s59xko55ny7xw`(`user_department`) USING BTREE,
  INDEX `FKgrgqullkikk4wfj8nsyysydy8`(`user_major`) USING BTREE,
  INDEX `FKied680nuc33futf3pbwdsyihr`(`user_role`) USING BTREE,
  CONSTRAINT `FKbgnpw5erbnf2s59xko55ny7xw` FOREIGN KEY (`user_department`) REFERENCES `department` (`object_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKgrgqullkikk4wfj8nsyysydy8` FOREIGN KEY (`user_major`) REFERENCES `major` (`object_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKied680nuc33futf3pbwdsyihr` FOREIGN KEY (`user_role`) REFERENCES `role` (`object_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1838383210@qq.com', '梁亚龙', '$2a$10$Xuo3BR0nUM7AEo.2t2daHukukQCzEJcXD.mgclr1Pksa.B6NS/MZC', 0, b'1', '20164081112', 1, 3, 1);
INSERT INTO `user` VALUES (3, NULL, '管理员', '$2a$10$Xuo3BR0nUM7AEo.2t2daHukukQCzEJcXD.mgclr1Pksa.B6NS/MZC', 0, b'1', 'admin', 1, NULL, 2);

SET FOREIGN_KEY_CHECKS = 1;
