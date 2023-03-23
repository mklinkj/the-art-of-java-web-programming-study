package org.mklinkj.taojwp.common;

import java.nio.file.Paths;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {
  public static String ifNullToNullString(String string) {
    return String.valueOf(string);
  }

  public static String fileNameOnly(String fileName) {
    return Paths.get(fileName).getFileName().toString();
  }
}
