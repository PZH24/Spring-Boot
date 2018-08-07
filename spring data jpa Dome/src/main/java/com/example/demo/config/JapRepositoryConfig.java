package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EntityScan(basePackageClasses= UserInfo.class)
@EnableJpaRepositories("com.example.demo.dao")
//@EnableJpaRepositories(basePackageClasses = IUserInfoDao.class)
@EnableTransactionManagement
public class JapRepositoryConfig {
//    private DataSource dataSource;
//    @Autowired
//    private Environment env;
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        //此处com.example.*.model是你的java bean所在的包名
//        factory.setPackagesToScan("com.example.demo.entity");
//        factory.setDataSource(dataSource());
//
//        Map<String, Object> jpaProperties = new HashMap<String, Object>();
//        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
//        jpaProperties.put("hibernate.jdbc.batch_size",50);
//
//        factory.setJpaPropertyMap(jpaProperties);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
//    /**
//     * 生成与spring-dao.xml对应的bean  dataSource
//     * @return
//     */
//    @Bean(name = "dataSource")
//    public ComboPooledDataSource dataSource() throws PropertyVetoException {
//        //创建DataSource实例
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass(env.getProperty("ms.db.driverClassName"));
//        dataSource.setJdbcUrl(env.getProperty("ms.db.url"));
//        dataSource.setUser(env.getProperty("ms.db.username"));
//        dataSource.setPassword(env.getProperty("ms.db.password"));
//        //配置c3p0连接池的私有属性
//        //连接池的最大线程数量
//        dataSource.setMaxPoolSize(30);
//        //连接池的最小线程数量
//        dataSource.setMinPoolSize(30);
//        //连接池不自动commit
//        dataSource.setAutoCommitOnClose(false);
//        //连接超时时间
//        dataSource.setCheckoutTimeout(10000);
//        //连接失败自动重试次数
//        dataSource.setAcquireRetryAttempts(2);
//
//        return dataSource;
//    }
//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
