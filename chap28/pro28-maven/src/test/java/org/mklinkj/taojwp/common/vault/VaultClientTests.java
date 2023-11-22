package org.mklinkj.taojwp.common.vault;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

class VaultClientTests {
  @Test
  void testDefault() {

    VaultClient client = new VaultClient();

    String testValue = client.get("test.value");
    assertThat(testValue).isEqualTo("1234");
  }
}
