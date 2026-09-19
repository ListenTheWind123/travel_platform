SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attractions
-- ----------------------------
DROP TABLE IF EXISTS `attractions`;
CREATE TABLE `attractions`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attractions_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attractions_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attractions_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attractions_status` int(2) NULL DEFAULT 0,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attractions
-- ----------------------------
INSERT INTO `attractions` VALUES ('01', 'MY_jingdian_01', '重庆邮电大学', '重庆市南岸区南山街道崇文路2号', '重庆邮电大学（CQUPT）为信息化部与重庆市人民政府共建的教学研究型大学，国家布点设立并重点建设的邮电高校之一，占地3800亩，在校学生3万余人，植被茂盛，校园风景优美。', 0, '2024-12-27 20:00:09');
INSERT INTO `attractions` VALUES ('08', 'MY_jingdian_08', '老君洞', '重庆市南岸区南山街道崇文路真武山13号', '洪崖洞的夜景固然迷人，但重庆的南山，更是一个隐藏的宝藏。探索南山，怎能错过老君洞？这座古老的道观，隐藏在南山的绿意之中，是道家文化的重要传承地。在这里，你可以感受到一种超脱尘世的宁静和神秘。依山造殿，凿壁成像，呈“玄”字形格局。建有东、西大门和南天门。东大门晨曦直射山门，意味“紫气东来”；西大门是蕞早的山门；南天门是登玉皇楼必经之路。\r\n', 0, '2024-12-27 06:00:54');
INSERT INTO `attractions` VALUES ('04', 'MY_jingdian_04', '三毛故居', '重庆南岸区黄桷垭145号', '走进三毛的故居，感受这位文学巨匠的生活气息。故居内保存了三毛曾经使用过的书桌、椅子等遗物，以及她的部分著作。这里的每一砖一瓦，都充满了故事，让人不禁沉浸在她的文学世界中。\r\n', 0, '2024-12-27 08:07:59');
INSERT INTO `attractions` VALUES ('06', 'MY_jingdian_06', '南山植物园', '重庆市南岸区南山植物园路101号', '重庆市南山植物园是国家AAAA级景区、重庆市十佳旅游景区、“国家重点公园”、重庆市青少年植物科普教育基地、重庆市南山南泉风景名胜区核心景区、重庆市“十大社会文化基础设施工程”和“八大民心工程”之一。\r\n', 0, '2024-12-27 21:00:09');
INSERT INTO `attractions` VALUES ('03', 'MY_jingdian_03', '黄桷垭老街', '重庆市南岸区南山街道重庆邮电大学对面', '老街妥善留存着175栋风格独特的巴渝建筑，展现着老街的旧时风貌。每当夜晚灯光亮起，老街便如一条璀璨的彩带，镶嵌于山林之中。而且是商业气息不重的一条老街，老街上茶馆比较多，很多手工小店和特产零食小铺，含绿量非常高！非常出片！可以原图直出！\r\n', 0, '2024-12-27 06:02:52');
-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_status` int(2) NULL DEFAULT 0,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('02', 'MY_kezhan_02', '重庆邮电大学宾馆', '重庆邮电大学宾馆,重庆市南岸区南山街道崇文路2号', '酒店功能完善，设有包括豪华商务套房等各色客房若干，酒店拥有中餐厅、西餐厅多个不同风味的餐饮场所，就餐总座位数520余位，另设有可接待1314人的宴会厅，可为宾客提供各式全新五星水准的酒店住宿、饮食、宴会、会议一体化的服务。另有学校好多个车位的大型停车场，为客人的出行提供了更多的便利。', 0, '2024-12-26 19:36:37');
INSERT INTO `hotel` VALUES ('01', 'MY_kezhan_01', '榕熙智慧酒店', '重庆市南岸区南山街道崇文路8号', '酒店以为旅客提供提供经济便捷、温馨的住宿环境为经营理念，女掌柜在外企任职10余年，热爱旅行，旅居多地，途中非常喜欢青派，遂打造此店为旅途中的朋友提供经济便捷、温馨的栖地。酒店翻新，以高品质的硬件设施、温馨细致的服务以及现代化装修设计共同打造温馨的住宿环境，以满足您的住宿需求。', 0, '2024-12-26 23:29:04');
INSERT INTO `hotel` VALUES ('03', 'MY_kezhan_03', '林间·幽篁民宿酒店', '重庆市南岸区凉风村洪家坡社区S103', '这是一家2023年6月重新升级打造的一家专门针对年轻人，很新的一家民宿，它新在那里呀，下午可以围炉煮茶，晚上可以坐在森林里吃着烤肉，吹着晚风，当然如果你不想吃烤肉，他还有烧烤，还有中餐，还有土货。娱乐可以去练歌房，放生高歌，当然你也可以约上朋友打台球比比高低，ps、桌游都有，全部都有。也可以当一个专一的钓鱼手，在大大小小的鱼塘里，当一个自由的灵魂钓手吧。全是山景房，有儿童房，亲子房，圆床房，投影房。只有你想不到的，没有它不存在的。', 0, '2024-12-27 14:17:38');
INSERT INTO `hotel` VALUES ('04', 'MY_kezhan_04', '邮寄落日酒店', '崇文路中国工商银行隔壁', '该店位于黄桷垭公交车站旁，交通便利，周围有多个景点，客房设施齐全，干净舒适，让您在旅途中有一个温馨的家。', 0, '2024-12-27 07:20:37');
INSERT INTO `hotel` VALUES ('06', 'MY_kezhan_06', '重庆南山宾馆', '重庆南岸区黄桷垭文峰段94号', '重庆南山宾馆隶为国家 AA风景区，系匠心打造的商务休闲旅游型酒店。宾馆位于南山风景区，群山环抱，绿树成荫，毗邻闻名遐迩的文峰塔、黄葛古道，海拔 500余米，占地 168亩，森林覆盖率达 90%以上，是名副其实的天然氧吧。距解放碑约20分钟车程，交通便捷，地理位置优越。宾馆装修以民国时期古朴风为主基调，怀旧装饰点缀，格调典雅温馨，环境静怡舒适。', 0, '2024-12-27 16:19:33');
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for travel_route
-- ----------------------------
DROP TABLE IF EXISTS `travel_route`;
CREATE TABLE `travel_route`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `route_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `route_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `route_status` int(2) NOT NULL DEFAULT 0,
  `route_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `collect_number` int(32) NOT NULL DEFAULT 0,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of travel_route
-- ----------------------------
INSERT INTO `travel_route` VALUES ('001', '重庆南山特种兵旅游路线', '老君洞→黄桷桠老街→三毛故居→黄葛古道→敦厚坡老街→开埠遗址公园→龙门浩老街→下浩里老街——>来重庆不要只去解放碑、洪崖洞啦，南山也有超多宝藏打卡地~~~', 0, '重庆南山', 0, '2024-12-28 10:43:40', NULL);
INSERT INTO `travel_route` VALUES ('002', '重庆南山绿色吸氧路线', '黄桷垭老街→三毛故居咖啡馆 →老君洞→黄葛古道（全程下坡）——>Tips：1、建议穿运动鞋或平底鞋比较好走路的鞋子，不累脚！2、山里蚊虫较多，易招蚊子体质可以带点花露水！3、可以带点小零食，水或者饮料，累了就休息补充点能量！', 0, '重庆南山', 0, '2024-12-29 09:37:47', NULL);
INSERT INTO `travel_route` VALUES ('003', '重庆南山逛吃路线', '九九牛肉馆→柒露营串串香→蒲小姐的饼儿车轮饼→刘氏重庆鸡公煲→苗妹面庄→南山有烧烤——>不会吧！去南山一棵树、老君洞的姐妹还要下山吃饭！南山上这些宝藏店铺真的太太太好吃了^^', 0, '重庆南山', 0, '2024-12-30 18:20:00', NULL);

-- ----------------------------
-- Table structure for travel_strategy
-- ----------------------------
DROP TABLE IF EXISTS `travel_strategy`;
CREATE TABLE `travel_strategy`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '旅游攻略strategy',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strategy_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `strategy_status` int(255) NULL DEFAULT NULL COMMENT '0是审核通过,1是未审核,2是审核未通过',
  `create_date` datetime NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `error_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `travel_strategy_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of travel_strategy
-- ----------------------------
INSERT INTO `travel_strategy` VALUES ('01', '0001', '来重庆不要只去解放碑、洪崖洞啦，南山也有超多宝藏打卡地：1、南山植物园：各种鲜花争奇斗艳，重庆拍照出片地。2、黄桷垭老街：历史悠久的黄桷垭老街，人文氛围浓厚，随手一拍就是满满的故事感，重庆打卡绝佳地。3、老君洞：老君洞为道教宫观，免费进入，重庆千年道观，是可以去拜拜的好地方，这里求签特别灵......', 0, '2024-12-26 13:17:54', '南山旅游攻略', NULL);
INSERT INTO `travel_strategy` VALUES ('02', '0002', '进校请提前在微信公众号“重邮小卫士”预约，因为校园面积较大且有一定坡度，建议穿着舒适的运动鞋。从腾飞门进入校园后，首先来到樱花大道。这里是重邮赏樱的主要区域，樱花树高大繁茂，粉白的花朵如云似霞，非常美丽。可以沿着大道漫步，欣赏樱花的娇艳，拍照留念。从山坡下来后，经过操场、食堂、教职工楼等地。这些区域也有少量樱花树，虽然规模不大，但能让人感受到校园生活的氛围。可以体验一下重邮的校园食堂，品尝价格实惠且具有当地特色的美食，感受学生们的日常饮食。', 0, '2024-12-27 14:07:18', '重庆邮电大学攻略', '攻略写的不够详细');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0001', 'superwei', '000811', '魏言松');
INSERT INTO `user` VALUES ('0002', 'dihan', '456789', '陈堤涵');
INSERT INTO `user` VALUES ('0003', 'lvyang', '000000', '陈老师');
INSERT INTO `user` VALUES ('0004', 'yansong', '999999', '松松松');
-- ----------------------------
-- Table structure for user_attractions
-- ----------------------------
DROP TABLE IF EXISTS `user_attractions`;
CREATE TABLE `user_attractions`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `attractions_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_attractions_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `attractions_id`(`attractions_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_attractions_ibfk_1` FOREIGN KEY (`attractions_id`) REFERENCES `attractions` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `user_attractions_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_attractions
-- ----------------------------
INSERT INTO `user_attractions` VALUES ('01', '0002', '06', NULL, '2024-12-26 12:22:21');

-- ----------------------------
-- Table structure for user_hotel
-- ----------------------------
DROP TABLE IF EXISTS `user_hotel`;
CREATE TABLE `user_hotel`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `hotel_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_hotel_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_hotel_ibfk_1`(`user_id`) USING BTREE,
  INDEX `user_hotel_ibfk_2`(`hotel_id`) USING BTREE,
  CONSTRAINT `user_hotel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_hotel_ibfk_2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_hotel
-- ----------------------------
INSERT INTO `user_hotel` VALUES ('01', '0002', '03', NULL, '2024-12-27 12:22:04');

-- ----------------------------
-- Table structure for user_route
-- ----------------------------
DROP TABLE IF EXISTS `user_route`;
CREATE TABLE `user_route`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `route_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `route_id`(`route_id`) USING BTREE,
  CONSTRAINT `user_route_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_route_ibfk_2` FOREIGN KEY (`route_id`) REFERENCES `travel_route` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_route
-- ----------------------------
INSERT INTO `user_route` VALUES ('01', '0002', '002', '2024-12-29 12:07:27');

-- ----------------------------
-- Table structure for user_strategy
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy`;
CREATE TABLE `user_strategy`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `strategy_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_hotel_ibfk_1`(`user_id`) USING BTREE,
  INDEX `user_hotel_ibfk_2`(`strategy_id`) USING BTREE,
  CONSTRAINT `user_strategy_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_strategy_ibfk_2` FOREIGN KEY (`strategy_id`) REFERENCES `travel_strategy` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_strategy
-- ----------------------------
INSERT INTO `user_strategy` VALUES ('01', '0002', '01', '2024-12-25 19:55:58', NULL);

SET FOREIGN_KEY_CHECKS = 1;

create database travel

use travel