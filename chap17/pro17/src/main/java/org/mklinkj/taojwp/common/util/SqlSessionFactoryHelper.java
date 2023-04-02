package org.mklinkj.taojwp.common.util;

import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mklinkj.taojwp.sec01.ex01.MemberVO;
import org.mklinkj.taojwp.sec03.brd01.ArticleVO;

/**
 * SqlSessionFactory는 애플리케이션 스코프로, 싱글톤이 될 필요가 있다.
 *
 * <p>https://mybatis.org/mybatis-3/ko/getting-started.html
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlSessionFactoryHelper {
  private static final SqlSessionFactory FACTORY = MyBatisSqlSessionFactoryHolder.FACTORY;

  public static SqlSessionFactory sqlSessionFactory() {
    return FACTORY;
  }

  private static class MyBatisSqlSessionFactoryHolder {
    private static final SqlSessionFactory FACTORY;

    static {
      DataSource dataSource = DBUtils.getDataSourceFromJNDI();
      TransactionFactory transactionFactory = TransactionFactoryHelper.transactionFactory();
      Environment environment = new Environment("dev", transactionFactory, dataSource);
      Configuration configuration = new Configuration(environment);
      configuration.setMapUnderscoreToCamelCase(true);
      // 매퍼를 등록하기전에 별칭을 추가해야 잘됌
      configuration.getTypeAliasRegistry().registerAlias("member", MemberVO.class);
      configuration.getTypeAliasRegistry().registerAlias("article", ArticleVO.class);
      configuration.addMappers("org.mklinkj.taojwp.mapper");

      FACTORY = new SqlSessionFactoryBuilder().build(configuration);
    }
  }
}
