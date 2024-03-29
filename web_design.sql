/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : web_design

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 21/01/2024 15:53:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`(
    `cart_id`  int(11) NOT NULL AUTO_INCREMENT,
    `goods_id` int(11) NULL DEFAULT NULL,
    `count`    int(11) NULL DEFAULT NULL,
    `user_id`  int(11) NULL DEFAULT NULL,
    PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`(
    `id`      int(11)                                                 NOT NULL AUTO_INCREMENT,
    `name`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `city`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `price`   int(11)                                                 NULL DEFAULT NULL,
    `number`  int(11)                                                 NULL DEFAULT NULL,
    `picture` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '沃特篮球鞋', '佛山', 180, 500, '001.jpg');
INSERT INTO `goods` VALUES (2, '安踏运动鞋', '福州', 120, 800, '002.jpg');
INSERT INTO `goods` VALUES (3, '耐克运动鞋', '广州', 500, 1000, '003.jpg');
INSERT INTO `goods` VALUES (4, '阿迪达斯T血衫', '上海', 388, 600, '004.jpg');
INSERT INTO `goods` VALUES (5, '李宁文化衫', '广州', 180, 900, '005.jpg');
INSERT INTO `goods` VALUES (6, '小米3', '北京', 1999, 3000, '006.jpg');
INSERT INTO `goods` VALUES (7, '小米2S', '北京', 1299, 1000, '007.jpg');
INSERT INTO `goods` VALUES (8, 'thinkpad笔记本', '北京', 6999, 500, '008.jpg');
INSERT INTO `goods` VALUES (9, 'dell笔记本', '北京', 3999, 500, '009.jpg');
INSERT INTO `goods` VALUES (10, 'ipad5', '北京', 5999, 500, '010.jpg');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`(
    `id`          int(11)                                                       NOT NULL AUTO_INCREMENT,
    `order_id`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `total_price` int(11)                                                       NULL DEFAULT NULL,
    `user_id`     int(11)                                                       NULL DEFAULT NULL,
    `state`       int(11)                                                       NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `order_id`    int(11) NULL DEFAULT NULL,
    `goods_id`    int(11) NULL DEFAULT NULL,
    `count`       int(11) NULL DEFAULT NULL,
    `total_price` int(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_item
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    `id`       int(11)                                                      NOT NULL AUTO_INCREMENT,
    `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123');
INSERT INTO `user` VALUES (2, 'lisi', '123');
INSERT INTO `user` VALUES (3, '123', '123');
INSERT INTO `user` VALUES (4, 'root', 'root');

SET FOREIGN_KEY_CHECKS = 1;
