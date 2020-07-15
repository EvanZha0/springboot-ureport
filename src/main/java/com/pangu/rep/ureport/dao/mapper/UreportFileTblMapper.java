package com.pangu.rep.ureport.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pangu.rep.ureport.dao.model.UreportFileTbl;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaojj
 * @since 2020-03-31
 */
public interface UreportFileTblMapper extends BaseMapper<UreportFileTbl> {

    int deleteByPrimaryKey(Long id);

    int insert(UreportFileTbl record);

    int insertSelective(UreportFileTbl record);

    UreportFileTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UreportFileTbl record);

    int updateByPrimaryKey(UreportFileTbl record);

    int deleteByPath(String path);

    UreportFileTbl selectByPath(String path);

}
