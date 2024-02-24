package org.mklinkj.taojwp.common.vault;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VaultClientTests {
  @Test
  void testDefault() {

    VaultClient client = new VaultClient();

    String testValue = client.get("test.value");
    assertThat(testValue).isEqualTo("1234");
  }
}
