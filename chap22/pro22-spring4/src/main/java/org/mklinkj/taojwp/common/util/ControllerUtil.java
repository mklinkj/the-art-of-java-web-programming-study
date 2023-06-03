package org.mklinkj.taojwp.common.util;

import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerUtil {

  public static String getViewName(HttpServletRequest request) {

    String contextPath = request.getContextPath();
    String uri = (String) request.getAttribute("javax.servlet.include.request_uri");

    if (uri == null || uri.isBlank()) {
      uri = request.getRequestURI();
    }

    int begin = 0;
    if (contextPath != null && !contextPath.isBlank()) {
      begin = contextPath.length();
    }

    int end;

    if (uri.indexOf(";") != -1) {
      end = uri.indexOf(";");
    } else if (uri.indexOf("?") != -1) {
      end = uri.indexOf("?");
    } else {
      end = uri.length();
    }

    String fileName = uri.substring(begin, end);
    if (fileName.indexOf(".") != -1) {
      fileName = fileName.substring(0, fileName.lastIndexOf("."));
    }
    if (fileName.lastIndexOf("/") + 1 <= fileName.length()) {
      fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
    }

    return fileName;
  }
}
