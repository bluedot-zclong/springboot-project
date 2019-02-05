package cn.edu.jxau.dao.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.*;

/**
 * 数据源配置
 *
 * @author：zclong
 * @date：2019/2/1
 */
@Configuration
//@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
//@AutoConfigureBefore(name = {"DataSourceConfig"})
public class JxauDataSourceConfig {

    @Value("${spring.datasource.jdbc-url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

//    @Bean(name = "jxauDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource jxauDataSource(EnvdbTypeironment env) {
//        /*return DataSourceBuilder.create()
//                .driverClassName("com.mysql.jdbc.Driver")
//                .url(url)
//                .type(DataSource.class)
//                .build();*/
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name = "jxauDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(Environment env) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, driverClassName);
        properties.put(DruidDataSourceFactory.PROP_URL, dbUrl);
        properties.put(DruidDataSourceFactory.PROP_USERNAME, userName);
        properties.put(DruidDataSourceFactory.PROP_PASSWORD, password);
        // 添加统计、SQL注入、日志过滤器
        properties.put(DruidDataSourceFactory.PROP_FILTERS, "stat,wall,log4j2");
        // sql合并，慢查询定义为5s
        properties.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES, "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
        try {
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean(name = "jxauSqlSessionFactory")
    @Primary
    public SqlSessionFactory jxauSqlSessionFactory(@Qualifier("jxauDataSource") DataSource jxauDataSource,
                                                   @Value("classpath:/jxau-mybatis/mybatis-config.xml") Resource configLocation,
                                                   Environment env) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(jxauDataSource);
        sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:jxau-mybatis/mybatis-config.xml"));
        /*List<Resource> mapperLocationList = new ArrayList<>();
        for (String location : env.getProperty("mybatis.mapper-locations").split(",")) {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            mapperLocationList.addAll(Arrays.asList(resolver.getResources(location)));
        }
        Resource[] mapperLocations = new Resource[mapperLocationList.size()];
        sqlSessionFactoryBean.setMapperLocations(mapperLocationList.toArray(mapperLocations));*/
        String mapperUrl = env.getProperty("mybatis.mapper-locations");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperUrl));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "jxauSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate jxauSqlSessionTemplate(@Qualifier("jxauSqlSessionFactory") SqlSessionFactory jxauSqlSessionFactory) {
        return new SqlSessionTemplate(jxauSqlSessionFactory);
    }

    @Bean
    @Primary
    public MapperScannerConfigurer jxauMapperScannerConfigurer(Environment env) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("jxauSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(env.getProperty("spring.mybatis.scan-base-package"));
        return mapperScannerConfigurer;
    }

    /**
     * 事务管理
     *
     * @param jxauDataSource
     * @return
     */
    @Bean(name = "jxauPlatformTransactionManager")
    @Primary
    public PlatformTransactionManager jxauPlatformTransactionManager(@Qualifier("jxauDataSource") DataSource jxauDataSource) {
        return new DataSourceTransactionManager(jxauDataSource);
    }

    @Bean
    @Primary
    public TransactionTemplate transactionTemplate(@Qualifier("jxauPlatformTransactionManager") PlatformTransactionManager jxauPlatformTransactionManager) {
        return new TransactionTemplate(jxauPlatformTransactionManager);
    }

}
