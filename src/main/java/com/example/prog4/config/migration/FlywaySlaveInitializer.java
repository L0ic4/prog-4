package com.example.prog4.config.migration;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywaySlaveInitializer {

  @Value("${spring.datasource.url}")
  String firstDatasourceUrl;
  @Value("${spring.datasource.username}")
  String firstDatasourceUser;
  @Value("${spring.datasource.password}")
  String firstDatasourcePassword;

  @Value("${spring.datasource.cnaps.url}")
  String secondDatasourceUrl;
  @Value("${spring.datasource.cnaps.username}")
  String secondDatasourceUser;
  @Value("${spring.datasource.cnaps.password}")
  String secondDatasourcePassword;


  @PostConstruct
  public void migrateFlyway() {
    Flyway flywayIntegration = Flyway.configure()
        .dataSource(firstDatasourceUrl, firstDatasourceUser, firstDatasourcePassword)
        .locations("filesystem:./src/main/resources/db/migration/employeedb")
        .load();

    Flyway flywayPhenom = Flyway.configure()
        .dataSource(secondDatasourceUrl, secondDatasourceUser, secondDatasourcePassword)
        .locations("filesystem:./src/main/resources/db/migration/cnapsdb")
        .load();

    flywayIntegration.migrate();
    flywayPhenom.migrate();
  }

}