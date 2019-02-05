package cn.edu.jxau.web.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开启监控功能，通过注册servlet和Filter的方式处理
 */
@Configuration  // 该注解相当于把该类变成一个xml配置文件
public class DruidConfiguration {

    /**
     * 等同于xml配置文件中的<bean>配置，springboot会把加上该注解的方法返回值装载进spring的ioc容器中，方法名对应<bean>标签的id属性值
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        // ServletRegistrationBean提供类的进行注册
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        // 添加初始化参数：initParams
        // 白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");

        // IP黑名单（如果同时存在，deny优先于allow）
        // 如果满足deny，就提示：Sorry，you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");

        //  登录查看信息的账号和密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");

        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");

        // 添加需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
