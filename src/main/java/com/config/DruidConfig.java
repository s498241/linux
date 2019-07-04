package com.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");//创建servlet注册实体
        servletRegistrationBean.addInitParameter("allow","192.168.1.72,127.0.0.1"); //设置ip白名单
        servletRegistrationBean.addInitParameter("deny","192.168.0.19"); //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        servletRegistrationBean.addInitParameter("loginUsername","admin");//设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginPassword","123123");
        servletRegistrationBean.addInitParameter("resetEnable","false"); //禁用HTML页面上的“Reset All”功能
        return servletRegistrationBean;
    }

    @Bean
    public Filter statFilter(){
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(5000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

}
