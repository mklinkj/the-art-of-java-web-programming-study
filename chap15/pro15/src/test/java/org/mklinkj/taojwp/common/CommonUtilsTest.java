package org.mklinkj.taojwp.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

class CommonUtilsTest {
  @Test
  void testIfNullToNullString() {
    assertThat(CommonUtils.ifNullToNullString(null)).isEqualTo("null");

    assertThat(CommonUtils.ifNullToNullString("test")).isEqualTo("test");
  }

  @Test
  @EnabledOnOs(OS.WINDOWS)
  void testFileNameOnly() {
    assertThat(CommonUtils.fileNameOnly("C:\\Test\\a.txt")).isEqualTo("a.txt");
    assertThat(CommonUtils.fileNameOnly("C:/Test/b.txt")).isEqualTo("b.txt");
    assertThat(CommonUtils.fileNameOnly("c.txt")).isEqualTo("c.txt");
  }
}
