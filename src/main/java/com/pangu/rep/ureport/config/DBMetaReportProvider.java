package com.pangu.rep.ureport.config;

import com.bstek.ureport.exception.ReportException;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.pangu.rep.ureport.dao.mapper.UreportFileTblMapper;
import com.pangu.rep.ureport.manager.UreportFileTblManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 数据库报表存储器
 * - 仅存储文件路径等元数据，实际的内容仍存储在文件中，路径配置在yaml文件中
 *
 * @author zhaojj
 * @date 2020-04-01
 **/
@Slf4j
@Component
public class DBMetaReportProvider implements ReportProvider, ApplicationContextAware {

    private static final String NAME = "数据库存储-Meta";
    private static final String ENCODING = "UTF-8";

    private final UreportConfig ureportConfig;
    @Autowired
    private UreportFileTblMapper ureportFileMapper;
    @Autowired
    private UreportFileTblManager ureportFileTblManager;


    public DBMetaReportProvider(UreportConfig ureportConfig) {
        this.ureportConfig = ureportConfig;
    }

    @Override
    public InputStream loadReport(String fileName) {
        try {
            File file = new File(ureportConfig.getFileStoreDir(), substring(fileName));
            log.info("load report file by path: {}", file.getPath());

            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ReportException(e);
        }
    }

    @Override
    public void deleteReport(String fileName) {
        try {
            Path fullPath = Paths.get(ureportConfig.getFileStoreDir(), substring(fileName));
            boolean result = Files.deleteIfExists(fullPath);
            ureportFileTblManager.delete(fullPath.toAbsolutePath().toString());
            log.info("delete report file {} by path: {}", result ? "success" : "fail", fullPath);
        } catch (IOException e) {
            throw new ReportException(e);
        }
    }

    @Override
    public List<ReportFile> getReportFiles() {
        File file = new File(ureportConfig.getFileStoreDir());
        File[] files = file.listFiles();
        if (files == null) {
            log.error("path: {}", file.getPath());
            throw new ReportException("存储目录下没有文件. file store directory is empty");
        }

        List<ReportFile> list = new ArrayList<>();
        for (File f : files) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(f.lastModified());
            list.add(new ReportFile(f.getName(), calendar.getTime()));
        }
        list.sort((f1, f2) -> f2.getUpdateDate().compareTo(f1.getUpdateDate()));

        log.info("报表文件数量: {}", list.size());
        return list;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void saveReport(String fileName, String content) {
        File file = new File(ureportConfig.getFileStoreDir(), substring(fileName));
        try (FileOutputStream outStream = new FileOutputStream(file)) {
            IOUtils.write(content, outStream, ENCODING);
            // 存储文件路径至数据库
            ureportFileTblManager.save(file.getAbsolutePath());
            log.info("save report file success by path: {}", file.getPath());
        } catch (Exception ex) {
            throw new ReportException(ex);
        }
    }

    @Override
    public boolean disabled() {
        return ureportConfig.isDisableDBMetaProvider();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        File file = new File(ureportConfig.getFileStoreDir());
        if (file.exists()) {
            log.info("DBReportProvider initialed");
            return;
        }

        if (!file.mkdirs()) {
            throw new ReportException("创建报表模板存储目录失败. path is " + file.getPath());
        }
    }

    @Override
    public String getPrefix() {
        return ureportConfig.getMetaPrefix();
    }

    /**
     * 去掉前缀
     */
    private String substring(String fileName) {
        String prefix = ureportConfig.getMetaPrefix();
        if (fileName.startsWith(prefix)) {
            return fileName.substring(prefix.length());
        }

        return fileName;
    }
}
