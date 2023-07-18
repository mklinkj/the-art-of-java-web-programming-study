package org.mklinkj.taojwp.file.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileServiceTests {

  @Autowired private FileService service;

  private static final String TEST_FILE_UUID = "bf0890ec-3a79-4f63-a560-63e84ed4dcad";

  private static final String TEST_FILE_EXTENSION = "png";

  @Test
  void getOriginalFileName() {
    String originalFileName =
        service.getOriginalFileName(TEST_FILE_UUID + "." + TEST_FILE_EXTENSION);
    assertThat(originalFileName).isEqualTo("file_name_1.png");
  }
}
