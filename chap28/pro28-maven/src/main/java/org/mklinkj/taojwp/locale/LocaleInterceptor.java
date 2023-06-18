package org.mklinkj.taojwp.locale;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Slf4j
public class LocaleInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    HttpSession session = request.getSession();

    String locale = request.getParameter("locale");

    if (locale != null && !locale.isBlank()) {
      LOGGER.info("### new locale: {}", locale);
      session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(locale));
    }

    return true;
  }
}
