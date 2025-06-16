-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.31 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 imdang.district 구조 내보내기
CREATE TABLE IF NOT EXISTS `district` (
                                          `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                          `si_do` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                          `si_gun_gu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                          `eup_myeon_dong` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                          `li` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                          `rank` int DEFAULT NULL,
                                          `created_at` date DEFAULT NULL,
                                          `deleted_at` date DEFAULT NULL,
                                          `ex_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                          PRIMARY KEY (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 imdang.insight 구조 내보내기
CREATE TABLE IF NOT EXISTS `insight_test` (
                                          `accused_count` int unsigned NOT NULL,
                                          `recommended_count` int unsigned NOT NULL,
                                         `view_count` int unsigned NOT NULL,
                                         `visit_at` date NOT NULL,
                                         `created_at` datetime(6) NOT NULL,
                                         `id` char(36) COLLATE utf8mb4_general_ci NOT NULL,
                                         `member_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `address_building_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                         `address_detail` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                         `address_eup_myeon_dong` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `address_road_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                         `address_si_do` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `address_si_gun_gu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `complex_environment` json NOT NULL,
                                         `complex_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `infra` json NOT NULL,
                                         `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `visit_methods` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `visit_times` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `access` enum('자유로움','제한됨','허락시_가능') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `address_latitude` double DEFAULT NULL,
                                         `address_longitude` double DEFAULT NULL,
                                         `is_deleted` bit(1) NOT NULL,
                                         PRIMARY KEY (`id`),
                                         KEY `fk_insight_test_member_1` (`member_id`),
                                         CONSTRAINT `fk_insight_test_member_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `insight_image` (
                               `insight_id` char(36) NOT NULL,
                               `type` int NOT NULL,
                               `sort_num` int NOT NULL,
                               `image` varchar(255) NOT NULL,
                               `created_at` datetime(6) NOT NULL,
                               CONSTRAINT `pk_insight_image` PRIMARY KEY (`image`),
                               CONSTRAINT `fk_insight_image_insight_1` FOREIGN KEY (`insight_id`) REFERENCES insight_test (id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 imdang.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
                                        `id` char(36) COLLATE utf8mb4_general_ci NOT NULL,
                                        `auth_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                                        `auth_type` enum('APPLE','GOOGLE','KAKAO','MOCK') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                        `birth_date` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `device_token` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `exchange_count` int unsigned NOT NULL,
                                        `gender` tinyint DEFAULT NULL,
                                        `insight_count` int unsigned NOT NULL,
                                        `is_deleted` bit(1) DEFAULT NULL,
                                        `is_login` bit(1) DEFAULT NULL,
                                        `nickname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `refresh_token` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `accused_count` int unsigned NOT NULL,
                                        `penalty_from` date DEFAULT NULL,
                                        `penalty_to` date DEFAULT NULL,
                                        `rejected_count` int unsigned NOT NULL,
                                        `status` enum('ACTIVE','EXCHANGE_RESTRICTED','PERMANENT_BANNED','TEMPORARY_BANNED') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `unique_member_1` (`nickname`),
                                        UNIQUE KEY `unique_member_2` (`auth_id`,`auth_type`,`is_deleted`),
                                        CONSTRAINT `member_chk_1` CHECK ((`gender` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 imdang.notification 구조 내보내기
CREATE TABLE IF NOT EXISTS `notification` (
                                              `id` bigint NOT NULL AUTO_INCREMENT,
                                              `category` enum('ACCEPTED','REJECTED','REQUESTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                              `checked_at` datetime(6) DEFAULT NULL,
                                              `created_at` datetime(6) NOT NULL,
                                              `is_checked` bit(1) NOT NULL,
                                              `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                              `receiver_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                              PRIMARY KEY (`id`),
                                              KEY `fk_notification_member_1` (`receiver_id`),
                                              CONSTRAINT `fk_notification_member_1` FOREIGN KEY (`receiver_id`) REFERENCES `member` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 imdang.recommend 구조 내보내기
CREATE TABLE IF NOT EXISTS `recommend` (
                                           `id` bigint NOT NULL AUTO_INCREMENT,
                                           `created_at` datetime(6) NOT NULL,
                                           `recommend_member_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                           `recommended_insight_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                           `recommended_member_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                           PRIMARY KEY (`id`),
                                           UNIQUE KEY `unique_recommend_1` (`recommend_member_id`,`recommended_insight_id`),
                                           KEY `fk_recommend_insight` (`recommended_insight_id`),
                                           KEY `fk_recommend_member_2` (`recommended_member_id`),
                                           CONSTRAINT `fk_recommend_insight` FOREIGN KEY (`recommended_insight_id`) REFERENCES `insight` (`id`) ON DELETE RESTRICT,
                                           CONSTRAINT `fk_recommend_member_1` FOREIGN KEY (`recommend_member_id`) REFERENCES `member` (`id`) ON DELETE RESTRICT,
                                           CONSTRAINT `fk_recommend_member_2` FOREIGN KEY (`recommended_member_id`) REFERENCES `member` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 imdang.terms 구조 내보내기
CREATE TABLE IF NOT EXISTS `terms` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `is_essential` bit(1) NOT NULL,
                                       `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                       `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 imdang.terms_agreement 구조 내보내기
CREATE TABLE IF NOT EXISTS `terms_agreement` (
                                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                                 `created_at` datetime(6) NOT NULL,
                                                 `member_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                                 `terms_id` bigint NOT NULL,
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE KEY `unique_terms_agreement_1` (`member_id`,`terms_id`),
                                                 KEY `fk_terms_agreement_terms_1` (`terms_id`),
                                                 CONSTRAINT `fk_terms_agreement_member_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE RESTRICT,
                                                 CONSTRAINT `fk_terms_agreement_terms_1` FOREIGN KEY (`terms_id`) REFERENCES `terms` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
