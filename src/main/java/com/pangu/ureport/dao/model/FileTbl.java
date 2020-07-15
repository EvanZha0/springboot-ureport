package com.pangu.ureport.dao.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaojj
 * @since 2020-07-15
 */
@Data
@TableName("ureport_file_tbl")
public class FileTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String path;

    /**
     * 报表名称
     */
    private String name;

    /**
     * 报表内容
     */
    private byte[] content;

    /**
     * 租户字段
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
