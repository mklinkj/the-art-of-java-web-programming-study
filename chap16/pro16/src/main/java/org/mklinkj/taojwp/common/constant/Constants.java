package org.mklinkj.taojwp.common.constant;

import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final String UTF_8_ENCODING = StandardCharsets.UTF_8.name();

  public static final String HTML_CONTENT_TYPE =
      String.format("text/html;charset=%s", UTF_8_ENCODING);

  public static final int MEGA_BYTE = 1024 * 1024;
}
