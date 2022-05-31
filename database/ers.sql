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

 Date: 31/05/2022 11:15:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for experiment
-- ----------------------------
DROP TABLE IF EXISTS `experiment`;
CREATE TABLE `experiment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实验标题',
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '提交预约的时间',
  `state` int(11) NULL DEFAULT NULL COMMENT '实验状态（\r\n0：已取消\r\n1：已预约\r\n2：进行中\r\n3：已完成\r\n4：超时\r\n）',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '预约者id（默认为组长）',
  `participant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '多位使用空格间隔',
  `instructor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指导老师',
  `content` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实验内容',
  `related_knowledge` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关知识',
  `resource_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件资源地址（多个使用\"、\"间隔开）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of experiment
-- ----------------------------
INSERT INTO `experiment` VALUES (1, '网络攻防—木马实验', '2022-04-29 18:09:38', '2022-04-29 19:09:53', '2022-04-27 18:10:06', 3, 1, '陈恺歌、吕子昂、丁俊语、余浩气、武玉萍、谢璇子、余欣然', '张三', '编写一个针对Windows的木马程序，该木马可以作为各种入侵程序的伪装外壳，保证信息窃探工作的顺利完成，基本程序功能如下：\r\n\r\n1、在主程序中要求将程序拷贝到系统盘\\windows\\目录下并更名为taskmgr.exe，同时复制第二份到系统盘\\windows\\system32目录下并更名为explorer.exe。用以混淆用户对木马的第一判断。\r\n\r\n2、程序建立两个windows进程，每个进程每一个时钟周期检查另外一个进程是否正在运行。如果存在弹出对话框“I’m still here!”,如果不存在启动另一个进程并弹出对话框“I’m still here!”。\r\n\r\n3、将拷贝好的两个病毒程序添加到注册表起动项中。（software \\\\microsoft\\\\windows\\\\currentversion\\\\run）。每个时钟程序在运行的时 候都要向注册表中添加此信息。', '会话劫持：\r\n\r\n攻击分为两种类型： 1）中间人攻击(Man In The Middle，简称MITM)，2）注射式攻击（Injection）；并且还可以把会话劫持攻击分为两种形式：1）被动劫持，2）主动劫持；被动劫持实际上就是在后台监视双方会话的数据流，从中获得敏感数据；而主动劫持则是将会话当中的某一台主机“踢”下线，然后由攻击者取代并接管会话，这种攻击方法危害非常大\r\n\r\nSYN Flood攻击：\r\n\r\nSYN Flood是当前最流行的DoS（拒绝服务攻击）与DdoS（分布式拒绝服务攻击）的方式之一，这是一种利用TCP协议缺陷，发送大量伪造的TCP连接请求，从而使得被攻击方资源耗尽（CPU满负荷或内存不足）的攻击方式。\r\n\r\nUDP攻击：\r\n\r\nUDP攻击,又称UDP洪水攻击或UDP淹没攻击（英文：UDP Flood Attack）是导致基於主机的服务拒绝攻击的一种。UDP 是一种无连接的协议，而且它不需要用任何程序建立连接来传输数据。当攻击者随机地向受害系统的端口发送 UDP 数据包的时候，就可能发生了 UDP 淹没攻击。\r\n\r\nARP协议攻击：\r\n\r\nARP攻击就是通过伪造IP地址和MAC地址实现ARP欺骗，能够在网络中产生大量的ARP通信量使网络阻塞，攻击者只要持续不断的发出伪造的ARP响应包就能更改目标主机ARP缓存中的IP-MAC条目，造成网络中断或中间人攻击。ARP攻击主要是存在于局域网网络中，局域网中若有一台计算机感染ARP木马，则感染该ARP木马的系统将会试图通过“ARP欺骗”手段截获所在网络内其它计算机的通信信息，并因此造成网内其它计算机的通信故障。', NULL);
INSERT INTO `experiment` VALUES (2, 'DNS溢出实验', '2022-04-30 18:12:36', '2022-04-28 20:12:40', '2022-04-28 18:12:48', 2, 2, '徐薇', '黄可新', '网络安全保护的核心是如何在网络环境下保证数据本身的秘密性、完整性与操作的正确性、合法性与不可否认性。而网络攻击的目的正相反，其立足于以各种方式通过网络破坏数据的秘密性和完整性或进行某些非法操作。\r\n网络及其应用的广泛发展，安全威胁呈现出攻击的种类、方法和总体数量越来越多、破坏性和系统恢复难度也越来越大。这就要求我们对攻击方法有更进一步的研究;对安全策略有更完善的发展，建立起一个全面的、可靠的、高效的安全体系。\r\nDNS 的设计被发现可攻击的漏洞，攻击者可透过伪装 DNS主要服务器的方式，引导使用者进入恶意网页，以钓鱼方式取得信息，或者植入恶意程序。\r\nMSO6 - 041:DNS解析中的漏洞可能允许远程代码执行。Microsoft Windows是微软发布的非常流行的操作系统。\r\nMicrosoft Windows DNS服务器的RPC接口在处理畸形请求时存在栈溢出漏洞，远程攻击者可能利用此漏洞获取服务器的管理权限。\r\n如果远程攻击者能够向有漏洞的系统发送特制的RPC报文的话，就可以触发这个溢出，导致以DNS服务的安全环境执行任意指令（默认为Local?SYSTEM)。\r\n\r\n实验目的：\r\n\r\n• 了解程序调入内存中的分段情况\r\n• 掌握常见缓冲区溢出攻击工具使用方法。\r\n• 掌握缓冲区溢出攻击防御方法。\r\n', 'DNS溢出前提是必须要开放53、445端口 \r\n1、选择superscan进行扫描，在端口列表中勾选上53、445端口，然后选择主机段进行扫描。\r\n2、选择存在53、445端口的主机，在cmd下输入dns -t ip对主机进行特定漏洞端口扫描，“1028 ：Vulnerability”表示 1028端口存在溢出漏洞，“os:window 2000”表示系统类型为window 2000。\r\n3、在执行dns -t 2000all ip 1028 对目标主机进行溢出。connection to target host tcp port established 成功连接到主机的tcp端口上，successfully bound to the vulnerale dcerpc interface 已经连接到对方的rpc端口上，os fingerprint result:windows 2000。系统指纹鉴定对方是wind2000系统，attack sent,check port 1100 攻击代码已经成功发送，并尝试连接1100端口。当出现最后这一句提示语句的时候就意味着我们要成功了。\r\n4、怎么连接对方的1100端口呢，当然我们要使用nc了。\r\n5、nc全称叫\"netcat\"，这是一个非常简单易用的基于tcp/ip协议（c/s模型的）的“瑞士军刀”。', NULL);
INSERT INTO `experiment` VALUES (3, 'ARP欺骗实验', '2022-05-05 19:23:36', '2022-05-05 21:23:44', '2022-05-02 19:23:52', 3, 3, '常岩州、沈子琳、计韵冶', '赵金鱼', 'ARP欺骗是黑客常用的攻击手段之一，ARP欺骗分为二种，一种是对路由器ARP表的欺骗；另一种是对内网PC的网关欺骗。\r\n①第一种ARP欺骗的原理是——截获网关数据。它通知路由器一系列错误的内网MAC地址，并按照一定的频率不断进行，使真实的地址信息无法通过更新保存在路由器中，结果路由器的所有数据只能发送给错误的MAC地址，造成正常PC无法收到信息。\r\n②第二种ARP欺骗的原理是——伪造网关。它的原理是建立假网关，让被它欺骗的PC向假网关发数据，而不是通过正常的路由器途径上网。在PC看来，就是上不了网了，“网络掉线了”。\r\n\r\nARP表是I地址和MAC地址的映射关系表，任何实现了IP协议栈的设备，一般情况下都通过该表维护地址和MAC地址的对应关系，这是为了避免ARP解析而造成的广播数据报文对网络造成冲击。ARP表的建立一般情况下是通过二个途径:\r\n(1)主动解析\r\n如果一台计算机想与另外一台不知道MAC地址的计算机通信，则该计算机主动发ARP请求，通过ARP协议建立(前提是这两台计算机位于同一个IP子网上)。\r\n(2)被动请求\r\n如果一台计算机接收到了一台计算机的ARP请求，则首先在本地建立请求计算机的IP地址和MAC地址的对应表。因此，针对ARP表项，一个可能的攻击就是误导计算机建立正确的ARP表。根据 ARP协议，如果一台计算机接收到了一个ARP请求报文，在满足下列两个条件的情况下，该计算机会用ARP请求报文中的源IP地址和源物理地址更新自己的ARP缓存:\r\n  1、如果发起该ARP请求的I地址在自己本地的ARP缓存中；\r\n  2、请求的目标IP地址不是自己的。', '• 局域网网络数据传输原理(交换机)\r\n• ARP协议\r\n• 相关的软件\r\n• Sniffer、Wireshark、VMware', NULL);
INSERT INTO `experiment` VALUES (4, '木马灰鸽子攻击', '2022-05-12 10:45:27', '2022-05-12 13:45:45', '2022-05-07 19:46:00', 1, 5, '王莲奇、钟艺军、徐诚怡、赵磊伟、裴念恬', '朱诺民', '*目的:了解灰鸽子是一个集多种控制方法于一体的木马病毒。\r\n*内容及要求\r\n	1.练习软件的哪些功能，自己总结\r\n	2.通过实验了解灰鸽子是一款远程控制软件。3.熟悉使用木马进行网络攻击的原理和方法。\r\n*实验环境:\r\n	虚拟机下安装组建一个局域网，2台安装win2000/win2003/XP系统的电脑，灰鸽子木马软件。', '木马，又名特洛伊木马，其名称取自古希腊神话的特洛伊木马记，它是一种基于远程控制的黑客工具，具有很强的隐蔽性和危害性。为了达到控制服务端主机的目的，木马往往要采用各种手段达到激活自己、加载运行的目的。本案例以国内著名的灰鸽子木马为例讲述木马的使用和防御。\r\n', NULL);
INSERT INTO `experiment` VALUES (5, '明文窃听', '2022-05-12 15:48:06', '2022-05-17 18:48:21', '2022-05-10 19:48:39', 1, 3, '何梓仪、欧阳远凡', '李若曦', '本机IP地址为172.20.1.178/16，Windows实验台IP地址为172.20.3.178/16(在实验中应根据具体实验环境进行实验)。\r\n1、设置抓包参数\r\n参考FTP连接与密码明文抓取中实验步骤中设置WireShark的过程。\r\n2、抽获pop3数据包，嗅探密码\r\n本步骤使用foxmail，学生可自行使用其它pop3连接工具进行实验。配置 foxmail 正常工作，再次以新浪邮箱为例，实验时可根据自己的实际情况设定\r\n', '密码学分为古典密码学和现代密码学，古典点密码学主要解决信息传递过程被窃听问题，将明文加密传输给对方，窃听者没有密钥和解密方式无法获取明文。\r\n\r\n现在密码学不仅解决信息窃听问题，还解决了信息传输过程中消息的完整性（消息摘要)、防伪装攻击(信息的数字签名、消息认证，是不是来自合法的发送方）等其他信息安全问题。\r\n\r\n先聊聊如何验证消息的完整性，一篇长篇长文被接收后，如何验证收到的是完整的信息呢，常规做法是发送者选择一种信息摘要算法计算出消息的信息摘要附加到消息末尾一起发送;接收者收到后计算出消息的哈希值与接收到的哈希值做一致性比较来验证消息的完整性。\r\n\r\n消息摘要（也称哈希值、散列值、杂凑值)︰信息摘要的字节长度是固定的，不随着参与计算得消息长度变化而变化，信息摘要计算的逆向操作也是难以完成的，而且发生碰撞(输入不同的消息，散列值一样)的概率非常小。消息摘要计算的不可逆性常用来保护密码，服务器存储的是密码的消息摘要而不是密码明文，防止密码被窃取。\r\n', NULL);
INSERT INTO `experiment` VALUES (6, 'PING扫描', '2022-05-09 19:49:31', '2022-05-09 20:49:42', '2022-05-07 19:49:56', 1, 6, '钟泳祺、洪敏瑛、姚丁嘉', '蒋小喵', '本机IP地址为172.20.1.178/16，Windows实验台IP地址为172.20.3.178/16。(在实验中应根据具体实验环境进行实验)\r\n点击“开始”菜单，单击“运行”，键入“cmd”，确定。打开命令行窗口。\r\n(注:本实验中需要借助Wireshark 抓包工具，进行数据分析，抓包工具从工具箱中下载)', '(1) Ping -t\r\n说明:不停的发送icmp 数据包命令如下: ping - t 172.20.3.178\r\n当使用 ping命令时，Windows平台默认发送四个icmp数据包。带有参数t的ping命令，将不断向目的地址发送icmp数据包;Ctrl+C键停止此命令。\r\n(2) Ping -i\r\n说明:修改ping命令发送的 icmp协议的TTL值命令如下: ping - i 32 172.20.3.178\r\n运行抓包工具，并设置Filter条件，对ping命令进行数据包的抓取。\r\n(3) Ping -f\r\n说明:设置 Don\'t Fragment标志位为1不加参数的ping命令。\r\n(4) Ping -l\r\n说明:发送特定长度的icmp数据包\r\n不加参数的ping命令，带有32字节的数据部分。', NULL);
INSERT INTO `experiment` VALUES (7, 'TOMCAT管理用户弱口令攻击', '2022-05-21 07:50:50', '2022-05-21 09:50:05', '2022-05-18 19:51:21', 1, 8, '裴念恬、赵磊伟', '胡晓辉', 'Tomcat是一个世界上广泛使用的支持JSP和servlets的Web服务器。它在JAVA运行时上能够很好地运行并支持Web应用部署。会因为设置不当，造成灾难性的后果。在Tomcat默认安装，Tomcat作为一个系统服务运行，如果没有将其作为系统服务运行，缺省地几乎所有Web服务器管理员都是将其以Administrator权限运行这两种方式都允许Java运行时访问Windows系统下任意文件夹中的任何文件。缺省情况下，Java运行时根据运行它的用户授予安全权限。当Tomcat以系统管理员身份或作为系统服务运行时，Java运行时取得了系统用户或系统管理员所具有的全部权限。这样一来，Java运行时就取得了所有文件夹中所有文件的全部权限。并且Servlets(JSP在运行过程中要转换成Servlets)取得了同样的权限，所以对于应用服务器的安全需要严格的设置。\r\n\r\n*实验目标\r\n1.了解Tomcat 的脆弱性\r\n2.通过对攻击过程的实施，掌握提高tomcat安全性的设置方法\r\n', '在客户主机上，通过Tomcat的admin应用，建立虚拟目录，查看服务器文件结构;然后在客户机上，通过manager应用添加WAR应用，通过本实验提供的工具在服务器主机上添加系统管理员帐号( Windows系统〉。', NULL);
INSERT INTO `experiment` VALUES (8, '这是新增的实验123', '2022-05-29 06:06:06', '2022-05-29 12:12:12', '2022-05-28 12:30:03', 1, 22, '王莲奇、钟艺军、徐诚怡、赵磊伟、裴念恬', '李若曦', '实验内容在这里', '相关知识在这里', NULL);
INSERT INTO `experiment` VALUES (9, '木马灰鸽子攻击', '2022-05-19 11:52:35', '2022-05-19 13:52:31', '2022-04-02 13:52:23', 2, 3, '陈恺歌、吕子昂、丁俊语、余浩气、武玉萍、谢璇子、余欣然', '赵金鱼', NULL, NULL, NULL);
INSERT INTO `experiment` VALUES (10, 'TOMCAT管理用户弱口令攻击', '2022-05-25 13:53:46', '2022-05-25 16:53:37', '2022-03-19 13:53:33', 3, 6, '常岩州、沈子琳、计韵冶', '黄可新', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `release_time` datetime(0) NULL DEFAULT NULL,
  `image_address` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '试验系统开放预约啦，马上预约吧', '欢迎前来预约实验，网络攻防在线预约系统正式开放使用啦！您只需点击实验预约按钮即可参与实验平台预约！', '2022-05-02 17:51:30', NULL);
INSERT INTO `notice` VALUES (2, '试验系统注意事项，快来看看吧', '实验室安全操作注意事项，每次做完实验，都要及时的分析实验数据，以便总结上次实验的经验与体会，为下一次实验方法的进一步完善提供理论依据。', '2022-05-01 17:53:28', NULL);
INSERT INTO `notice` VALUES (3, '守护国家安全，人人有责！维护网络安全，从我做起！很快就发生的黑咖啡加砂咖啡和hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh跨境电商，ahfkhaskfhksfhkdhkfa', '在网络时代，更需要提高国家安全意识，否则一不留神就容易掉入网络“陷阱”。当前，未成年人学龄前触网比例显著提升，再加之线上教育的迅猛发展，越来越多的青少年早早就成为了“网络原住民”。网络安全教育从娃娃抓起，需要在孩子们心中播下网络安全的种子，学校、家庭、社会相互配合，加强学龄前儿童的上网管理和教育，帮助孩子从触网之初就养成良好的网络使用习惯。\r\n\r\n好公民应该：\r\n· 不能危害网络安全\r\n· 不得利用网络危害国家安全、荣誉和利益\r\n· 不能煽动颠覆国家政权、推翻社会主义制度\r\n· 不能煽动分裂国家、破坏国家统一\r\n· 不能宣扬恐怖主义、极端主义\r\n· 不能宣扬民族仇恨、民族歧视\r\n· 不能传播暴力、淫秽色情信息\r\n· 不能编造、传播虚假信息扰乱经济秩序和社会秩序\r\n· 不能侵他人名誉、隐私、知识产权', '2022-04-26 18:01:52', NULL);
INSERT INTO `notice` VALUES (4, '教育的全面数字化转型已成必然趋势', '党的十八大以来，建设网络强国、数字中国的战略决策。党的十八届五中全会提出，实施网络强国战略和国家大数据战略。党的十九大提出，推动互联网、大数据、人工智能和实体经济深度融合，建设数字中国、智慧社会。党的十九届五中全会提出，发展数字经济，推进数字产业化和产业数字化。产业数字化对社会各行业劳动者素质提出了更高要求，创新能力、沟通协作能力、复杂问题解决能力、人机协作能力等将成为面向未来的关键能力。\r\n\r\n人才需求的变化倒逼教育系统必须进行全面、彻底的转型和升级，建设以数字化为支撑的高质量教育体系，是应对新阶段人才培养挑战的必然选择。要因应信息技术的发展，推动教育变革和创新；要高度重视人工智能对教育的深刻影响，积极推动人工智能和教育深度融合；要总结应对新冠肺炎疫情以来大规模在线教育的经验，利用信息技术更新教育理念、变革教育模式。在中国这样的人口大国，只有充分利用大数据、人工智能等技术，构建网络化、数字化、个性化、终身化的教育体系，才能实现“人人皆学、处处能学、时时可学”的学习型社会。加快推进教育数字化转型，是我国教育实现从基本均衡到高位均衡、从教育大国到教育强国的必然选择。\r\n\r\n教育数字化转型具有良好前期基础。\r\n\r\n教育部一直高度重视信息技术对教育的影响，陆续出台一系列政策，部署推进教育数字化转型。《教育信息化十年发展规划（2011—2020年）》《教育信息化2.0行动计划》等系列规划文件陆续发布。2021年，教育部等六部门发布《关于推进教育新型基础设施建设构建高质量教育支撑体系的指导意见》，提出要以教育新基建促进线上线下教育融合发展，推动教育数字转型、智能升级、融合创新，支撑教育高质量发展。\r\n\r\n经过十余年的努力，我国教育信息化实现了跨越式发展，取得了显著成效，“三通两平台”建设与应用取得重大进展，教师信息技术应用能力大幅提升，教育信息化技术水平大幅提高，信息化对教育改革推动作用大幅提升，教育信息化国际影响力大幅增强，教育信息化应用模式取得重大突破，坚持应用驱动和机制创新，探索形成了中国特色教育信息化道路。', '2022-04-21 17:54:08', NULL);
INSERT INTO `notice` VALUES (5, '自觉学网懂网用网 切实维护网络安全', '市委网络安全和信息化委员会召开会议，学习贯彻习近平总书记关于网络强国的重要思想和党中央关于网信工作的部署要求，审议我市数字化绿色化协同转型发展实施方案、提升全民数字素养与技能行动实施方案等文件。市委书记、市委网络安全和信息化委员会主任李鸿忠主持并讲话。\r\n\r\n　　会议指出，要坚持以习近平新时代中国特色社会主义思想为指导，深入学习贯彻习近平总书记关于网络强国的重要思想，增强“四个意识”、坚定“四个自信”、做到“两个维护”，不断提高政治判断力、政治领悟力、政治执行力，紧紧围绕迎接服务保障和学习宣传贯彻党的二十大这条主线，全面提高网络安全和信息化工作水平。\r\n\r\n　　会议对网络安全和信息化工作提出明确要求。一是强化思想引领，深入宣传阐释“两个确立”的决定性意义，坚持守正创新，精心组织网络重大主题宣传，让党的声音成为网络空间最强音。二是着力推进网信事业建设，加快推进新一代信息基础设施谋划布局，加大数字经济、人工智能等关键核心技术研发力度，加快信创产业发展，壮大网信专业人才队伍。三是完善网信工作管理体系，优化“三级确保、建设四级、多级完善”的网信工作格局，确保网络管理及时、科学、高效、到位。四是做好网络意识形态工作，牢牢掌握网络意识形态工作领导权，有效化解各类网络风险，切实维护网络安全。五是从各级领导干部做起，自觉学网懂网用网，强化数字化素养，不断提高对互联网规律的把握能力、对网络舆论的引导能力、对信息化发展的驾驭能力、对网络安全的保障能力，推动网络强市建设。', '2022-04-24 17:56:27', NULL);
INSERT INTO `notice` VALUES (6, '关于网络安全管理的通知\r\n', '随着网络信息的发展，上网计算机台数不断增加，致使我区网络速度减慢，有时形成阻塞，为保证网络正常运行，更好的为办公服务，特下发通知，望各部门认真遵照执行。\r\n一、接入互联网的计算机，如需文件共享要做好安全防范工作，严格杜绝保密资料泄漏。\r\n二、我区的内部办公网要禁止区外人员浏览，在内部办公网上发布的文件、通知、通报、信息要严把质量关，确保内部办公网的质量和安全。\r\n三、严禁在上班时间利用计算机从事与工作无关的活动，如玩游戏、聊天、炒股和传播违法内容。\r\n四、严禁上班时间下载电影、在线看电影、观看黄色图片。\r\n五、因工作需要下载资料的，请及时关闭相关的下载软件。\r\n六、严禁在网络上发布不真实的信息或散布计算机病毒。\r\n七、做到人离开即锁定计算机，下班关闭计算机。\r\n八、各部门要加强宣传教育，让全体干部职工知道网络畅通对工作的重要性，各部门、各单位领导要以身作则，管好自已的工作人员。\r\n九、相关部门将不定期进行明察暗访，并通过服务器软件时时监控。一经发现，按照相关规定严肃处理并追究相关领导人的责任。\r\n', '2022-04-17 16:10:55', NULL);
INSERT INTO `notice` VALUES (7, '系统漏洞公告', 'WordPress SEUR Oficial Plugin跨站脚本漏洞（CVE-2021-25005）\r\nWordPress WP Booking System Plugin跨站脚本漏洞（CVE-2021-25061）\r\nMake-Ca输入验证错误漏洞（CVE-2022-21672）\r\nSmarty输入验证错误漏洞（CVE-2021-21408）\r\nNavigateCMS路径遍历漏洞（CVE-2021-44351）\r\nhoppscotch信息泄露漏洞（CVE-2022-0121）\r\nCisco Enterprise NFV Infrastructure Software XML外部实体注入漏洞（CVE-2022-20779）\r\nCisco Adaptive Security Appliance和Firepower Threat Defense权限提升漏洞（CVE-2022-20759）\r\nCisco Small Business RV Series Routers命令注入漏洞（CVE-2022-20799）\r\nCisco Umbrella Secure Web Gateway文件检查绕过漏洞（CVE-2022-20805）\r\nCisco TelePresence CE和RoomOS Software拒绝服务漏洞（CVE-2022-20764）\r\nCisco Small Business RV Series Routers远程代码执行漏洞（CVE-2022-20753）\r\nAnker Eufy Homebase拒绝服务漏洞（CVE-2022-26073）\r\nAnker Eufy Homebase身份验证绕过漏洞（CVE-2022-25989）\r\nTOTOLINK A7100RU命令注入漏洞（CVE-2022-28577）\r\nTOTOLINK N600R命令注入漏洞（CVE-2022-27411）\r\nClam AntiVirus拒绝服务漏洞（CVE-2022-20796）\r\nClam AntiVirus内存泄露漏洞（CVE-2022-20785）\r\nClam AntiVirus拒绝服务漏洞（CVE-2022-20770）\r\nClam AntiVirus拒绝服务漏洞（CVE-2022-20771）\r\nAdobe After Effects堆栈缓冲区溢出漏洞（CVE-2022-27784）\r\nCisco Firepower Threat Defense拒绝服务漏洞（CVE-2022-20751）\r\nAdobe Photoshop越界写入漏洞（CVE-2022-28278）\r\nAdobe Photoshop内存错误引用漏洞（CVE-2022-28271）\r\nCisco Firepower Management Center存在多个跨站脚本漏洞（CVE-2022-20627）\r\nCisco Firepower Management Center跨站脚本漏洞（CVE-2022-20740）\r\nCisco Firepower Management Center存在多个跨站脚本漏洞（CVE-2022-20629）\r\nAdobe Photoshop内存错误引用漏洞（CVE-2022-28279）\r\nAdobe Photoshop越界读取漏洞（CVE-2022-24099）\r\nAdobe Photoshop越界写入漏洞（CVE-2022-28276）\r\nAdobe Photoshop越界写入漏洞（CVE-2022-28273）\r\nCisco Firepower Management Center安全性绕过漏洞（CVE-2022-20743）\r\nCisco Firepower Management Center存在多个跨站脚本漏洞（CVE-2022-20628）\r\nCisco Firepower Threat Defense XML注入漏洞（CVE-2022-20729）\r\nCisco Firepower Threat Defense拒绝服务漏洞（CVE-2022-20767）\r\nCisco Firepower Threat Defense安全情报NDS源绕过漏洞（CVE-2022-20730）\r\nAdobe Photoshop越界写入漏洞（CVE-2022-28277）\r\nAdobe Photoshop输入验证错误漏洞（CVE-2022-24098）\r\n', '2022-04-19 17:10:24', NULL);
INSERT INTO `notice` VALUES (8, '关于网络信息安全的通知', '2020国家网络安全宣传周即将来临，在此，我们提醒广大师生注意个人网络信息安全,希望注意几点:\r\n\r\n1、不要打开来历不明的邮件的附件，并立刻删除该邮件。如果不小心点了来历不明的附件或链接，请立刻查杀病毒、木马等，避免造成更大损失。\r\n\r\n2、注意邮箱密码安全。不要将密码转告给其他人，定期更新密码。如果长期不修改密码，我们将强制重置，请谅解。如果密码被强制重置，可以按照提示重新制定密码（注意不要使用弱密码），或者联系信息网络中心工作人员帮助修改新密码。\r\n\r\n3、如果遇到一些冒充管理员的欺骗性的邮件（称为钓鱼邮件，专门骗取他人的账号和密码），一定要直接删除，切勿上当受骗。\r\n\r\n4、除非工作必要，尽量不公开自己的各类账号；除学术交流之外，不向校外人员提供自己统一身份认证的账号密码。\r\n\r\n5、保护好个人电脑，安装防火墙和防病毒软件，并经常升级，及时更新木马库，为操作系统打补丁。 \r\n\r\n                                                                                         信息网络中心', '2022-04-20 17:12:20', NULL);
INSERT INTO `notice` VALUES (9, '教育部“大学数学国家虚拟教研室”启动仪式在我校举行', '4月30日，由我校牵头建设的教育部“大学数学国家虚拟教研室”举行启动仪式暨首次研讨会。校长梁樑出席启动仪式，高等教育出版社副总编辑林金安，北京邮电大学副校长孙洪祥，北方工业大学副校长栗苹等在线上出席仪式并致辞。\r\n\r\n梁樑在致辞中对大学数学国家虚拟教研室正式启动及首次研讨会的召开表示热烈祝贺。他表示，虚拟教研室建设试点由教育部组织实施，旨在以现代化信息技术为依托，探索建设新型基层教学组织，打造教师教学发展共同体和质量文化，推动教学组织探索突破时空、地域限制建立高效便捷的教学研究新模式，引导教师回归教学、热爱教学、研究教学，提升教育教学能力。希望大学数学虚拟教研室在“五校一社”的共同建设下，汇聚各方合力，创新教学机制，取得优质成果，为我国高等教育高质量发展作出更大贡献。\r\n\r\n教育部“大学数学国家虚拟教研室”建设由我校国家级教学名师朱士信教授牵头，合肥工业大学、东南大学、北京科技大学、哈尔滨工业大学、西南交通大学五所“双一流”建设高校和高等教育出版社联合建设。虚拟教研室弥补了传统教研方式的不足，推动大学数学教研形式进一步动态开放。依托这一平台， 广大数学教师将通过现代信息技术与教育教学的深度融合，广泛开展教育教学研究交流活动，全面提高教书育人能力，提升人才培养质量。\r\n\r\n与会嘉宾在致辞中表示，参与建设的各成员单位将积极推荐名师、名课、名教材的资深教师团队投入到大学数学虚拟教研室的运行、教学研究、凝练成果等建设环节中，创新教研形态，加强教学研究，共建优质资源，大力开展教师培训。\r\n\r\n研讨会上，朱士信教授从建设总体规划、具体建设目标、整体架构三个方面，详细介绍了大学数学国家虚拟教研室的建设方案与举措。全国首届数学教学创新大赛教授组第一名、东南大学陈建龙教授以《线性代数课程教学的创新实践》为题，从基本情况、理念目标、资源创新、方法创新、教学成效五个方面就线性代数课程设计进行了示范展示。\r\n\r\n当天的启动仪式和研讨会受到全国高校数学学科负责人和骨干教师的广泛关注，近700名各高校骨干教师通过线上直播和收看转播的方式参加了本次启动仪式和研讨会。\r\n\r\n本科生院院长陈翌庆主持会议，本科生院、数学学院等相关单位负责同志出席活动。\r\n', '2022-04-30 17:13:47', NULL);
INSERT INTO `notice` VALUES (10, '学校推进预算管理一体化建设工作', '4月29日，学校组织召开了预算管理一体化建设工作推进会议，贯彻落实财政部实施中央预算管理一体化建设有关工作要求。校党委书记余其俊出席会议并讲话，校长梁樑主持会议，校领导吴玉程、陈刚、陈鸿海、刘晓平、刘志峰、季益洪、郑磊出席会议。\r\n\r\n余其俊强调，学校高度重视预算管理一体化系统建设工作，把一体化系统建设作为“打基础、管长远”、提高学校治理和财务管理水平的重要工作内容。预算管理一体化建设要求高、时间紧、任务重，全校各单位要切实提高政治站位，深刻认识重要意义，准确把握工作要求，主动思考、提前筹备、抓实抓细、多措并举，确保预算管理一体化工作扎实推进、落实落细、取得实效。\r\n\r\n梁樑指出，预算管理一体化建设是深化预算管理制度改革在高校推广实施的重要部署，旨在运用系统化思维和信息化手段，全面提升预算管理规范化、科学化和标准化水平。各单位要以一体化建设为契机，倒逼管理机制改革和管理水平提升。一要提高认识、统一思想，充分认识一体化建设在落实党中央、国务院关于坚持“过紧日子”要求、提高财政资金使用绩效等方面的重要意义，高度重视一体化建设对学校治理和事业发展的重大影响；二要改进思路、转变模式，响应财政部预算管理新要求，打破传统惯性思维，转变“边做边想”的工作模式，充分预估实施周期与执行问题，早筹划、早立项、早启动；三要加强组织、压实责任，成立预算管理一体化实施工作领导小组，对标一体化建设标准和时限要求，紧密配合、共同推进预算管理一体化系统顺利上线运行；四要讲求绩效、追踪问责，在加快预算执行、规范资金使用的基础上，不断提高资金使用效益，切实做到“花钱必问效、无效必问责”。\r\n\r\n会上，校长助理、财务处处长吴华清作了关于预算管理一体化建设推进工作的报告，传达了财政部、教育部关于推广实施中央预算管理一体化建设的文件精神，剖析了我校当前预算管理存在的问题与不足。报告指出，预算一体化作为高校财经治理现代化的重要基础平台，对高校财务与业务集成融合、项目预算科学化、预算执行透明化与预算约束刚性化等提出极大的挑战。学校各部门要按照财政部、教育部统一时间安排等要求，进一步强化绩效意识，转变工作模式，夯实数据基础，完善项目规划与实施，增强风险防范与控制，合理分工协作，共同确保我校预算一体化系统接得住、用的好。\r\n\r\n会议以线上线下视频会议形式同步进行，学校各二级单位党政主要负责人在主会场参加会议，全校副处级以上干部在分会场参加会议。\r\n', '2022-04-29 13:15:28', NULL);
INSERT INTO `notice` VALUES (11, '实验室信息上报公告', '新冠核酸检测信息平台已上线实验室信息上报功能，请各新冠核酸检测机构将本单位的实验室建设情况汇总上报。\r\n若本单位无列表中的设备，填写“0”不得为空。\r\n若本单位无移动方舱实验室可不填写移动方舱实验室的相关信息。\r\n如本单位有多个新冠核酸检测实验室，请将数据进行汇总后上报。\r\n如今后本单位实验室信息有所改变，请及时在“实验室信息”模块中进行更新。\r\n操作手册见附件，也可系统公告栏中查看，请仔细阅读，认真填报。 ', '2022-04-23 17:29:00', NULL);
INSERT INTO `notice` VALUES (12, '21~22学年第二学期工作计划', '以“邓小平理论”和“三个代表”重要思想为指导,认真学习贯彻党的十七大精神,全面贯彻党的教育方针，以素质教育为主题,以教育科研为先导,以办群众满意的学校为宗旨，积极推进教育改革和创新，多方协调资金，努力改善办学条件，加强教师队伍的自身建设，进一步加大教育教学管理的力度，全面提高教育教学质量。', '2022-04-11 19:29:08', NULL);
INSERT INTO `notice` VALUES (13, '围绕全面提高教学质量，加大教学管理力度', '对教师的教学工作要从备课、上课、作业批改、辅导、听课评课、出勤等方面加强管理。\r\n\r\n具体措施:\r\n1、每月检查一项，检查有记录，结果公开;与绩效工资挂钩，奖惩兑现;\r\n2、抓好课堂教学，向课堂要质量，开展校级优质课活动;\r\n3、组织一次教案互览、作业互览，评出优胜者给予奖励。对学生的学习管理，一方面是对学生的学习过程管理和学习效果的管理，另一方面是对学生学习习惯、学习方法、学习能力的培养，这一方面较前一方面更为重要，开学初要订出方案，要与任课教师共同研究制定，不能由领导凭空而定。\r\n', '2022-04-27 17:29:19', NULL);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
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
  `professional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(255) NULL DEFAULT NULL,
  `class_num` int(255) NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师教学科目',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `day_number` int(11) NULL DEFAULT 0 COMMENT '本日预约次数',
  `week_number` int(11) NULL DEFAULT 0 COMMENT '本周预约次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '贺情岚', '13145209999', '952158354@qq.com', '123456', 1, '北京大学', '软件学院', '软件工程', NULL, NULL, '', '女', 'upload\\img\\avatar\\bc3c7c4fc015434ebfe2faebe03bc43d.jpg', '2022-03-26 12:38:07', 0, 0);
INSERT INTO `user` VALUES (2, '徐诚怡', '15049653274', 'admin@163.com', 'abcdef', 1, '清华大学', '软件学院', '', 18, 5, '数据结构与算法', '男', 'img/userlogo.png', '2022-04-08 20:34:50', 0, 0);
INSERT INTO `user` VALUES (3, '计韵冶', '18495176136', '1648399827@qq.com', 'qwerasdf', 3, '浙江大学', '数学学院', '信息与计算科学专业', 19, 2, NULL, '女', 'img/userlogo.png', '2022-03-30 20:34:59', 0, 0);
INSERT INTO `user` VALUES (4, '赵磊伟', '17372967367', 'zhaoleiwei@outlook.com', 'qey1324', 3, '同济大学', '软件学院', '大数据与人工智能', 18, 1, NULL, '男', 'img/userlogo.png', '2022-04-01 12:35:12', 0, 0);
INSERT INTO `user` VALUES (5, '朱诺民', '18407862439', 'haihaihai@gmail.com', '112233abc', 2, '山东大学', '计算机学院', '', 21, 6, 'C++', '女', 'img/userlogo.png', '2022-04-15 16:35:27', 0, 0);
INSERT INTO `user` VALUES (6, '王莲奇', '14623342143', '12324wlq@qq.com', 'dsafwr32', 2, '华南理工大学', '电子信息工程学院', '电子科学与技术', 19, 2, 'Python', '女', 'img/userlogo.png', '2022-04-12 20:35:44', 0, 0);
INSERT INTO `user` VALUES (7, '常岩州', '17021219332', 'ieuw329@163.com', '42rfewg', 4, '', NULL, NULL, NULL, NULL, NULL, '男', 'img/userlogo.png', '2022-03-31 20:35:53', 0, 0);
INSERT INTO `user` VALUES (8, '钟泳祺', '19983993222', 'qq32174752@sohu.com', 'dsrgv432', 4, '', NULL, NULL, NULL, NULL, NULL, '男', 'img/userlogo.png', '2022-04-04 20:36:05', 0, 0);
INSERT INTO `user` VALUES (9, '何梓仪', '13858185698', '123123456@qq.com', '3fads@rg3', 3, '合肥工业大学', '软件学院', '软件工程', 20, 1, NULL, '女', 'img/userlogo.png', '2022-04-20 18:45:13', 0, 0);
INSERT INTO `user` VALUES (10, '姚丁嘉', '18077541312', 'aaaaaaa@qq.com', 'afefs32231t5', 2, '武汉大学', '网络安全学院', NULL, 19, 2, '网络安全', '男', 'img/userlogo.png', '2022-04-06 17:36:25', 0, 0);
INSERT INTO `user` VALUES (11, '沈子琳', '18635462651', '13846236@163.com', 'rerg23', 4, NULL, '', NULL, NULL, NULL, NULL, '男', 'img/userlogo.png', '2022-04-08 10:06:40', 0, 0);
INSERT INTO `user` VALUES (12, '许令艺', '15054046779', 'sdfkh@163.com', 'erfbth23111', 2, '中山大学', '电子信息工程学院', '信息与通信工程', 18, 3, 'Java', '女', 'img/userlogo.png', '2022-04-29 20:37:03', 0, 0);
INSERT INTO `user` VALUES (13, '钟艺军', '13311517037', '39824gfa@outlook.com', '111222333', 3, '天津大学', '计算机学院', '信息安全', 22, 2, NULL, '男', 'img/userlogo.png', '2022-05-01 01:37:16', 0, 0);
INSERT INTO `user` VALUES (14, '秦如钟', '14702687829', '12123123@icloud.com', '123456789', 4, NULL, NULL, NULL, NULL, NULL, NULL, '男', 'img/userlogo.png', '2022-04-23 06:50:24', 0, 0);
INSERT INTO `user` VALUES (15, '裴念恬', '19115494189', '123762849@qq.com', 'fgbed123', 3, '合肥工业大学', '软件学院', '软件工程', 21, 5, NULL, '女', 'img/userlogo.png', '2022-04-24 13:37:41', 0, 0);
INSERT INTO `user` VALUES (16, '欧阳远凡', '17122658262', 'oyyf6666@gmail.com', 'jikkmnt1122', 3, '合肥工业大学', '软件学院', '软件工程', 18, 3, NULL, '男', 'img/userlogo.png', '2022-05-06 11:38:00', 0, 0);
INSERT INTO `user` VALUES (17, '范易嵩', '13871867039', '88888888@qq.com', '112233', 3, '合肥工业大学', '计算机学院', '计算机科学与技术', 18, 2, NULL, '男', 'img/userlogo.png', '2022-05-10 10:38:10', 0, 0);
INSERT INTO `user` VALUES (18, '洪敏瑛', '13535633973', '99999999@qq.com', '666666', 3, '合肥工业大学', '计算机学院', '通信工程', 19, 1, NULL, '女', 'img/userlogo.png', '2022-05-12 03:32:16', 0, 0);
INSERT INTO `user` VALUES (19, '开放式', '13123416256', 'skfdj@qq.com', '123456', 3, '合肥工业大学', '工商管理学院', '工商管理学', 18, 2, NULL, '男', 'img/userlogo.png', NULL, 0, 0);
INSERT INTO `user` VALUES (21, 'abab', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/userlogo.png', NULL, 0, 0);
INSERT INTO `user` VALUES (22, '小明呀', '12345678901', '1640667118@qq.com', 'qwerasdf', 4, NULL, NULL, NULL, NULL, NULL, NULL, '男', 'upload\\img\\avatar\\80a41569b2e742a1a1c13131d52ca687.png', '2022-05-28 14:00:12', 0, 0);

-- ----------------------------
-- Procedure structure for reset_auto_increment
-- ----------------------------
DROP PROCEDURE IF EXISTS `reset_auto_increment`;
delimiter ;;
CREATE PROCEDURE `reset_auto_increment`()
BEGIN
	#Routine body goes here...
	ALTER TABLE user AUTO_INCREMENT = 1;
	ALTER TABLE notice AUTO_INCREMENT = 1;
	ALTER TABLE resource AUTO_INCREMENT = 1;
	ALTER TABLE experiment AUTO_INCREMENT = 1;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for update_user_day_number
-- ----------------------------
DROP PROCEDURE IF EXISTS `update_user_day_number`;
delimiter ;;
CREATE PROCEDURE `update_user_day_number`()
BEGIN
	#Routine body goes here...
	UPDATE user SET day_number = 0;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for update_user_week_number
-- ----------------------------
DROP PROCEDURE IF EXISTS `update_user_week_number`;
delimiter ;;
CREATE PROCEDURE `update_user_week_number`()
BEGIN
	#Routine body goes here...
	UPDATE user SET week_number = 0;
END
;;
delimiter ;

-- ----------------------------
-- Event structure for day_number_timer
-- ----------------------------
DROP EVENT IF EXISTS `day_number_timer`;
delimiter ;;
CREATE EVENT `day_number_timer`
ON SCHEDULE
EVERY '1' DAY STARTS '2022-05-26 00:00:00'
ON COMPLETION PRESERVE
DO call update_user_day_number()
;;
delimiter ;

-- ----------------------------
-- Event structure for reset_auto_increment_timer
-- ----------------------------
DROP EVENT IF EXISTS `reset_auto_increment_timer`;
delimiter ;;
CREATE EVENT `reset_auto_increment_timer`
ON SCHEDULE
EVERY '1' HOUR STARTS '2022-05-25 21:30:45'
DO call reset_auto_increment()
;;
delimiter ;

-- ----------------------------
-- Event structure for week_number_timer
-- ----------------------------
DROP EVENT IF EXISTS `week_number_timer`;
delimiter ;;
CREATE EVENT `week_number_timer`
ON SCHEDULE
EVERY '1' WEEK STARTS '2022-05-26 00:00:00'
ON COMPLETION PRESERVE
DO call update_user_week_number()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
