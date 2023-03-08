package org.mklinkj.taojwp.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {
  public static String ifNullToNullString(String string) {
    if (string == null) {
      return "null";
    } else {
      return string;
    }
  }
}
