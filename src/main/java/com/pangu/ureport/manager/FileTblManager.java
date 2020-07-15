package com.pangu.ureport.manager;

import com.pangu.ureport.dao.model.FileTbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaojj
 * @since 2020-07-15
 */
public interface FileTblManager extends IService<FileTbl> {

    /**
     * 存储报表模板文件路径
     *
     * @param path path
     */
    void save(String path);

    /**
     * 删除报表数据
     *
     * @param path path
     */
    void delete(String path);
}
