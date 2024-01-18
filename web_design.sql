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

 Date: 17/01/2024 17:33:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int(11) NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `picture` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `total_price` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (11, '170359874844784', 2000, 2, 0);
INSERT INTO `order` VALUES (12, '170359880611725', 8496, 2, 0);
INSERT INTO `order` VALUES (13, '170360019502627', 7479, 2, 0);
INSERT INTO `order` VALUES (14, '170367958988917', 2032, 2, 0);
INSERT INTO `order` VALUES (15, '170373292193254', 2092, 1, 0);
INSERT INTO `order` VALUES (16, '170381808132957', 3772, 2, 0);
INSERT INTO `order` VALUES (17, '170400677111472', 776, 18, 0);
INSERT INTO `order` VALUES (18, '170400900775237', 5997, 20, 0);
INSERT INTO `order` VALUES (19, '170442779382261', 2480, 2, 0);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `total_price` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (13, 11, 3, 4, 2000);
INSERT INTO `order_item` VALUES (14, 12, 3, 1, 500);
INSERT INTO `order_item` VALUES (15, 12, 6, 4, 7996);
INSERT INTO `order_item` VALUES (16, 13, 2, 4, 480);
INSERT INTO `order_item` VALUES (17, 13, 8, 1, 6999);
INSERT INTO `order_item` VALUES (18, 14, 2, 4, 480);
INSERT INTO `order_item` VALUES (19, 14, 4, 4, 1552);
INSERT INTO `order_item` VALUES (20, 15, 4, 4, 1552);
INSERT INTO `order_item` VALUES (21, 15, 5, 3, 540);
INSERT INTO `order_item` VALUES (22, 16, 3, 3, 1500);
INSERT INTO `order_item` VALUES (23, 16, 4, 4, 1552);
INSERT INTO `order_item` VALUES (24, 16, 1, 4, 720);
INSERT INTO `order_item` VALUES (25, 17, 4, 2, 776);
INSERT INTO `order_item` VALUES (26, 18, 6, 3, 5997);
INSERT INTO `order_item` VALUES (27, 19, 3, 4, 2000);
INSERT INTO `order_item` VALUES (28, 19, 2, 4, 480);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123');
INSERT INTO `user` VALUES (2, 'lisi', '123');
INSERT INTO `user` VALUES (14, '123', '123');
INSERT INTO `user` VALUES (15, 'root', 'root');
INSERT INTO `user` VALUES (16, 'aaa', 'aaa');
INSERT INTO `user` VALUES (17, '1234', '1234');
INSERT INTO `user` VALUES (18, 'hello', 'hello');
INSERT INTO `user` VALUES (19, '12345', '12345');
INSERT INTO `user` VALUES (20, '1111', '1111');

SET FOREIGN_KEY_CHECKS = 1;
