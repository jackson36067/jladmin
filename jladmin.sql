/*
 Navicat MySQL Dump SQL

 Source Server         : jackson
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : jladmin

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 12/03/2026 16:11:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_column_config
-- ----------------------------
DROP TABLE IF EXISTS `code_column_config`;
CREATE TABLE `code_column_config`  (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `column_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `form_show` bit(1) NULL DEFAULT NULL,
  `form_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `key_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `list_show` bit(1) NULL DEFAULT NULL,
  `not_null` bit(1) NULL DEFAULT NULL,
  `query_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_annotation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX `idx_table_name`(`table_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 191 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成字段信息存储' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of code_column_config
-- ----------------------------

-- ----------------------------
-- Table structure for code_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `code_gen_config`;
CREATE TABLE `code_gen_config`  (
  `config_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表名',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `cover` bit(1) NULL DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接口名称',
  PRIMARY KEY (`config_id`) USING BTREE,
  INDEX `idx_table_name`(`table_name`(100) ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成器配置' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of code_gen_config
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_app
-- ----------------------------
DROP TABLE IF EXISTS `mnt_app`;
CREATE TABLE `mnt_app`  (
  `app_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应用名称',
  `upload_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传目录',
  `deploy_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部署路径',
  `backup_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备份路径',
  `port` int NULL DEFAULT NULL COMMENT '应用端口',
  `start_script` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '启动脚本',
  `deploy_script` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部署脚本',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '应用管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_app
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_database
-- ----------------------------
DROP TABLE IF EXISTS `mnt_database`;
CREATE TABLE `mnt_database`  (
  `db_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `jdbc_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'jdbc连接',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`db_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据库管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_database
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy`;
CREATE TABLE `mnt_deploy`  (
  `deploy_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `app_id` bigint NULL DEFAULT NULL COMMENT '应用编号',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`deploy_id`) USING BTREE,
  INDEX `FK6sy157pseoxx4fmcqr1vnvvhy`(`app_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部署管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_deploy
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy_history
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_history`;
CREATE TABLE `mnt_deploy_history`  (
  `history_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `app_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用名称',
  `deploy_date` datetime NOT NULL COMMENT '部署日期',
  `deploy_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部署用户',
  `ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务器IP',
  `deploy_id` bigint NULL DEFAULT NULL COMMENT '部署编号',
  PRIMARY KEY (`history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部署历史管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_deploy_history
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_deploy_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_server`;
CREATE TABLE `mnt_deploy_server`  (
  `deploy_id` bigint NOT NULL COMMENT '部署ID',
  `server_id` bigint NOT NULL COMMENT '服务ID',
  PRIMARY KEY (`deploy_id`, `server_id`) USING BTREE,
  INDEX `FKeaaha7jew9a02b3bk9ghols53`(`server_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '应用与服务器关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_deploy_server
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_server`;
CREATE TABLE `mnt_server`  (
  `server_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `port` int NULL DEFAULT NULL COMMENT '端口',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`server_id`) USING BTREE,
  INDEX `idx_ip`(`ip` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '服务器管理' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_server
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', 'report', 'dailyReport', '0/5 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', 'reset', 'dailyReset', '0 0 12 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', 'resetLog', 'monthReset', '0 0 12 L * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', 'report', 'dailyReport', NULL, 'com.jackson.task.MyTask', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', 'reset', 'dailyReset', NULL, 'com.jackson.task.MyTask', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', 'resetLog', 'monthReset', NULL, 'com.jackson.task.LogTask', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `INT_PROP_1` int NULL DEFAULT NULL,
  `INT_PROP_2` int NULL DEFAULT NULL,
  `LONG_PROP_1` bigint NULL DEFAULT NULL,
  `LONG_PROP_2` bigint NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PRIORITY` int NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', 'report', 'dailyReport', 'report', 'dailyReport', NULL, 1731474900000, 1731474895000, 5, 'PAUSED', 'CRON', 1731325691000, 0, NULL, 0, '');
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', 'reset', 'dailyReset', 'reset', 'dailyReset', NULL, 1763611200000, 1763531947079, 5, 'WAITING', 'CRON', 1731417928000, 0, NULL, 0, '');
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', 'resetLog', 'monthReset', 'resetLog', 'monthReset', NULL, 1764475200000, 1762264035488, 5, 'WAITING', 'CRON', 1732251197000, 0, NULL, 0, '');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级部门',
  `sub_count` int NULL DEFAULT 0 COMMENT '子部门数目',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `dept_sort` int NULL DEFAULT 999 COMMENT '排序',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `inx_pid`(`pid` ASC) USING BTREE,
  INDEX `inx_enabled`(`enabled` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (2, 7, 1, '研发部', 3, b'1', 'admin', 'admin', '2019-03-25 09:15:32', '2020-08-02 14:48:47');
INSERT INTO `sys_dept` VALUES (5, 7, 0, '运维部', 4, b'1', 'admin', 'admin', '2019-03-25 09:20:44', '2020-05-17 14:27:27');
INSERT INTO `sys_dept` VALUES (6, 8, 0, '测试部', 6, b'1', 'admin', 'admin', '2019-03-25 09:52:18', '2020-06-08 11:59:21');
INSERT INTO `sys_dept` VALUES (7, NULL, 2, '华南分部', 0, b'1', 'admin', 'admin', '2019-03-25 11:04:50', '2024-11-10 11:32:15');
INSERT INTO `sys_dept` VALUES (8, NULL, 2, '华北分部', 2, b'1', 'admin', 'admin', '2019-03-25 11:04:53', '2024-11-19 10:12:52');
INSERT INTO `sys_dept` VALUES (15, 8, 0, 'UI部门', 7, b'1', 'admin', 'jackson', '2020-05-13 22:56:53', '2024-12-02 12:36:11');
INSERT INTO `sys_dept` VALUES (17, 2, 0, '研发一组', 999, b'1', 'admin', 'admin', '2020-08-02 14:49:07', '2020-08-02 14:49:07');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'user_status', '用户状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (4, 'dept_status', '部门状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '岗位状态', NULL, NULL, '2019-10-27 20:31:36', NULL);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `detail_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_id` bigint NULL DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值',
  `dict_sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典详情' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 1, '激活', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (4, 4, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (6, 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `job_sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name` ASC) USING BTREE,
  INDEX `inx_enabled`(`enabled` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '岗位' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (8, '人事专员', b'1', 3, NULL, NULL, '2019-03-29 14:52:28', NULL);
INSERT INTO `sys_job` VALUES (10, '产品经理', b'1', 4, NULL, NULL, '2019-03-29 14:55:51', NULL);
INSERT INTO `sys_job` VALUES (11, '全栈开发', b'1', 2, NULL, 'admin', '2019-03-31 13:39:30', '2020-05-05 11:33:43');
INSERT INTO `sys_job` VALUES (12, '软件测试', b'1', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `log_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` bigint NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `log_create_time_index`(`create_time` ASC) USING BTREE,
  INDEX `inx_log_type`(`log_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3897 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统日志' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (3830, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"ehpg\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 480, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-14 12:21:23');
INSERT INTO `sys_log` VALUES (3831, '删除用户', 'DELETE', 'com.jackson.controller.UserController.deleteUserByIdIn()', '{\"ids\":[17]}', '127.0.0.1', 19, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-14 12:22:03');
INSERT INTO `sys_log` VALUES (3832, '暂停任务调度', 'PAUSE', 'com.jackson.controller.QuartzJobController.pauseJob()', '{\"taskDTO\":{\"jobName\":\"reset\",\"id\":13,\"jobGroup\":\"dailyReset\"}}', '127.0.0.1', 22, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-14 12:25:50');
INSERT INTO `sys_log` VALUES (3833, '恢复任务调度', 'RESUME', 'com.jackson.controller.QuartzJobController.resumeJob()', '{\"taskDTO\":{\"jobName\":\"reset\",\"id\":13,\"jobGroup\":\"dailyReset\"}}', '127.0.0.1', 8, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-14 12:25:53');
INSERT INTO `sys_log` VALUES (3834, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"eehw\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 444, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:25:44');
INSERT INTO `sys_log` VALUES (3835, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":10,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83]}}', '127.0.0.1', 102, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:26:10');
INSERT INTO `sys_log` VALUES (3836, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":3,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,2,44,45,46,6,7,9,32,41,80,10,11,15,33,34,83,135]}}', '127.0.0.1', 53, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:36:36');
INSERT INTO `sys_log` VALUES (3837, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":3,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,2,44,45,46,6,7,9,32,41,80,10,11,15,33,34,83,135,2,44,45,46,6,7,9,32,41,80,10,135]}}', '127.0.0.1', 62, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:37:19');
INSERT INTO `sys_log` VALUES (3838, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":10,\"menuIdList\":[10,11,15,33,34,83]}}', '127.0.0.1', 162, 'jackson', '内网IP', 'Chrome 142', 'java.lang.StackOverflowError', '2025-11-16 11:44:18');
INSERT INTO `sys_log` VALUES (3839, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":3,\"menuIdList\":[10,11,15,33,34,83,2,44,45,46,10,11,15,33,34,83,135]}}', '127.0.0.1', 162, 'jackson', '内网IP', 'Chrome 142', 'java.lang.StackOverflowError', '2025-11-16 11:44:52');
INSERT INTO `sys_log` VALUES (3840, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[10,11,15,33,34,83,2,44,45,46,10,11,15,33,34,83,135,1,2,44,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116]}}', '127.0.0.1', 107, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:46:08');
INSERT INTO `sys_log` VALUES (3841, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[10,11,15,33,34,83,2,44,45,46,10,11,15,33,34,83,135,1,2,44,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,1,2,44,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116]}}', '127.0.0.1', 33, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:46:28');
INSERT INTO `sys_log` VALUES (3842, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[10,11,15,33,34,83,2,44,45,46,10,11,15,33,34,83,135,1,2,44,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,1,2,44,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,1,2,44,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116]}}', '127.0.0.1', 34, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:47:18');
INSERT INTO `sys_log` VALUES (3843, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":10,\"menuIdList\":[10,11,15,33,34,83]}}', '127.0.0.1', 12, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:51:16');
INSERT INTO `sys_log` VALUES (3844, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,135]}}', '127.0.0.1', 20, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:52:36');
INSERT INTO `sys_log` VALUES (3845, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,135,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,38,82,116,135]}}', '127.0.0.1', 17, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:52:50');
INSERT INTO `sys_log` VALUES (3846, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":3,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,135,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,38,82,116,135,2,44,45,46,6,7,9,32,41,80,10,11,15,33,34,83]}}', '127.0.0.1', 20, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:53:07');
INSERT INTO `sys_log` VALUES (3847, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":1,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,135,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,38,82,116,135,2,44,45,46,6,7,9,32,41,80,10,11,15,33,34,83,1,2,44,45,46,3,48,49,50,5,52,53,54,28,73,74,75,35,56,57,58,37,60,61,62,39,64,65,66,6,7,9,32,41,80,10,11,15,33,34,83,36,14,18,77,78,79,19,30,38,82,116,135,90,92,103,104,105,93,106,107,108,94,109,110,111,97,102,98,112,113,114]}}', '127.0.0.1', 45, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:53:24');
INSERT INTO `sys_log` VALUES (3848, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,82,116,135,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,38,82,116,135,2,44,45,46,6,7,9,32,41,80,10,11,15,33,34,83,1,2,44,45,46,3,48,49,50,5,52,53,54,28,73,74,75,35,56,57,58,37,60,61,62,39,64,65,66,6,7,9,32,41,80,10,11,15,33,34,83,36,14,18,77,78,79,19,30,38,82,116,135,90,92,103,104,105,93,106,107,108,94,109,110,111,97,102,98,112,113,114,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,38,82,116]}}', '127.0.0.1', 43, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:53:30');
INSERT INTO `sys_log` VALUES (3849, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[64,65,66,6,7,9,32,41,80,10,11,15,33,34,83,36,14,18,77,78,79,19,30,38,82,116,135]}}', '127.0.0.1', 19, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:54:32');
INSERT INTO `sys_log` VALUES (3850, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":2,\"menuIdList\":[64,65,66,6,7,9,32,41,80,10,11,15,33,34,83,36,14,18,77,78,79,19,30,38,82,116,135,64,65,66,6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,38,82,116,135]}}', '127.0.0.1', 17, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 11:55:58');
INSERT INTO `sys_log` VALUES (3851, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":3,\"menuIdList\":[6,7,9,32,41,80,10,11,15,33,34,83,36,14,19,30,38,82,116,135]}}', '127.0.0.1', 21, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:00:32');
INSERT INTO `sys_log` VALUES (3852, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":3,\"menuIdList\":[6,7,9,32,41,80,36,14,18,77,78,79,19,30,38,82,116,135]}}', '127.0.0.1', 15, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:01:14');
INSERT INTO `sys_log` VALUES (3853, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"14770901237\",\"nickName\":\"wendy\",\"roles\":[10],\"jobs\":[10],\"deptId\":15,\"email\":\"jacksonn36062@gmail.com\",\"enabled\":true,\"username\":\"wendy\"}}', '127.0.0.1', 80, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:03:18');
INSERT INTO `sys_log` VALUES (3854, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"zli8\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 401, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:04:35');
INSERT INTO `sys_log` VALUES (3855, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"gbuq\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 99, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:05:15');
INSERT INTO `sys_log` VALUES (3856, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"bxb6\",\"isAdmin\":true,\"username\":\"wendy\"}}', '127.0.0.1', 72, 'wendy', '内网IP', 'Chrome 142', '用户不存在, 请更改用户名或者身份试试', '2025-11-16 12:05:26');
INSERT INTO `sys_log` VALUES (3857, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"23x4\",\"isAdmin\":false,\"username\":\"jackson\"}}', '127.0.0.1', 83, 'jackson', '内网IP', 'Chrome 142', '用户不存在, 请更改用户名或者身份试试', '2025-11-16 12:27:55');
INSERT INTO `sys_log` VALUES (3858, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"x3vv\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 400, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:33:01');
INSERT INTO `sys_log` VALUES (3859, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"mrcp\",\"isAdmin\":false,\"username\":\"wendy\"}}', '127.0.0.1', 84, 'wendy', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:33:12');
INSERT INTO `sys_log` VALUES (3860, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"brib\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 238, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:34:20');
INSERT INTO `sys_log` VALUES (3861, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":10,\"menuIdList\":[10,11,15,33,34]}}', '127.0.0.1', 38, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:34:36');
INSERT INTO `sys_log` VALUES (3862, '更新角色菜单', 'UPDATE', 'com.jackson.controller.RoleController.updateRoleMenuList()', '{\"updateRoleMenuDTO\":{\"id\":10,\"menuIdList\":[10,11,15,33,34,83]}}', '127.0.0.1', 12, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-16 12:34:44');
INSERT INTO `sys_log` VALUES (3863, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"aqa3\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 349, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-18 19:12:39');
INSERT INTO `sys_log` VALUES (3864, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"14993143212\",\"nickName\":\"jackosn1\",\"roles\":[1],\"jobs\":[11],\"deptId\":2,\"email\":\"jackson@gmail.com\",\"enabled\":true,\"username\":\"jackson1\"}}', '127.0.0.1', 90, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-18 19:13:59');
INSERT INTO `sys_log` VALUES (3865, '更新用户', 'UPDATE', 'com.jackson.controller.UserController.updateUser()', '{\"id\":19,\"updateUserDTO\":{\"gender\":\"男\",\"phone\":\"14993143212\",\"nickName\":\"jackosn\",\"roles\":[1],\"jobs\":[11],\"deptId\":2,\"id\":19,\"email\":\"jackson@gmail.com\",\"enabled\":true,\"username\":\"jackson1\"}}', '127.0.0.1', 22, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-18 19:14:05');
INSERT INTO `sys_log` VALUES (3866, '更新用户', 'UPDATE', 'com.jackson.controller.UserController.updateUser()', '{\"id\":19,\"updateUserDTO\":{\"gender\":\"男\",\"phone\":\"14993143212\",\"nickName\":\"jackosn\",\"roles\":[1],\"jobs\":[11,10],\"deptId\":2,\"id\":19,\"email\":\"jackson@gmail.com\",\"enabled\":true,\"username\":\"jackson1\"}}', '127.0.0.1', 15, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-18 19:14:12');
INSERT INTO `sys_log` VALUES (3867, '删除用户', 'DELETE', 'com.jackson.controller.UserController.deleteUserByIdIn()', '{\"ids\":[19]}', '127.0.0.1', 18, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-18 19:14:38');
INSERT INTO `sys_log` VALUES (3868, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"x49q\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 345, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 13:59:23');
INSERT INTO `sys_log` VALUES (3869, '删除用户', 'DELETE', 'com.jackson.controller.UserController.deleteUserByIdIn()', '{\"ids\":[18]}', '127.0.0.1', 21, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 13:59:30');
INSERT INTO `sys_log` VALUES (3870, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"14995439889\",\"nickName\":\"rewqrw\",\"roles\":[],\"jobs\":[8],\"email\":\"fjdskajk@qq.com\",\"enabled\":true,\"username\":\"fdasfdsfdsafds\"}}', '127.0.0.1', 13, 'jackson', '内网IP', 'Chrome 142', 'The given id must not be null', '2025-11-19 14:01:57');
INSERT INTO `sys_log` VALUES (3871, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"14995439889\",\"nickName\":\"rewqrw\",\"roles\":[1],\"jobs\":[8],\"deptId\":5,\"email\":\"fjdskajk@qq.com\",\"enabled\":true,\"username\":\"fdasfdsfdsafds\"}}', '127.0.0.1', 96, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:02:11');
INSERT INTO `sys_log` VALUES (3872, '更新用户', 'UPDATE', 'com.jackson.controller.UserController.updateUser()', '{\"id\":20,\"updateUserDTO\":{\"gender\":\"男\",\"phone\":\"14995439889\",\"nickName\":\"rewqfds\",\"roles\":[1],\"jobs\":[8],\"deptId\":5,\"id\":20,\"email\":\"fjdskajk@qq.com\",\"enabled\":true,\"username\":\"fdasfdsfdsafds\"}}', '127.0.0.1', 22, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:02:23');
INSERT INTO `sys_log` VALUES (3873, '删除用户', 'DELETE', 'com.jackson.controller.UserController.deleteUserByIdIn()', '{\"ids\":[18,20]}', '127.0.0.1', 8, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:02:32');
INSERT INTO `sys_log` VALUES (3874, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"123456789765\",\"nickName\":\"mamsam\",\"roles\":[2],\"jobs\":[8],\"deptId\":7,\"email\":\"adada@qq.com\",\"enabled\":true,\"username\":\"daada\"}}', '127.0.0.1', 87, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:07:24');
INSERT INTO `sys_log` VALUES (3875, '删除用户', 'DELETE', 'com.jackson.controller.UserController.deleteUserByIdIn()', '{\"ids\":[21]}', '127.0.0.1', 7, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:07:37');
INSERT INTO `sys_log` VALUES (3876, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"0je2\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 14, 'jackson', '内网IP', 'Chrome 142', '验证码错误', '2025-11-19 14:11:34');
INSERT INTO `sys_log` VALUES (3877, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"7whv\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 120, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:11:40');
INSERT INTO `sys_log` VALUES (3878, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"1237744378\",\"nickName\":\"dfjsafsd\",\"roles\":[3],\"jobs\":[10],\"deptId\":5,\"email\":\"jfdsajfj@qq.com\",\"enabled\":true,\"username\":\"1234\"}}', '127.0.0.1', 79, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:12:32');
INSERT INTO `sys_log` VALUES (3879, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"jjqt\",\"isAdmin\":false,\"username\":\"1234\"}}', '127.0.0.1', 94, '1234', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:13:13');
INSERT INTO `sys_log` VALUES (3880, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"xjec\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 90, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-19 14:13:51');
INSERT INTO `sys_log` VALUES (3881, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"ygy6\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 370, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:29:07');
INSERT INTO `sys_log` VALUES (3882, '删除用户', 'DELETE', 'com.jackson.controller.UserController.deleteUserByIdIn()', '{\"ids\":[22]}', '127.0.0.1', 19, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:29:20');
INSERT INTO `sys_log` VALUES (3883, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"zzqc\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 5, 'jackson', '内网IP', 'Chrome 142', '验证码错误', '2025-11-20 08:31:41');
INSERT INTO `sys_log` VALUES (3884, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"ltc5\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 94, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:31:49');
INSERT INTO `sys_log` VALUES (3885, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"123423432\",\"nickName\":\"432143\",\"roles\":[3],\"jobs\":[8],\"deptId\":5,\"email\":\"23432jkfj@qq.com\",\"enabled\":true,\"username\":\"1234\"}}', '127.0.0.1', 91, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:33:06');
INSERT INTO `sys_log` VALUES (3886, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"d812\",\"isAdmin\":false,\"username\":\"1234\"}}', '127.0.0.1', 4, '1234', '内网IP', 'Chrome 142', '验证码错误', '2025-11-20 08:34:07');
INSERT INTO `sys_log` VALUES (3887, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"ottt\",\"isAdmin\":false,\"username\":\"1234\"}}', '127.0.0.1', 3, '1234', '内网IP', 'Chrome 142', '验证码错误', '2025-11-20 08:34:12');
INSERT INTO `sys_log` VALUES (3888, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"1nhp\",\"isAdmin\":false,\"username\":\"1234\"}}', '127.0.0.1', 88, '1234', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:34:19');
INSERT INTO `sys_log` VALUES (3889, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"rheq\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 83, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:35:36');
INSERT INTO `sys_log` VALUES (3890, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"1212121221\",\"nickName\":\"adad\",\"roles\":[3],\"jobs\":[8],\"deptId\":5,\"email\":\"adad@qq.com\",\"enabled\":true,\"username\":\"12345\"}}', '127.0.0.1', 68, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:36:28');
INSERT INTO `sys_log` VALUES (3891, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"iwch\",\"isAdmin\":false,\"username\":\"1234\"}}', '127.0.0.1', 77, '1234', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:37:53');
INSERT INTO `sys_log` VALUES (3892, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"2u8j\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 78, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:41:27');
INSERT INTO `sys_log` VALUES (3893, '新增用户', 'ADD', 'com.jackson.controller.UserController.saveUser()', '{\"userDTO\":{\"gender\":\"男\",\"phone\":\"156352413\",\"nickName\":\"adad\",\"roles\":[3],\"jobs\":[8],\"deptId\":5,\"email\":\"142414@qq.com\",\"enabled\":true,\"username\":\"13242\"}}', '127.0.0.1', 73, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:42:18');
INSERT INTO `sys_log` VALUES (3894, '删除用户', 'DELETE', 'com.jackson.controller.UserController.deleteUserByIdIn()', '{\"ids\":[25]}', '127.0.0.1', 6, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:42:25');
INSERT INTO `sys_log` VALUES (3895, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"g1pt\",\"isAdmin\":false,\"username\":\"1234\"}}', '127.0.0.1', 76, '1234', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:43:04');
INSERT INTO `sys_log` VALUES (3896, '用户登录', 'LOGIN', 'com.jackson.controller.UserController.login()', '{\"userLoginDTO\":{\"password\":\"123456\",\"code\":\"b8oh\",\"isAdmin\":true,\"username\":\"jackson\"}}', '127.0.0.1', 79, 'jackson', '内网IP', 'Chrome 142', NULL, '2025-11-20 08:45:42');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int NULL DEFAULT 0 COMMENT '子菜单数目',
  `type` int NULL DEFAULT NULL COMMENT '菜单类型',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件',
  `menu_sort` int NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接地址',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `cache` bit(1) NULL DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '隐藏',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE INDEX `uniq_title`(`title` ASC) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name` ASC) USING BTREE,
  INDEX `inx_pid`(`pid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 136 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统菜单' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', '/system', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-18 15:11:29', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', '/user', b'0', b'0', b'1', 'user:list', NULL, 'anonymousUser', '2018-12-18 15:14:44', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', '/role', b'0', b'0', b'1', 'roles:list', NULL, 'anonymousUser', '2018-12-18 15:16:07', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', '/menu', b'0', b'0', b'1', 'menu:list', NULL, 'anonymousUser', '2018-12-18 15:17:28', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', '/monitor', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-18 15:17:48', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', '/logs', b'0', b'1', b'1', NULL, NULL, 'anonymousUser', '2018-12-18 15:18:26', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', '/druid', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-18 15:19:34', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (10, NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', '/components', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-19 13:38:16', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (11, 10, 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', '/icon', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-19 13:38:49', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (14, 36, 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', '/email', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-27 10:13:09', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (15, 10, 0, 1, '富文本', 'Editor', 'components/Editor', 52, 'fwb', '/tinymce', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-27 11:58:25', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', '/storage', b'0', b'0', b'1', 'storage:list', NULL, 'anonymousUser', '2018-12-31 11:12:15', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (19, 36, 0, 1, '支付宝工具', 'AliPay', 'tools/aliPay/index', 37, 'alipay', '/aliPay', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2018-12-31 14:52:38', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (28, 1, 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', '/timing', b'0', b'0', b'1', 'timing:list', NULL, 'anonymousUser', '2019-01-07 20:34:40', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', '/generator', b'0', b'1', b'1', NULL, NULL, 'anonymousUser', '2019-01-11 15:45:55', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', '/errorLog', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2019-01-13 13:49:03', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (33, 10, 0, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', '/markdown', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2019-03-08 13:46:44', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (34, 10, 0, 1, 'Yaml编辑器', 'YamlEdit', 'components/YamlEdit', 54, 'dev', '/yaml', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2019-03-08 15:49:40', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', '/dept', b'0', b'0', b'1', 'dept:list', NULL, 'anonymousUser', '2019-03-25 09:46:00', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (36, NULL, 8, 0, '系统工具', NULL, '', 30, 'sys-tools', '/sys-tools', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2019-03-29 10:57:35', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (37, 1, 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', '/job', b'0', b'0', b'1', 'job:list', NULL, 'anonymousUser', '2019-03-29 13:51:18', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', '/swagger2', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2019-03-29 19:57:53', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', '/dict', b'0', b'0', b'1', 'dict:list', NULL, 'anonymousUser', '2019-04-10 11:49:04', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', '/online', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2019-10-26 22:08:43', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'1', 'user:add', NULL, 'anonymousUser', '2019-10-29 10:59:46', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'1', 'user:edit', NULL, 'anonymousUser', '2019-10-29 11:00:08', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'user:del', NULL, 'anonymousUser', '2019-10-29 11:00:23', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'1', 'roles:add', NULL, 'anonymousUser', '2019-10-29 12:45:34', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'1', 'roles:edit', NULL, 'anonymousUser', '2019-10-29 12:46:16', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'roles:del', NULL, 'anonymousUser', '2019-10-29 12:46:51', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'1', 'menu:add', NULL, 'anonymousUser', '2019-10-29 12:55:07', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'1', 'menu:edit', NULL, 'anonymousUser', '2019-10-29 12:55:40', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'menu:del', NULL, 'anonymousUser', '2019-10-29 12:56:00', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'1', 'dept:add', NULL, 'anonymousUser', '2019-10-29 12:57:09', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'1', 'dept:edit', NULL, 'anonymousUser', '2019-10-29 12:57:27', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'dept:del', NULL, 'anonymousUser', '2019-10-29 12:57:41', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'1', 'job:add', NULL, 'anonymousUser', '2019-10-29 12:58:27', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'1', 'job:edit', NULL, 'anonymousUser', '2019-10-29 12:58:45', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'job:del', NULL, 'anonymousUser', '2019-10-29 12:59:04', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'1', 'dict:add', NULL, 'anonymousUser', '2019-10-29 13:00:17', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'1', 'dict:edit', NULL, 'anonymousUser', '2019-10-29 13:00:42', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'dict:del', NULL, 'anonymousUser', '2019-10-29 13:00:59', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'1', 'timing:add', NULL, 'anonymousUser', '2019-10-29 13:07:28', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'1', 'timing:edit', NULL, 'anonymousUser', '2019-10-29 13:07:41', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'timing:del', NULL, 'anonymousUser', '2019-10-29 13:07:54', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'1', 'storage:add', NULL, 'anonymousUser', '2019-10-29 13:09:09', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'1', 'storage:edit', NULL, 'anonymousUser', '2019-10-29 13:09:22', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'1', 'storage:del', NULL, 'anonymousUser', '2019-10-29 13:09:34', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'1', 'monitor:list', NULL, 'anonymousUser', '2019-11-07 13:06:39', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, 'anonymousUser', '2019-11-17 20:08:56', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (83, 10, 0, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', b'0', b'1', b'1', '', NULL, 'anonymousUser', '2019-11-21 09:04:32', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (90, NULL, 5, 0, '运维管理', 'Mnt', '', 21, 'mnt', 'mnt', b'0', b'0', b'1', NULL, NULL, 'anonymousUser', '2019-11-09 10:31:08', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'1', 'serverDeploy:list', NULL, 'anonymousUser', '2019-11-10 10:29:25', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'1', 'app:list', NULL, 'anonymousUser', '2019-11-10 11:05:16', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'1', 'deploy:list', NULL, 'anonymousUser', '2019-11-10 15:56:55', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'1', 'deployHistory:list', NULL, 'anonymousUser', '2019-11-10 16:49:44', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (98, 90, 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', b'0', b'0', b'1', 'database:list', NULL, 'anonymousUser', '2019-11-10 20:40:04', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (102, 97, 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'1', 'deployHistory:del', NULL, 'anonymousUser', '2019-11-17 09:32:48', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'1', 'serverDeploy:add', NULL, 'anonymousUser', '2019-11-17 11:08:33', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'1', 'serverDeploy:edit', NULL, 'anonymousUser', '2019-11-17 11:08:57', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'1', 'serverDeploy:del', NULL, 'anonymousUser', '2019-11-17 11:09:15', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'1', 'app:add', NULL, 'anonymousUser', '2019-11-17 11:10:03', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'1', 'app:edit', NULL, 'anonymousUser', '2019-11-17 11:10:28', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'1', 'app:del', NULL, 'anonymousUser', '2019-11-17 11:10:55', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'1', 'deploy:add', NULL, 'anonymousUser', '2019-11-17 11:11:22', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'1', 'deploy:edit', NULL, 'anonymousUser', '2019-11-17 11:11:41', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'1', 'deploy:del', NULL, 'anonymousUser', '2019-11-17 11:12:01', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'1', 'database:add', NULL, 'anonymousUser', '2019-11-17 11:12:43', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'1', 'database:edit', NULL, 'anonymousUser', '2019-11-17 11:12:58', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, 'anonymousUser', '2019-11-17 11:13:14', '2025-11-19 13:59:23');
INSERT INTO `sys_menu` VALUES (116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, 'anonymousUser', '2019-11-26 14:54:36', '2025-11-20 08:34:19');
INSERT INTO `sys_menu` VALUES (135, 36, NULL, 1, '聊天工具', 'Chat', 'tools/chat/index', 15, 'chat', '/chat', b'0', b'0', b'1', '', 'jackson', 'anonymousUser', '2024-11-24 09:59:05', '2025-11-20 08:34:19');

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报警邮箱',
  `pause_after_failure` bit(1) NULL DEFAULT NULL COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `job_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务组',
  PRIMARY KEY (`job_id`) USING BTREE,
  INDEX `inx_is_pause`(`is_pause` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES (11, 'MyTask', '0/5 * * * * ?', b'1', 'report', '测试任务调度', 'jackson', 'jacksonn36067@gmail.com', NULL, 'admin', 'jackson', '2024-11-11 19:48:12', '2024-12-02 12:34:32', 'dailyReport');
INSERT INTO `sys_quartz_job` VALUES (13, 'MyTask', '0 0 12 * * ?', b'0', 'reset', '每天12点重置任务', 'jackson', 'jacksonn36067@gmail.com', NULL, 'admin', 'jackson', '2024-11-12 21:25:28', '2025-11-14 12:25:53', 'dailyReset');
INSERT INTO `sys_quartz_job` VALUES (14, 'LogTask', '0 0 12 L * ?', b'0', 'resetLog', '月底清理操作日志', 'jackson', 'jacksonn36067@gmail.com', NULL, 'jackson', 'jackson', '2024-11-22 12:53:18', '2024-11-22 12:53:18', 'monthReset');

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log`  (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `is_success` bit(1) NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 209 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务日志' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_quartz_log
-- ----------------------------
INSERT INTO `sys_quartz_log` VALUES (151, 'class com.jackson.task.MyTask', '2024-11-12 22:30:06', '0/5 * * * * ?', NULL, b'1', 'report', 1);
INSERT INTO `sys_quartz_log` VALUES (152, 'class com.jackson.task.MyTask', '2024-11-12 22:30:10', '0/5 * * * * ?', NULL, b'1', 'report', 0);
INSERT INTO `sys_quartz_log` VALUES (153, 'class com.jackson.task.MyTask', '2024-11-12 22:30:15', '0/5 * * * * ?', NULL, b'1', 'report', 0);
INSERT INTO `sys_quartz_log` VALUES (154, 'class com.jackson.task.MyTask', '2024-11-12 22:30:20', '0/5 * * * * ?', NULL, b'1', 'report', 2);
INSERT INTO `sys_quartz_log` VALUES (155, 'class com.jackson.task.MyTask', '2024-11-13 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (156, 'class com.jackson.task.MyTask', '2024-11-13 13:14:38', '0/5 * * * * ?', NULL, b'1', 'report', 0);
INSERT INTO `sys_quartz_log` VALUES (157, 'class com.jackson.task.MyTask', '2024-11-13 13:14:40', '0/5 * * * * ?', NULL, b'1', 'report', 1);
INSERT INTO `sys_quartz_log` VALUES (158, 'class com.jackson.task.MyTask', '2024-11-13 13:14:45', '0/5 * * * * ?', NULL, b'1', 'report', 0);
INSERT INTO `sys_quartz_log` VALUES (159, 'class com.jackson.task.MyTask', '2024-11-13 13:14:50', '0/5 * * * * ?', NULL, b'1', 'report', 0);
INSERT INTO `sys_quartz_log` VALUES (160, 'class com.jackson.task.MyTask', '2024-11-13 13:14:55', '0/5 * * * * ?', NULL, b'1', 'report', 0);
INSERT INTO `sys_quartz_log` VALUES (161, 'class com.jackson.task.MyTask', '2024-11-14 15:49:57', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (162, 'class com.jackson.task.MyTask', '2024-11-16 09:54:57', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (163, 'class com.jackson.task.MyTask', '2024-11-16 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (164, 'class com.jackson.task.MyTask', '2024-11-17 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (165, 'class com.jackson.task.MyTask', '2024-11-18 20:40:29', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (166, 'class com.jackson.task.MyTask', '2024-11-19 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (167, 'class com.jackson.task.MyTask', '2024-11-20 16:48:45', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (168, 'class com.jackson.task.MyTask', '2024-11-21 12:46:18', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (169, 'class com.jackson.task.MyTask', '2024-11-22 12:33:17', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (170, 'class com.jackson.task.MyTask', '2024-11-23 12:21:56', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (171, 'class com.jackson.task.MyTask', '2024-11-24 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (172, 'class com.jackson.task.MyTask', '2024-11-25 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (173, 'class com.jackson.task.MyTask', '2024-11-26 13:42:25', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (174, 'class com.jackson.task.MyTask', '2024-11-27 19:29:09', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (175, 'class com.jackson.task.MyTask', '2024-11-29 13:55:19', '0 0 12 * * ?', NULL, b'1', 'reset', 4);
INSERT INTO `sys_quartz_log` VALUES (176, 'class com.jackson.task.MyTask', '2024-12-01 21:26:20', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (177, 'class com.jackson.task.LogTask', '2024-12-01 21:26:21', '0 0 12 L * ?', NULL, b'1', 'resetLog', 647);
INSERT INTO `sys_quartz_log` VALUES (178, 'class com.jackson.task.MyTask', '2024-12-02 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (179, 'class com.jackson.task.MyTask', '2024-12-03 16:03:12', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (180, 'class com.jackson.task.MyTask', '2024-12-05 22:20:54', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (181, 'class com.jackson.task.MyTask', '2024-12-06 14:06:55', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (182, 'class com.jackson.task.MyTask', '2024-12-07 13:30:10', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (183, 'class com.jackson.task.MyTask', '2025-02-14 13:22:46', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (184, 'class com.jackson.task.LogTask', '2025-02-14 13:22:47', '0 0 12 L * ?', NULL, b'1', 'resetLog', 668);
INSERT INTO `sys_quartz_log` VALUES (185, 'class com.jackson.task.MyTask', '2025-02-16 14:05:02', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (186, 'class com.jackson.task.MyTask', '2025-02-27 13:44:38', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (187, 'class com.jackson.task.MyTask', '2025-03-03 10:19:25', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (188, 'class com.jackson.task.LogTask', '2025-03-03 10:19:26', '0 0 12 L * ?', NULL, b'1', 'resetLog', 519);
INSERT INTO `sys_quartz_log` VALUES (189, 'class com.jackson.task.MyTask', '2025-03-03 12:10:57', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (190, 'class com.jackson.task.MyTask', '2025-03-12 10:39:44', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (191, 'class com.jackson.task.MyTask', '2025-05-23 09:24:27', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (192, 'class com.jackson.task.LogTask', '2025-05-23 09:24:28', '0 0 12 L * ?', 'org.quartz.SchedulerException: Job threw an unhandled exception. [See nested exception: org.springframework.data.redis.RedisConnectionFailureException: Unable to connect to Redis]', b'0', 'resetLog', 437);
INSERT INTO `sys_quartz_log` VALUES (193, 'class com.jackson.task.MyTask', '2025-05-26 08:35:48', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (194, 'class com.jackson.task.MyTask', '2025-05-26 12:05:31', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (195, 'class com.jackson.task.MyTask', '2025-05-28 18:09:54', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (196, 'class com.jackson.task.MyTask', '2025-06-03 17:45:46', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (197, 'class com.jackson.task.LogTask', '2025-06-03 17:45:47', '0 0 12 L * ?', NULL, b'1', 'resetLog', 512);
INSERT INTO `sys_quartz_log` VALUES (198, 'class com.jackson.task.MyTask', '2025-06-04 19:11:56', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (199, 'class com.jackson.task.MyTask', '2025-06-12 20:48:17', '0 0 12 * * ?', NULL, b'1', 'reset', 1);
INSERT INTO `sys_quartz_log` VALUES (200, 'class com.jackson.task.MyTask', '2025-09-07 19:48:53', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (201, 'class com.jackson.task.LogTask', '2025-09-07 19:48:54', '0 0 12 L * ?', NULL, b'1', 'resetLog', 615);
INSERT INTO `sys_quartz_log` VALUES (202, 'class com.jackson.task.MyTask', '2025-11-04 21:47:16', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (203, 'class com.jackson.task.LogTask', '2025-11-04 21:47:16', '0 0 12 L * ?', NULL, b'1', 'resetLog', 728);
INSERT INTO `sys_quartz_log` VALUES (204, 'class com.jackson.task.MyTask', '2025-11-14 12:21:08', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (205, 'class com.jackson.task.MyTask', '2025-11-16 11:24:39', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (206, 'class com.jackson.task.MyTask', '2025-11-16 12:00:00', '0 0 12 * * ?', NULL, b'1', 'reset', 0);
INSERT INTO `sys_quartz_log` VALUES (207, 'class com.jackson.task.MyTask', '2025-11-18 19:11:11', '0 0 12 * * ?', NULL, b'1', 'reset', 2);
INSERT INTO `sys_quartz_log` VALUES (208, 'class com.jackson.task.MyTask', '2025-11-19 13:59:07', '0 0 12 * * ?', NULL, b'1', 'reset', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `level` int NULL DEFAULT NULL COMMENT '角色级别',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `data_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name` ASC) USING BTREE,
  INDEX `role_name_index`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, '-', '全部', NULL, 'jackson', '2018-11-23 11:04:37', '2025-11-16 11:53:24');
INSERT INTO `sys_role` VALUES (2, '普通用户', 2, '-', '本级', NULL, 'jackson', '2018-11-23 13:09:06', '2025-11-16 11:55:58');
INSERT INTO `sys_role` VALUES (3, '系统监控人员', 3, '用与监控系统以及系统用户管理', '自定义', NULL, 'jackson', '2024-11-06 16:23:26', '2025-11-16 12:01:14');
INSERT INTO `sys_role` VALUES (10, '组件管理者', 3, '组件管理者,用户管理组件', '自定义', 'admin', 'jackson', '2024-11-07 15:47:00', '2025-11-16 12:34:44');

-- ----------------------------
-- Table structure for sys_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_depts`;
CREATE TABLE `sys_roles_depts`  (
  `role_id` bigint NOT NULL,
  `dept_id` bigint NOT NULL,
  UNIQUE INDEX `role_id`(`role_id` ASC, `dept_id` ASC) USING BTREE,
  INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色部门关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_roles_depts
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus`  (
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  UNIQUE INDEX `role_id`(`role_id` ASC, `menu_id` ASC) USING BTREE,
  INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
INSERT INTO `sys_roles_menus` VALUES (1, 1);
INSERT INTO `sys_roles_menus` VALUES (2, 1);
INSERT INTO `sys_roles_menus` VALUES (3, 1);
INSERT INTO `sys_roles_menus` VALUES (5, 1);
INSERT INTO `sys_roles_menus` VALUES (6, 1);
INSERT INTO `sys_roles_menus` VALUES (7, 1);
INSERT INTO `sys_roles_menus` VALUES (9, 1);
INSERT INTO `sys_roles_menus` VALUES (10, 1);
INSERT INTO `sys_roles_menus` VALUES (11, 1);
INSERT INTO `sys_roles_menus` VALUES (14, 1);
INSERT INTO `sys_roles_menus` VALUES (15, 1);
INSERT INTO `sys_roles_menus` VALUES (18, 1);
INSERT INTO `sys_roles_menus` VALUES (19, 1);
INSERT INTO `sys_roles_menus` VALUES (28, 1);
INSERT INTO `sys_roles_menus` VALUES (30, 1);
INSERT INTO `sys_roles_menus` VALUES (32, 1);
INSERT INTO `sys_roles_menus` VALUES (33, 1);
INSERT INTO `sys_roles_menus` VALUES (34, 1);
INSERT INTO `sys_roles_menus` VALUES (35, 1);
INSERT INTO `sys_roles_menus` VALUES (36, 1);
INSERT INTO `sys_roles_menus` VALUES (37, 1);
INSERT INTO `sys_roles_menus` VALUES (38, 1);
INSERT INTO `sys_roles_menus` VALUES (39, 1);
INSERT INTO `sys_roles_menus` VALUES (41, 1);
INSERT INTO `sys_roles_menus` VALUES (44, 1);
INSERT INTO `sys_roles_menus` VALUES (45, 1);
INSERT INTO `sys_roles_menus` VALUES (46, 1);
INSERT INTO `sys_roles_menus` VALUES (48, 1);
INSERT INTO `sys_roles_menus` VALUES (49, 1);
INSERT INTO `sys_roles_menus` VALUES (50, 1);
INSERT INTO `sys_roles_menus` VALUES (52, 1);
INSERT INTO `sys_roles_menus` VALUES (53, 1);
INSERT INTO `sys_roles_menus` VALUES (54, 1);
INSERT INTO `sys_roles_menus` VALUES (56, 1);
INSERT INTO `sys_roles_menus` VALUES (57, 1);
INSERT INTO `sys_roles_menus` VALUES (58, 1);
INSERT INTO `sys_roles_menus` VALUES (60, 1);
INSERT INTO `sys_roles_menus` VALUES (61, 1);
INSERT INTO `sys_roles_menus` VALUES (62, 1);
INSERT INTO `sys_roles_menus` VALUES (64, 1);
INSERT INTO `sys_roles_menus` VALUES (65, 1);
INSERT INTO `sys_roles_menus` VALUES (66, 1);
INSERT INTO `sys_roles_menus` VALUES (73, 1);
INSERT INTO `sys_roles_menus` VALUES (74, 1);
INSERT INTO `sys_roles_menus` VALUES (75, 1);
INSERT INTO `sys_roles_menus` VALUES (77, 1);
INSERT INTO `sys_roles_menus` VALUES (78, 1);
INSERT INTO `sys_roles_menus` VALUES (79, 1);
INSERT INTO `sys_roles_menus` VALUES (80, 1);
INSERT INTO `sys_roles_menus` VALUES (82, 1);
INSERT INTO `sys_roles_menus` VALUES (83, 1);
INSERT INTO `sys_roles_menus` VALUES (90, 1);
INSERT INTO `sys_roles_menus` VALUES (92, 1);
INSERT INTO `sys_roles_menus` VALUES (93, 1);
INSERT INTO `sys_roles_menus` VALUES (94, 1);
INSERT INTO `sys_roles_menus` VALUES (97, 1);
INSERT INTO `sys_roles_menus` VALUES (98, 1);
INSERT INTO `sys_roles_menus` VALUES (102, 1);
INSERT INTO `sys_roles_menus` VALUES (103, 1);
INSERT INTO `sys_roles_menus` VALUES (104, 1);
INSERT INTO `sys_roles_menus` VALUES (105, 1);
INSERT INTO `sys_roles_menus` VALUES (106, 1);
INSERT INTO `sys_roles_menus` VALUES (107, 1);
INSERT INTO `sys_roles_menus` VALUES (108, 1);
INSERT INTO `sys_roles_menus` VALUES (109, 1);
INSERT INTO `sys_roles_menus` VALUES (110, 1);
INSERT INTO `sys_roles_menus` VALUES (111, 1);
INSERT INTO `sys_roles_menus` VALUES (112, 1);
INSERT INTO `sys_roles_menus` VALUES (113, 1);
INSERT INTO `sys_roles_menus` VALUES (114, 1);
INSERT INTO `sys_roles_menus` VALUES (116, 1);
INSERT INTO `sys_roles_menus` VALUES (135, 1);
INSERT INTO `sys_roles_menus` VALUES (6, 2);
INSERT INTO `sys_roles_menus` VALUES (7, 2);
INSERT INTO `sys_roles_menus` VALUES (9, 2);
INSERT INTO `sys_roles_menus` VALUES (10, 2);
INSERT INTO `sys_roles_menus` VALUES (11, 2);
INSERT INTO `sys_roles_menus` VALUES (14, 2);
INSERT INTO `sys_roles_menus` VALUES (15, 2);
INSERT INTO `sys_roles_menus` VALUES (18, 2);
INSERT INTO `sys_roles_menus` VALUES (19, 2);
INSERT INTO `sys_roles_menus` VALUES (30, 2);
INSERT INTO `sys_roles_menus` VALUES (32, 2);
INSERT INTO `sys_roles_menus` VALUES (33, 2);
INSERT INTO `sys_roles_menus` VALUES (34, 2);
INSERT INTO `sys_roles_menus` VALUES (36, 2);
INSERT INTO `sys_roles_menus` VALUES (38, 2);
INSERT INTO `sys_roles_menus` VALUES (41, 2);
INSERT INTO `sys_roles_menus` VALUES (64, 2);
INSERT INTO `sys_roles_menus` VALUES (65, 2);
INSERT INTO `sys_roles_menus` VALUES (66, 2);
INSERT INTO `sys_roles_menus` VALUES (77, 2);
INSERT INTO `sys_roles_menus` VALUES (78, 2);
INSERT INTO `sys_roles_menus` VALUES (79, 2);
INSERT INTO `sys_roles_menus` VALUES (80, 2);
INSERT INTO `sys_roles_menus` VALUES (82, 2);
INSERT INTO `sys_roles_menus` VALUES (83, 2);
INSERT INTO `sys_roles_menus` VALUES (116, 2);
INSERT INTO `sys_roles_menus` VALUES (135, 2);
INSERT INTO `sys_roles_menus` VALUES (6, 3);
INSERT INTO `sys_roles_menus` VALUES (7, 3);
INSERT INTO `sys_roles_menus` VALUES (9, 3);
INSERT INTO `sys_roles_menus` VALUES (14, 3);
INSERT INTO `sys_roles_menus` VALUES (18, 3);
INSERT INTO `sys_roles_menus` VALUES (19, 3);
INSERT INTO `sys_roles_menus` VALUES (30, 3);
INSERT INTO `sys_roles_menus` VALUES (32, 3);
INSERT INTO `sys_roles_menus` VALUES (36, 3);
INSERT INTO `sys_roles_menus` VALUES (38, 3);
INSERT INTO `sys_roles_menus` VALUES (41, 3);
INSERT INTO `sys_roles_menus` VALUES (77, 3);
INSERT INTO `sys_roles_menus` VALUES (78, 3);
INSERT INTO `sys_roles_menus` VALUES (79, 3);
INSERT INTO `sys_roles_menus` VALUES (80, 3);
INSERT INTO `sys_roles_menus` VALUES (82, 3);
INSERT INTO `sys_roles_menus` VALUES (116, 3);
INSERT INTO `sys_roles_menus` VALUES (135, 3);
INSERT INTO `sys_roles_menus` VALUES (10, 10);
INSERT INTO `sys_roles_menus` VALUES (11, 10);
INSERT INTO `sys_roles_menus` VALUES (15, 10);
INSERT INTO `sys_roles_menus` VALUES (33, 10);
INSERT INTO `sys_roles_menus` VALUES (34, 10);
INSERT INTO `sys_roles_menus` VALUES (83, 10);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门名称',
  `username` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像真实路径',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `is_admin` bit(1) NULL DEFAULT b'0' COMMENT '是否为admin账号',
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `pwd_reset_time` datetime NULL DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email` ASC) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `uniq_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `uniq_email`(`email` ASC) USING BTREE,
  INDEX `FK5rwmryny6jthaaxkogownknqp`(`dept_id` ASC) USING BTREE,
  INDEX `inx_enabled`(`enabled` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 2, 'admin', '管理员', '男', '19139719505', '201507802@qq.com', 'avatar-20200806032259161.png', 'http://jackson1.oss-cn-beijing.aliyuncs.com/3e2e43fb-0a98-43a1-ae6c-e41963145266.jpg', '$2a$10$a296xtwzoLSF5ufWJtVaZ.jfwgp.VOG6T//eL9YwuidVaG/ElZjtG', b'1', b'1', NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2024-11-25 18:49:24');
INSERT INTO `sys_user` VALUES (8, 2, 'jackson', 'jackson', '男', '14770901310', 'jacksonn36067@gmail.com', NULL, 'http://jackson1.oss-cn-beijing.aliyuncs.com/a61be1b7-f7b3-4027-8e18-930fab3bdeef.jpg', '$2a$10$IQiNvtL6EWw4VBTNSVi6tuc7gTaHrfr4xz0s6t0MsZFm7MvVxrOFy', b'1', b'1', 'admin', 'jackson', '2024-11-02 22:03:38', '2024-11-02 22:03:33', '2024-12-03 16:52:08');
INSERT INTO `sys_user` VALUES (23, 5, '1234', '432143', '男', '123423432', '23432jkfj@qq.com', NULL, NULL, '$2a$10$1rsBnhulOoR55CGajBR9Au0xcDi.qDqxLwtBLOxvpcSCgwlZr99ba', b'0', b'1', 'jackson', 'jackson', NULL, '2025-11-20 08:33:06', '2025-11-20 08:33:06');
INSERT INTO `sys_user` VALUES (24, 5, '12345', 'adad', '男', '1212121221', 'adad@qq.com', NULL, NULL, '$2a$10$9UvkBhaGtCTyAEWq5GU24.t22cINtVntmZCrtJZJ8oE7r4MU96P7G', b'1', b'1', 'jackson', 'jackson', NULL, '2025-11-20 08:36:28', '2025-11-20 08:36:28');

-- ----------------------------
-- Table structure for sys_user_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_message`;
CREATE TABLE `sys_user_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '信息',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `recipient` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '接收人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_message
-- ----------------------------
INSERT INTO `sys_user_message` VALUES (7, 'admin', '在吗', '2024-11-24 14:47:39', 'jackson');
INSERT INTO `sys_user_message` VALUES (8, 'jackson', '嗯', '2024-11-24 14:48:01', 'admin');
INSERT INTO `sys_user_message` VALUES (9, 'admin', '我想问问啥时候放假', '2024-11-24 14:48:41', 'jackson');
INSERT INTO `sys_user_message` VALUES (10, 'jackson', '马上了', '2024-11-24 14:48:57', 'admin');
INSERT INTO `sys_user_message` VALUES (56, 'admin', '又马上了,又来了', '2024-11-25 09:35:45', 'jackson');
INSERT INTO `sys_user_message` VALUES (57, 'admin', '到底什么时候', '2024-11-25 09:35:52', 'jackson');
INSERT INTO `sys_user_message` VALUES (58, 'admin', '我受不了了', '2024-11-25 09:35:54', 'jackson');
INSERT INTO `sys_user_message` VALUES (59, 'jackson', '我也不知道啊', '2024-11-25 09:35:59', 'admin');
INSERT INTO `sys_user_message` VALUES (60, 'jackson', '你跟我抱怨有啥用', '2024-11-25 09:36:07', 'admin');
INSERT INTO `sys_user_message` VALUES (61, 'admin', '你快点去问啊', '2024-11-25 09:36:16', 'jackson');
INSERT INTO `sys_user_message` VALUES (62, 'admin', '我要早点放假回家', '2024-11-25 09:36:21', 'jackson');
INSERT INTO `sys_user_message` VALUES (63, 'jackson', '你回个🥚啊', '2024-11-25 09:36:27', 'admin');
INSERT INTO `sys_user_message` VALUES (64, 'admin', '不是,我就想回家,快点你去问问', '2024-11-25 09:36:42', 'jackson');
INSERT INTO `sys_user_message` VALUES (65, 'jackson', '你回个🥚啊', '2024-11-26 16:36:21', 'admin');
INSERT INTO `sys_user_message` VALUES (66, 'admin', '好好好,我等下干死你', '2024-11-26 16:36:40', 'jackson');
INSERT INTO `sys_user_message` VALUES (67, 'jackson', '就你?', '2024-11-26 16:37:08', 'admin');
INSERT INTO `sys_user_message` VALUES (68, 'admin', '你等着就行了', '2024-11-26 16:37:17', 'jackson');
INSERT INTO `sys_user_message` VALUES (69, 'jackson', '好好好.我等着', '2024-11-26 16:38:21', 'admin');
INSERT INTO `sys_user_message` VALUES (70, 'admin', '6', '2024-11-26 16:38:25', 'jackson');
INSERT INTO `sys_user_message` VALUES (71, 'admin', 'ss', '2024-11-29 13:57:16', 'jackson');
INSERT INTO `sys_user_message` VALUES (72, 'jackson', 'weflsjfsld\\', '2024-11-29 13:57:21', 'admin');

-- ----------------------------
-- Table structure for sys_users_jobs
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_jobs`;
CREATE TABLE `sys_users_jobs`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `job_id` bigint NOT NULL COMMENT '岗位ID',
  UNIQUE INDEX `user_id`(`user_id` ASC, `job_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users_jobs
-- ----------------------------
INSERT INTO `sys_users_jobs` VALUES (1, 11);
INSERT INTO `sys_users_jobs` VALUES (8, 10);
INSERT INTO `sys_users_jobs` VALUES (8, 11);
INSERT INTO `sys_users_jobs` VALUES (23, 8);
INSERT INTO `sys_users_jobs` VALUES (24, 8);

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  UNIQUE INDEX `user_id`(`user_id` ASC, `role_id` ASC) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES (1, 1);
INSERT INTO `sys_users_roles` VALUES (8, 1);
INSERT INTO `sys_users_roles` VALUES (23, 3);
INSERT INTO `sys_users_roles` VALUES (24, 3);

-- ----------------------------
-- Table structure for tool_alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_alipay_config`;
CREATE TABLE `tool_alipay_config`  (
  `config_id` bigint NOT NULL COMMENT 'ID',
  `app_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应用ID',
  `charset` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编码',
  `format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型 固定格式json',
  `gateway_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '异步回调',
  `private_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '私钥',
  `public_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '公钥',
  `return_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商户号',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付宝配置类' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_alipay_config
-- ----------------------------
INSERT INTO `tool_alipay_config` VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');

-- ----------------------------
-- Table structure for tool_email_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_email_config`;
CREATE TABLE `tool_email_config`  (
  `config_id` bigint NOT NULL COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `port` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '端口',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '邮箱配置' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_email_config
-- ----------------------------

-- ----------------------------
-- Table structure for tool_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `tool_local_storage`;
CREATE TABLE `tool_local_storage`  (
  `storage_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `size` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '大小',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '本地存储' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_local_storage
-- ----------------------------

-- ----------------------------
-- Table structure for tool_qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_config`;
CREATE TABLE `tool_qiniu_config`  (
  `config_id` bigint NOT NULL COMMENT 'ID',
  `access_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'accessKey',
  `bucket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '外链域名',
  `secret_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'secretKey',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '空间类型',
  `zone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机房',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '七牛云配置' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_qiniu_config
-- ----------------------------

-- ----------------------------
-- Table structure for tool_qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_content`;
CREATE TABLE `tool_qiniu_content`  (
  `content_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bucket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型：私有或公开',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `update_time` datetime NULL DEFAULT NULL COMMENT '上传或同步的时间',
  PRIMARY KEY (`content_id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '七牛云文件存储' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tool_qiniu_content
-- ----------------------------

-- ----------------------------
-- View structure for view_sys_user
-- ----------------------------
DROP VIEW IF EXISTS `view_sys_user`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_sys_user` AS select `sys_user`.`user_id` AS `user_id`,`sys_user`.`dept_id` AS `dept_id`,`sys_user`.`username` AS `username`,`sys_user`.`nick_name` AS `nick_name`,`sys_user`.`gender` AS `gender`,`sys_user`.`phone` AS `phone`,`sys_user`.`email` AS `email`,`sys_user`.`avatar_name` AS `avatar_name`,`sys_user`.`avatar_path` AS `avatar_path`,`sys_user`.`password` AS `password`,`sys_user`.`is_admin` AS `is_admin`,`sys_user`.`enabled` AS `enabled`,`sys_user`.`create_by` AS `create_by`,`sys_user`.`update_by` AS `update_by`,`sys_user`.`pwd_reset_time` AS `pwd_reset_time`,`sys_user`.`create_time` AS `create_time`,`sys_user`.`update_time` AS `update_time` from `sys_user`;

-- ----------------------------
-- Procedure structure for GetDeptDetails
-- ----------------------------
DROP PROCEDURE IF EXISTS `GetDeptDetails`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetDeptDetails`(in deptId bigint)
BEGIN
  SELECT
    d.dept_id,
    d.name as dept_name,
    d.sub_count
  from
    sys.dept d
  where d.dept_id = deptId;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept
-- ----------------------------
DROP TRIGGER IF EXISTS `trg_init_sub_count`;
delimiter ;;
CREATE TRIGGER `trg_init_sub_count` BEFORE INSERT ON `sys_dept` FOR EACH ROW begin
if NEW.sub_count is null then set NEW.sub_count = 0;
end if;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
