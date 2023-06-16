package org.mklinkj.taojwp.common.vault;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Vault 클라이언트 싱글톤 생성 헬퍼 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VaultClientHelper {
  public static VaultClient vaultClient() {
    return VaultClientHolder.INSTANCE;
  }

  private static class VaultClientHolder {
    private static final VaultClient INSTANCE = new VaultClient();
  }
}
