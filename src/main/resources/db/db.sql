CREATE DATABASE IF NOT EXISTS vr DEFAULT CHARSET utf8 COLLATE utf8_general_ci; 

USE vr;

DROP TABLE IF EXISTS vr_t_attachment;

/*==============================================================*/
/* Table: vr_t_attachment                                       */
/*==============================================================*/
CREATE TABLE vr_t_attachment
(
   id                   BIGINT(20) NOT NULL COMMENT 'id',
   attachment_name      VARCHAR(255) NOT NULL COMMENT '附件名称',
   attachment_path      VARCHAR(500) NOT NULL COMMENT '附件路径',
   attachment_size      INT COMMENT '附件大小',
   attachment_sign      VARCHAR(500) NOT NULL COMMENT '文件签名',
   qr_code_path         VARCHAR(500) NULL COMMENT '二维码路径',
   create_date          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   create_user          BIGINT COMMENT '创建人',
   last_update_date     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   last_update_user     BIGINT COMMENT '更新人',
   is_delete            CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否删除
            0-未删除/1-已删除',
   VERSION              INT NOT NULL DEFAULT 0 COMMENT '版本号',
   memo                 VARCHAR(255) COMMENT '备注',
   PRIMARY KEY (id)
)
ENGINE = INNODB
CHARSET = utf8
COLLATE = utf8_general_ci;

ALTER TABLE vr_t_attachment COMMENT '附件表';
