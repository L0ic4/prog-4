package com.example.prog4.config.database;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "employeeEntityManagerFactory",
    transactionManagerRef = "employeeTransactionManager",
    basePackages = {"com.example.prog4.repository.Employee"})
public class EmployeeDatasourceConfiguration {
  @Primary
  @Bean(name = "employeeProperties")
  @ConfigurationProperties("spring.datasource")
  public DataSourceProperties dataSourceProperties() {

    return new DataSourceProperties();
  }

  @Primary
  @Bean(name = "employeeDatasource")
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource datasource(@Qualifier("employeeProperties") DataSourceProperties properties) {

    return properties.initializeDataSourceBuilder().build();
  }

  @Primary
  @Bean(name = "employeeEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
      (EntityManagerFactoryBuilder builder,
       @Qualifier("employeeDatasource") DataSource dataSource) {

    return builder.dataSource(dataSource)
        .packages("com.example.prog4.entity.Employee")
        .persistenceUnit("employees").build();
  }

  @Primary
  @Bean(name = "employeeTransactionManager")
  @ConfigurationProperties("spring.jpa")
  public PlatformTransactionManager transactionManager(
      @Qualifier("employeeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

    return new JpaTransactionManager(entityManagerFactory);
  }
}
