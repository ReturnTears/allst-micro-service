DROP TABLE IF EXISTS `promotion_space`;
CREATE TABLE `promotion_space`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) DEFAULT NULL COMMENT '名称',
    `spaceKey`   varchar(255) DEFAULT NULL COMMENT '广告位key',
    `createTime` datetime     DEFAULT NULL,
    `updateTime` datetime     DEFAULT NULL,
    `isDel`      int(2)       DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `promotion_space_key_isDel` (`spaceKey`, `isDel`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `promotion_ad`;
CREATE TABLE `promotion_ad`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255)     DEFAULT NULL COMMENT '广告名',
    `spaceId`     int(11)          DEFAULT NULL COMMENT '广告位id',
    `keyword`     varchar(255)     DEFAULT NULL COMMENT '精确搜索关键词',
    `htmlContent` text COMMENT '静态广告的内容',
    `text`        varchar(255)     DEFAULT NULL COMMENT '文字一',
    `link`        varchar(255)     DEFAULT NULL COMMENT '链接一',
    `startTime`   datetime         DEFAULT NULL COMMENT '开始时间',
    `endTime`     datetime         DEFAULT NULL COMMENT '结束时间',
    `createTime`  datetime         DEFAULT NULL,
    `updateTime`  datetime         DEFAULT NULL,
    `status`      int(2)  NOT NULL DEFAULT '0',
    `priority`    int(4)           DEFAULT '0' COMMENT '优先级',
    `img`         varchar(255)     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY `promotion_ad_SEG` (`spaceId`, `startTime`, `endTime`, `status`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;