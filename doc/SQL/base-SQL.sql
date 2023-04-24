-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: database-1.ctdc1adeoozq.ap-northeast-1.rds.amazonaws.com    Database: base
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
                                      `SCHED_NAME` varchar(120) NOT NULL,
                                      `TRIGGER_NAME` varchar(190) NOT NULL,
                                      `TRIGGER_GROUP` varchar(190) NOT NULL,
                                      `BLOB_DATA` blob,
                                      PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
                                      KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
                                      CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_BLOB_TRIGGERS`
--

LOCK TABLES `QRTZ_BLOB_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CALENDARS`
--

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_CALENDARS` (
                                  `SCHED_NAME` varchar(120) NOT NULL,
                                  `CALENDAR_NAME` varchar(190) NOT NULL,
                                  `CALENDAR` blob NOT NULL,
                                  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CALENDARS`
--

LOCK TABLES `QRTZ_CALENDARS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
                                      `SCHED_NAME` varchar(120) NOT NULL,
                                      `TRIGGER_NAME` varchar(190) NOT NULL,
                                      `TRIGGER_GROUP` varchar(190) NOT NULL,
                                      `CRON_EXPRESSION` varchar(120) NOT NULL,
                                      `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
                                      PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
                                      CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CRON_TRIGGERS`
--

LOCK TABLES `QRTZ_CRON_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('baseQZ','repeatTrigger','Common','0 0/30 * * * ?','Asia/Taipei');
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_FIRED_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
                                       `SCHED_NAME` varchar(120) NOT NULL,
                                       `ENTRY_ID` varchar(95) NOT NULL,
                                       `TRIGGER_NAME` varchar(190) NOT NULL,
                                       `TRIGGER_GROUP` varchar(190) NOT NULL,
                                       `INSTANCE_NAME` varchar(190) NOT NULL,
                                       `FIRED_TIME` bigint NOT NULL,
                                       `SCHED_TIME` bigint NOT NULL,
                                       `PRIORITY` int NOT NULL,
                                       `STATE` varchar(16) NOT NULL,
                                       `JOB_NAME` varchar(190) DEFAULT NULL,
                                       `JOB_GROUP` varchar(190) DEFAULT NULL,
                                       `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
                                       `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
                                       PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
                                       KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
                                       KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
                                       KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
                                       KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
                                       KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
                                       KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_FIRED_TRIGGERS`
--

LOCK TABLES `QRTZ_FIRED_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_JOB_DETAILS` (
                                    `SCHED_NAME` varchar(120) NOT NULL,
                                    `JOB_NAME` varchar(190) NOT NULL,
                                    `JOB_GROUP` varchar(190) NOT NULL,
                                    `DESCRIPTION` varchar(250) DEFAULT NULL,
                                    `JOB_CLASS_NAME` varchar(250) NOT NULL,
                                    `IS_DURABLE` varchar(1) NOT NULL,
                                    `IS_NONCONCURRENT` varchar(1) NOT NULL,
                                    `IS_UPDATE_DATA` varchar(1) NOT NULL,
                                    `REQUESTS_RECOVERY` varchar(1) NOT NULL,
                                    `JOB_DATA` blob,
                                    PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
                                    KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
                                    KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

LOCK TABLES `QRTZ_JOB_DETAILS` WRITE;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('baseQZ','RepeatJob','Common','範例排程','tw.gov.idb.base.job.repeatJob.RepeatJob','1','1','1','0',_binary '¬\휰sr\0org.quartz.JobDataMap°迩°\˂\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap\蜃û\ŝ(\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\殭(v\n\΂\0Z\0dirtyL\0mapt\0Ljava/util/Map;xp\0sr\0java.util.HashMap\ځ\Ö`\у\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0');
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_LOCKS`
--

DROP TABLE IF EXISTS `QRTZ_LOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_LOCKS` (
                              `SCHED_NAME` varchar(120) NOT NULL,
                              `LOCK_NAME` varchar(40) NOT NULL,
                              PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_LOCKS`
--

LOCK TABLES `QRTZ_LOCKS` WRITE;
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
INSERT INTO `QRTZ_LOCKS` VALUES ('baseQZ','STATE_ACCESS'),('baseQZ','TRIGGER_ACCESS');
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
                                            `SCHED_NAME` varchar(120) NOT NULL,
                                            `TRIGGER_GROUP` varchar(190) NOT NULL,
                                            PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

LOCK TABLES `QRTZ_PAUSED_TRIGGER_GRPS` WRITE;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
                                        `SCHED_NAME` varchar(120) NOT NULL,
                                        `INSTANCE_NAME` varchar(190) NOT NULL,
                                        `LAST_CHECKIN_TIME` bigint NOT NULL,
                                        `CHECKIN_INTERVAL` bigint NOT NULL,
                                        PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SCHEDULER_STATE`
--

LOCK TABLES `QRTZ_SCHEDULER_STATE` WRITE;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('baseQZ','2008019NB501682066675354',1682066980224,20000);
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
                                        `SCHED_NAME` varchar(120) NOT NULL,
                                        `TRIGGER_NAME` varchar(190) NOT NULL,
                                        `TRIGGER_GROUP` varchar(190) NOT NULL,
                                        `REPEAT_COUNT` bigint NOT NULL,
                                        `REPEAT_INTERVAL` bigint NOT NULL,
                                        `TIMES_TRIGGERED` bigint NOT NULL,
                                        PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
                                        CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPLE_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
                                         `SCHED_NAME` varchar(120) NOT NULL,
                                         `TRIGGER_NAME` varchar(190) NOT NULL,
                                         `TRIGGER_GROUP` varchar(190) NOT NULL,
                                         `STR_PROP_1` varchar(512) DEFAULT NULL,
                                         `STR_PROP_2` varchar(512) DEFAULT NULL,
                                         `STR_PROP_3` varchar(512) DEFAULT NULL,
                                         `INT_PROP_1` int DEFAULT NULL,
                                         `INT_PROP_2` int DEFAULT NULL,
                                         `LONG_PROP_1` bigint DEFAULT NULL,
                                         `LONG_PROP_2` bigint DEFAULT NULL,
                                         `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
                                         `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
                                         `BOOL_PROP_1` varchar(1) DEFAULT NULL,
                                         `BOOL_PROP_2` varchar(1) DEFAULT NULL,
                                         PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
                                         CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPROP_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPROP_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `QRTZ_TRIGGERS` (
                                 `SCHED_NAME` varchar(120) NOT NULL,
                                 `TRIGGER_NAME` varchar(190) NOT NULL,
                                 `TRIGGER_GROUP` varchar(190) NOT NULL,
                                 `JOB_NAME` varchar(190) NOT NULL,
                                 `JOB_GROUP` varchar(190) NOT NULL,
                                 `DESCRIPTION` varchar(250) DEFAULT NULL,
                                 `NEXT_FIRE_TIME` bigint DEFAULT NULL,
                                 `PREV_FIRE_TIME` bigint DEFAULT NULL,
                                 `PRIORITY` int DEFAULT NULL,
                                 `TRIGGER_STATE` varchar(16) NOT NULL,
                                 `TRIGGER_TYPE` varchar(8) NOT NULL,
                                 `START_TIME` bigint NOT NULL,
                                 `END_TIME` bigint DEFAULT NULL,
                                 `CALENDAR_NAME` varchar(190) DEFAULT NULL,
                                 `MISFIRE_INSTR` smallint DEFAULT NULL,
                                 `JOB_DATA` blob,
                                 PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
                                 KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
                                 KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
                                 KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
                                 KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
                                 KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
                                 KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
                                 KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
                                 KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
                                 KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
                                 KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
                                 KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
                                 KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
                                 CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_TRIGGERS`
--

LOCK TABLES `QRTZ_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_TRIGGERS` VALUES ('baseQZ','repeatTrigger','Common','RepeatJob','Common',NULL,1682067600000,-1,0,'WAITING','CRON',1682066675000,0,NULL,0,'');
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aplog`
--

DROP TABLE IF EXISTS `aplog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aplog` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `userid` varchar(100) DEFAULT NULL COMMENT '使用者ID',
                         `page` varchar(2000) DEFAULT NULL COMMENT '功能頁面',
                         `url` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '使用功能',
                         `accessTime` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '存取時間',
                         `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '使用者輸入資料',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2740 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aplog`
--

LOCK TABLES `aplog` WRITE;
/*!40000 ALTER TABLE `aplog` DISABLE KEYS */;
/*!40000 ALTER TABLE `aplog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cityarea`
--

DROP TABLE IF EXISTS `cityarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cityarea` (
                            `city` varchar(10) NOT NULL COMMENT '縣市',
                            `cityId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '縣市代碼',
                            `area` varchar(10) NOT NULL COMMENT '區鄉鎮',
                            `areaId` varchar(10) DEFAULT NULL,
                            UNIQUE KEY `cityarea_UN` (`area`,`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cityarea`
--

LOCK TABLES `cityarea` WRITE;
/*!40000 ALTER TABLE `cityarea` DISABLE KEYS */;
INSERT INTO `cityarea` VALUES ('基隆市','01','七堵區','07'),('澎湖縣','20','七美鄉','01'),('臺南市','14','七股區','35'),('屏東縣','16','三地門鄉','25'),('新北市','03','三峽區','25'),('宜蘭縣','19','三星鄉','01'),('高雄市','15','三民區','03'),('苗栗縣','07','三灣鄉','17'),('苗栗縣','07','三義鄉','16'),('新北市','03','三芝區','28'),('新北市','03','三重區','27'),('臺南市','14','下營區','33'),('臺中市','08','中區','38'),('新北市','03','中和區','26'),('嘉義縣','13','中埔鄉','06'),('桃園市','04','中壢區','07'),('南投縣','10','中寮鄉','04'),('基隆市','01','中山區','02'),('臺北市','02','中山區','11'),('基隆市','01','中正區','03'),('臺北市','02','中正區','09'),('臺南市','14','中西區','31'),('屏東縣','16','九如鄉','17'),('雲林縣','11','二崙鄉','16'),('彰化縣','09','二林鎮','01'),('彰化縣','09','二水鄉','07'),('新竹縣','06','五峰鄉','09'),('宜蘭縣','19','五結鄉','02'),('新北市','03','五股區','29'),('臺南市','14','仁德區','30'),('基隆市','01','仁愛區','05'),('南投縣','10','仁愛鄉','08'),('高雄市','15','仁武區','09'),('彰化縣','09','伸港鄉','13'),('屏東縣','16','佳冬鄉','23'),('臺南市','14','佳里區','34'),('屏東縣','16','來義鄉','10'),('基隆市','01','信義區','06'),('臺北市','02','信義區','05'),('南投縣','10','信義鄉','10'),('雲林縣','11','元長鄉','14'),('花蓮縣','18','光復鄉','09'),('屏東縣','16','內埔鄉','11'),('臺北市','02','內湖區','04'),('高雄市','15','內門區','18'),('桃園市','04','八德區','13'),('新北市','03','八里區','03'),('苗栗縣','07','公館鄉','06'),('臺南市','14','六甲區','36'),('嘉義縣','13','六腳鄉','03'),('高雄市','15','六龜區','22'),('宜蘭縣','19','冬山鄉','09'),('高雄市','15','前金區','11'),('高雄市','15','前鎮區','23'),('新竹市','05','北區','03'),('臺中市','08','北區','28'),('臺南市','14','北區','12'),('新竹縣','06','北埔鄉','10'),('臺中市','08','北屯區','27'),('臺北市','02','北投區','10'),('彰化縣','09','北斗鎮','02'),('雲林縣','11','北港鎮','13'),('連江縣','22','北竿鄉','03'),('臺南市','14','北門區','13'),('臺東縣','17','卑南鄉','03'),('花蓮縣','18','卓溪鄉','13'),('苗栗縣','07','卓蘭鎮','12'),('臺南市','14','南化區','20'),('臺中市','08','南區','25'),('臺南市','14','南區','14'),('臺中市','08','南屯區','26'),('屏東縣','16','南州鄉','02'),('苗栗縣','07','南庄鄉','15'),('南投縣','10','南投市','13'),('臺北市','02','南港區','07'),('宜蘭縣','19','南澳鄉','03'),('連江縣','22','南竿鄉','01'),('雲林縣','11','口湖鄉','08'),('雲林縣','11','古坑鄉','06'),('花蓮縣','18','吉安鄉','12'),('南投縣','10','名間鄉','11'),('臺中市','08','后里區','23'),('臺中市','08','和平區','33'),('彰化縣','09','和美鎮','18'),('宜蘭縣','19','員山鄉','12'),('彰化縣','09','員林鎮','17'),('臺南市','14','善化區','15'),('雲林縣','11','四湖鄉','09'),('南投縣','10','國姓鄉','07'),('新北市','03','土城區','08'),('雲林縣','11','土庫鎮','10'),('新北市','03','坪林區','09'),('彰化縣','09','埔心鄉','15'),('南投縣','10','埔里鎮','06'),('彰化縣','09','埔鹽鄉','16'),('彰化縣','09','埤頭鄉','24'),('臺北市','02','士林區','01'),('宜蘭縣','19','壯圍鄉','11'),('花蓮縣','18','壽豐鄉','10'),('臺中市','08','外埔區','35'),('臺南市','14','大內區','16'),('臺北市','02','大同區','02'),('宜蘭縣','19','大同鄉','06'),('桃園市','04','大園區','08'),('彰化縣','09','大城鄉','11'),('嘉義縣','13','大埔鄉','11'),('雲林縣','11','大埤鄉','04'),('臺中市','08','大安區','02'),('臺北市','02','大安區','03'),('高雄市','15','大寮區','16'),('彰化縣','09','大村鄉','25'),('嘉義縣','13','大林鎮','13'),('高雄市','15','大樹區','15'),('臺東縣','17','大武鄉','12'),('苗栗縣','07','大湖鄉','01'),('桃園市','04','大溪區','01'),('臺中市','08','大甲區','07'),('高雄市','15','大社區','07'),('臺中市','08','大肚區','08'),('臺中市','08','大里區','09'),('臺中市','08','大雅區','20'),('嘉義縣','13','太保市','14'),('臺中市','08','太平區','22'),('臺東縣','17','太麻里鄉','13'),('臺南市','14','學甲區','18'),('臺南市','14','安南區','10'),('臺南市','14','安定區','21'),('臺南市','14','安平區','22'),('基隆市','01','安樂區','01'),('臺南市','14','官田區','23'),('宜蘭縣','19','宜蘭市','04'),('花蓮縣','18','富里鄉','01'),('新竹縣','06','寶山鄉','04'),('臺南市','14','將軍區','24'),('高雄市','15','小港區','02'),('新竹縣','06','尖石鄉','07'),('屏東縣','16','屏東市','18'),('臺南市','14','山上區','26'),('高雄市','15','岡山區','05'),('新竹縣','06','峨眉鄉','13'),('屏東縣','16','崁頂鄉','24'),('雲林縣','11','崙背鄉','17'),('高雄市','15','左營區','06'),('臺南市','14','左鎮區','28'),('嘉義縣','13','布袋鎮','18'),('新北市','03','平溪區','14'),('桃園市','04','平鎮區','12'),('臺東縣','17','延平鄉','10'),('高雄市','15','彌陀區','25'),('彰化縣','09','彰化市','12'),('臺南市','14','後壁區','27'),('苗栗縣','07','後龍鎮','08'),('桃園市','04','復興區','10'),('屏東縣','16','恆春鎮','22'),('臺東縣','17','成功鎮','16'),('臺北市','02','文山區','06'),('雲林縣','11','斗六市','18'),('雲林縣','11','斗南鎮','19'),('臺南市','14','新化區','25'),('屏東縣','16','新園鄉','19'),('花蓮縣','18','新城鄉','02'),('新竹縣','06','新埔鎮','06'),('屏東縣','16','新埤鄉','27'),('桃園市','04','新屋區','06'),('臺南市','14','新市區','19'),('新北市','03','新店區','16'),('嘉義縣','13','新港鄉','09'),('臺南市','14','新營區','17'),('臺中市','08','新社區','05'),('高雄市','15','新興區','26'),('新北市','03','新莊區','02'),('新竹縣','06','新豐鄉','08'),('高雄市','15','旗山區','28'),('高雄市','15','旗津區','35'),('屏東縣','16','春日鄉','05'),('基隆市','01','暖暖區','04'),('澎湖縣','20','望安鄉','03'),('嘉義縣','13','朴子市','15'),('高雄市','15','杉林區','33'),('臺中市','08','東勢區','21'),('雲林縣','11','東勢鄉','01'),('嘉義市','12','東區','01'),('新竹市','05','東區','02'),('臺中市','08','東區','34'),('臺南市','14','東區','07'),('臺南市','14','東山區','11'),('連江縣','22','東引鄉','04'),('臺東縣','17','東河鄉','08'),('屏東縣','16','東港鎮','14'),('嘉義縣','13','東石鄉','02'),('臺北市','02','松山區','08'),('新北市','03','板橋區','19'),('屏東縣','16','枋寮鄉','07'),('屏東縣','16','枋山鄉','30'),('雲林縣','11','林內鄉','02'),('新北市','03','林口區','20'),('高雄市','15','林園區','29'),('屏東縣','16','林邊鄉','26'),('臺南市','14','柳營區','32'),('桃園市','04','桃園區','04'),('高雄市','15','桃源區','34'),('嘉義縣','13','梅山鄉','01'),('高雄市','15','梓官區','36'),('臺中市','08','梧棲區','39'),('桃園市','04','楊梅區','09'),('高雄市','15','楠梓區','38'),('臺南市','14','楠西區','37'),('新北市','03','樹林區','21'),('高雄市','15','橋頭區','19'),('新竹縣','06','橫山鄉','03'),('臺南市','14','歸仁區','29'),('嘉義縣','13','民雄鄉','08'),('嘉義縣','13','水上鄉','07'),('雲林縣','11','水林鄉','03'),('南投縣','10','水里鄉','01'),('新北市','03','永和區','22'),('高雄市','15','永安區','37'),('臺南市','14','永康區','01'),('彰化縣','09','永靖鄉','09'),('新北市','03','汐止區','13'),('臺東縣','17','池上鄉','07'),('臺中市','08','沙鹿區','31'),('苗栗縣','07','泰安鄉','09'),('新北市','03','泰山區','24'),('屏東縣','16','泰武鄉','32'),('臺東縣','17','海端鄉','09'),('新北市','03','淡水區','23'),('新北市','03','深坑區','18'),('臺中市','08','清水區','36'),('高雄市','15','湖內區','31'),('新竹縣','06','湖口鄉','12'),('澎湖縣','20','湖西鄉','05'),('嘉義縣','13','溪口鄉','10'),('彰化縣','09','溪州鄉','21'),('彰化縣','09','溪湖鎮','22'),('屏東縣','16','滿州鄉','12'),('臺中市','08','潭子區','06'),('屏東縣','16','潮州鎮','09'),('金門縣','21','烈嶼鄉','01'),('新北市','03','烏來區','15'),('金門縣','21','烏坵鄉','06'),('臺中市','08','烏日區','01'),('高雄市','15','燕巢區','27'),('屏東縣','16','牡丹鄉','01'),('屏東縣','16','獅子鄉','33'),('苗栗縣','07','獅潭鄉','14'),('臺南市','14','玉井區','06'),('花蓮縣','18','玉里鎮','06'),('屏東縣','16','琉球鄉','31'),('花蓮縣','18','瑞穗鄉','04'),('新北市','03','瑞芳區','01'),('屏東縣','16','瑪家鄉','28'),('彰化縣','09','田中鎮','03'),('高雄市','15','田寮區','30'),('彰化縣','09','田尾鄉','06'),('高雄市','15','甲仙區','32'),('嘉義縣','13','番路鄉','12'),('澎湖縣','20','白沙鄉','02'),('臺南市','14','白河區','04'),('臺中市','08','石岡區','03'),('新北市','03','石碇區','12'),('新北市','03','石門區','11'),('宜蘭縣','19','礁溪鄉','07'),('彰化縣','09','社頭鄉','10'),('臺中市','08','神岡區','04'),('彰化縣','09','福興鄉','05'),('花蓮縣','18','秀林鄉','07'),('彰化縣','09','秀水鄉','04'),('新竹縣','06','竹北市','11'),('苗栗縣','07','竹南鎮','04'),('彰化縣','09','竹塘鄉','26'),('南投縣','10','竹山鎮','02'),('嘉義縣','13','竹崎鄉','17'),('新竹縣','06','竹東鎮','05'),('屏東縣','16','竹田鄉','20'),('臺東縣','17','綠島鄉','15'),('彰化縣','09','線西鄉','23'),('宜蘭縣','19','羅東鎮','05'),('高雄市','15','美濃區','04'),('嘉義縣','13','義竹鄉','16'),('臺東縣','17','臺東市','14'),('雲林縣','11','臺西鄉','15'),('新竹縣','06','芎林鄉','01'),('彰化縣','09','芬園鄉','20'),('彰化縣','09','花壇鄉','14'),('花蓮縣','18','花蓮市','03'),('彰化縣','09','芳苑鄉','19'),('苗栗縣','07','苑裡鎮','18'),('高雄市','15','苓雅區','10'),('苗栗縣','07','苗栗市','02'),('高雄市','15','茂林區','13'),('高雄市','15','茄萣區','14'),('南投縣','10','草屯鎮','03'),('連江縣','22','莒光鄉','02'),('雲林縣','11','莿桐鄉','05'),('屏東縣','16','萬丹鄉','03'),('屏東縣','16','萬巒鄉','04'),('花蓮縣','18','萬榮鄉','08'),('臺北市','02','萬華區','12'),('新北市','03','萬里區','10'),('新北市','03','蘆洲區','07'),('桃園市','04','蘆竹區','11'),('宜蘭縣','19','蘇澳鎮','10'),('臺東縣','17','蘭嶼鄉','11'),('雲林縣','11','虎尾鎮','11'),('雲林縣','11','褒忠鄉','07'),('嘉義市','12','西區','02'),('臺中市','08','西區','30'),('臺中市','08','西屯區','29'),('澎湖縣','20','西嶼鄉','04'),('臺南市','14','西港區','09'),('苗栗縣','07','西湖鄉','13'),('雲林縣','11','西螺鎮','12'),('桃園市','04','觀音區','02'),('臺中市','08','豐原區','24'),('花蓮縣','18','豐濱鄉','11'),('新北市','03','貢寮區','06'),('高雄市','15','路竹區','20'),('屏東縣','16','車城鄉','08'),('苗栗縣','07','通霄鎮','11'),('苗栗縣','07','造橋鄉','10'),('臺東縣','17','達仁鄉','02'),('高雄市','15','那瑪夏區','21'),('屏東縣','16','里港鄉','15'),('金門縣','21','金城鎮','05'),('金門縣','21','金寧鄉','04'),('新北市','03','金山區','05'),('臺東縣','17','金峰鄉','04'),('金門縣','21','金沙鎮','02'),('金門縣','21','金湖鎮','03'),('苗栗縣','07','銅鑼鄉','03'),('屏東縣','16','長治鄉','16'),('臺東縣','17','長濱鄉','06'),('臺東縣','17','關山鎮','01'),('臺南市','14','關廟區','08'),('新竹縣','06','關西鎮','02'),('高雄市','15','阿蓮區','24'),('嘉義縣','13','阿里山鄉','04'),('南投縣','10','集集鎮','12'),('新北市','03','雙溪區','04'),('屏東縣','16','霧台鄉','13'),('臺中市','08','霧峰區','32'),('苗栗縣','07','頭份鎮','05'),('宜蘭縣','19','頭城鎮','08'),('苗栗縣','07','頭屋鄉','07'),('新竹市','05','香山區','01'),('澎湖縣','20','馬公市','06'),('屏東縣','16','高樹鄉','06'),('南投縣','10','魚池鄉','09'),('高雄市','15','鳥松區','17'),('高雄市','15','鳳山區','12'),('花蓮縣','18','鳳林鎮','05'),('新北市','03','鶯歌區','17'),('屏東縣','16','鹽埔鄉','29'),('高雄市','15','鹽埕區','08'),('臺南市','14','鹽水區','05'),('彰化縣','09','鹿港鎮','08'),('嘉義縣','13','鹿草鄉','05'),('南投縣','10','鹿谷鄉','05'),('臺東縣','17','鹿野鄉','05'),('屏東縣','16','麟洛鄉','21'),('雲林縣','11','麥寮鄉','20'),('臺南市','14','麻豆區','03'),('高雄市','15','鼓山區','01'),('臺中市','08','龍井區','37'),('臺南市','14','龍崎區','02'),('桃園市','04','龍潭區','05'),('桃園市','04','龜山區','03');
/*!40000 ALTER TABLE `cityarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `companyName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司名稱',
                           `owner` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '負責人姓名',
                           `ownerIdn` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '負責人IDN',
                           `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司EMAIL',
                           `website` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '公司網址',
                           `uniformNumbers` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '統一編號',
                           `persons` bigint DEFAULT NULL COMMENT '員工人數',
                           `capital` bigint DEFAULT NULL COMMENT '資本額',
                           `city` varchar(100) DEFAULT NULL COMMENT '縣市',
                           `area` varchar(100) DEFAULT NULL COMMENT '鄉鎮市',
                           `addr` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
                           `issueDate` varchar(8) NOT NULL COMMENT '公司成立日期',
                           `updateTime` varchar(14) NOT NULL COMMENT '資料異動時間',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='CRUD範例TABLE';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',90,5,'臺南市','北區','CCCC','20200430','20230414104615'),(16,'紡拓','王OO','W123456789','tttt@yahoo.com.tw','','',10,2000,'屏東縣','九如鄉','','20210101','20230317172946');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_backup`
--

DROP TABLE IF EXISTS `company_backup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_backup` (
                                  `id` bigint NOT NULL,
                                  `companyName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司名稱',
                                  `owner` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '負責人姓名',
                                  `ownerIdn` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '負責人IDN',
                                  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司EMAIL',
                                  `website` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '公司網址',
                                  `uniformNumbers` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '統一編號',
                                  `persons` bigint DEFAULT NULL COMMENT '員工人數',
                                  `capital` bigint DEFAULT NULL COMMENT '資本額',
                                  `city` varchar(100) DEFAULT NULL COMMENT '縣市',
                                  `area` varchar(100) DEFAULT NULL COMMENT '鄉鎮市',
                                  `addr` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
                                  `issueDate` varchar(8) NOT NULL COMMENT '公司成立日期',
                                  `updateTime` varchar(14) NOT NULL COMMENT '資料異動時間',
                                  `backupTime` varchar(14) NOT NULL COMMENT '備份時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_backup`
--

LOCK TABLES `company_backup` WRITE;
/*!40000 ALTER TABLE `company_backup` DISABLE KEYS */;
INSERT INTO `company_backup` VALUES (1,'資拓宏宇','林XX','B123456789','invest@iisigroup.com','','12345678',NULL,NULL,'彰化縣','田尾鄉','','20110410','20230310142258','20230310143446'),(5,'紡拓','WWW','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230310143822','20230310143830'),(1,'資拓宏宇','林OO','B123456789','invest@iisigroup.com','','12345678',NULL,NULL,'彰化縣','田尾鄉','','20110410','20230310143446','20230310143851'),(6,'test','test','A123456789','33fe@gmail.com','','',NULL,NULL,NULL,NULL,'','20200101','20230310152249','20230310152312'),(7,'紡拓','X','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314105605','20230314105610'),(8,'紡拓','W','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314105909','20230314105952'),(9,'紡拓','EEE','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314110344','20230314110348'),(1,'資拓宏宇','林OO','B123456789','invest@iisigroup.com','','12345679',NULL,NULL,'彰化縣','田尾鄉','','20110410','20230310143851','20230314110409'),(1,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','12345679',NULL,NULL,'彰化縣','田尾鄉','','20110410','20230314110409','20230314110612'),(10,'紡拓','EEE','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314111115','20230314111122'),(10,'紡拓','EEE','W123456780','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314111122','20230314111127'),(11,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',NULL,NULL,'','','','20110410','20230314111319','20230314111335'),(12,'紡拓','EEE','B123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314111355','20230314111400'),(11,'資拓宏宇','林OO','B123456789','invest@iisigroup.com','','',NULL,NULL,'','','','20110410','20230314111335','20230314111404'),(14,'紡拓','W','B123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314111538','20230314111546'),(13,'資拓宏宇','EEE','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'','','','20210101','20230314111519','20230314111549'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',NULL,NULL,'','','','20110410','20230314111613','20230317145118'),(16,'紡拓','王OO','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'屏東縣','里港鄉','','20210101','20230316144843','20230317145131'),(16,'紡拓','王OO','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'屏東縣','九如鄉','','20210101','20230317145131','20230317172947'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',NULL,NULL,'臺東縣','關山鎮','','20110410','20230317145118','20230323150409'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',80,NULL,'臺東縣','關山鎮','','20110410','20230323150409','20230323163429'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',0,NULL,'臺東縣','關山鎮','','20110410','20230323163429','20230323163436'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',80,NULL,'臺東縣','關山鎮','','20110410','20230323163436','20230323163448'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',0,NULL,'臺東縣','關山鎮','','20110410','20230323163448','20230323163458'),(17,'TEST','R','W123456780','tttt@yahoo.com.tw','','',80,NULL,'','','','20210101','20230323163528','20230323163537'),(17,'TEST','R','W123456780','tttt@yahoo.com.tw','','',80,444,'','','','20210101','20230323163537','20230323163549'),(17,'TEST','R','W123456780','tttt@yahoo.com.tw','','',80,0,'','','','20210101','20230323163549','20230323163558'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',80,NULL,'臺東縣','關山鎮','','20110410','20230323163458','20230412160426'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',80,NULL,'臺東縣','關山鎮','','20200410','20230412160425','20230412160437'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',80,NULL,'臺東縣','關山鎮','','20200410','20230412160437','20230412160634'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',80,NULL,'臺東縣','關山鎮','','20200430','20230412160634','20230414095638'),(18,'TEST','WWW','W123456789','tttt@yahoo.com.tw','','',NULL,NULL,'臺東縣','臺東市','','20210101','20230414095706','20230414095723'),(18,'TEST','WWW','Q123456789','tttt@yahoo.com.tw','','',NULL,NULL,'臺東縣','臺東市','','20230101','20230414095723','20230414095729'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',90,5,'臺南市','北區','AAAA','20200430','20230414095638','20230414104359'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',90,5,'臺南市','北區','AAAA','20200430','20230414104359','20230414104411'),(15,'資拓宏宇','林OO','B123456780','invest@iisigroup.com','','',90,5,'臺南市','北區','CCCC','20200430','20230414104411','20230414104615');
/*!40000 ALTER TABLE `company_backup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
                        `itemId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能項ID',
                        `itemIdParent` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '父功能項ID',
                        `levelNo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能項層數',
                        `itemName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能項名稱',
                        `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '功能項 URL',
                        `sortOrder` decimal(10,0) NOT NULL COMMENT '功能項 排序',
                        `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能項種類1:功能列  0:標題列',
                        PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='功能表單';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('BASE',' ','0','底層系統',NULL,0,'0'),('BASE01','BASE','1','CRUD範例','',10,'0'),('BASE0101','BASE01','2','基本範例','/base0101_enter.action',10,'1'),('BASE0102','BASE01','2','完整範例','/base0102_enter.action',20,'1'),('BASE0103','BASE01','2','JS範例','',30,'0'),('BASE010301','BASE0103','3','AJAX範例','/base0103_enter.action',10,'1'),('BASE02','BASE','1','報表範例','',20,'0'),('BASE0201','BASE02','2','產製報表','/base0201_enter.action',10,'1'),('BASE03','BASE','1','其他範例','',30,'0'),('BASE0301','BASE03','2','排程範例','/base0301_enter.action',10,'1'),('BASE04','BASE','1','系統管理',NULL,40,'0'),('BASE0401','BASE04','2','帳號管理','/base0401_enter.action',10,'1'),('BASE0402','BASE04','2','功能項目管理','/base0402_enter.action',20,'1'),('BASE0403','BASE04','2','角色管理','/base0403_enter.action',30,'1'),('BASE05','BASE','1','TEST','/test_enter.action',50,'1');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `roleId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
                        `roleName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名稱',
                        `roleDesc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色說明',
                        PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='腳色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('admin','系統管理者','系統管理者'),('test','測試人員','測試人員');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_backup`
--

DROP TABLE IF EXISTS `role_backup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_backup` (
                               `roleId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色ID',
                               `roleName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名稱',
                               `roleDesc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色說明',
                               `backupTime` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_backup`
--

LOCK TABLES `role_backup` WRITE;
/*!40000 ALTER TABLE `role_backup` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_backup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_item`
--

DROP TABLE IF EXISTS `role_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_item` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `roleId` varchar(100) NOT NULL COMMENT '角色名稱',
                             `itemId` varchar(100) NOT NULL COMMENT '系統功能ID',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `role_item_UN` (`roleId`,`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_item`
--

LOCK TABLES `role_item` WRITE;
/*!40000 ALTER TABLE `role_item` DISABLE KEYS */;
INSERT INTO `role_item` VALUES (34,'a','a'),(35,'admin','BASE0101'),(26,'admin','BASE0102'),(13,'admin','BASE0103'),(14,'admin','BASE010301'),(15,'admin','BASE02'),(16,'admin','BASE0201'),(33,'admin','BASE0301'),(17,'admin','BASE0401'),(18,'admin','BASE0402'),(19,'admin','BASE0403');
/*!40000 ALTER TABLE `role_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_item_backup`
--

DROP TABLE IF EXISTS `role_item_backup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_item_backup` (
                                    `id` bigint NOT NULL,
                                    `roleId` varchar(100) NOT NULL COMMENT '角色名稱',
                                    `itemId` varchar(100) NOT NULL COMMENT '系統功能ID',
                                    `backupTime` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '備份時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_item_backup`
--

LOCK TABLES `role_item_backup` WRITE;
/*!40000 ALTER TABLE `role_item_backup` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_item_backup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
                            `jobId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'iobId',
                            `jobName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'job名稱',
                            `description` varchar(4000) DEFAULT NULL COMMENT 'job說明',
                            `param` varchar(100) DEFAULT NULL COMMENT 'job參數',
                            `scheduleTime` varchar(14) NOT NULL COMMENT '排定時間',
                            `exeStart` varchar(14) DEFAULT NULL COMMENT 'job啟動時間',
                            `exeEnd` varchar(14) DEFAULT NULL COMMENT 'job結束時間',
                            `status` varchar(100) NOT NULL COMMENT '執行狀態, 1-等待中、2-執行中、3-執行失敗、4-執行成功、5-已取消',
                            `result` varchar(4000) DEFAULT NULL COMMENT '執行結果',
                            `addId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '排程人員',
                            `addTime` varchar(14) NOT NULL COMMENT '新增排程時間',
                            `cancelId` varchar(100) DEFAULT NULL COMMENT '取消人員',
                            `cancelTime` varchar(14) DEFAULT NULL COMMENT '取消排程時間',
                            PRIMARY KEY (`jobId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='排程資訊';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('28cbd62e-1f34-4643-bbfb-eb9a8ace6572','測試2','',NULL,'20230330164200','20230330164201','20230330164201','3','Sample2Job發生錯誤','sys','20230330164113',NULL,NULL),('453b362e-f55e-4af7-a9d6-9d216f248349','測試6','測試6','測試6','20230330165000','20230330165001','20230330165001','3','Sample2Job發生錯誤 param:測試6','sys','20230330164921',NULL,NULL),('46d37412-b88e-4760-bb7f-fcf1255a611b','測試6','','aaa','20230419114800','20230419114800','20230419114806','4','Sample1Job結束 param:aaa','sys','20230419114654',NULL,NULL),('5e0e5bfd-4232-4d0c-a601-8297046d52f1','測試3','',NULL,'20230330164700','20230330164700','20230330164706','4','Sample1Job結束 param:null','sys','20230330164538',NULL,NULL),('a910f9d8-7258-4c2e-8d1d-703b429b933a','測試5','測試5','測試5','20230330165000','20230330165003','20230330165008','4','Sample1Job結束 param:測試5','sys','20230330164905',NULL,NULL),('ca693516-4c67-41b5-b408-5b30b633ad61','測試1','',NULL,'20230330164200','20230330164203','20230330164208','4','Sample1Job結束','sys','20230330164055',NULL,NULL),('d0b2a712-0f94-4143-9237-6b85fb0c5cce','測試4','測試4',NULL,'20230330164700','20230330164700','20230330164700','3','Sample2Job發生錯誤','sys','20230330164607',NULL,NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '使用者ID',
                        `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '使用者名稱',
                        `pw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '使用者密碼(SHA加密)',
                        `mail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '使用者信箱',
                        `empNo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '員工編號',
                        `memo` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '帳號說明',
                        `updateTime` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新時間',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='使用者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('sys','系統人員','C7C63D56AB9F48F1413B06C73977FE0FC4C3FCFD','sys@iisigroup.com','iisi-1','測試用系統人員',NULL),('test','測試人員','B6C5AE99C0C5AE41358A9820CC487FE3735A2441','abc@aaa','abc','abc','20230209155137');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `userId` varchar(100) NOT NULL,
                             `roleId` varchar(100) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (8,'sys','admin');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'base'
--
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-24 15:32:58
