package org.mklinkj.taojwp.common.vault;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.Versioned;

/** 프로젝트 DB암호를 전부 Vault로 사용할 예정이라, 공통 프로젝트에 Client를 배치해 봄. */
public class VaultClient {

  private final VaultTemplate vaultTemplate;

  private final VaultConfig config;

  public VaultClient() {
    try {
      Properties prop =
          PropertiesLoaderUtils.loadProperties(new ClassPathResource("config/vault.properties"));
      Properties tokenProp =
          PropertiesLoaderUtils.loadProperties(new ClassPathResource("config/vault-token.properties"));

      VaultConfig config =
          new VaultConfig(
              prop.getProperty("vault.server.url"),
              tokenProp.getProperty("vault.policy.token"),
              prop.getProperty("vault.root.path"),
              prop.getProperty("vault.project.path"));
      this.config = config;
      VaultEndpoint vaultEndpoint = VaultEndpoint.from(URI.create(config.getServerUrl()));

      vaultTemplate =
          new VaultTemplate(vaultEndpoint, new TokenAuthentication(config.getPolicyToken()));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public VaultClient(VaultConfig config) {
    this.config = config;
    VaultEndpoint vaultEndpoint = VaultEndpoint.from(URI.create(config.getServerUrl()));

    vaultTemplate =
        new VaultTemplate(vaultEndpoint, new TokenAuthentication(config.getPolicyToken()));
  }

  public int getInt(String key) {
    return Integer.parseInt(get(key));
  }

  public long getLong(String key) {
    String longValue = get(key);
    // L이 붙어있는 long 값인 경우 변경해서 파싱한다. L이 붙으면 파싱할 수 없음.
    return Long.parseLong(longValue.toUpperCase().replace("L", ""));
  }

  public String get(String key) {
    Versioned<Map<String, Object>> read =
        vaultTemplate.opsForVersionedKeyValue(config.getRootPath()).get(config.getProjectPath());

    Map<String, Object> data = Objects.requireNonNull(read).getData();

    if (data == null) {
      throw new IllegalArgumentException("데이터가 없습니다. 전체 path를 확인해주세요.: " + config.getFullPath());
    }

    return (String) data.get(key);
  }
}
