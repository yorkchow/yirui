SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- DROP DATABASE IF EXISTS `yirui_db`;

CREATE DATABASE IF NOT EXISTS `yirui_db`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `yirui_db`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

SET FOREIGN_KEY_CHECKS=0;

-- -----------------------------
-- Table structure for sys_user
-- -----------------------------
DROP TABLE IF EXISTS `sys_user`;
create table `sys_user`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username`  varchar(100) NOT NULL DEFAULT '',
  `email`     varchar(100) NOT NULL DEFAULT '',
  `mobile`    varchar(20) NOT NULL DEFAULT '',
  `password`  varchar(100) NOT NULL DEFAULT '',
  `salt`      varchar(10) NOT NULL DEFAULT '',
  `create_date` timestamp NOT NULL DEFAULT 0,
  `status`    enum('normal','blocked') NOT NULL DEFAULT 'normal',
  `admin`     tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_user_username_email` (`username`,`email`),
  INDEX `idx_sys_user_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

-- -------------------------------------------
-- Table structure for sys_user_status_history
-- -------------------------------------------
DROP TABLE IF EXISTS `sys_user_status_history`;
create table `sys_user_status_history`(
  `id`         int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id`    int(10) UNSIGNED NOT NULL DEFAULT 0,
  `status`     enum('normal','blocked') NOT NULL DEFAULT 'normal',
  `reason`     varchar(200) NOT NULL DEFAULT '',
  `op_user_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `op_date`    timestamp NOT NULL default 0 ,
  PRIMARY KEY (`id`),
  INDEX `idx_sys_user_block_history_user_id_block_date` (`user_id`,`op_date`),
  INDEX `idx_sys_user_block_history_op_user_id_op_date` (`op_user_id`, `op_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------------------
-- Table structure for sys_user_last_online
-- ----------------------------------------
DROP TABLE IF EXISTS `sys_user_last_online`;
create table `sys_user_last_online`(
  `id`         int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id`    int(10) UNSIGNED NOT NULL DEFAULT 0,
  `username`   varchar(100) NOT NULL DEFAULT '',
  `uid`        varchar(100) NOT NULL DEFAULT '',
  `host`       varchar(100) NOT NULL DEFAULT '',
  `user_agent` varchar(200) NOT NULL DEFAULT '',
  `system_host`  varchar(100) NOT NULL DEFAULT '',
  `last_login_timestamp`    timestamp NOT NULL DEFAULT 0,
  `last_stop_timestamp`    timestamp NOT NULL DEFAULT 0,
  `login_count`    bigint NOT NULL DEFAULT 0,
  `total_online_time` bigint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_user_last_online_sys_user_id` (`user_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ------------------------------------
-- Table structure for sys_organization
-- ------------------------------------
DROP TABLE IF EXISTS `sys_organization`;
create table `sys_organization`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`      varchar(100) NOT NULL DEFAULT '',
  `type`      enum('bloc','branch_office','department','group') NOT NULL DEFAULT 'group',
  `parent_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `parent_ids`  varchar(200) NOT NULL DEFAULT '',
  `icon`      varchar(200) NOT NULL DEFAULT '',
  `weight`    int(10) UNSIGNED NOT NULL DEFAULT 0,
  `is_show`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `idx_sys_organization_name` (`name`),
  INDEX `idx_sys_organization_type` (`type`),
  INDEX `idx_sys_organization_parent_id` (`parent_id`),
  INDEX `idx_sys_organization_parent_ids_weight` (`parent_ids`, `weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

-- ---------------------------
-- Table structure for sys_job
-- ---------------------------
DROP TABLE IF EXISTS `sys_job`;
create table `sys_job`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`      varchar(100) NOT NULL DEFAULT '',
  `parent_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `parent_ids`  varchar(200) NOT NULL DEFAULT '',
  `icon`      varchar(200) NOT NULL DEFAULT '',
  `weight`    int(10) UNSIGNED NOT NULL DEFAULT 0,
  `is_show`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `idx_sys_job_nam` (`name`),
  INDEX `idx_sys_job_parent_id` (`parent_id`),
  INDEX `idx_sys_job_parent_ids_weight` (`parent_ids`, `weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

-- ---------------------------------------------
-- Table structure for sys_user_organization_job
-- ---------------------------------------------
DROP TABLE IF EXISTS `sys_user_organization_job`;
create table `sys_user_organization_job`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id`   int(10) UNSIGNED NOT NULL DEFAULT 0,
  `organization_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `job_id`    int(10) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_user_organization_job` (`user_id`, `organization_id`, `job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------
-- Table structure for sys_resource
-- --------------------------------
DROP TABLE IF EXISTS `sys_resource`;
create table `sys_resource`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`      varchar(100) NOT NULL DEFAULT '',
  `identity`  varchar(100) NOT NULL DEFAULT '',
  `url`       varchar(200) NOT NULL DEFAULT '',
  `parent_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `parent_ids`  varchar(200) NOT NULL DEFAULT '',
  `icon`      varchar(200) NOT NULL DEFAULT '',
  `weight`    int(10) UNSIGNED NOT NULL DEFAULT 0,
  `is_show`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `idx_sys_resource_name` (`name`),
  INDEX `idx_sys_resource_identity` (`identity`),
  INDEX `idx_sys_resource_parent_id` (`parent_id`),
  INDEX `idx_sys_resource_parent_ids_weight` (`parent_ids`, `weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------------
-- Table structure for sys_permission
-- ----------------------------------
DROP TABLE IF EXISTS `sys_permission`;
create table `sys_permission`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`      varchar(100) NOT NULL DEFAULT '',
  `permission`  varchar(100) NOT NULL DEFAULT '',
  `description` varchar(200) NOT NULL DEFAULT '',
  `is_show`     tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX idx_sys_permission_name (`name`),
  INDEX idx_sys_permission_permission (`permission`),
  INDEX idx_sys_permission_show (`is_show`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
create table `sys_role`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`      varchar(100) NOT NULL DEFAULT '',
  `role`      varchar(100) NOT NULL DEFAULT '',
  `description` varchar(200) NOT NULL DEFAULT '',
  `is_show`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `idx_sys_role_name` (`name`),
  INDEX `idx_sys_role_role` (`role`),
  INDEX `idx_sys_role_show` (`is_show`)
) charset=utf8 ENGINE=InnoDB AUTO_INCREMENT=1000;

-- ------------------------------------------------
-- Table structure for sys_role_resource_permission
-- ------------------------------------------------
DROP TABLE IF EXISTS `sys_role_resource_permission`;
create table `sys_role_resource_permission`(
  `id`        int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id`   int(10) UNSIGNED NOT NULL DEFAULT 0,
  `resource_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `permission_ids` varchar(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_role_resource_permission` (`role_id`, `resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------
-- Table structure for sys_group
-- -----------------------------
DROP TABLE IF EXISTS `sys_group`;
create table `sys_group`(
  `id`            int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`          varchar(100) NOT NULL DEFAULT '',
  `type`          varchar(50) NOT NULL DEFAULT '',
  `is_show`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `default_group` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `idx_sys_group_type` (`type`),
  INDEX `idx_sys_group_show` (`is_show`),
  INDEX `idx_sys_group_default_group` (`default_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------
-- Table structure for sys_group_relation
-- --------------------------------------
DROP TABLE IF EXISTS `sys_group_relation`;
create table `sys_group_relation`(
  `id`              int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `group_id`        int(10) UNSIGNED NOT NULL DEFAULT 0,
  `organization_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `user_id`         int(10) UNSIGNED NOT NULL DEFAULT 0,
  `start_user_id`   int(10) UNSIGNED NOT NULL DEFAULT 0,
  `end_user_id`     int(10) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `idx_sys_group_relation_group` (`group_id`),
  INDEX `idx_sys_group_relation_organization` (`organization_id`),
  INDEX `idx_sys_group_relation_user` (`user_id`),
  INDEX `idx_sys_group_relation_start_user_id` (`start_user_id`),
  INDEX `idx_sys_group_relation_end_user_id` (`end_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
create table `sys_auth`(
  `id`              int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `organization_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `job_id`          int(10) UNSIGNED NOT NULL DEFAULT 0,
  `user_id`         int(10) UNSIGNED NOT NULL DEFAULT 0,
  `group_id`        int(10) UNSIGNED NOT NULL DEFAULT 0,
  `role_ids`        varchar(500) NOT NULL DEFAULT '',
  `type`            enum('user','user_group','organization_job','organization_group') NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`),
  INDEX `idx_sys_auth_organization` (`organization_id`),
  INDEX `idx_sys_auth_job` (`job_id`),
  INDEX `idx_sys_auth_user` (`user_id`),
  INDEX `idx_sys_auth_group` (`group_id`),
  INDEX `idx_sys_auth_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;