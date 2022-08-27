/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `name` varchar(255) NOT NULL COMMENT '用户昵称',
    `portrait` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
    `phone` varchar(255) NOT NULL COMMENT '注册手机',
    `password` varchar(255) DEFAULT NULL COMMENT '用户密码（可以为空，支持只用验证码注册、登录）',
    `reg_ip` varchar(255) DEFAULT NULL COMMENT '注册ip',
    `account_non_expired` bit(1) DEFAULT b'1' COMMENT '是否有效用户',
    `credentials_non_expired` bit(1) DEFAULT b'1' COMMENT '账号是否未过期',
    `account_non_locked` bit(1) DEFAULT b'1' COMMENT '是否未锁定',
    `status` varchar(20) NOT NULL DEFAULT 'ENABLE' COMMENT '用户状态：ENABLE能登录，DISABLE不能登录',
    `is_del` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `create_time` datetime NOT NULL COMMENT '注册时间',
    `update_time` datetime NOT NULL COMMENT '记录更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_phone_is_del` (`phone`,`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100030027 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`portrait`,`phone`,`password`,`reg_ip`,`account_non_expired`,`credentials_non_expired`,`account_non_locked`,`status`,`is_del`,`create_time`,`update_time`) values
(100030011,'15321919577','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/06/28/15933251448762927.png','15321919577','$2a$10$aobP02xbn3N/v.ZCcXqCI.6cV02rLA.8.nakqyIBLCKPTGPsFvJou',NULL,'','','','DISABLE','','2020-07-10 10:19:15','2020-08-14 21:44:25'),
(100030012,'15510792994',NULL,'15510792994','$2a$10$h815kSB3UYIct1lPk7tybesRd.JdAPvnJKwSBkauGlNiIJHKpUsuS',NULL,'','','','ENABLE','\0','2020-07-10 10:43:01','2020-07-10 10:43:01'),
(100030013,'15801363456',NULL,'15801363456','$2a$10$JsBLd.2kpFjPWx4dGev1Mut..mLLTTXrM2qVb6nCPhOhgFu7RoGmK',NULL,'','','','ENABLE','\0','2020-07-10 11:09:11','2020-07-10 11:09:11'),
(100030014,'15321919571',NULL,'15321919571','$2a$10$.15BYpIXvEyUIpcuAEKdN.AcVGWjs7gi4KXTzGMh15aDabkM0LZMe',NULL,'','','','ENABLE','\0','2020-07-10 11:10:26','2020-07-10 11:10:26'),
(100030015,'15801363195',NULL,'15801363195','$2a$10$FWWb4sULtpEr72mRfrEWFO5Wxxxfu0gnk8jJvtvmyUgHV7y.AysOm',NULL,'','','','ENABLE','\0','2020-07-10 11:21:50','2020-07-10 11:21:50'),
(100030016,'15510792995',NULL,'15510792995','$2a$10$DDOW0oJO9cNm.ZEDNmJmF.AZhsQxoyQ84zSx.UKZBbc58qJWh9HSy',NULL,'','','','ENABLE','\0','2020-07-10 11:23:56','2020-07-10 11:23:56'),
(100030017,'15811111111',NULL,'15811111111','$2a$10$j08ZtKUfYeQ5cTCRLoeFeuNDmob1DnLRK7Mfkdhr1/SuUKhqkwkCC',NULL,'','','','DISABLE','','2020-07-10 11:25:45','2020-07-13 10:56:31'),
(100030018,'15813795039',NULL,'15813795039','$2a$10$dudhkaLfSJJhpy7q5dqX4uEbmWk9XpS8UcpmzGIM8YI3UWGzOcMHm',NULL,'','','','ENABLE','\0','2020-07-10 12:17:35','2020-07-10 12:17:35'),
(100030019,'18201288771','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/10/15943594999396473.png','18201288771','$2a$10$XmknffykNPNMs97wJKtOwem6tR8HGMQTx0PfALGA66311pzrW8rq2',NULL,'','','','ENABLE','\0','2020-07-10 12:20:16','2020-07-10 12:20:16'),
(100030020,'18211111111',NULL,'18211111111','$2a$10$zwz0Qp6H0TYZjDs0hKwiU.wKb91ws4xYkfFf1tujgPg/AcPXTChN2',NULL,'','','','ENABLE','\0','2020-07-10 13:57:41','2020-07-10 13:57:41'),
(100030021,'15811111111',NULL,'15811111111','$2a$10$LeMiCC/TNUXdvSAaXmUmn.WAGcmkcBVKG4oAhbqZDAneCBOUgVc1.',NULL,'','','','ENABLE','\0','2020-07-13 11:35:20','2020-07-13 11:35:20'),
(100030022,'用户8666',NULL,'18201288666','$2a$10$zO8M7N/f53OAuyo1GqlW5ujlj3KSeb9lKMwNCNVyuLPNtTfKddo2.',NULL,'','','','ENABLE','\0','2020-07-13 17:43:52','2020-07-13 17:43:52'),
(100030024,'18631142257',NULL,'18631142257','$2a$10$DDOW0oJO9cNm.ZEDNmJmF.AZhsQxoyQ84zSx.UKZBbc58qJWh9HSy',NULL,'','','','ENABLE','\0','2020-09-09 15:41:59','2020-09-09 15:42:03'),
(100030025,'用户6870',NULL,'17090086870','$2a$10$.Njlz4JFOQ3QDv5fKFJm..YTb5Ig05U1oUxMS1I2VznnKJOwaDikK',NULL,'','','','ENABLE','\0','2020-09-23 18:34:33','2020-09-23 18:34:33'),
(100030026,'18631142258',NULL,'18631142258','$2a$10$DDOW0oJO9cNm.ZEDNmJmF.AZhsQxoyQ84zSx.UKZBbc58qJWh9HSy',NULL,'','','','ENABLE','\0','2020-09-28 15:07:47','2020-09-28 15:07:51');

/*Table structure for table `user_phone_verification_code` */

DROP TABLE IF EXISTS `user_phone_verification_code`;

CREATE TABLE `user_phone_verification_code` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `phone` varchar(15) DEFAULT '' COMMENT '手机号',
    `verification_code` varchar(15) DEFAULT '' COMMENT '验证码',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `isCheck` bit(1) DEFAULT b'0' COMMENT '验证码是否校验过',
    `check_times` int(2) DEFAULT '0' COMMENT '校验次数',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `l_phone_verification_code_ind_01` (`phone`,`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33327 DEFAULT CHARSET=utf8;

/*Data for the table `user_phone_verification_code` */

insert  into `user_phone_verification_code`(`id`,`phone`,`verification_code`,`create_time`,`isCheck`,`check_times`) values
(33305,'18201288775','111111','2020-07-03 23:59:31','',80),
(33306,'008615321919577','472148','2020-07-17 16:41:21','\0',0),
(33307,'008615321919577','254709','2020-07-17 16:53:02','\0',0),
(33308,'0086','149696','2020-07-17 17:33:19','\0',0),
(33309,'0086','164595','2020-07-17 18:00:48','\0',0),
(33310,'008615321919577','994241','2020-07-17 18:01:27','\0',0),
(33311,'008615321919577','775431','2020-07-17 18:04:31','\0',0),
(33312,'008615321919577','610538','2020-07-17 18:06:45','\0',0),
(33313,'008615321919577','317040','2020-07-17 18:17:05','\0',0),
(33314,'008618201288770','956444','2020-07-23 16:18:56','',2),
(33315,'18201288771','029570','2020-07-23 18:01:20','',2),
(33316,'18201288771','058365','2020-07-23 19:11:35','',2),
(33317,'18631142257','596241','2020-08-25 13:59:15','\0',0),
(33318,'18631142257','976339','2020-08-25 14:08:18','\0',0),
(33319,'008218631142257','309912','2020-09-08 21:48:58','\0',0),
(33320,'13580509685','477501','2020-09-12 18:03:06','\0',0),
(33321,'17090086870','430423','2020-09-23 18:19:42','\0',0),
(33322,'17090086870','502657','2020-09-23 18:20:56','\0',0),
(33323,'17090086870','864045','2020-09-23 18:22:11','\0',0),
(33324,'15311351040','239238','2020-09-23 18:23:46','\0',0),
(33325,'17090086870','039845','2020-09-23 18:38:09','\0',0),
(33326,'18358332543','571197','2020-09-28 13:39:12','\0',0);

/*Table structure for table `user_weixin` */

DROP TABLE IF EXISTS `user_weixin`;

CREATE TABLE `user_weixin` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `user_id` int(11) DEFAULT NULL COMMENT '用户id',
   `union_id` varchar(255) NOT NULL DEFAULT '' COMMENT '认证id,微信对应的时unionId',
   `open_id` varchar(255) DEFAULT NULL COMMENT 'openId',
   `nick_name` varchar(255) NOT NULL COMMENT '昵称',
   `portrait` varchar(512) DEFAULT NULL COMMENT '头像',
   `city` varchar(255) DEFAULT NULL COMMENT '城市',
   `sex` int(11) DEFAULT NULL COMMENT '性别, 1-男，2-女',
   `create_time` datetime NOT NULL COMMENT '创建时间',
   `update_time` datetime NOT NULL COMMENT '更新时间',
   `is_del` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
   PRIMARY KEY (`id`) USING BTREE,
   UNIQUE KEY `oauthId_and_oauthType_unique` (`union_id`,`open_id`,`is_del`) USING BTREE,
   UNIQUE KEY `userId_and_oauthType_unique_index` (`user_id`,`open_id`,`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=506563 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `user_weixin` */

insert  into `user_weixin`(`id`,`user_id`,`union_id`,`open_id`,`nick_name`,`portrait`,`city`,`sex`,`create_time`,`update_time`,`is_del`) values
(506561,100030019,'oXEX_svcbl-mCDhWloqlEFNVHgP8','oGYgl0u0vZMKVAByQ3hR0i7jpKew','leo','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epKy1c3YeeI5vRqSxqDkaYc9XDuPao1BRLFKGf65SiaRIFqHTpeJg90RfrCXCo7WkicpfsPdKTdNTpA/132','',1,'2020-07-23 19:12:21','2020-07-23 19:12:21','\0');
