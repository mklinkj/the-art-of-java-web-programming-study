package org.mklinkj.taojwp.common.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@RequiredArgsConstructor
@Configuration
@PropertySource("classpath:config/jdbc.properties")
public class RootConfig {

  private final Environment environment;

  @Bean
  MultipartFilter multipartFilter() {
    return new MultipartFilter();
  }

  @Bean
  CommonsMultipartResolver commonsMultipartResolver() {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    resolver.setMaxUploadSize(52428800);
    resolver.setMaxInMemorySize(10485760);
    resolver.setMaxUploadSizePerFile(10485760);
    resolver.setDefaultEncoding("UTF-8");
    return resolver;
  }

  @Bean
  DataSource dataSource() {
    PooledDataSource dataSource = new PooledDataSource();

    dataSource.setDriver(environment.getProperty("jdbc.driver"));
    dataSource.setUrl(environment.getProperty("jdbc.url"));
    dataSource.setUsername(environment.getProperty("jdbc.username"));
    dataSource.setPassword(environment.getProperty("jdbc.password"));
    return dataSource;
  }

  @Bean
  TransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

  @Primary
  @Bean(initMethod = "resetDB")
  DBDataInitializer dbDataInitializer() {
    return new DBDataInitializer(dataSource(), "sql/hsqldb/init-sql.sql");
  }
}
