package org.mklinkj.taojwp.common.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.mklinkj.taojwp.member.dto.SearchType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@RequiredArgsConstructor
@Configuration
@MapperScan(basePackages = "org.mklinkj.taojwp.member.mapper")
public class MyBatisConfig {

  private final DataSource dataSource;

  @Bean
  SqlSessionFactory sessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    // XML 설정과는 다르게 Wildcard 사용이 안된다. 개별 명시해야함.
    factoryBean.setMapperLocations(new ClassPathResource("mappers/MemberMapper.xml"));
    factoryBean.setTypeAliases(
        MemberVO.class, //
        SearchDTO.class,
        SearchType.class);
    return factoryBean.getObject();
  }

  @Bean
  ConfigurationCustomizer mybatisConfigurationCustomizer() {
    return (configuration) -> configuration.setMapUnderscoreToCamelCase(true);
  }
}
