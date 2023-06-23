package org.mklinkj.taojwp.common.vault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class VaultConfig {
  private String serverUrl;
  @ToString.Exclude private String policyToken;
  private String rootPath;
  private String projectPath;

  public String getFullPath() {
    return String.format("/%s/%s", rootPath, projectPath);
  }
}
