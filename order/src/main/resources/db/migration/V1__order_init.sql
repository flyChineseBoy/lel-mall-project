/*
 Navicat Premium Data Transfer

 Source Server         : 49.232.140.99
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 49.232.140.99
 Source Database       : mall_order

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : utf-8

 Date: 06/14/2020 23:31:20 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `m_order` (
  `id` bigint(20) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT '' COMMENT '订单状态，有START开始、WAITING延时等待、TIME_OUT的等待超时、PROCESSING处理中、COMPLETE完成、CANCEL取消、CART在购物车中。',
  `apply_user` varchar(50) NOT NULL DEFAULT '' COMMENT '申请人',
  `payd` datetime DEFAULT NULL COMMENT '支付时间',
  `price` double(20,0) DEFAULT NULL COMMENT '订单原价总金额',
  `payable` double(20,0) DEFAULT NULL COMMENT '应付总金额',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_STATUS` (`status`),
  KEY `IDX_APPLY_USER` (`apply_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单总表';

-- ----------------------------
--  Table structure for `order_item`
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `price` double(20,0) DEFAULT NULL COMMENT '商品原价金额',
  `payable` double(20,0) DEFAULT NULL COMMENT '该商品金额',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon` bigint(20) DEFAULT NULL COMMENT '优惠券、暂留空',
  `product_specs_id` bigint(20) DEFAULT NULL COMMENT '商品-规格的id',
  PRIMARY KEY (`id`),
  KEY `IDX_ORDER_ID` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
