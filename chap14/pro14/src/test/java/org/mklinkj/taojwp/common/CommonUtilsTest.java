package org.mklinkj.taojwp.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CommonUtilsTest {
  @Test
  void testIfNullToNullString() {
    assertThat(CommonUtils.ifNullToNullString(null)).isEqualTo("null");

    assertThat(CommonUtils.ifNullToNullString("test")).isEqualTo("test");
  }
}
