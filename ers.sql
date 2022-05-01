/*
 Navicat Premium Data Transfer

 Source Server         : ycs
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : ers

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 01/05/2022 23:14:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for experiment
-- ----------------------------
DROP TABLE IF EXISTS `experiment`;
CREATE TABLE `experiment`  (
  `experiment_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实验标题',
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `appointment_time` datetime(0) NULL DEFAULT NULL COMMENT '提交预约的时间',
  `state` int(11) NULL DEFAULT NULL COMMENT '实验状态（\r\n0：已取消\r\n1：已预约\r\n2：进行中\r\n3：已完成\r\n4：超时\r\n）',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '预约者id（默认为组长）',
  `participant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '多位使用空格间隔',
  `instructor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指导老师',
  `content` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实验内容',
  `related_knowledge` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关知识',
  `resource_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件资源地址（多个使用\"、\"间隔开）',
  PRIMARY KEY (`experiment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of experiment
-- ----------------------------
INSERT INTO `experiment` VALUES (1, '网络攻防—木马实验', '2022-04-29 18:09:38', '2022-04-29 19:09:53', '2022-04-27 18:10:06', 3, 1, '陈恺歌、吕子昂、丁俊语、余浩气、武玉萍、谢璇子、余欣然', '张三', '编写一个针对Windows的木马程序，该木马可以作为各种入侵程序的伪装外壳，保证信息窃探工作的顺利完成，基本程序功能如下：\r\n\r\n1、在主程序中要求将程序拷贝到系统盘\\windows\\目录下并更名为taskmgr.exe，同时复制第二份到系统盘\\windows\\system32目录下并更名为explorer.exe。用以混淆用户对木马的第一判断。\r\n\r\n2、程序建立两个windows进程，每个进程每一个时钟周期检查另外一个进程是否正在运行。如果存在弹出对话框“I’m still here!”,如果不存在启动另一个进程并弹出对话框“I’m still here!”。\r\n\r\n3、将拷贝好的两个病毒程序添加到注册表起动项中。（software \\\\microsoft\\\\windows\\\\currentversion\\\\run）。每个时钟程序在运行的时 候都要向注册表中添加此信息。', '会话劫持：\r\n\r\n攻击分为两种类型： 1）中间人攻击(Man In The Middle，简称MITM)，2）注射式攻击（Injection）；并且还可以把会话劫持攻击分为两种形式：1）被动劫持，2）主动劫持；被动劫持实际上就是在后台监视双方会话的数据流，从中获得敏感数据；而主动劫持则是将会话当中的某一台主机“踢”下线，然后由攻击者取代并接管会话，这种攻击方法危害非常大\r\n\r\nSYN Flood攻击：\r\n\r\nSYN Flood是当前最流行的DoS（拒绝服务攻击）与DdoS（分布式拒绝服务攻击）的方式之一，这是一种利用TCP协议缺陷，发送大量伪造的TCP连接请求，从而使得被攻击方资源耗尽（CPU满负荷或内存不足）的攻击方式。\r\n\r\nUDP攻击：\r\n\r\nUDP攻击,又称UDP洪水攻击或UDP淹没攻击（英文：UDP Flood Attack）是导致基於主机的服务拒绝攻击的一种。UDP 是一种无连接的协议，而且它不需要用任何程序建立连接来传输数据。当攻击者随机地向受害系统的端口发送 UDP 数据包的时候，就可能发生了 UDP 淹没攻击。\r\n\r\nARP协议攻击：\r\n\r\nARP攻击就是通过伪造IP地址和MAC地址实现ARP欺骗，能够在网络中产生大量的ARP通信量使网络阻塞，攻击者只要持续不断的发出伪造的ARP响应包就能更改目标主机ARP缓存中的IP-MAC条目，造成网络中断或中间人攻击。ARP攻击主要是存在于局域网网络中，局域网中若有一台计算机感染ARP木马，则感染该ARP木马的系统将会试图通过“ARP欺骗”手段截获所在网络内其它计算机的通信信息，并因此造成网内其它计算机的通信故障。', NULL);
INSERT INTO `experiment` VALUES (2, 'DNS溢出实验', '2022-04-30 18:12:36', '2022-04-28 20:12:40', '2022-04-28 18:12:48', 2, 2, '徐薇', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `release_time` datetime(0) NULL DEFAULT NULL,
  `image_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '试验系统开放预约啦', '欢迎前来预约实验，网络攻防在线预约系统正式开放使用啦！您只需点击实验预约按钮即可参与实验平台预约！', '2022-04-28 17:51:30', NULL);
INSERT INTO `notice` VALUES (2, '试验系统注意事项', '实验室安全操作注意事项，每次做完实验，都要及时的分析实验数据，以便总结上次实验的经验与体会，为下一次实验方法的进一步完善提供理论依据。', '2022-04-27 17:53:28', NULL);
INSERT INTO `notice` VALUES (3, '守护国家安全，人人有责！维护网络安全，从我做起！', '在网络时代，更需要提高国家安全意识，否则一不留神就容易掉入网络“陷阱”。当前，未成年人学龄前触网比例显著提升，再加之线上教育的迅猛发展，越来越多的青少年早早就成为了“网络原住民”。网络安全教育从娃娃抓起，需要在孩子们心中播下网络安全的种子，学校、家庭、社会相互配合，加强学龄前儿童的上网管理和教育，帮助孩子从触网之初就养成良好的网络使用习惯。\r\n\r\n好公民应该：\r\n· 不能危害网络安全\r\n· 不得利用网络危害国家安全、荣誉和利益\r\n· 不能煽动颠覆国家政权、推翻社会主义制度\r\n· 不能煽动分裂国家、破坏国家统一\r\n· 不能宣扬恐怖主义、极端主义\r\n· 不能宣扬民族仇恨、民族歧视\r\n· 不能传播暴力、淫秽色情信息\r\n· 不能编造、传播虚假信息扰乱经济秩序和社会秩序\r\n· 不能侵他人名誉、隐私、知识产权', '2022-04-26 18:01:52', NULL);
INSERT INTO `notice` VALUES (4, '全民参与的网络安全建设 共同守护数字化时代', '央广网北京4月28日消息“没有网络安全就没有国家安全，就没有经济社会稳定运行。”今天，维护网络安全既是关系到国家安全、国家主权的大事，也是关系到人民群众日常生活和自身利益的身边事。\r\n\r\n2022年4月29日是第九个“首都网络安全日”。本届首都网络安全日由北京市人民政府主办，北京市公安局联合北京市互联网信息办公室共同承办，旨在展示网络安全建设成果，倡导社会各界承担网络安全责任，投身网络安全建设，维护网络生态环境。\r\n\r\n在第九个“首都网络安全日”来临之际，央广网记者采访了数位网络安全业界专家，请他们分析当前网络安全发展的形势，并提供提升网络安全意识的一些建议。有了全民参与的网络安全建设，才能让所有人轻松享受数字化时代的便捷。\r\n', '2022-04-28 18:04:27', NULL);
INSERT INTO `notice` VALUES (5, '《2022年中国网络安全市场全景图》正式发布', '《2022年中国网络安全市场全景图》是数说安全正式发布的第五版全景图。数说安全始终秉承科学、遵循市场发展规律且符合客户采购习惯的分类方法对市场进行研究，以遵从国家/行业主管部门权威结果为前提，持续优化市场分类方法，目前已基本形成一套符合我国网络安全行业真实供需关系的市场分类架构模型，在此基础上深入分析各细分市场的实际发展状况、市场成熟度与技术发展趋势，并以全景图为载体对各细分市场中的热点品牌进行汇总和展示，希望为我国网络安全行业主管部门、从业者、网络安全产品及服务的使用者和购买单位以及资本机构提供借鉴和参考。', '2022-04-27 18:05:38', NULL);
INSERT INTO `notice` VALUES (6, '系好风险防控“安全带” 织密高校网络安全“防护网”', '2022年4月15日，是第七个全民国家安全教育日。随着互联网的快速发展和广泛应用，当前网络舆论主体的多元化、传播平台的多样化、舆论交锋的复杂化，对高校网络意识形态阵地安全带来了严峻挑战。面对复杂多变的网络环境，高校如何适应互联网快速发展的形势，进一步加强师生网络安全意识教育，是摆在大学面前刻不容缓的课题。\r\n\r\n从外卖点餐到打车出行，从网络课堂到网络交友，从直播健身到网络购物……如今高校师生的生活早已和网络密不可分，他们作为网络空间中最为活跃的群体之一，在享受着互联网带来的便利与快捷的同时，也是网络舆论的重要参与者，高校也成为网络意识形态安全风险需要高度关注的地方。高校应高度重视大学生网络安全教育工作，将网络安全教育纳入“三全育人”体系，着力增强和提升大学生维护网络安全的意识与能力。', '2022-04-20 18:08:22', NULL);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '先知社区', '安全技术社区', 'https://xz.aliyun.com/', '2022-04-28 17:09:09');
INSERT INTO `resource` VALUES (2, 'whois查询', '子域/IP信息查询', 'http://whois.chinaz.com/', '2022-04-27 17:11:25');
INSERT INTO `resource` VALUES (3, '攻防世界', '在线靶场', 'https://adworld.xctf.org.cn/', '2022-04-26 17:12:21');
INSERT INTO `resource` VALUES (4, 'Hack The Box', '渗透测试在线练习平台', 'https://www.hackthebox.com/', '2022-04-25 17:37:30');
INSERT INTO `resource` VALUES (5, 'W3Cschool', '门户博客论坛', 'https://www.w3school.com.cn/index.html', '2022-04-24 17:37:36');
INSERT INTO `resource` VALUES (6, 'CTF在线工具', '综合在线工具平台', 'http://www.hiencode.com/', '2022-04-22 17:37:43');
INSERT INTO `resource` VALUES (7, 'HawkEye', '网络信息安全攻防学习平台', 'http://hackinglab.cn/#', '2022-04-20 17:37:50');
INSERT INTO `resource` VALUES (8, '黑客街', '为网络安全爱好者提供网站导航', 'https://www.hackjie.com/', '2022-04-19 19:37:55');
INSERT INTO `resource` VALUES (9, '360众测', '安全众测服务平台', 'https://zhongce.360.net/', '2022-04-16 17:38:05');
INSERT INTO `resource` VALUES (10, 'HackerOne', '黑客驱动安全平台，帮助你在被利用之前发现并修复关键漏洞', 'https://www.hackerone.com/', '2022-04-15 17:38:09');
INSERT INTO `resource` VALUES (11, 'Paper', '漏洞文档，漏洞分析，安全技术', 'https://paper.seebug.org/', '2022-04-13 17:42:00');
INSERT INTO `resource` VALUES (12, '钟馗之眼', '网络空间搜索引擎', 'https://www.zoomeye.org/', '2022-04-12 17:42:05');
INSERT INTO `resource` VALUES (13, 'DnsDB', '全球最大的DNS查询数据库', 'https://dnsdb.io/zh-cn/', '2022-04-10 17:42:14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` int(11) NULL DEFAULT NULL COMMENT '用户角色（\r\n1：管理员\r\n2：教师\r\n3：学生\r\n4：社会人员\r\n）',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `faculty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(255) NULL DEFAULT NULL,
  `class` int(255) NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教学科目',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '贺情岚', '15804889522', '952158354@qq.com', '123456', 1, '北京大学', '计算机学院', 20, NULL, NULL, '女', NULL);
INSERT INTO `user` VALUES (2, '徐诚怡', '15049653274', 'pofvor@163.com', 'abcdef', 2, '清华大学', '软件学院', 18, 5, '数据结构与算法', '男', NULL);
INSERT INTO `user` VALUES (3, '计韵冶', '18495176136', '1648399827@qq.com', 'qwerasdf', 3, '浙江大学', '数学学院', 19, 2, NULL, '女', NULL);
INSERT INTO `user` VALUES (4, '赵磊伟', '17372967367', 'zhaoleiwei@outlook.com', 'qey1324', 3, '同济大学', '软件学院', 18, 1, NULL, '男', NULL);
INSERT INTO `user` VALUES (5, '朱诺民', '\r\n18407862439\r\n', 'haihaihai@gmail.com', '112233abc', 2, '山东大学', '信息安全学院', 21, 6, 'C++', '男', NULL);
INSERT INTO `user` VALUES (6, '王莲奇', '14623342143', '12324wlq@qq.com', 'dsafwr32', 2, '华南理工大学', '电子信息工程学院', 19, 2, 'Python', '女', NULL);
INSERT INTO `user` VALUES (7, '常岩州', '17021219332', 'ieuw329@163.com', '42rfewg', 4, '', NULL, NULL, NULL, NULL, '男', NULL);
INSERT INTO `user` VALUES (8, '钟泳祺', '19983993222', 'qq32174752@sohu.com', 'dsrgv432', 4, '', NULL, NULL, NULL, NULL, '男', NULL);
INSERT INTO `user` VALUES (9, '何梓仪', '13858185698', '123123456@qq.com', '3fads@rg3', 3, '合肥工业大学', '软件学院', 20, 1, NULL, '女', NULL);
INSERT INTO `user` VALUES (10, '姚丁嘉', '18077541312', 'aaaaaaa@qq.com', 'afefs32231t5', 2, '武汉大学', '网络安全学院', 19, 2, '网络安全', '男', NULL);
INSERT INTO `user` VALUES (11, '沈子琳', '18635462651', '13846236@163.com', 'rerg23', 4, NULL, '', NULL, NULL, NULL, '男', NULL);
INSERT INTO `user` VALUES (12, '许令艺', '15054046779', NULL, 'erfbth23111', 2, '中山大学', '电子信息工程学院', 18, 3, 'Java', '女', NULL);
INSERT INTO `user` VALUES (13, '钟艺军', '13311517037', '39824gfa@outlook.com', '111222333', 3, '天津大学', '计算机学院', 22, 2, NULL, '男', NULL);
INSERT INTO `user` VALUES (14, '秦如钟', '14702687829', '12123123@icloud.com', '123456789', 4, NULL, NULL, NULL, NULL, NULL, '男', NULL);
INSERT INTO `user` VALUES (15, '裴念恬', '19115494189', '123762849@qq.com', 'fgbed123', 3, '合肥工业大学', '软件学院', 21, 5, NULL, '女', NULL);
INSERT INTO `user` VALUES (16, '欧阳远凡', '17122658262', 'oyyf6666@gmail.com', 'jikkmnt1122', 3, '合肥工业大学', '软件学院', 18, 3, NULL, '男', NULL);
INSERT INTO `user` VALUES (17, '范易嵩', '13871867039', '88888888@qq.com', '112233', 3, '合肥工业大学', '计算机学院', 18, 2, NULL, '男', NULL);
INSERT INTO `user` VALUES (18, '洪敏瑛', '13535633973', NULL, '666666', 3, '合肥工业大学', '计算机学院', 19, 1, NULL, '女', NULL);

SET FOREIGN_KEY_CHECKS = 1;
