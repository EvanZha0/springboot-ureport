package com.pangu.ureport.manager.impl;

import com.pangu.ureport.dao.model.FileTbl;
import com.pangu.ureport.dao.mapper.FileTblMapper;
import com.pangu.ureport.manager.FileTblManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2020-07-15
 */
@Slf4j
@Service
public class FileTblManagerImpl extends ServiceImpl<FileTblMapper, FileTbl> implements FileTblManager {

    @Resource
    private FileTblMapper mapper;

    @Override
    public void save(String path) {
        log.info("save path: {}", path);
        if (isExisted(path)) {
            return;
        }

        FileTbl reportMeta = new FileTbl();
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
