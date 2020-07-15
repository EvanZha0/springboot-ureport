package com.pangu.rep.ureport.dao.model;

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
 * @since 2020-03-31
 */
@Data
public class UreportFileTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报表路径
     */
    private String path;

    /**
     * 报表名称
     */
    private String name;//改成路径存储，暂时没用到

    /**
     * 报表内容
     */
    private byte[] content;//改成路径存储，暂时没用到

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
