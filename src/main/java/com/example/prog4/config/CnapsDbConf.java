package com.example.prog4.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
    basePackages = "com.example.prog4.repository.CnapsDb",
    entityManagerFactoryRef = "cnapsEntityManager",
    transactionManagerRef = "cnapsTransactionManager")
public class CnapsDbConf {
  @Autowired
  private Environment env;
  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean cnapsEntityManager() {
    LocalContainerEntityManagerFactoryBean em
        = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(cnapsDataSource());
    em.setPackagesToScan(new String[] { "com.example.prog4.entity.cnaps" });

    HibernateJpaVendorAdapter vendorAdapter
        = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto",
        env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect",
        env.getProperty("hibernate.dialect"));
    em.setJpaPropertyMap(properties);

    return em;
  }

  @Primary
  @Bean
  @ConfigurationProperties(prefix="spring.datasource")
  public DataSource userDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public DataSource cnapsDataSource() {

    DriverManagerDataSource dataSource
        = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("spring.second-datasource.jdbcUrl"));
    dataSource.setUrl(env.getProperty("product.jdbc.url"));
    dataSource.setUsername(env.getProperty("jdbc.user"));
    dataSource.setPassword(env.getProperty("jdbc.pass"));

    return dataSource;
  }

  @Bean
  public PlatformTransactionManager cnapsTransactionManager() {
    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        cnapsEntityManager().getObject());
    return transactionManager;
  }
}
