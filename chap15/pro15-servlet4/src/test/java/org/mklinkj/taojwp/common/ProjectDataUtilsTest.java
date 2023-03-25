package org.mklinkj.taojwp.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.common.Constants.MEGA_BYTE;

import org.junit.jupiter.api.Test;

class ProjectDataUtilsTest {
  @Test
  void testProjectDataProps() {
    assertThat(ProjectDataUtils.getIntProperty("fileUpload.threshold")).isEqualTo(MEGA_BYTE);
    assertThat(ProjectDataUtils.getLongProperty("fileUpload.maxFileSize"))
        .isEqualTo(MEGA_BYTE * 10);
    assertThat(ProjectDataUtils.getLongProperty("fileUpload.maxRequestSize"))
        .isEqualTo(MEGA_BYTE * 15);
  }
}
