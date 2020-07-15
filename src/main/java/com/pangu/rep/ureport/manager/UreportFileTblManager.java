package com.pangu.rep.ureport.manager;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pangu.rep.ureport.dao.model.UreportFileTbl;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaojj
 * @since 2020-03-31
 */
public interface UreportFileTblManager extends IService<UreportFileTbl> {

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
