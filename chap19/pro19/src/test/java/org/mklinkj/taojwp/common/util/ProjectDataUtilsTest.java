package org.mklinkj.taojwp.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProjectDataUtilsTest {

  @Test
  void testGetProperty() {
    assertThat(ProjectDataUtils.getProperty("project.name")).isEqualTo("pro19");
  }
}
