package org.mklinkj.taojwp.common.vault;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

class VaultClientTests {

  @Test
  void test() throws IOException {

    Properties prop =
        PropertiesLoaderUtils.loadProperties(new ClassPathResource("config/vault.properties"));
    Properties tokenProp =
        PropertiesLoaderUtils.loadProperties(
            new ClassPathResource("config/vault-token.properties"));

    VaultConfig config =
        new VaultConfig(
            prop.getProperty("vault.server.url"), //
            tokenProp.getProperty("vault.policy.token"),
            prop.getProperty("vault.root.path"),
            prop.getProperty("vault.project.path"));

    VaultClient client = new VaultClient(config);

    String testValue = client.get("test.value");
    assertThat(testValue).isEqualTo("1234");
  }

  @Test
  void testDefault() {

    VaultClient client = new VaultClient();

    String testValue = client.get("test.value");
    assertThat(testValue).isEqualTo("1234");
  }
}
