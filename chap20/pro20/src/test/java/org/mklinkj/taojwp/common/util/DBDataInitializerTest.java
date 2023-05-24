package org.mklinkj.taojwp.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:application-context.xml")
class DBDataInitializerTest {

  @Autowired private DBDataInitializer dbDataInitializer;

  @Test
  void testResetDB() {
    dbDataInitializer.resetDB();
  }
}
