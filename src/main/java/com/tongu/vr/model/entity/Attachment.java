package com.tongu.vr.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 附件表
 * @author Admin
 * @create 2019-04-25
 */
@Data
@Entity
@Table(name = "vr_t_attachment")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    /** id */
    @Id
    private Long id;

    /** 附件名称 */
    @Column(name = "attachment_name")
    private String attachmentName;

    /** 附件路径 */
    @Column(name = "attachment_path")
    private String attachmentPath;
    
    /** 附件大小 */
    @Column(name = "attachment_size")
    private Integer attachmentSize;
    
    /** 附件签名 */
    @Column(name = "attachment_sign")
    private String attachmentSign;
    
    /** 二维码路径 */
    @Column(name = "qr_code_path")
    private String qrCodePath;

    /** 创建时间 */
    @Column(name = "create_date")
    private Date createDate;

    /** 创建人 */
    @Column(name = "create_user")
    private Long createUser;

    /** 更新时间 */
    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /** 更新人 */
    @Column(name = "last_update_user")
    private Long lastUpdateUser;

    /** 是否删除
            0-未删除/1-已删除 */
    @Column(name = "is_delete")
    private String isDelete;

    /** 版本号 */
    @Column(name = "version")
    private Integer version;

    /** 备注 */
    @Column(name = "memo")
    private String memo;
}
