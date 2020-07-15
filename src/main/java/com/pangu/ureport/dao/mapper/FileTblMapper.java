package com.pangu.ureport.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pangu.ureport.dao.model.FileTbl;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaojj
 * @since 2020-07-15
 */
public interface FileTblMapper extends BaseMapper<FileTbl> {


    int deleteByPrimaryKey(Long id);

    int insertSelective(FileTbl record);

    FileTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileTbl record);

    int updateByPrimaryKey(FileTbl record);

    int deleteByPath(String path);

    FileTbl selectByPath(String path);

}
