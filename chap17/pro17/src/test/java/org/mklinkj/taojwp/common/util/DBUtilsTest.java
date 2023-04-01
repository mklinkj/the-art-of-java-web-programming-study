package org.mklinkj.taojwp.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;

class DBUtilsTest {

  @Test
  void testResetDB() {
    DBUtils.resetDB();
  }

  /**
   * 테스트는 Simple JNDI 환경이라서 완전히 동일하진 않지만, <br>
   * 실제 Tomcat 환경도 동일한 데이타 소스 객체 반환할 것 같음.. <br>
   * Tomcat에서 로그 찍어서 확인해봤는데 동일했다.
   */
  @Test
  void testDataSourceIsSame() {
    DataSource a = DBUtils.getDataSourceFromJNDI();
    DataSource b = DBUtils.getDataSourceFromJNDI();

    assertThat(a).isSameAs(b);
  }
}
