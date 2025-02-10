CREATE TABLE `district` (
    `code` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci',
    `si_do` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `si_gun_gu` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `eup_myeon_dong` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `li` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `rank` INT(10) NULL DEFAULT NULL,
    `created_at` DATE NULL DEFAULT NULL,
    `deleted_at` DATE NULL DEFAULT NULL,
    `ex_code` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    PRIMARY KEY (`code`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
