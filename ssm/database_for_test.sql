# Host: 127.0.01  (Version: 5.5.15)
# Date: 2019-02-21 17:16:49
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "tickets"
#

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `order_id` int(20) NOT NULL,
  `entry_date` date DEFAULT NULL,
  `passagers` varchar(50) DEFAULT NULL,
  `passagers_nums` int(2) DEFAULT NULL,
  `dep_city` varchar(3) DEFAULT NULL,
  `arr_city` varchar(3) DEFAULT NULL,
  `flight_date` date DEFAULT NULL,
  `sold_price` int(5) DEFAULT NULL,
  `clear_price` int(5) DEFAULT NULL,
  `profit` int(5) DEFAULT NULL,
  `ticket_no` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tickets"
#

INSERT INTO `tickets` VALUES (397459521,'2019-02-15','ZHU/XIAOHONG',1,'CXR','CTU','2019-03-01',830,800,30,68315859),(397517165,'2019-02-15','YANG/SHENGSONG',1,'CTU','CXR','2019-02-26',830,800,30,68261569),(397545323,'2019-02-15','NIU/CHAOLING',1,'CXR','CTU','2019-03-01',830,800,30,68315859),(397554005,'2019-02-15','ZHANG/CHUNXIA',1,'CTU','DAD','2019-03-09',1030,1000,30,68094215),(397556367,'2019-02-15','WAN/LIQIONG',1,'DAD','CTU','2019-02-25',830,800,30,68251595),(397598350,'2019-02-15','ZHU/ZHIYONG',1,'CXR','CTU','2019-03-03',830,800,30,68031219),(397630375,'2019-02-16','LIN/DAN,LIU/RUI',2,'DAD','CTU','2019-03-06',1660,1600,60,68064295);
