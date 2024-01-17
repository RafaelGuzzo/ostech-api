CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `district` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `client` (
    `id` char(36) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `document_number` varchar(255) DEFAULT NULL,
    `contact` varchar(255) DEFAULT NULL,
    `phone` varchar(255) DEFAULT NULL,
    `create_at` datetime(6) DEFAULT NULL,
    `update_at` datetime(6) DEFAULT NULL,
    `address_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_address_client` (`address_id`),
    CONSTRAINT `fk_address_client` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS  `order_service` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `equipament` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `client_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_service_client` (`client_id`),
  CONSTRAINT `fk_order_service_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `system_configuration` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `logo` longtext,
  `principal` bit(1) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;