package org.mklinkj.qna.react_spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.sql.DataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;

@Configuration
// 서비스 설정에서는 Controller와 MVC 설정 클래스를 제외하고 스캔하게 했다.
@ComponentScan(
    basePackages = "org.mklinkj.qna.react_spring",
    excludeFilters = {
      @Filter(type = FilterType.ANNOTATION, value = Controller.class),
      @Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebConfiguration.class)
    })
@EnableJpaRepositories(basePackages = "org.mklinkj.qna.react_spring.repository")
public class ServiceConfig {

  @Bean(destroyMethod = "close")
  public HikariDataSource dataSource() {
    HikariConfig config = new HikariConfig();

    config.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
    // 메모리  DB:  jdbc:hsqldb:mem:the_art_of_java_web
    // 독립실행 DB:  jdbc:hsqldb:hsql://hsqldb-host:9001/the_art_of_java_web
    config.setJdbcUrl("jdbc:hsqldb:mem:the_art_of_java_web");
    config.setUsername("SA");
    config.setPassword("");
    // 아래는 HikariCP 공식 홈페이지의 MySQL 추천 옵션이긴 함.
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    return new HikariDataSource(config);
  }

  @Bean
  public ModelMapper getMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper
        .getConfiguration() //
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        .setMatchingStrategy(MatchingStrategies.STRICT);

    return modelMapper;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
      throws IOException {

    LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();

    emfBean.setDataSource(dataSource);
    emfBean.setPersistenceUnitName("react_spring");
    emfBean.setPackagesToScan("org.mklinkj.qna.react_spring.domain");

    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

    hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
    hibernateJpaVendorAdapter.setShowSql(true);
    emfBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

    Properties properties = new Properties();

    properties.load(
        new EncodedResource(new ClassPathResource("jpa.properties"), StandardCharsets.UTF_8)
            .getInputStream());

    emfBean.setJpaProperties(properties);

    return emfBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }
}
