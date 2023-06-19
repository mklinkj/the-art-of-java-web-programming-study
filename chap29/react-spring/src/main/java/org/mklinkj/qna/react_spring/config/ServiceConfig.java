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
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "org.mklinkj.qna.react_spring.repository")
public class ServiceConfig {

  @Bean(destroyMethod = "close")
  HikariDataSource dataSource() {
    HikariConfig config = new HikariConfig();

    config.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
    // 메모리:  jdbc:hsqldb:mem:the_art_of_java_web
    // 독립실행: jdbc:hsqldb:hsql://hsqldb-host:9001/the_art_of_java_web
    config.setJdbcUrl("jdbc:hsqldb:mem:the_art_of_java_web");
    config.setUsername("SA");
    config.setPassword("");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    return new HikariDataSource(config);
  }

  @Bean
  ModelMapper getMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper
        .getConfiguration() //
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        .setMatchingStrategy(MatchingStrategies.STRICT);

    return modelMapper;
  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
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
  JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }
}
