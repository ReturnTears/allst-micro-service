DROP TABLE IF EXISTS `undo_log`;

CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'increment id',
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime NOT NULL COMMENT 'create datetime',
  `log_modified` datetime NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';

/*Data for the table `undo_log` */

/*Table structure for table `user_course_order_0` */

DROP TABLE IF EXISTS `user_course_order_0`;

CREATE TABLE `user_course_order_0` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_0` */

insert  into `user_course_order_0`(`id`,`order_no`,`user_id`,`course_id`,`activity_course_id`,`source_type`,`status`,`create_time`,`update_time`,`is_del`) values 
(33857,'368468506558197760',100030016,7,0,1,30,'2020-07-10 12:00:42','2020-07-10 12:00:42',0),
(33858,'368472821184196608',100030018,9,10,1,30,'2020-07-10 12:17:51','2020-07-10 12:17:51',0),
(33859,'368472847897718784',100030018,7,0,1,20,'2020-07-10 12:17:57','2020-07-10 12:17:57',0),
(489391524103061504,'489391524103061505',0,1,0,1,0,'2020-07-13 11:07:12','2020-07-13 11:07:12',0),
(489391526237962240,'489391526237962241',2,1,0,1,0,'2020-07-13 11:07:12','2020-07-13 11:07:12',0),
(489391526883885056,'489391526883885057',4,1,0,1,0,'2020-07-13 11:07:12','2020-07-13 11:07:12',0),
(489391527647248384,'489391527647248385',6,1,0,1,0,'2020-07-13 11:07:13','2020-07-13 11:07:13',0),
(489391528272199680,'489391528272199681',8,1,0,1,0,'2020-07-13 11:07:13','2020-07-13 11:07:13',0);

/*Table structure for table `user_course_order_1` */

DROP TABLE IF EXISTS `user_course_order_1`;

CREATE TABLE `user_course_order_1` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_1` */

insert  into `user_course_order_1`(`id`,`order_no`,`user_id`,`course_id`,`activity_course_id`,`source_type`,`status`,`create_time`,`update_time`,`is_del`) values 
(33852,'368455272916709376',100030011,7,0,1,20,'2020-07-10 11:08:07','2020-07-10 11:08:07',0),
(33853,'368459754211766272',100030017,9,10,1,20,'2020-07-10 11:25:56','2020-07-10 11:25:56',0),
(33854,'368461816232275968',100030017,7,0,1,20,'2020-07-10 11:34:07','2020-07-10 11:34:07',0),
(33855,'368464701842124800',100030011,8,0,1,20,'2020-07-10 11:45:35','2020-07-10 11:45:35',0),
(33856,'368466164689530880',100030011,9,10,1,20,'2020-07-10 11:51:24','2020-07-10 11:51:24',0),
(33860,'368474329971814400',100030019,7,0,1,20,'2020-07-10 12:23:51','2020-07-10 12:23:51',0),
(33861,'368493469109448704',100030019,9,10,1,20,'2020-07-10 13:39:54','2020-07-10 13:39:54',0),
(489391525860474880,'489391525860474881',1,1,0,1,0,'2020-07-13 11:07:12','2020-07-13 11:07:12',0),
(489391526569312256,'489391526569312257',3,1,0,1,0,'2020-07-13 11:07:12','2020-07-13 11:07:12',0),
(489391527311704064,'489391527311704065',5,1,0,1,0,'2020-07-13 11:07:13','2020-07-13 11:07:13',0),
(489391527961821184,'489391527961821185',7,1,0,1,0,'2020-07-13 11:07:13','2020-07-13 11:07:13',0),
(489391528578383872,'489391528578383873',9,1,0,1,20,'2020-07-13 11:07:13','2020-07-13 11:07:13',0),
(490276988007743488,'490276988007743489',100030021,7,0,1,20,'2020-07-15 21:45:43','2020-07-15 21:45:43',0),
(493498126678622208,'493498126678622209',100030021,10,0,1,20,'2020-07-24 19:05:22','2020-07-24 19:05:22',0),
(494836834161917952,'494836834161917953',100030021,8,0,1,30,'2020-07-28 11:44:55','2020-07-28 11:44:55',0),
(495187026509824000,'495187026509824001',100030021,9,0,1,30,'2020-07-29 10:56:27','2020-07-29 10:56:27',0),
(495201398372171776,'495201398372171777',100030021,11,11,1,30,'2020-07-29 11:53:34','2020-07-29 11:53:34',0);

/*Table structure for table `user_course_order_2` */

DROP TABLE IF EXISTS `user_course_order_2`;

CREATE TABLE `user_course_order_2` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_2` */

/*Table structure for table `user_course_order_3` */

DROP TABLE IF EXISTS `user_course_order_3`;

CREATE TABLE `user_course_order_3` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_3` */

/*Table structure for table `user_course_order_4` */

DROP TABLE IF EXISTS `user_course_order_4`;

CREATE TABLE `user_course_order_4` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_4` */

insert  into `user_course_order_4`(`id`,`order_no`,`user_id`,`course_id`,`activity_course_id`,`source_type`,`status`,`create_time`,`update_time`,`is_del`) values 
(513670350194606080,'513670350194606081',100030024,9,0,1,0,'2020-09-18 11:02:35','2020-09-18 11:02:35',0),
(515215049946562560,'515215049946562561',100030024,7,0,1,0,'2020-09-22 17:20:40','2020-09-22 17:20:40',0),
(515251237550030848,'515251237550030849',100030024,13,0,1,0,'2020-09-22 19:44:28','2020-09-22 19:44:28',0),
(515259735629365248,'515259735629365249',100030024,12,0,1,0,'2020-09-22 20:18:14','2020-09-22 20:18:14',0);

/*Table structure for table `user_course_order_5` */

DROP TABLE IF EXISTS `user_course_order_5`;

CREATE TABLE `user_course_order_5` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_5` */

/*Table structure for table `user_course_order_6` */

DROP TABLE IF EXISTS `user_course_order_6`;

CREATE TABLE `user_course_order_6` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_6` */

insert  into `user_course_order_6`(`id`,`order_no`,`user_id`,`course_id`,`activity_course_id`,`source_type`,`status`,`create_time`,`update_time`,`is_del`) values 
(512307399017627648,'512307399017627649',100030016,7,0,1,0,'2020-09-14 16:46:42','2020-09-14 16:46:42',0);

/*Table structure for table `user_course_order_7` */

DROP TABLE IF EXISTS `user_course_order_7`;

CREATE TABLE `user_course_order_7` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_7` */

/*Table structure for table `user_course_order_8` */

DROP TABLE IF EXISTS `user_course_order_8`;

CREATE TABLE `user_course_order_8` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_8` */

/*Table structure for table `user_course_order_9` */

DROP TABLE IF EXISTS `user_course_order_9`;

CREATE TABLE `user_course_order_9` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id，根据订单中的课程类型来选择',
  `activity_course_id` int(11) DEFAULT '0' COMMENT '活动课程id',
  `source_type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单来源类型: 1 用户下单购买 2 后台添加专栏',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期 ',
  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
  `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_userId_courseType_courseId_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE,
  UNIQUE KEY `uniq_userId_sourceType_refDataId_courseId` (`user_id`,`source_type`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户课程订单表';

/*Data for the table `user_course_order_9` */

insert  into `user_course_order_9`(`id`,`order_no`,`user_id`,`course_id`,`activity_course_id`,`source_type`,`status`,`create_time`,`update_time`,`is_del`) values 
(490923793858953216,'490923793858953217',100030019,10,0,1,30,'2020-07-17 16:35:53','2020-07-17 16:35:53',0),
(490923861034926080,'490923861034926081',100030019,7,0,1,20,'2020-07-17 16:36:09','2020-07-17 16:36:09',0);

/*Table structure for table `user_course_order_record` */

DROP TABLE IF EXISTS `user_course_order_record`;

CREATE TABLE `user_course_order_record` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `from_status` varchar(16) DEFAULT NULL COMMENT '原订单状态',
  `to_status` varchar(16) DEFAULT NULL COMMENT '新订单状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=utf8mb4 COMMENT='课程订单状态日志表';

/*Data for the table `user_course_order_record` */

insert  into `user_course_order_record`(`id`,`order_no`,`from_status`,`to_status`,`remark`,`create_time`,`create_user`,`update_time`,`update_user`) values 
(129,'490276988007743489',NULL,'0',NULL,'2020-07-15 21:45:43','auto','2020-07-15 21:45:43','auto'),
(130,'490276988007743489','0','20',NULL,'2020-07-15 21:45:58','auto','2020-07-15 21:45:58','auto'),
(131,'490615979089330177',NULL,'0',NULL,'2020-07-16 20:12:45','auto','2020-07-16 20:12:45','auto'),
(132,'490615979089330177','0','30',NULL,'2020-07-16 22:12:45','auto','2020-07-16 22:12:45','auto'),
(133,'490923793858953217',NULL,'0',NULL,'2020-07-17 16:35:53','auto','2020-07-17 16:35:53','auto'),
(134,'490923861034926081',NULL,'0',NULL,'2020-07-17 16:36:09','auto','2020-07-17 16:36:09','auto'),
(135,'490923861034926081','0','20',NULL,'2020-07-17 16:36:19','auto','2020-07-17 16:36:19','auto'),
(136,'490923793858953217','0','30',NULL,'2020-07-17 18:35:54','auto','2020-07-17 18:35:54','auto'),
(137,'490923793858953217','0','30',NULL,'2020-07-17 18:35:58','auto','2020-07-17 18:35:58','auto'),
(138,'490923861034926081','0','30',NULL,'2020-07-17 18:36:10','auto','2020-07-17 18:36:10','auto'),
(139,'489391528578383873','0','20',NULL,'2020-07-24 15:09:50','auto','2020-07-24 15:09:50','auto'),
(140,'489391528578383873','0','20',NULL,'2020-07-24 15:13:31','auto','2020-07-24 15:13:31','auto'),
(141,'489391528578383873','0','20',NULL,'2020-07-24 15:55:22','auto','2020-07-24 15:55:22','auto'),
(142,'489391528578383873','0','20',NULL,'2020-07-24 17:07:50','auto','2020-07-24 17:07:50','auto'),
(143,'493498126678622209',NULL,'0',NULL,'2020-07-24 19:05:22','auto','2020-07-24 19:05:22','auto'),
(144,'493498126678622209','0','20',NULL,'2020-07-24 19:11:50','auto','2020-07-24 19:11:50','auto'),
(145,'490615979089330177','0','20',NULL,'2020-07-25 16:03:15','auto','2020-07-25 16:03:15','auto'),
(146,'490615979089330177','0','20',NULL,'2020-07-25 16:05:45','auto','2020-07-25 16:05:45','auto'),
(147,'490615979089330177','0','20',NULL,'2020-07-25 16:12:10','auto','2020-07-25 16:12:10','auto'),
(148,'490615979089330177','0','20',NULL,'2020-07-25 16:16:16','auto','2020-07-25 16:16:16','auto'),
(149,'490615979089330177','0','20',NULL,'2020-07-25 16:23:09','auto','2020-07-25 16:23:09','auto'),
(150,'490615979089330177','0','20',NULL,'2020-07-25 16:26:05','auto','2020-07-25 16:26:05','auto'),
(151,'490615979089330177','0','20',NULL,'2020-07-25 18:22:29','auto','2020-07-25 18:22:29','auto'),
(152,'490615979089330177','0','20',NULL,'2020-07-26 18:12:26','auto','2020-07-26 18:12:26','auto'),
(153,'490615979089330177','0','20',NULL,'2020-07-26 18:14:28','auto','2020-07-26 18:14:28','auto'),
(154,'490615979089330177','0','20',NULL,'2020-07-26 18:17:47','auto','2020-07-26 18:17:47','auto'),
(155,'490615979089330177','0','20',NULL,'2020-07-26 18:35:34','auto','2020-07-26 18:35:34','auto'),
(156,'490615979089330177','0','20',NULL,'2020-07-26 18:42:16','auto','2020-07-26 18:42:16','auto'),
(157,'490615979089330177','0','20',NULL,'2020-07-26 18:43:13','auto','2020-07-26 18:43:13','auto'),
(158,'490615979089330177','0','20',NULL,'2020-07-26 18:51:45','auto','2020-07-26 18:51:45','auto'),
(159,'490615979089330177','0','20',NULL,'2020-07-26 18:52:29','auto','2020-07-26 18:52:29','auto'),
(160,'490615979089330177','0','20',NULL,'2020-07-26 18:59:15','auto','2020-07-26 18:59:15','auto'),
(161,'490615979089330177','0','20',NULL,'2020-07-26 19:03:39','auto','2020-07-26 19:03:39','auto'),
(162,'490615979089330177','0','20',NULL,'2020-07-26 19:04:19','auto','2020-07-26 19:04:19','auto'),
(163,'490615979089330177','0','20',NULL,'2020-07-26 19:06:25','auto','2020-07-26 19:06:25','auto'),
(164,'490615979089330177','0','20',NULL,'2020-07-26 19:10:38','auto','2020-07-26 19:10:38','auto'),
(165,'490615979089330177','0','20',NULL,'2020-07-26 19:10:44','auto','2020-07-26 19:10:44','auto'),
(166,'490615979089330177','0','20',NULL,'2020-07-26 19:13:55','auto','2020-07-26 19:13:55','auto'),
(167,'490615979089330177','0','20',NULL,'2020-07-26 19:30:32','auto','2020-07-26 19:30:32','auto'),
(168,'490615979089330177','0','20',NULL,'2020-07-26 19:33:36','auto','2020-07-26 19:33:36','auto'),
(169,'490615979089330177','0','20',NULL,'2020-07-26 19:47:54','auto','2020-07-26 19:47:54','auto'),
(170,'490615979089330177','0','20',NULL,'2020-07-26 19:53:09','auto','2020-07-26 19:53:09','auto'),
(171,'490615979089330177','0','20',NULL,'2020-07-26 19:53:25','auto','2020-07-26 19:53:25','auto'),
(172,'490615979089330177','0','20',NULL,'2020-07-26 19:57:58','auto','2020-07-26 19:57:58','auto'),
(173,'490615979089330177','0','20',NULL,'2020-07-26 20:43:11','auto','2020-07-26 20:43:11','auto'),
(174,'490615979089330177','0','20',NULL,'2020-07-26 20:43:30','auto','2020-07-26 20:43:30','auto'),
(175,'490615979089330177','0','20',NULL,'2020-07-26 20:43:49','auto','2020-07-26 20:43:49','auto'),
(176,'490615979089330177','0','20',NULL,'2020-07-26 23:12:57','auto','2020-07-26 23:12:57','auto'),
(177,'490615979089330177','0','20',NULL,'2020-07-26 23:13:52','auto','2020-07-26 23:13:52','auto'),
(178,'490615979089330177','0','20',NULL,'2020-07-27 00:10:13','auto','2020-07-27 00:10:13','auto'),
(179,'490615979089330177','0','20',NULL,'2020-07-27 00:14:39','auto','2020-07-27 00:14:39','auto'),
(180,'490615979089330177','0','20',NULL,'2020-07-27 00:17:34','auto','2020-07-27 00:17:34','auto'),
(181,'490615979089330177','0','20',NULL,'2020-07-27 01:42:15','auto','2020-07-27 01:42:15','auto'),
(182,'490615979089330177','0','20',NULL,'2020-07-27 01:47:02','auto','2020-07-27 01:47:02','auto'),
(183,'490615979089330177','0','20',NULL,'2020-07-27 21:05:06','auto','2020-07-27 21:05:06','auto'),
(184,'490615979089330177','0','20',NULL,'2020-07-27 21:10:39','auto','2020-07-27 21:10:39','auto'),
(185,'490615979089330177','0','20',NULL,'2020-07-27 21:14:37','auto','2020-07-27 21:14:37','auto'),
(186,'490615979089330177','0','20',NULL,'2020-07-27 21:36:09','auto','2020-07-27 21:36:09','auto'),
(187,'490615979089330177','0','20',NULL,'2020-07-27 21:36:54','auto','2020-07-27 21:36:54','auto'),
(188,'490615979089330177','0','20',NULL,'2020-07-27 21:38:33','auto','2020-07-27 21:38:33','auto'),
(189,'490615979089330177','0','20',NULL,'2020-07-27 21:44:30','auto','2020-07-27 21:44:30','auto'),
(190,'490615979089330177','0','20',NULL,'2020-07-27 21:50:46','auto','2020-07-27 21:50:46','auto'),
(191,'490615979089330177','0','20',NULL,'2020-07-27 21:51:25','auto','2020-07-27 21:51:25','auto'),
(192,'490615979089330177','0','20',NULL,'2020-07-27 21:51:32','auto','2020-07-27 21:51:32','auto'),
(193,'490615979089330177','0','20',NULL,'2020-07-27 21:51:33','auto','2020-07-27 21:51:33','auto'),
(194,'490615979089330177','0','20',NULL,'2020-07-27 21:51:33','auto','2020-07-27 21:51:33','auto'),
(195,'490615979089330177','0','20',NULL,'2020-07-27 21:51:36','auto','2020-07-27 21:51:36','auto'),
(196,'490615979089330177','0','20',NULL,'2020-07-27 21:51:37','auto','2020-07-27 21:51:37','auto'),
(197,'490615979089330177','0','20',NULL,'2020-07-27 21:51:37','auto','2020-07-27 21:51:37','auto'),
(198,'490615979089330177','0','20',NULL,'2020-07-27 21:51:38','auto','2020-07-27 21:51:38','auto'),
(199,'490615979089330177','0','20',NULL,'2020-07-27 21:51:38','auto','2020-07-27 21:51:38','auto'),
(200,'490615979089330177','0','20',NULL,'2020-07-27 21:51:39','auto','2020-07-27 21:51:39','auto'),
(201,'490615979089330177','0','20',NULL,'2020-07-27 21:52:03','auto','2020-07-27 21:52:03','auto'),
(202,'490615979089330177','0','20',NULL,'2020-07-27 21:53:41','auto','2020-07-27 21:53:41','auto'),
(203,'490615979089330177','0','20',NULL,'2020-07-27 21:54:16','auto','2020-07-27 21:54:16','auto'),
(204,'490615979089330177','0','20',NULL,'2020-07-27 21:56:08','auto','2020-07-27 21:56:08','auto'),
(205,'490615979089330177','0','20',NULL,'2020-07-28 00:16:58','auto','2020-07-28 00:16:58','auto'),
(206,'490615979089330177','0','20',NULL,'2020-07-28 00:18:42','auto','2020-07-28 00:18:42','auto'),
(207,'490615979089330177','0','20',NULL,'2020-07-28 00:19:22','auto','2020-07-28 00:19:22','auto'),
(208,'490615979089330177','0','20',NULL,'2020-07-28 00:41:50','auto','2020-07-28 00:41:50','auto'),
(209,'490615979089330177','0','20',NULL,'2020-07-28 00:49:06','auto','2020-07-28 00:49:06','auto'),
(210,'490615979089330177','0','20',NULL,'2020-07-28 01:02:09','auto','2020-07-28 01:02:09','auto'),
(211,'490615979089330177','0','20',NULL,'2020-07-28 01:02:52','auto','2020-07-28 01:02:52','auto'),
(212,'490615979089330177','0','20',NULL,'2020-07-28 01:03:17','auto','2020-07-28 01:03:17','auto'),
(213,'490615979089330177','0','20',NULL,'2020-07-28 01:04:02','auto','2020-07-28 01:04:02','auto'),
(214,'490615979089330177','0','20',NULL,'2020-07-28 01:05:25','auto','2020-07-28 01:05:25','auto'),
(215,'490615979089330177','0','20',NULL,'2020-07-28 01:06:53','auto','2020-07-28 01:06:53','auto'),
(216,'490615979089330177','0','20',NULL,'2020-07-28 01:08:23','auto','2020-07-28 01:08:23','auto'),
(217,'490615979089330177','0','20',NULL,'2020-07-28 01:10:02','auto','2020-07-28 01:10:02','auto'),
(218,'490615979089330177','0','20',NULL,'2020-07-28 01:11:39','auto','2020-07-28 01:11:39','auto'),
(219,'490615979089330177','0','20',NULL,'2020-07-28 01:53:33','auto','2020-07-28 01:53:33','auto'),
(220,'490615979089330177','0','20',NULL,'2020-07-28 01:56:38','auto','2020-07-28 01:56:38','auto'),
(221,'490615979089330177','0','20',NULL,'2020-07-28 01:56:48','auto','2020-07-28 01:56:48','auto'),
(222,'490615979089330177','0','20',NULL,'2020-07-28 10:39:48','auto','2020-07-28 10:39:48','auto'),
(223,'490615979089330177','0','20',NULL,'2020-07-28 11:02:49','auto','2020-07-28 11:02:49','auto'),
(224,'490615979089330177','0','20',NULL,'2020-07-28 11:03:39','auto','2020-07-28 11:03:39','auto'),
(225,'490615979089330177','0','20',NULL,'2020-07-28 11:40:00','auto','2020-07-28 11:40:00','auto'),
(226,'494836834161917953',NULL,'0',NULL,'2020-07-28 11:44:55','auto','2020-07-28 11:44:55','auto'),
(227,'494836834161917953','0','20',NULL,'2020-07-28 11:48:45','auto','2020-07-28 11:48:45','auto'),
(228,'494836834161917953','0','20',NULL,'2020-07-28 15:45:29','auto','2020-07-28 15:45:29','auto'),
(229,'494836834161917953','0','20',NULL,'2020-07-28 15:57:28','auto','2020-07-28 15:57:28','auto'),
(230,'494836834161917953','0','20',NULL,'2020-07-28 20:18:01','auto','2020-07-28 20:18:01','auto'),
(231,'494836834161917953','0','20',NULL,'2020-07-28 20:29:32','auto','2020-07-28 20:29:32','auto'),
(232,'495185098472161281',NULL,'0',NULL,'2020-07-29 10:48:48','auto','2020-07-29 10:48:48','auto'),
(233,'495187026509824001',NULL,'0',NULL,'2020-07-29 10:56:27','auto','2020-07-29 10:56:27','auto'),
(234,'495187879186661377',NULL,'0',NULL,'2020-07-29 10:59:51','auto','2020-07-29 10:59:51','auto'),
(235,'495191288757354497',NULL,'0',NULL,'2020-07-29 11:13:23','auto','2020-07-29 11:13:23','auto'),
(236,'495194347638095873',NULL,'0',NULL,'2020-07-29 11:25:33','auto','2020-07-29 11:25:33','auto'),
(237,'495197345093255169',NULL,'0',NULL,'2020-07-29 11:37:27','auto','2020-07-29 11:37:27','auto'),
(238,'495201398372171777',NULL,'0',NULL,'2020-07-29 11:53:34','auto','2020-07-29 11:53:34','auto'),
(239,'495187026509824001','0','30',NULL,'2020-07-29 12:56:29','auto','2020-07-29 12:56:29','auto'),
(240,'495201398372171777','0','30',NULL,'2020-07-29 13:53:37','auto','2020-07-29 13:53:37','auto'),
(241,'512307399017627649',NULL,'0',NULL,'2020-09-14 16:46:43','auto','2020-09-14 16:46:43','auto'),
(242,'513670350194606081',NULL,'0',NULL,'2020-09-18 11:02:35','auto','2020-09-18 11:02:35','auto'),
(243,'515215049946562561',NULL,'0',NULL,'2020-09-22 17:20:40','auto','2020-09-22 17:20:40','auto'),
(244,'515251237550030849',NULL,'0',NULL,'2020-09-22 19:44:28','auto','2020-09-22 19:44:28','auto'),
(245,'515259735629365249',NULL,'0',NULL,'2020-09-22 20:18:14','auto','2020-09-22 20:18:14','auto');
