package com.pangu.rep.ureport.manager.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pangu.rep.ureport.dao.mapper.UreportFileTblMapper;
import com.pangu.rep.ureport.dao.model.UreportFileTbl;
import com.pangu.rep.ureport.manager.UreportFileTblManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaojj
 * @since 2020-03-31
 */
@Slf4j
@Service
public class UreportFileTblManagerImpl extends ServiceImpl<UreportFileTblMapper, UreportFileTbl> implements UreportFileTblManager {

    @Resource
    private UreportFileTblMapper mapper;

    @Override
    public void save(String path) {
        log.info("save path: {}", path);
        if (isExisted(path)) {
            return;
        }

        UreportFileTbl reportMeta = new UreportFileTbl();
        reportMeta.setPath(path);
        reportMeta.setCreateTime(new Date());
        mapper.insertSelective(reportMeta);
    }

    @Override
    public void delete(String path) {
        log.info("delete path: {}", path);
        mapper.deleteByPath(path);
    }

    private boolean isExisted(String path) {
        return mapper.selectByPath(path) != null;
    }
}
