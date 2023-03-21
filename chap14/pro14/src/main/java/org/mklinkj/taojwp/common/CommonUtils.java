package org.mklinkj.taojwp.common;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {
  public static String ifNullToNullString(String string) {
    return Optional.ofNullable(string).orElse("null");
  }
}
