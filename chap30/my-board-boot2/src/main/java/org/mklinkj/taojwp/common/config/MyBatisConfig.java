package org.mklinkj.taojwp.common.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mklinkj.taojwp.board.domain.ArticleVO;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.mklinkj.taojwp.member.dto.SearchType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/*
 지금은 Spring 레거시 설정으로 쓰던 것을 그대로 가져왔는데..
 아래 내용 보고 yml 설정으로 최대한 적용하는 것이 나을 것 같다.
 https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
*/
@RequiredArgsConstructor
@Configuration
@MapperScan(basePackages = "org.mklinkj.taojwp.member.mapper,org.mklinkj.taojwp.board.mapper")
public class MyBatisConfig {

  private final DataSource dataSource;

  @Bean
  SqlSessionFactory sessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    // XML 설정과는 다르게 Wildcard 사용이 안된다. 개별 명시해야함.
    factoryBean.setMapperLocations(
        new ClassPathResource("mappers/MemberMapper.xml"),
        new ClassPathResource("mappers/BoardMapper.xml"));

    org.apache.ibatis.session.Configuration configuration =
        new org.apache.ibatis.session.Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    configuration.setJdbcTypeForNull(JdbcType.NULL);

    factoryBean.setConfiguration(configuration);

    factoryBean.setTypeAliases(
        MemberVO.class, //
        SearchDTO.class,
        SearchType.class,
        ArticleVO.class);

    return factoryBean.getObject();
  }
}
