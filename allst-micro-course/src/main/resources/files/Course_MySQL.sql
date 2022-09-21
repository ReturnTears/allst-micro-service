/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.5.42-log : Database - edu_course
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`edu_course` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `edu_course`;

/*Table structure for table `activity_course` */

DROP TABLE IF EXISTS `activity_course`;

CREATE TABLE `activity_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `begin_time` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '活动结束时间',
  `amount` double(10,2) DEFAULT NULL COMMENT '活动价格',
  `stock` int(10) DEFAULT NULL COMMENT '库存值',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态 0未上架 10已上架',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0未删除 1删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_course_id` (`course_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='活动课程表';

/*Data for the table `activity_course` */

insert  into `activity_course`(`id`,`course_id`,`begin_time`,`end_time`,`amount`,`stock`,`status`,`is_del`,`remark`,`create_time`,`create_user`,`update_time`,`update_user`) values 
(10,9,'2020-07-10 11:00:00','2020-08-11 12:00:00',1.00,98,0,0,NULL,'2020-07-10 11:24:31','auto',NULL,'auto'),
(11,11,'2020-07-28 12:00:00','2020-08-20 12:00:00',1.00,94,0,0,NULL,'2020-07-29 10:59:29','auto',NULL,'auto');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `course_name` varchar(255) DEFAULT NULL COMMENT '课程名',
  `brief` varchar(255) DEFAULT '' COMMENT '课程一句话简介',
  `price` double(10,2) DEFAULT NULL COMMENT '原价',
  `price_tag` varchar(255) DEFAULT '' COMMENT '原价标签',
  `discounts` double(10,2) DEFAULT NULL COMMENT '优惠价',
  `discounts_tag` varchar(255) DEFAULT NULL COMMENT '优惠标签',
  `course_description_mark_down` longtext COMMENT '描述markdown',
  `course_description` longtext COMMENT '课程描述',
  `course_img_url` varchar(255) DEFAULT NULL COMMENT '课程分享图片url',
  `is_new` tinyint(1) DEFAULT NULL COMMENT '是否新品',
  `is_new_des` varchar(255) DEFAULT NULL COMMENT '广告语',
  `last_operator_id` int(11) DEFAULT NULL COMMENT '最后操作者',
  `auto_online_time` datetime DEFAULT NULL COMMENT '自动上架时间',
  `create_time` datetime NOT NULL COMMENT '记录创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `total_duration` int(11) DEFAULT NULL COMMENT '总时长(分钟)',
  `course_list_img` varchar(255) DEFAULT NULL COMMENT '课程列表展示图片',
  `status` int(2) DEFAULT '0' COMMENT '课程状态，0-草稿，1-上架',
  `sort_num` int(11) DEFAULT NULL COMMENT '课程排序，用于后台保存草稿时用到',
  `preview_first_field` varchar(255) DEFAULT NULL COMMENT '课程预览第一个字段',
  `preview_second_field` varchar(255) DEFAULT NULL COMMENT '课程预览第二个字段',
  `sales` int(11) DEFAULT '0' COMMENT '销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`course_name`,`brief`,`price`,`price_tag`,`discounts`,`discounts_tag`,`course_description_mark_down`,`course_description`,`course_img_url`,`is_new`,`is_new_des`,`last_operator_id`,`auto_online_time`,`create_time`,`update_time`,`is_del`,`total_duration`,`course_list_img`,`status`,`sort_num`,`preview_first_field`,`preview_second_field`,`sales`) values 
(7,'文案高手的18项修炼','手把手教你写出实用的高转化文案',263.00,'',100.00,'成就自己','<p>背景介绍<br>\n自媒体时代，无论你是做新媒体编辑、运营，还是市场营销、电商，微信推文、推广海报、产品详情页、朋友圈话术……文案需求无处不在。</p>\n<p>写出价值百万的文案，其实并不难，因为它们背后都有可总结、可借鉴的规律。只要通过系统学习和刻意训练，你也能创造出爆款文案，掌握可复用的硬通货技能，轻松获得职场晋升，或者开拓副业，实现财务自由！</p>\n<p>专栏解读<br>\n这是一个零基础就能学会的爆款文案写作与变现路径，专栏共分为三大模块：入门基础篇、高手实战篇、进阶拓展篇。从入门写作到如何变现，让你成为赚钱达人。</p>\n<p>第一部分：掌握写作基础，夯实文字功底。这一模块会带你如何深入了解产品、洞悉用户需求，告诉你如何搭建文章框架，积累文字素材，做好动笔前的准备工作，只有掌握了这些，你对文案才有更深刻的理解。</p>\n<p>第二部分：爆款文案写作法，让你成为文案操盘手。这一模块为你拆解爆款文案的组成，手把手教你写作的5个步骤，从爆款标题的写作，到让用户爽快下单的技巧，从文字构建信任，到文案促成下单，这些技巧足以让你成为爆款文案操盘手。</p>\n<p>第三部分：如何让你的文案变成钱？带你搞定文案写作之后，为你提供4大变现路径，让你用文案打造个人影响力，并找到利用文案轻松赚钱的方法。</p>\n<p>18讲的内容，每一讲都汇集了爆款文案写作经验和实战技巧，每一讲都是经过验证的经验复用，每个文字都来自文案人深夜的凝思和血泪。</p>\n','<p>背景介绍<br> 自媒体时代，无论你是做新媒体编辑、运营，还是市场营销、电商，微信推文、推广海报、产品详情页、朋友圈话术……文案需求无处不在。</p> \n<p>写出价值百万的文案，其实并不难，因为它们背后都有可总结、可借鉴的规律。只要通过系统学习和刻意训练，你也能创造出爆款文案，掌握可复用的硬通货技能，轻松获得职场晋升，或者开拓副业，实现财务自由！</p> \n<p>专栏解读<br> 这是一个零基础就能学会的爆款文案写作与变现路径，专栏共分为三大模块：入门基础篇、高手实战篇、进阶拓展篇。从入门写作到如何变现，让你成为赚钱达人。</p> \n<p>第一部分：掌握写作基础，夯实文字功底。这一模块会带你如何深入了解产品、洞悉用户需求，告诉你如何搭建文章框架，积累文字素材，做好动笔前的准备工作，只有掌握了这些，你对文案才有更深刻的理解。</p> \n<p>第二部分：爆款文案写作法，让你成为文案操盘手。这一模块为你拆解爆款文案的组成，手把手教你写作的5个步骤，从爆款标题的写作，到让用户爽快下单的技巧，从文字构建信任，到文案促成下单，这些技巧足以让你成为爆款文案操盘手。</p> \n<p>第三部分：如何让你的文案变成钱？带你搞定文案写作之后，为你提供4大变现路径，让你用文案打造个人影响力，并找到利用文案轻松赚钱的方法。</p> \n<p>18讲的内容，每一讲都汇集了爆款文案写作经验和实战技巧，每一讲都是经过验证的经验复用，每个文字都来自文案人深夜的凝思和血泪。</p>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/10/15943482627237468.jpg',NULL,NULL,NULL,NULL,'2020-07-10 10:33:56','2020-07-10 10:45:38',0,NULL,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/10/1594348262748358.jpg',1,1,'100讲','50课时',1314),
(8,'Vue.js 3.0 核心源码解析123','掌握框架原理，精通经典应用场景',99.00,'',88.00,'','<p>背景介绍<br>\n在过去的几年里，Vue、React、Angular 一直是国内前端的三大主流框架。在 2019 年 Vue 就像一匹黑马力压群雄，独占前端er 的宠爱，Github Star 排名荣登第一也反映了国内开发者对 Vue 的喜爱。</p>\n<p>最近，Vue 的作者尤雨溪在 Github 上介绍了 Vue 3.0 的最新进展，目标发布日期是 7 月中旬发布 RC 版本，8 月初发布正式版，也就是说 Vue 3.0 就在眼前！</p>\n<p>专栏解读<br>\n专栏将对 Vue.js 3.0 的框架源码进行系统、细致地分析。 深入到内核剖析实现原理，探究源码背后的设计思想。一方面帮你夯实 Vue.js 开发技能，一方面帮你理解源码分析的方式与编程思路，为你从底层逻辑理解优秀框架背后的技术思想。</p>\n<p>专栏主要分为以下3个部分：</p>\n<p>第一部分核心模块，这个部分会带你去分析 Vue.js 3.0 最核心的组件的实现原理以及 Vue.js 3.0 新特性 Composition API 的实现原理。掌握这个部分，可以让你对组件如何渲染和更新能有深刻的理解，并掌握 Composition API 背后的实现原理和应用场景。</p>\n<p>第二部分进阶模块，这个部分会带你分析 Vue.js 3.0 模板的编译和优化过程。带你了解 Vue.js 是如何编译模板并生成代码的，以及编译过程背后的性能优化思想是怎样的。</p>\n<p>第三部分扩展模块，将带你分析 Vue.js 3.0 的内置组件的实现原理、Vue.js 3.0 一些实用特性的实现原理以及 Vue.js 3.0 官方生态实现原理。经过学习，你可以了解这些功能的实现原理和职责边界，在平时工作中应用起来更加得心应手。</p>\n<p>在学习这门课程后，不仅可以了解 Vue.js 3.0 核心实现原理，还能一定程度地了解背后的设计思想。源码中一些好的编程思路和优秀的代码可以吸收来为自己的工作所用，修炼内功，提升技术能力。</p>\n<p>讲师简介<br>\n黄轶（ustbhuangyi） Zoom 前端架构师，前百度、滴滴资深技术专家</p>\n<p>现任 Zoom 前端架构师，主要负责推进前后端分离架构方案和 Zoom 自研组件库，不仅将Vue.js 带入 Zoom，而且通过魔改 Vue.js 源码开发了 Vue.js 2.x 的 CSP 兼容版本，并稳定服务于几十个用 Vue.js 做增强开发的页面。之前，他先后在百度和滴滴担任前端资深技术专家，并曾使用 Vue.js 重构了滴滴出行WebApp，主导开发 Vue.js 开源组件库 cube-ui。</p>\n<p>课程大纲</p>\n','<p>背景介绍<br> 在过去的几年里，Vue、React、Angular 一直是国内前端的三大主流框架。在 2019 年 Vue 就像一匹黑马力压群雄，独占前端er 的宠爱，Github Star 排名荣登第一也反映了国内开发者对 Vue 的喜爱。</p> \n<p>最近，Vue 的作者尤雨溪在 Github 上介绍了 Vue 3.0 的最新进展，目标发布日期是 7 月中旬发布 RC 版本，8 月初发布正式版，也就是说 Vue 3.0 就在眼前！</p> \n<p>专栏解读<br> 专栏将对 Vue.js 3.0 的框架源码进行系统、细致地分析。 深入到内核剖析实现原理，探究源码背后的设计思想。一方面帮你夯实 Vue.js 开发技能，一方面帮你理解源码分析的方式与编程思路，为你从底层逻辑理解优秀框架背后的技术思想。</p> \n<p>专栏主要分为以下3个部分：</p> \n<p>第一部分核心模块，这个部分会带你去分析 Vue.js 3.0 最核心的组件的实现原理以及 Vue.js 3.0 新特性 Composition API 的实现原理。掌握这个部分，可以让你对组件如何渲染和更新能有深刻的理解，并掌握 Composition API 背后的实现原理和应用场景。</p> \n<p>第二部分进阶模块，这个部分会带你分析 Vue.js 3.0 模板的编译和优化过程。带你了解 Vue.js 是如何编译模板并生成代码的，以及编译过程背后的性能优化思想是怎样的。</p> \n<p>第三部分扩展模块，将带你分析 Vue.js 3.0 的内置组件的实现原理、Vue.js 3.0 一些实用特性的实现原理以及 Vue.js 3.0 官方生态实现原理。经过学习，你可以了解这些功能的实现原理和职责边界，在平时工作中应用起来更加得心应手。</p> \n<p>在学习这门课程后，不仅可以了解 Vue.js 3.0 核心实现原理，还能一定程度地了解背后的设计思想。源码中一些好的编程思路和优秀的代码可以吸收来为自己的工作所用，修炼内功，提升技术能力。</p> \n<p>讲师简介<br> 黄轶（ustbhuangyi） Zoom 前端架构师，前百度、滴滴资深技术专家</p> \n<p>现任 Zoom 前端架构师，主要负责推进前后端分离架构方案和 Zoom 自研组件库，不仅将Vue.js 带入 Zoom，而且通过魔改 Vue.js 源码开发了 Vue.js 2.x 的 CSP 兼容版本，并稳定服务于几十个用 Vue.js 做增强开发的页面。之前，他先后在百度和滴滴担任前端资深技术专家，并曾使用 Vue.js 重构了滴滴出行WebApp，主导开发 Vue.js 开源组件库 cube-ui。</p> \n<p>课程大纲</p>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/17/15949659206804677.png',NULL,NULL,NULL,NULL,'2020-07-10 11:20:43','2020-08-25 10:22:07',0,NULL,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/10/15943511296066408.png',0,7,'共22节','更新10节',100),
(9,'秒杀11','秒杀11',200.00,'',100.00,'11','<p>11111</p>\n','<p>11111</p>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/10/15943514165219908.jpg',NULL,NULL,NULL,NULL,'2020-07-10 11:24:31','2020-07-29 10:55:31',0,NULL,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/10/15943514200926156.jpg',1,1,'秒杀','秒杀',10),
(10,'React 入门','React 入门',164.00,'',100.00,'','<p>React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门</p>\n','<p>React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门React 入门</p>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/17/15949603348287878.png',NULL,NULL,NULL,NULL,'2020-07-17 12:32:43','2020-07-28 16:12:40',0,NULL,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/17/15949603393915818.png',0,12,'React 入门','React 入门',2323),
(11,'大数据','大数据',199.00,'',100.00,'大数据','<p>大数据大数据大数据大数据</p>\n','<p>大数据大数据大数据大数据</p>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/29/15959915143054811.jpg',NULL,NULL,NULL,NULL,'2020-07-29 10:59:29','2020-08-21 11:35:15',0,NULL,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/07/29/15959915238525570.jpg',0,20,'大数据','大数据',99),
(12,'111222','111',1.00,'',11.00,'111','<p>11</p>\n','<p>11</p>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/08/13/15973037458842466.JPG',NULL,NULL,NULL,NULL,'2020-08-13 15:29:30','2020-08-13 15:30:40',0,NULL,'https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/08/13/15973037593381230.JPG',1,1,'111','',11),
(13,'111111','111111',NULL,'',11111111.00,'','<ul>\n<li>111111</li>\n</ul>\n','<ul> \n <li>111111</li> \n</ul>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/08/21/15979790272263280.jpg',NULL,NULL,NULL,NULL,'2020-08-21 11:04:35','2020-09-17 20:55:32',0,NULL,'',1,111111,'111111','',0),
(14,'测试新增课程','测试新增课程',NULL,'',199.00,'','<p>测试新增课程</p>\n','<p>测试新增课程</p>','https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/08/21/15979806709269738.jpg',NULL,NULL,NULL,NULL,'2020-08-21 11:32:45','2020-08-21 11:32:45',0,NULL,'',0,1,'测试新增课程','',0);

/*Table structure for table `course_lesson` */

DROP TABLE IF EXISTS `course_lesson`;

CREATE TABLE `course_lesson` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `section_id` int(11) NOT NULL DEFAULT '0' COMMENT '章节id',
  `theme` varchar(255) NOT NULL COMMENT '课时主题',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '课时时长(分钟)',
  `is_free` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否免费',
  `create_time` datetime NOT NULL COMMENT '记录创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `order_num` int(11) DEFAULT NULL COMMENT '排序字段',
  `status` int(2) DEFAULT '0' COMMENT '课时状态,0-隐藏，1-未发布，2-已发布',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_id_index` (`course_id`,`section_id`) USING BTREE,
  KEY `idx_sectionId_orderNum` (`section_id`,`order_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='课程节内容';

/*Data for the table `course_lesson` */

insert  into `course_lesson`(`id`,`course_id`,`section_id`,`theme`,`duration`,`is_free`,`create_time`,`update_time`,`is_del`,`order_num`,`status`) values 
(8,7,7,'从小白到文案高手',0,1,'2020-07-10 10:35:30','2020-07-27 14:26:47',0,1,2),
(9,7,7,'手把手教你写出爆款文案',0,0,'2020-07-10 10:35:53','2020-07-27 14:26:47',0,0,2),
(10,7,8,'重点内容',0,0,'2020-07-10 10:36:09','2020-07-27 14:26:52',0,1,2),
(11,7,8,'内容总结',0,0,'2020-07-10 10:36:21','2020-07-27 14:26:52',0,0,2),
(12,8,9,'开篇词 | 解析 Vue.js 源码，提升编码能力',0,1,'2020-07-10 11:21:49','2020-07-22 12:10:49',0,0,2),
(13,8,9,'导读 | 一文看懂 Vue.js 3.0 的优化',0,0,'2020-07-10 11:22:13','2020-07-22 12:10:49',0,1,2),
(14,9,10,'11111',0,0,'2020-07-10 11:30:47','2020-07-13 15:54:52',0,0,2),
(15,10,11,'撒短发',0,1,'2020-07-17 12:33:20','2020-07-17 12:35:23',0,22,2),
(16,11,12,'第一讲 Hadoop介绍',0,0,'2020-08-04 10:50:02','2020-08-04 10:50:02',0,0,0),
(17,11,12,'第二讲 Hadoop生态介绍',0,0,'2020-08-04 10:50:26','2020-08-04 10:50:26',0,1,0),
(18,11,13,'课时1',0,0,'2020-08-04 11:43:15','2020-08-04 11:43:15',0,0,0),
(19,11,13,'1111',0,0,'2020-08-21 11:10:08','2020-08-21 11:10:08',0,0,0),
(20,11,12,'dsf',0,0,'2020-08-21 11:40:50','2020-08-21 11:40:50',0,0,0),
(21,8,16,'组件学习',0,0,'2020-08-25 11:35:46','2020-08-25 11:35:46',0,0,0),
(22,8,19,'第一课时',0,0,'2020-08-25 18:19:51','2020-08-25 18:19:51',0,0,0),
(23,13,21,'1231',7,1,'2020-09-15 15:56:32','2020-09-15 22:17:36',0,0,2),
(24,13,22,'121',0,0,'2020-09-15 16:15:12','2020-09-15 16:15:12',0,0,0),
(25,13,22,'121',0,0,'2020-09-15 16:16:24','2020-09-15 16:16:24',0,0,0),
(26,13,24,'课时名称',0,0,'2020-09-17 10:45:18','2020-09-17 10:45:18',0,0,0);

/*Table structure for table `course_media` */

DROP TABLE IF EXISTS `course_media`;

CREATE TABLE `course_media` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程媒体主键ID',
  `course_id` int(11) DEFAULT NULL COMMENT '课程Id',
  `section_id` int(11) DEFAULT NULL COMMENT '章ID',
  `lesson_id` int(11) DEFAULT NULL COMMENT '课时ID',
  `cover_image_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '封面图URL',
  `duration` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '时长（06:02）',
  `file_edk` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '媒体资源文件对应的EDK',
  `file_size` double(10,2) DEFAULT NULL COMMENT '文件大小MB',
  `file_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名称',
  `file_dk` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '媒体资源文件对应的DK',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除，0未删除，1删除',
  `duration_num` int(11) DEFAULT NULL COMMENT '时长，秒数（主要用于音频在H5控件中使用）',
  `file_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '媒体资源文件ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_lessonid_channel_mediatype_idx` (`lesson_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `course_media` */

insert  into `course_media`(`id`,`course_id`,`section_id`,`lesson_id`,`cover_image_url`,`duration`,`file_edk`,`file_size`,`file_name`,`file_dk`,`create_time`,`update_time`,`is_del`,`duration_num`,`file_id`) values 
(5,7,7,8,'http://outin-61fd129aa62411eaa4ef00163e1c94a4.oss-cn-shanghai.aliyuncs.com/image/cover/7FDABE200A424897A7ED5CE05764BB4C-6-2.png?Expires=1594363749&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=dqBQJCvfJY1wDCBgCGnI5cPInww%3D','00:11','YTU5ZGFkM2ItOGI0Mi00NWFmLWJiMGItMTI4YWRjNjQzOWFlRjdaVUlmYTUwYWkwR3pwK1YzZENnUjExMnI1VkxiVFhBQUFBQUFBQUFBQ3FJN3pnZnFQUndad24rT2djTmM4MkxXb3ZXcDNNNkk0RENXeW9NRDVrZFNucHFiaUxRNm4r',1.90,'屏幕录制2020-07-10 13.48.08.mov','JPagaxcX6Ihpn5nu+dLi0Q==','2020-07-10 11:14:51','2020-07-10 13:49:14',0,11,'4f0dfb878a4d4d4881428b950396228a'),
(6,7,8,10,'http://outin-61fd129aa62411eaa4ef00163e1c94a4.oss-cn-shanghai.aliyuncs.com/image/cover/DBA8C56E2EA44F6382DCF5B7AD5763E6-6-2.png?Expires=1594356125&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=s5jKXcyQzw%2BjJKah75oBobYEy3g%3D','00:08','YTU5ZGFkM2ItOGI0Mi00NWFmLWJiMGItMTI4YWRjNjQzOWFlNDlJL3pGM0dvS1IzSFhxMkFzNnYwcnhBelB6bUU3NENBQUFBQUFBQUFBQkxYTzQ4Q2JyK05XTHRoKzZiWmpoZG55azJ5NFVYUWtIRUhWb1BuQ3FZd2FPTVhPM2d6UDJ4',0.64,'测试视频课程.mp4','T3q/VVunsKwx7aNeRtaLGg==','2020-07-10 11:17:38','2020-07-10 11:42:10',0,8,'8dbf98ac948a4b389f99f17034c998cd'),
(7,8,9,12,'http://outin-61fd129aa62411eaa4ef00163e1c94a4.oss-cn-shanghai.aliyuncs.com/image/cover/07ED3325C001418AA46A95F88B1DA6C8-6-2.png?Expires=1594969610&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=biyZXbn1V2gY4GxmPiXKBME3E7I%3D','00:05','YTU5ZGFkM2ItOGI0Mi00NWFmLWJiMGItMTI4YWRjNjQzOWFlaWxjVHdDaW5NSDJGTWdqZnYxWFg1YWZ6OUJXc0cvS3FBQUFBQUFBQUFBQzVwV2NTQUh4NjhiaU1YZ1drYlFZQnlVKyt0YkdTRzdlVXJmRFVOZHRESWM3T3ZSdEwwM1hN',11.37,'test.mov','BRRHQV/DivcEwf0Zosn9dA==','2020-07-10 11:40:29','2020-07-17 14:06:55',0,5,'a029c496959a457e92eb9e4f480e0018'),
(8,7,8,11,'http://outin-61fd129aa62411eaa4ef00163e1c94a4.oss-cn-shanghai.aliyuncs.com/image/cover/8D2E8BB920F94ECAB32CCAA73222DF32-6-2.png?Expires=1594625842&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=H2rqXRF0a%2FjY1dnrVtACXinwBRI%3D','00:08','YTU5ZGFkM2ItOGI0Mi00NWFmLWJiMGItMTI4YWRjNjQzOWFlQnBqQnBlU042WEd4UWx2eVEwV1hZS2JQdE5PS1dBYVpBQUFBQUFBQUFBQ0ViRHkrd0U1WWVLRGhObFJGOFE5OGJMYlppRzNwdnVWc2xuUnpINnVhN3dkay9QMDlucXhR',0.64,'测试视频课程.mp4','MWGs74Qco6LYjlCXc7ymEg==','2020-07-10 11:43:10','2020-07-13 14:37:27',0,8,'bb7d23a9334c4b07abb1187a2c61ce1f'),
(9,8,9,13,'http://outin-61fd129aa62411eaa4ef00163e1c94a4.oss-cn-shanghai.aliyuncs.com/image/cover/1B8791F4BC4B48B08A37C2B7B669FE52-6-2.png?Expires=1594356307&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=QISH8TpvWX%2F0G%2B%2F1rA0OJqTug3g%3D','00:08','YTU5ZGFkM2ItOGI0Mi00NWFmLWJiMGItMTI4YWRjNjQzOWFlSHk2RWhDUURHejlyYzN1cnNNaE9ubG9jVUZlb2NTbFFBQUFBQUFBQUFBQjRRV0lGOHRvUGxIcCtINGhTdWxBR3JWV1ZmR3ZDT3R2YnVJcDlkS0NjSWxoL1EwMmpHcVdK',0.64,'测试视频课程.mp4','B0CqECTjmT1JeCVndYvXtg==','2020-07-10 11:45:13','2020-07-10 11:45:13',0,8,'717bc01edbb24283937d7fa903f06d60'),
(10,9,10,14,'http://outin-61fd129aa62411eaa4ef00163e1c94a4.oss-cn-shanghai.aliyuncs.com/image/cover/EEB28294E90F4D85A2609A2A978A9264-6-2.png?Expires=1594356669&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=wR9JpRJp6UMBs%2B55QSWSFYBM%2FCs%3D','00:08','YTU5ZGFkM2ItOGI0Mi00NWFmLWJiMGItMTI4YWRjNjQzOWFlajlJcXU0RGYyc0V2blZ5c0pXMGVDaUdNT0ZVZlNYODVBQUFBQUFBQUFBQjFKWCtoZGU4WDZPTXM3RnZmanFXTWxzVVh0Uy80OEt6U2ttY1h1TDJURlJ6dlJ3V2ZpcjFT',0.64,'测试视频课程.mp4','6I5a1R2dG/WrUCQotk6Uxw==','2020-07-10 11:51:14','2020-07-10 11:51:14',0,8,'451d48ec785646b8ab1aa97ae3741100'),
(11,13,21,23,'http://outin-61fd129aa62411eaa4ef00163e1c94a4.oss-cn-shanghai.aliyuncs.com/image/cover/E0FE9E6B1B1846B98538A92DCE6673F9-6-2.png?Expires=1600343092&OSSAccessKeyId=LTAIVVfYx6D0HeL2&Signature=IWQUDdMZ1tSEUP%2FSRiP2uLo5uO0%3D','07:43','YTU5ZGFkM2ItOGI0Mi00NWFmLWJiMGItMTI4YWRjNjQzOWFlUjZ3MkFRd0w0bml2TEZYUHQyeFNIVE1wWmNUdm12RWZBQUFBQUFBQUFBQUlSdnhZbjAzM2dLdGtWYUxhVGRyY1BVWjhIMVFET1QwN0crOUU5WE56UEU3czFIVGx3cytT',19.98,'01_系统开发流程说明.mp4','Pgg1pgh00tpl7iTs8WEbtQ==','2020-09-17 18:44:47','2020-09-17 18:44:47',0,463,'dd1cc2e4175340efa08a692dd04f0825');

/*Table structure for table `course_play_history` */

DROP TABLE IF EXISTS `course_play_history`;

CREATE TABLE `course_play_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `section_id` int(11) NOT NULL COMMENT '章节id',
  `lesson_id` int(11) NOT NULL COMMENT '课时id',
  `history_node` int(11) NOT NULL COMMENT '历史播放节点(s)',
  `history_highest_node` int(11) DEFAULT '0' COMMENT '最高历史播放节点',
  `create_time` datetime NOT NULL COMMENT '记录创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_userid_lessonid_type_idx` (`user_id`,`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Data for the table `course_play_history` */

insert  into `course_play_history`(`id`,`user_id`,`course_id`,`section_id`,`lesson_id`,`history_node`,`history_highest_node`,`create_time`,`update_time`,`is_del`) values 
(35,1,7,7,8,12,12,'2020-07-10 10:51:09','2020-07-13 17:07:00',0),
(36,100030011,7,7,8,11,11,'2020-07-10 11:15:07','2020-07-13 15:34:23',0),
(37,100030011,7,8,10,0,8,'2020-07-10 11:19:09','2020-07-17 12:29:57',0),
(38,100030011,8,9,12,8,8,'2020-07-10 11:41:00','2020-07-10 11:47:58',0),
(39,100030011,7,8,11,0,1,'2020-07-10 11:43:44','2020-07-10 14:05:27',0),
(40,100030011,8,9,13,0,0,'2020-07-10 11:46:04','2020-07-10 11:51:21',0),
(41,100030011,9,10,14,0,0,'2020-07-10 11:51:59','2020-07-17 12:33:43',0),
(42,100030018,7,8,11,0,0,'2020-07-10 12:18:19','2020-07-10 12:28:58',0),
(43,100030019,7,7,8,11,12,'2020-07-10 12:23:50','2020-07-17 16:37:04',0),
(44,100030019,7,8,11,3,9,'2020-07-10 12:24:17','2020-07-17 16:36:51',0),
(45,1,7,8,11,0,0,'2020-07-10 12:57:43','2020-07-10 13:30:06',0),
(46,100030019,7,7,9,0,5,'2020-07-10 14:05:36','2020-07-17 16:36:32',0),
(47,100030019,8,9,12,8,8,'2020-07-10 14:56:56','2020-07-10 14:59:01',0),
(48,100030011,7,7,9,0,0,'2020-07-10 17:08:14','2020-07-13 10:08:12',0),
(49,100030021,9,10,14,0,0,'2020-07-15 21:03:32','2020-07-15 21:03:40',0),
(50,100030019,7,8,10,8,8,'2020-07-17 16:36:36','2020-07-17 16:36:46',0),
(55,-1,7,7,8,11,11,'2020-09-18 21:07:59','2020-09-27 18:31:24',0);

/*Table structure for table `course_section` */

DROP TABLE IF EXISTS `course_section`;

CREATE TABLE `course_section` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `section_name` varchar(255) NOT NULL DEFAULT '' COMMENT '章节名',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '章节描述',
  `create_time` datetime NOT NULL COMMENT '记录创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_de` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `order_num` int(11) DEFAULT NULL COMMENT '排序字段',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0:隐藏；1：待更新；2：已发布',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_id_index` (`course_id`) USING BTREE,
  KEY `idx_course_id` (`course_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `course_section` */

insert  into `course_section`(`id`,`course_id`,`section_name`,`description`,`create_time`,`update_time`,`is_de`,`order_num`,`status`) values 
(7,7,'开篇词 | 从小白到文案高手，手把手教你写出爆款文案','你好，我是兔妈！第一次见面，我用几句话简单介绍下自己','2020-07-10 10:34:47','2020-07-10 10:44:50',0,1,2),
(8,7,'重点内容总结','重点内容总结','2020-07-10 10:35:05','2020-07-10 17:08:57',0,2,2),
(9,8,'开篇词','Vue的简单介绍','2020-07-10 11:21:35','2020-08-25 17:53:00',0,1,0),
(10,9,'1111','1111','2020-07-10 11:30:38','2020-07-10 11:51:46',0,1111,2),
(11,10,'第一章','第一章','2020-07-17 12:33:00','2020-07-17 12:33:07',0,12,2),
(12,11,'第一章 Hadoop','第一章 Hadoop','2020-08-04 10:49:28','2020-08-21 11:39:48',0,1,2),
(13,11,'阶段测试','测试。。。','2020-08-04 11:42:32','2020-08-04 11:42:32',0,2,0),
(14,11,'第二章','第二章','2020-08-21 11:08:29','2020-08-21 11:08:29',0,3,0),
(15,11,'第三章','第三章','2020-08-21 11:38:12','2020-08-21 11:38:12',0,4,0),
(16,8,'第一阶段','第一阶段','2020-08-25 11:35:29','2020-08-25 17:29:34',0,2,2),
(17,8,'第二阶段','第二阶段','2020-08-25 15:17:28','2020-08-25 17:28:56',0,2,1),
(18,8,'第三阶段','第三阶段','2020-08-25 15:39:57','2020-08-25 15:39:57',0,3,0),
(19,8,'第四阶段','第四阶段','2020-08-25 15:40:30','2020-08-25 18:04:24',0,4,0),
(20,8,'sdfds','sdfds','2020-08-25 17:11:21','2020-08-25 17:28:51',0,5,1),
(21,13,'第一阶段','第一阶段','2020-09-15 11:36:47','2020-09-15 22:17:20',0,11,0),
(22,13,'第二阶段','1111','2020-09-15 16:13:25','2020-09-15 16:13:25',0,11,0),
(23,13,'第三阶段','222','2020-09-15 16:14:10','2020-09-15 16:17:29',0,34,0),
(24,13,'第四阶段','第四阶段','2020-09-15 16:14:31','2020-09-15 16:17:38',0,4444,0);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `course_id` int(11) DEFAULT '0' COMMENT '课程ID',
  `teacher_name` varchar(255) DEFAULT NULL COMMENT '讲师姓名',
  `position` varchar(100) DEFAULT '' COMMENT '职务',
  `description` text COMMENT '讲师介绍',
  `create_time` datetime NOT NULL COMMENT '记录创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_courseId` (`course_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`course_id`,`teacher_name`,`position`,`description`,`create_time`,`update_time`,`is_del`) values 
(158,7,'兔妈','有赞高级讲师','多家平台的头部商家文案顾问，有赞高级讲师，具有 8 年文案营销实战经验','2020-07-10 10:33:56','2020-07-10 10:33:56',0),
(159,8,'那朋朋123','前京东资深','掌握框架原理，精通经典应用场景','2020-07-10 11:20:43','2020-08-24 20:27:45',0),
(160,9,'秒杀11','秒杀','秒杀1','2020-07-10 11:24:31','2020-07-10 11:24:31',0),
(161,10,'React 入门','React 入门','React 入门','2020-07-17 12:32:43','2020-07-17 12:32:43',0),
(162,11,'大数据','大数据','大数据','2020-07-29 10:59:29','2020-07-29 10:59:29',0),
(163,12,'1111','111','111','2020-08-13 15:29:30','2020-08-13 15:30:15',0),
(164,13,'111111','111111','111111','2020-08-21 11:04:35','2020-09-17 20:55:32',0),
(165,14,'测试新增课程','测试新增课程','测试新增课程','2020-08-21 11:32:45','2020-08-21 11:32:45',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
