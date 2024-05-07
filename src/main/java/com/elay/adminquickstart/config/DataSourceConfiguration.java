package com.elay.adminquickstart.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * li
 * 2024/5/7
 **/
//@Configuration
@Slf4j
public class DataSourceConfiguration implements WebMvcConfigurer {
//    @ConfigurationProperties("spring.datasource")
//    @Bean
//    public DataSource dataSource() throws SQLException {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        log.info("DataSource Create Success");
//        log.info(druidDataSource.toString());
//        //同时开启 sql 监控(stat) 和防火墙(wall)，中间用逗号隔开。
//        //开启防火墙能够防御 SQL 注入攻击
//        druidDataSource.setFilters("stat,wall");
//        return druidDataSource;
//    }
//
////    @Bean
//    public ServletRegistrationBean statViewServlet() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        // 设置管理后台的账号密码
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("loginUsername", "admin");
//        initParams.put("loginPassword", "admin");
//        bean.setInitParameters(initParams);
//        return bean;
//    }


}
