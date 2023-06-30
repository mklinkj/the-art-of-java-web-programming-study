package org.mklinkj.taojwp.common.config;

import java.io.IOException;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.common.util.DBDataInitializer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@RequiredArgsConstructor
@Configuration
@PropertySource({
  "classpath:config/db-oracle.properties",
  "classpath:config/project-data.properties"
})
public class RootConfig {

  private final Environment environment;

  /*
   * 스프링 부트가 자동 설정해주는 것을 그대로 유지하면서도
   * 프로퍼티 처리는 별도로 관리하고 싶어서 아래와 같이 처리
   */
  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder.create() //
        .driverClassName(environment.getProperty("jdbc.driver"))
        .url(environment.getProperty("jdbc.url"))
        .username(environment.getProperty("jdbc.username"))
        .password(environment.getProperty("jdbc.password"))
        .build();
  }

  @Bean
  MultipartFilter multipartFilter() {
    return new MultipartFilter();
  }

  @Bean
  CommonsMultipartResolver commonsMultipartResolver() throws IOException {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    resolver.setMaxUploadSize(52428800);
    resolver.setMaxInMemorySize(10485760);
    resolver.setMaxUploadSizePerFile(10485760);
    resolver.setDefaultEncoding("UTF-8");
    resolver.setUploadTempDir(
        new FileSystemResource(
            Objects.requireNonNull(environment.getProperty("upload_temp_path"))));
    return resolver;
  }

  @Primary
  @Bean(initMethod = "resetDB")
  DBDataInitializer dbDataInitializer() {
    return new DBDataInitializer(dataSource(), "sql/oracle/init-sql.sql");
  }
}
