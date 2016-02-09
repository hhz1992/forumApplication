/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50142
Source Host           : localhost:3306
Source Database       : wtf_tjpu_bbs

Target Server Type    : MYSQL
Target Server Version : 50142
File Encoding         : 65001

Date: 2012-07-14 08:43:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `javabean_attachment_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_attachment_table`;
CREATE TABLE `javabean_attachment_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'OID',
  `fileName` varchar(45) DEFAULT NULL COMMENT '文件名',
  `fileSize` int(10) unsigned DEFAULT NULL COMMENT '文件大小',
  `path` varchar(200) DEFAULT NULL COMMENT '文件存放路径',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `uploadTime` varchar(45) DEFAULT NULL COMMENT '上传时间',
  `downloadCount` int(10) unsigned DEFAULT NULL COMMENT '被下载次数',
  `topicId` int(10) unsigned DEFAULT NULL COMMENT '所属主题',
  `lowIntegral` int(10) unsigned DEFAULT NULL COMMENT '下载所需要的最低积分',
  `roplyId` int(10) unsigned DEFAULT NULL COMMENT '所属的回复',
  `memberId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_attachment_table
-- ----------------------------
INSERT INTO `javabean_attachment_table` VALUES ('1', '5fdf8db1cb1349547a25f0ca564e9258d1094a60.jpg', '0', 'D:\\Program Files (x86)\\apache-tomcat-7.0.11\\webapps\\wtf_bbs\\upload/5fdf8db1cb1349547a25f0ca564e9258d1094a60.jpg', 'asdasda', '2012-07-13 09:41:50', null, '56', null, null, '1');

-- ----------------------------
-- Table structure for `javabean_category_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_category_table`;
CREATE TABLE `javabean_category_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'OID',
  `name` varchar(20) DEFAULT NULL COMMENT '分区名',
  `sortNo` int(10) unsigned DEFAULT NULL COMMENT '排序编号',
  `state` int(45) unsigned DEFAULT NULL COMMENT '分区状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_category_table
-- ----------------------------
INSERT INTO `javabean_category_table` VALUES ('1', '美国的华莱士', '1', '1');
INSERT INTO `javabean_category_table` VALUES ('2', '我和他们谈笑风生', '2', '1');
INSERT INTO `javabean_category_table` VALUES ('3', '比其他西方记者跑的还快', '3', '1');
INSERT INTO `javabean_category_table` VALUES ('4', '蛤蛤', '4', '1');

-- ----------------------------
-- Table structure for `javabean_datadictionary_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_datadictionary_table`;
CREATE TABLE `javabean_datadictionary_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'OID',
  `key` varchar(45) DEFAULT NULL COMMENT '信息项',
  `value` varchar(45) DEFAULT NULL COMMENT '值',
  `description` varchar(100) DEFAULT NULL COMMENT '简要描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_datadictionary_table
-- ----------------------------

-- ----------------------------
-- Table structure for `javabean_forum_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_forum_table`;
CREATE TABLE `javabean_forum_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'OID',
  `name` varchar(20) DEFAULT NULL COMMENT '板块名',
  `sortNo` int(10) unsigned DEFAULT NULL COMMENT '排序编号',
  `keywords` varchar(45) DEFAULT NULL COMMENT '关键字',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `rule` varchar(200) DEFAULT NULL COMMENT '板块规则',
  `topicCount` int(20) unsigned DEFAULT NULL COMMENT '总主题数',
  `articleCount` int(20) unsigned DEFAULT NULL COMMENT '总文章数',
  `topicTodayCount` int(10) unsigned DEFAULT NULL COMMENT '今日主题数',
  `lastTopic` varchar(200) DEFAULT NULL COMMENT '最后一个主题',
  `categoryName` varchar(45) DEFAULT NULL COMMENT '版主列表',
  `categoryId` int(10) unsigned DEFAULT NULL COMMENT '所属分区',
  `lastMember` varchar(50) DEFAULT NULL,
  `regTime` varchar(50) DEFAULT NULL,
  `lastTopicId` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_forum_table
-- ----------------------------
INSERT INTO `javabean_forum_table` VALUES ('1', '告诉你一声', '1', '无可奉告', '自己想', '1', '5', '5', null, 'asddasd', '美国的华莱士', '1', '谈笑风生', '2012-07-13 13:04:55', null);
INSERT INTO `javabean_forum_table` VALUES ('2', '不知道要高到哪里去了', '2', '比你们', '美国的华莱士', '蛤蛤', '0', '0', null, null, '我和他们谈笑风生', '2', null, null, null);
INSERT INTO `javabean_forum_table` VALUES ('3', 'too simple', '3', '跑到什么地方', '跑到什么地方', '跑到什么地方', '0', '0', null, null, '比其他西方记者跑的还快', '3', null, null, null);
INSERT INTO `javabean_forum_table` VALUES ('4', 'some naive', '4', 'jzm', '蛤蛤无双', '蛤蛤说了算', '0', '0', null, null, '美国的华莱士', '1', null, null, null);

-- ----------------------------
-- Table structure for `javabean_manager_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_manager_table`;
CREATE TABLE `javabean_manager_table` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `realName` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `state` int(50) DEFAULT NULL,
  `loginNum` int(50) DEFAULT NULL,
  `createTime` varchar(50) DEFAULT NULL,
  `lastLoginTime` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_manager_table
-- ----------------------------
INSERT INTO `javabean_manager_table` VALUES ('1', 'admin', '21232F297A57A5A743894A0E4A801FC3', 'hhz', '123', '1', '18', '2012-07-13 08:43:27', '2012-07-13 14:37:24', '0:0:0:0:0:0:0:1');
INSERT INTO `javabean_manager_table` VALUES ('2', 'hhz', 'CCE71C321988E57C75333D0B16619335', 'hhz', '123', '2', '0', '2012-07-13 13:10:56', null, null);

-- ----------------------------
-- Table structure for `javabean_member_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_member_table`;
CREATE TABLE `javabean_member_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'OID',
  `loginName` varchar(20) NOT NULL COMMENT '登录名',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱号',
  `nickName` varchar(20) DEFAULT NULL COMMENT '昵称',
  `gender` int(5) unsigned DEFAULT NULL COMMENT '性别',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像路径',
  `integral` int(10) unsigned DEFAULT NULL COMMENT '积分',
  `signature` varchar(45) DEFAULT NULL COMMENT '签名',
  `introducation` varchar(200) DEFAULT NULL COMMENT '简介',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq号',
  `msn` varchar(20) DEFAULT NULL COMMENT 'msn号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `address` varchar(50) DEFAULT NULL COMMENT '联系地址',
  `postalCode` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `regTime` varchar(30) NOT NULL COMMENT '注册时间',
  `lastVisitTime` varchar(30) DEFAULT NULL COMMENT '最后访问时间',
  `lastIP` varchar(30) DEFAULT NULL COMMENT '最后登录时的IP',
  `topicCount` int(20) unsigned DEFAULT NULL COMMENT '发表的总主题数',
  `replyCount` int(20) unsigned DEFAULT NULL COMMENT '发表的总回复数',
  `status` int(20) unsigned NOT NULL COMMENT '状态',
  `autoLoginKey` varchar(45) DEFAULT NULL COMMENT '自动登录的认证字符串',
  `memberIdentity` int(10) unsigned DEFAULT NULL COMMENT '会员身份',
  `bithplace` varchar(45) DEFAULT NULL COMMENT '籍贯',
  `forumId` int(50) DEFAULT NULL,
  `forumName` varchar(50) DEFAULT NULL,
  `best` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_member_table
-- ----------------------------
INSERT INTO `javabean_member_table` VALUES ('1', 'hhz', 'CCE71C321988E57C75333D0B16619335', 'hhz92@sina.com', 'hhz', '1', '/upload/avatar/20120713111243745.jpg', '75', 'asdasd', '', '', '', '', '', '', '2012-07-13 08:47:52', '2012-07-13 12:47:56', '0:0:0:0:0:0:0:1', '8', '3', '1', null, '2', 'asdasd', '1', '告诉你一声', '1');
INSERT INTO `javabean_member_table` VALUES ('2', 'hhz123', 'CCE71C321988E57C75333D0B16619335', 'hhz92@sina.com', 'hhz123', null, null, '5', null, null, null, null, null, null, null, '2012-07-13 10:06:47', null, null, '0', '0', '2', null, '2', null, '4', 'some naive', '0');
INSERT INTO `javabean_member_table` VALUES ('3', 'hhzhhz', 'CCE71C321988E57C75333D0B16619335', 'hhz92@sina.com', '蛤蛤', null, null, '10', null, null, null, null, null, null, null, '2012-07-13 10:12:31', '2012-07-13 11:13:33', '0:0:0:0:0:0:0:1', '0', '1', '1', null, '2', null, '2', '不知道要高到哪里去了', '0');
INSERT INTO `javabean_member_table` VALUES ('4', 'hhz92', 'CCE71C321988E57C75333D0B16619335', 'hhz92@sina.com', '谈笑风生', null, null, '15', null, null, null, null, null, null, null, '2012-07-13 10:13:15', '2012-07-13 13:04:33', '0:0:0:0:0:0:0:1', '1', '1', '4', null, '2', null, '3', 'too simple', '0');

-- ----------------------------
-- Table structure for `javabean_memloginlog_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_memloginlog_table`;
CREATE TABLE `javabean_memloginlog_table` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) DEFAULT NULL,
  `nickName` varchar(50) DEFAULT NULL,
  `loginIp` varchar(50) DEFAULT NULL,
  `ipAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_memloginlog_table
-- ----------------------------
INSERT INTO `javabean_memloginlog_table` VALUES ('1', 'hhz', null, '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('2', 'hhz', null, '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('3', 'hhz', null, '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('4', 'hhz', null, '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('5', 'hhz', null, '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('6', 'hhz', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('7', 'hhzhhz', '蛤蛤', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('8', 'hhz', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('9', 'hhz', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_memloginlog_table` VALUES ('10', 'hhz92', '谈笑风生', '0:0:0:0:0:0:0:1', '');

-- ----------------------------
-- Table structure for `javabean_mloginlog_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_mloginlog_table`;
CREATE TABLE `javabean_mloginlog_table` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) DEFAULT NULL,
  `realName` varchar(50) DEFAULT NULL,
  `loginIp` varchar(50) DEFAULT NULL,
  `ipAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_mloginlog_table
-- ----------------------------
INSERT INTO `javabean_mloginlog_table` VALUES ('202', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('203', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('204', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('205', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('206', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('207', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('208', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('209', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('210', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('211', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('212', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('213', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('214', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('215', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('216', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('217', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('218', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');
INSERT INTO `javabean_mloginlog_table` VALUES ('219', 'admin', 'hhz', '0:0:0:0:0:0:0:1', '');

-- ----------------------------
-- Table structure for `javabean_reply_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_reply_table`;
CREATE TABLE `javabean_reply_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'OID',
  `content` longtext COMMENT '回复类容',
  `pubTime` varchar(45) DEFAULT NULL COMMENT '回复时间',
  `ip` varchar(45) DEFAULT NULL COMMENT '回复时所用的IP',
  `floor` int(10) unsigned DEFAULT NULL COMMENT '所在楼层',
  `topicId` int(10) unsigned DEFAULT NULL COMMENT '所属主题',
  `member` varchar(45) DEFAULT NULL COMMENT '作者',
  `attachId` int(10) unsigned DEFAULT NULL COMMENT '回复所带的附件列表',
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_reply_table
-- ----------------------------
INSERT INTO `javabean_reply_table` VALUES ('130', '<p>&nbsp;sdasdasd</p>', '2012-07-13 09:07:12', '0:0:0:0:0:0:0:1', '2', '1', 'hhz', null, '1');
INSERT INTO `javabean_reply_table` VALUES ('131', '<p>&nbsp;dsfsdfdsf</p>', '2012-07-13 09:10:17', '0:0:0:0:0:0:0:1', '3', '1', 'hhz', null, '1');
INSERT INTO `javabean_reply_table` VALUES ('132', '<p>&nbsp;holy shit!</p>', '2012-07-13 10:58:00', '0:0:0:0:0:0:0:1', '2', '55', 'hhz', null, '1');
INSERT INTO `javabean_reply_table` VALUES ('133', '<p>...</p>', '2012-07-13 11:13:44', '0:0:0:0:0:0:0:1', '3', '55', 'hhzhhz', null, '1');
INSERT INTO `javabean_reply_table` VALUES ('134', '<p>&nbsp;asdasdasd</p>', '2012-07-13 13:05:11', '0:0:0:0:0:0:0:1', '2', '58', 'hhz92', null, '1');

-- ----------------------------
-- Table structure for `javabean_topic_table`
-- ----------------------------
DROP TABLE IF EXISTS `javabean_topic_table`;
CREATE TABLE `javabean_topic_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'OID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `pubTime` varchar(20) DEFAULT NULL COMMENT '发表时间',
  `attachId` int(10) unsigned DEFAULT NULL COMMENT '主题所附带的附件列表',
  `forumId` int(10) unsigned DEFAULT NULL COMMENT '所属板块',
  `ip` varchar(45) DEFAULT NULL COMMENT '发表者所用的ip',
  `status` int(10) unsigned DEFAULT NULL COMMENT '状态(普通,移除,锁定,精华)',
  `type` int(10) unsigned DEFAULT NULL COMMENT '类型(置顶，隐藏，公告)',
  `visitCount` int(10) unsigned DEFAULT NULL COMMENT '人气',
  `replyCount` int(10) unsigned DEFAULT NULL COMMENT '回复数',
  `lastReplyTime` varchar(20) DEFAULT NULL COMMENT '最后回复时间',
  `replyid` int(10) unsigned DEFAULT NULL COMMENT '主题下的列表',
  `memberId` int(10) DEFAULT NULL,
  `lastReplyMember` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of javabean_topic_table
-- ----------------------------
INSERT INTO `javabean_topic_table` VALUES ('55', 'first blood', '<p>&nbsp;我会乱说？</p>', '2012-07-13 09:25:43', null, '1', '0:0:0:0:0:0:0:1', '1', '1', '16', '2', '2012-07-13 11:13:44', '133', '1', 'hhzhhz');
INSERT INTO `javabean_topic_table` VALUES ('56', 'hehehe', '<p>&nbsp;asdasdsd</p>', '2012-07-13 09:38:22', '1', '1', '0:0:0:0:0:0:0:1', '4', '0', '4', '0', null, null, '1', 'hhz');
INSERT INTO `javabean_topic_table` VALUES ('57', 'axaxaxa', '<p>&nbsp;asdasd</p>', '2012-07-13 11:25:25', null, '1', '0:0:0:0:0:0:0:1', '3', '0', '4', '0', null, null, '1', null);
INSERT INTO `javabean_topic_table` VALUES ('58', 'asddasd', '<p>&nbsp;asdasd</p>', '2012-07-13 13:04:55', null, '1', '0:0:0:0:0:0:0:1', '1', '0', '2', '1', '2012-07-13 13:05:11', '134', '4', 'hhz92');
