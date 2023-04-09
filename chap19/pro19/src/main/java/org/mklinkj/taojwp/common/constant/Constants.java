package org.mklinkj.taojwp.common.constant;

import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final String UTF_8_ENCODING = StandardCharsets.UTF_8.name();

  public static final String HTML_CONTENT_TYPE =
      String.format("text/html;charset=%s", UTF_8_ENCODING);

  public static final String JSON_CONTENT_TYPE = "application/json";

  public static final int MEGA_BYTE = 1024 * 1024;

  public static final String UPLOAD_DIR = "C:\\upload\\art_of_java_web";

  public static final String UPLOAD_TEMP_DIR = UPLOAD_DIR + "\\temp";

  public static final int PAGE_SIZE = 10;

  public static final int PAGE_NAVI_SIZE = 10;

  public static final String VIEW_ROOT_PATH_FORMAT = "/WEB-INF/views%s";

  public static final String LOGIN_INFO_KEY_NAME = "loginInfo";
}
