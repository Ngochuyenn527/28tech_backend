CREATE DATABASE  IF NOT EXISTS `estateadvance`;
USE `estateadvance`;

DROP TABLE IF EXISTS `rentarea`;
CREATE TABLE `rentarea` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `value` int(11) DEFAULT NULL,
                            `buildingid` bigint(20) DEFAULT NULL,
                            `createddate` datetime DEFAULT NULL,
                            `modifieddate` datetime DEFAULT NULL,
                            `createdby` varchar(255) DEFAULT NULL,
                            `modifiedby` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `rentarea_building` (`buildingid`),
                            CONSTRAINT `rentarea_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`)
);
--
-- Dumping data for table `rentarea`
--

LOCK TABLES `rentarea` WRITE;
INSERT INTO `rentarea` VALUES (1,100,1,NULL,NULL,NULL,NULL),(2,200,1,NULL,NULL,NULL,NULL),(3,200,2,NULL,NULL,NULL,NULL),(4,300,2,NULL,NULL,NULL,NULL),(5,400,2,NULL,NULL,NULL,NULL),(6,300,3,NULL,NULL,NULL,NULL),(7,400,3,NULL,NULL,NULL,NULL),(8,500,3,NULL,NULL,NULL,NULL),(9,100,4,NULL,NULL,NULL,NULL),(10,400,4,NULL,NULL,NULL,NULL),(11,250,4,NULL,NULL,NULL,NULL),(24,700,6,NULL,NULL,NULL,NULL);
UNLOCK TABLES;


--
-- Table structure for table `building`
--
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
                            `id` bigint(20) AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL,
                            `street` varchar(255) DEFAULT NULL,
                            `ward` varchar(255) DEFAULT NULL,
                            `district` varchar(255) DEFAULT NULL,
                            `structure` varchar(255) DEFAULT NULL,
                            `numberofbasement` int(11) DEFAULT NULL,
                            `direction` varchar(255) DEFAULT NULL,
                            `rentprice` int(11) DEFAULT NULL,
                            `rentpricedescription` text,
                            `servicefee` varchar(255) DEFAULT NULL,
                            `waterfee` varchar(255) DEFAULT NULL,
                            `electricityfee` varchar(255) DEFAULT NULL,
                            `deposit` varchar(255) DEFAULT NULL,
                            `brokeragefee` decimal(13,2) DEFAULT NULL,
                            `type` varchar(255) DEFAULT NULL,
                            `createddate` datetime DEFAULT NULL,
                            `modifieddate` datetime DEFAULT NULL,
                            `createdby` varchar(255) DEFAULT NULL,
                            `modifiedby` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
);

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
INSERT INTO `building` VALUES

                           (1,'Nam Giao Building Tower','59 phan xích long','Phường 2','QUAN_1','Cao ốc',2,'Tây Nam',15,'15 triệu/m2','100000','3000','25000','200000','100000','TANG_TRET,NGUYEN_CAN',NULL,NULL,NULL,NULL),
                           (2,'ACM Tower','96 cao thắng','Phường 4','QUAN_2','Xoắn ốc',2, 'Tây Nam',18,'18 triệu/m2','100000','3000','25000','200000','100000','NGUYEN_CAN',NULL,NULL,NULL,NULL),
                           (3,'Alpha 2 Building Tower','153 nguyễn đình chiểu','Phường 6','QUAN_1','Xoắn ốc',1,'Tây Nam',20,'20 triệu/m2','100000','3000','25000','200000','100000','NOI_THAT',NULL,NULL,NULL,NULL),
                           (4,'IDD 1 Building','111 Lý Chính Thắng','Phường 7','QUAN_4','Xoắn ốc',1,'Tây Nam',12,'12 triệu/m2','100000','3000','25000','200000','100000','TANG_TRET,NGUYEN_CAN,NOI_THAT',NULL,NULL,NULL,NULL),
                           (6,'test',NULL,NULL,NULL,NULL,10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `code` varchar(255) NOT NULL,
                        `createddate` datetime DEFAULT NULL,
                        `modifieddate` datetime DEFAULT NULL,
                        `createdby` varchar(255) DEFAULT NULL,
                        `modifiedby` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
);

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'Quản lý','MANAGER',NULL,NULL,NULL,NULL),(2,'Nhân viên','STAFF',NULL,NULL,NULL,NULL);
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `fullname` varchar(255) DEFAULT NULL,
                        `phone` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `status` int(11) NOT NULL,
                        `createddate` datetime DEFAULT NULL,
                        `modifieddate` datetime DEFAULT NULL,
                        `createdby` varchar(255) DEFAULT NULL,
                        `modifiedby` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `username` (`username`)
);

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',NULL,NULL,1,NULL,NULL,NULL,NULL),(2,'nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van b',NULL,NULL,1,NULL,NULL,NULL,NULL),(3,'nguyenvanc','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van c',NULL,NULL,1,NULL,NULL,NULL,NULL),(4,'nguyenvand','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van d',NULL,NULL,1,NULL,NULL,NULL,NULL);
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `role_id` bigint(20) NOT NULL,
                             `user_id` bigint(20) NOT NULL,
                             `createddate` datetime DEFAULT NULL,
                             `modifieddate` datetime DEFAULT NULL,
                             `createdby` varchar(255) DEFAULT NULL,
                             `modifiedby` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `fk_user_role` (`user_id`),
                             KEY `fk_role_user` (`role_id`),
                             CONSTRAINT `fk_role_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                             CONSTRAINT `fk_user_role` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` VALUES (1,1,1,NULL,NULL,NULL,NULL),(2,2,2,NULL,NULL,NULL,NULL),(3,2,3,NULL,NULL,NULL,NULL),(4,2,4,NULL,NULL,NULL,NULL);
UNLOCK TABLES;

--
-- Dumping events for database 'estateadvance'
--

--
-- Dumping routines for database 'estateadvance'
--

