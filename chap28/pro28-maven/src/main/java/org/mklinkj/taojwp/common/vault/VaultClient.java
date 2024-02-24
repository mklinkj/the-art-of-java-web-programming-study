package org.mklinkj.taojwp.common.vault;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StreamUtils;
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

      this.config =
          new VaultConfig(
              prop.getProperty("vault.server.url"),
              token(),
              prop.getProperty("vault.root.path"),
              prop.getProperty("vault.project.path"));

      VaultEndpoint vaultEndpoint = VaultEndpoint.from(URI.create(config.getServerUrl()));

      vaultTemplate =
          new VaultTemplate(vaultEndpoint, new TokenAuthentication(config.getPolicyToken()));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private String token() {
    Path vaultTokenPath = Paths.get(System.getProperty("user.home"), ".vault-token");
    try (InputStream inputStream = Files.newInputStream(vaultTokenPath)) {
      return StreamUtils.copyToString(inputStream, UTF_8).trim();
    } catch (Exception e) {
      throw new IllegalArgumentException("사용자 홈 경로의 토큰 로드 실패", e);
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
