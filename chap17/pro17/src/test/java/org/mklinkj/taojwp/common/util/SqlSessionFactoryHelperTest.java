package org.mklinkj.taojwp.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.util.SqlSessionFactoryHelper.sqlSessionFactory;

import java.time.LocalDateTime;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.mapper.MemberMapper;
import org.mklinkj.taojwp.sec01.ex01.MemberVO;

class SqlSessionFactoryHelperTest {
  @DisplayName("SessionFactory는 싱글톤으로 관리되야한다.")
  @Test
  void testSessionFactory_isSame() {
    SqlSessionFactory a = sqlSessionFactory();
    SqlSessionFactory b = sqlSessionFactory();
    assertThat(a).isSameAs(b);
  }
}
