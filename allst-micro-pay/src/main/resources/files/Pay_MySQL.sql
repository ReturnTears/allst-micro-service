USE `edu_pay`;

/*Table structure for table `pay_order` */

DROP TABLE IF EXISTS `pay_order`;

CREATE TABLE `pay_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) NOT NULL DEFAULT '' COMMENT '订单号(唯一)',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `product_id` int(11) DEFAULT NULL COMMENT '商品唯一标识(ID)',
  `product_name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '金额,单位元',
  `count` int(11) DEFAULT '1' COMMENT '商品数量',
  `currency` varchar(6) NOT NULL DEFAULT 'cny' COMMENT '货币类型，cny-人民币 gbeans-勾豆',
  `channel` varchar(16) NOT NULL COMMENT '支付渠道：weChat-微信支付，aliPay-支付宝支付,applePay-苹果支付',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '订单状态：1-未支付 2-支付成功 3-支付失败 -1-订单失效',
  `channel_status` tinyint(8) DEFAULT NULL COMMENT '渠道中的状态码值',
  `order_type` tinyint(4) DEFAULT NULL COMMENT '类型 1-购买课程 2-充值',
  `source` tinyint(4) DEFAULT NULL COMMENT '支付来源 1-app 2-h5 3-pc',
  `client_ip` varchar(16) NOT NULL DEFAULT '127.0.0.1' COMMENT '用户支付IP',
  `buy_id` varchar(128) DEFAULT NULL COMMENT '购买账号id',
  `out_trade_no` varchar(128) DEFAULT NULL COMMENT '外部支付渠道交易号',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `extra` text COMMENT '附加字段，KV json，例如:{"mobile":13020202,"success_url":123}',
  `goods_order_no` varchar(64) DEFAULT NULL COMMENT '商品订单编号',
  `platform` int(11) DEFAULT '1' COMMENT '支付所使用的平台：1 拉勾 2 拉勾教育',
  `wx_type` int(11) DEFAULT '0' COMMENT '微信类型, 参考自lg-wechat-boot项目中的wxinfo',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_order_no` (`order_no`) USING BTREE,
  KEY `idx_user_id_order_no` (`user_id`,`order_no`) USING BTREE,
  KEY `idx_created_time` (`created_time`) USING BTREE,
  KEY `idx_user_id_product_id_status` (`user_id`,`product_id`,`status`) USING BTREE COMMENT '查询用户购买信息',
  KEY `idx_goods_order_no` (`goods_order_no`) USING BTREE COMMENT '根据商品订单编号来查询'
) ENGINE=InnoDB AUTO_INCREMENT=1308380188765376515 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='支付订单信息表';

/*Data for the table `pay_order` */

insert  into `pay_order`(`id`,`order_no`,`user_id`,`product_id`,`product_name`,`amount`,`count`,`currency`,`channel`,`status`,`channel_status`,`order_type`,`source`,`client_ip`,`buy_id`,`out_trade_no`,`created_time`,`updated_time`,`pay_time`,`extra`,`goods_order_no`,`platform`,`wx_type`) values 
(1281424938355687426,'368455274795757568',100030011,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 11:08:08','2020-07-10 13:08:03',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax07713ywloigw27m6h20a0\"}','368455272916709376',1,0),
(1281424948266827777,'368455284702703616',100030011,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',2,NULL,1,3,'124.193.79.2',NULL,'4200000595202007102795722008','2020-07-10 11:08:10','2020-07-10 11:08:24','2020-07-10 11:08:23','{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=2FynR0a\"}','368455272916709376',1,0),
(1281429419344560129,'368459755780435968',100030017,9,'秒杀11',0.01,1,'GBEANS','aliPay',2,NULL,1,3,'124.193.79.2',NULL,'2020071022001476181414677434','2020-07-10 11:25:56','2020-07-10 11:26:26','2020-07-10 11:26:26','{\"pay_url\":\"https://qr.alipay.com/bax04964pzhmhf4mz8dr2000\"}','368459754211766272',1,0),
(1281431480668815362,'368461817108885504',100030017,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 11:34:07','2020-07-10 13:34:03',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax02986uo9u4fksdgtu206f\"}','368461816232275968',1,0),
(1281431491397844994,'368461827837915136',100030017,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',2,NULL,1,3,'124.193.79.2',NULL,'4200000587202007105576355959','2020-07-10 11:34:10','2020-07-10 11:34:23','2020-07-10 11:34:23','{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=7IhBU2C\"}','368461816232275968',1,0),
(1281434366517739522,'368464702953615360',100030011,8,'Vue.js 3.0 核心源码解析',0.01,1,'GBEANS','aliPay',2,NULL,1,3,'124.193.79.2',NULL,'2020071022001440051449436090','2020-07-10 11:45:36','2020-07-10 11:45:49','2020-07-10 11:45:48','{\"pay_url\":\"https://qr.alipay.com/bax04848prdsuseyeis7504f\"}','368464701842124800',1,0),
(1281435829277065217,'368466165712941056',100030011,9,'秒杀11',0.01,1,'GBEANS','aliPay',2,NULL,1,3,'124.193.79.2',NULL,'2020071022001440051449692065','2020-07-10 11:51:24','2020-07-10 11:51:29','2020-07-10 11:51:29','{\"pay_url\":\"https://qr.alipay.com/bax09282nl8ruk9bnp4s000c\"}','368466164689530880',1,0),
(1281438170982154242,'368468507422224384',100030016,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 12:00:43','2020-07-10 14:00:38',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax06570srbk9jqrvhta00d0\"}','368468506558197760',1,0),
(1281438179437871105,'368468515873746944',100030016,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 12:00:45','2020-07-10 14:00:40',NULL,'{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=ufaeQII\"}','368468506558197760',1,0),
(1281442490721009666,'368472827156885504',100030018,9,'秒杀11',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 12:17:52','2020-07-10 14:17:48',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax00677cymwff3yiiyk508f\"}','368472821184196608',1,0),
(1281442514590793730,'368472851026669568',100030018,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 12:17:58','2020-07-10 14:17:53',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax00257dvhaarjqr5h40065\"}','368472847897718784',1,0),
(1281442539450433538,'368472875886309376',100030018,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',2,NULL,1,3,'124.193.79.2',NULL,'4200000579202007104407679979','2020-07-10 12:18:04','2020-07-10 12:18:06','2020-07-10 12:18:06','{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=ByXS8SB\"}','368472847897718784',1,0),
(1281443994781646850,'368474331217522688',100030019,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 12:23:51','2020-07-10 14:23:46',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax06616miad00rpoubb5012\"}','368474329971814400',1,0),
(1281444037974589442,'368474374410465280',100030019,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',2,NULL,1,3,'124.193.79.2',NULL,'4200000573202007102677077614','2020-07-10 12:24:01','2020-07-10 12:24:04','2020-07-10 12:24:03','{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=jSd83wx\"}','368474329971814400',1,0),
(1281463133483073537,'368493469918949376',100030019,9,'秒杀11',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-10 13:39:54','2020-07-10 15:39:49',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax04595fmhu85blnbgi808d\"}','368493469109448704',1,0),
(1281463140718247938,'368493477154123776',100030019,9,'秒杀11',0.01,1,'GBEANS','weChat',2,NULL,1,3,'124.193.79.2',NULL,'4200000590202007105916096626','2020-07-10 13:39:56','2020-07-10 13:42:17','2020-07-10 13:42:16','{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=yXKC1fJ\"}','368493469109448704',1,0),
(1283397333836750850,'370427670264147968',100030021,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',2,NULL,1,3,'124.193.79.2',NULL,'2020071522001476181417828862','2020-07-15 21:45:43','2020-07-15 21:45:53','2020-07-15 21:45:52','{\"pay_url\":\"https://qr.alipay.com/bax04164occvad6pbo5i206b\"}','490276988007743489',1,0),
(1283736323609714689,'370766660041306112',100030021,8,'Vue.js 3.0 核心源码解析',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-16 20:12:45','2020-07-28 01:54:24','2020-07-28 00:00:00','{\"pay_url\":\"https://qr.alipay.com/bax07949w6bngkl9tdbn208b\"}','490615979089330177',1,0),
(1284044138232537089,'371074474668322816',100030019,10,'React 入门',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-17 16:35:54','2020-07-17 18:35:49',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax03506lgdcix1t1yvp50e9\"}','490923793858953217',1,0),
(1284044155253022721,'371074491688808448',100030019,10,'React 入门',0.01,1,'GBEANS','weChat',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-17 16:35:58','2020-07-17 18:35:53',NULL,'{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=qi4lIaQ\"}','490923793858953217',1,0),
(1284044205395927041,'371074541831712768',100030019,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-17 16:36:10','2020-07-17 18:36:05',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax08708u1m8ojusvc9z8094\"}','490923861034926081',1,0),
(1284044210542338049,'371074546978123776',100030019,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',2,NULL,1,3,'124.193.79.2',NULL,'4200000576202007172770355272','2020-07-17 16:36:11','2020-07-17 16:36:14','2020-07-17 16:36:13','{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=4RURnAJ\"}','490923861034926081',1,0),
(1286619970503303169,'373650306901340160',100030021,10,'React 入门',0.01,1,'GBEANS','aliPay',2,NULL,1,3,'124.193.79.2',NULL,'2020072422001476181420911207','2020-07-24 19:11:20','2020-07-24 19:11:45','2020-07-24 19:11:44','{\"pay_url\":\"https://qr.alipay.com/bax03160szvhhjgjarrb800c\"}','493498126678622209',1,0),
(1287958066096271361,'374988402494402560',100030021,8,'Vue.js 3.0 核心源码解析',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,'2020072822001476181422450794','2020-07-28 00:00:00','2020-07-28 20:26:22','2020-07-28 00:00:00','{\"pay_url\":\"https://qr.alipay.com/bax02594dbt8gctuf0sk503d\"}','494836834161917953',1,0),
(1288307371286061058,'375337707717652480',100030021,9,'秒杀11',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-29 00:00:00','2020-07-29 12:56:23',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax0509544rxhz189ja22057\"}','495187026509824001',1,0),
(1288321749779595266,'375352086169251840',100030021,11,'大数据',0.01,1,'GBEANS','aliPay',-1,NULL,1,3,'124.193.79.2',NULL,NULL,'2020-07-29 00:00:00','2020-07-29 13:53:32',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax07920tkvf14znkrkz802a\"}','495201398372171777',1,0),
(1305427749951033345,'392458086340751360',100030016,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-14 00:00:00','2020-09-14 16:46:49',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax06437wkfbajkt4grv201e\"}','512307399017627649',1,0),
(1305427793093644290,'392458129529499648',100030016,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-14 00:00:00','2020-09-14 16:46:59',NULL,'{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=Faqcwn2\"}','512307399017627649',1,0),
(1306790694660395009,'393821031096250368',100030024,9,'秒杀11',0.01,1,'GBEANS','aliPay',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-18 00:00:00','2020-09-18 11:02:40',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax01058qgnvse9qen2e8053\"}','513670350194606081',1,0),
(1308335394399768577,'395365730835623936',100030024,7,'文案高手的18项修炼',0.01,1,'GBEANS','aliPay',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-22 00:00:00','2020-09-22 17:20:46',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax09810qoqtdttnxpml50ee\"}','515215049946562561',1,0),
(1308335410539450370,'395365746975305728',100030024,7,'文案高手的18项修炼',0.01,1,'GBEANS','weChat',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-22 00:00:00','2020-09-22 17:20:49',NULL,'{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=0oRWAmP00\"}','515215049946562561',1,0),
(1308371581910962177,'395401918342623232',100030024,13,'111111',0.01,1,'GBEANS','aliPay',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-22 00:00:00','2020-09-22 19:44:33',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax09555krmp6gypetci20c7\"}','515251237550030849',1,0),
(1308380080099348481,'395410416535203840',100030024,12,'111222',0.01,1,'GBEANS','aliPay',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-22 00:00:00','2020-09-22 20:18:20',NULL,'{\"pay_url\":\"https://qr.alipay.com/bax08599gyn2nowxkjc580f0\"}','515259735629365249',1,0),
(1308380188765376514,'395410525201231872',100030024,12,'111222',0.01,1,'GBEANS','weChat',1,NULL,1,3,'113.31.87.57',NULL,NULL,'2020-09-22 00:00:00','2020-09-22 20:18:45',NULL,'{\"pay_url\":\"weixin://wxpay/bizpayurl?pr=bFjABP200\"}','515259735629365249',1,0);

/*Table structure for table `pay_order_record` */

DROP TABLE IF EXISTS `pay_order_record`;

CREATE TABLE `pay_order_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `type` varchar(16) NOT NULL COMMENT '操作类型：CREATE|PAY|REFUND...',
  `from_status` varchar(16) DEFAULT NULL COMMENT '原订单状态',
  `to_status` varchar(16) NOT NULL COMMENT '新订单状态',
  `paid_amount` int(11) DEFAULT '0' COMMENT '实付金额，单位为分',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(64) NOT NULL COMMENT '操作人',
  `created_at` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='支付订单状态日志表';

/*Data for the table `pay_order_record` */

insert  into `pay_order_record`(`id`,`order_no`,`type`,`from_status`,`to_status`,`paid_amount`,`remark`,`created_by`,`created_at`) values 
(164,'370427670264147968','CREATE',NULL,'1',1,NULL,'auto','2020-07-15 21:45:43'),
(165,'370427670264147968','PAY','1','2',1,NULL,'auto','2020-07-15 21:45:58'),
(166,'370766660041306112','CREATE',NULL,'1',1,NULL,'auto','2020-07-16 20:12:45'),
(167,'370766660041306112','PAY','1','-1',1,NULL,'auto','2020-07-16 22:12:45'),
(168,'371074474668322816','CREATE',NULL,'1',1,NULL,'auto','2020-07-17 16:35:54'),
(169,'371074491688808448','CREATE',NULL,'1',1,NULL,'auto','2020-07-17 16:35:58'),
(170,'371074541831712768','CREATE',NULL,'1',1,NULL,'auto','2020-07-17 16:36:10'),
(171,'371074546978123776','CREATE',NULL,'1',1,NULL,'auto','2020-07-17 16:36:11'),
(172,'371074546978123776','PAY','1','2',1,NULL,'auto','2020-07-17 16:36:19'),
(173,'371074474668322816','PAY','1','-1',1,NULL,'auto','2020-07-17 18:35:54'),
(174,'371074491688808448','PAY','1','-1',1,NULL,'auto','2020-07-17 18:35:58'),
(175,'371074541831712768','PAY','1','-1',1,NULL,'auto','2020-07-17 18:36:10'),
(176,'373650306901340160','CREATE',NULL,'1',1,NULL,'auto','2020-07-24 19:11:20'),
(177,'373650306901340160','PAY','1','2',1,NULL,'auto','2020-07-24 19:11:50');

/*Table structure for table `undo_log` */

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


