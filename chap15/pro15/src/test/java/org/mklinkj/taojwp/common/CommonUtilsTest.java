package org.mklinkj.taojwp.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CommonUtilsTest {
  @Test
  void testIfNullToNullString() {
    assertThat(CommonUtils.ifNullToNullString(null)).isEqualTo("null");

    assertThat(CommonUtils.ifNullToNullString("test")).isEqualTo("test");
  }

  @Test
  void testFileNameOnly() {
    assertThat(CommonUtils.fileNameOnly("C:\\Test\\a.txt")).isEqualTo("a.txt");
    assertThat(CommonUtils.fileNameOnly("C:/Test/b.txt")).isEqualTo("b.txt");
    assertThat(CommonUtils.fileNameOnly("c.txt")).isEqualTo("c.txt");
  }
}
