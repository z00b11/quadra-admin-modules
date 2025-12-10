-- 管理域
CREATE
DATABASE IF NOT EXISTS quadra_admin DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE
quadra_admin;
DROP TABLE IF EXISTS tb_admin;
CREATE TABLE tb_admin
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(64) NOT NULL,
    avatar   VARCHAR(500) NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';
DROP TABLE IF EXISTS tb_analysis;
CREATE TABLE tb_analysis
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    record_date     DATE   NOT NULL,
    num_registered  INT      DEFAULT 0,
    num_active      INT      DEFAULT 0,
    num_login       INT      DEFAULT 0,
    num_retention1d INT      DEFAULT 0,
    created         DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_record_date (record_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据分析表';

