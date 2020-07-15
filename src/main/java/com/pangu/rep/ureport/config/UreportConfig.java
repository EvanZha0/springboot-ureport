package com.pangu.rep.ureport.config;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Zhaojj
 * @version 1.0
 * @date 2020/3/30 10:07
 */
@Data
@Slf4j
@ImportResource("classpath:ureport-console-context.xml")//（没有这个xml也要加）不加项目能够启动但是会导致加载数据源报错或加载不了
@Configuration
@ConfigurationProperties("ureport")
public class UreportConfig implements BuildinDatasource {

    /**
     * 文件名前缀，用于标识不同的存储器存储的文件
     * 否则在查看文件列表的时候只能看到其中一个存储器的文件
     */
    private String metaPrefix = "re:";
    /**
     * 是否禁用DBMetaReportProvider
     */
    private boolean disableDBMetaProvider = false;

    /**
     * 是否禁用DBFullReportProvider
     */
    private boolean disableDBFullProvider = false;

    /**
     * 是否禁用FileReportProvider
     */
    private boolean disableFileProvider = false;

    /**
     * 报表模板文件存储路径
     */
    private String fileStoreDir = "ureportfiles";

    @Resource
    DataSource dataSource;

    @Bean //定义ureport的启动servlet
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean ureportServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new UReportServlet());
        servletRegistrationBean.addUrlMappings("/ureport/*");
        return servletRegistrationBean;
    }

    @Override
    public String name() {
        return "myUReportDatasource";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}