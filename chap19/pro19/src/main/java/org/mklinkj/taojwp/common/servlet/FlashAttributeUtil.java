package org.mklinkj.taojwp.common.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlashAttributeUtil {
  private static final String FLASH_KEY_PREFIX = "flash___";

  public static void setFlashAttribute(HttpServletRequest request, String key, Object value) {
    HttpSession session = request.getSession();
    session.setAttribute(FLASH_KEY_PREFIX + key, 1);
    session.setAttribute(key, value);
  }

  /** 필터로 등록하는 것이 나을지 고민을 해봐야겠다. */
  public static void cleanFlashAttribute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    Enumeration<String> names = request.getSession().getAttributeNames();

    while (names.hasMoreElements()) {
      String KeyName = names.nextElement();
      if (KeyName.startsWith(FLASH_KEY_PREFIX)) {
        int count = (int) session.getAttribute(KeyName);
        if (count == 1) {
          session.setAttribute(KeyName, 0);
        } else {
          session.removeAttribute(KeyName);
          session.removeAttribute(KeyName.replace(FLASH_KEY_PREFIX, ""));
        }
      }
    }
  }
}
