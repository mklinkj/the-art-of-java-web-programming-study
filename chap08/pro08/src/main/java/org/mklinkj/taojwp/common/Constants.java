package org.mklinkj.taojwp.common;

import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final String SERVER_ENCODING = StandardCharsets.UTF_8.name();

  public static final String HTML_CONTENT_TYPE =
      String.format("text/html;charset=%s", SERVER_ENCODING);
}
