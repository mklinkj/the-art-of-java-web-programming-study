package org.mklinkj.taojwp.sec03.ex01;

import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/*")
public class EncoderFilter implements Filter { // HttpFilter 구현체가 있긴함.

  ServletContext context;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    LOGGER.info("{}} 인코딩.....", SERVER_ENCODING);
    context = filterConfig.getServletContext();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    LOGGER.info("doFilter 호출");
    request.setCharacterEncoding(SERVER_ENCODING);

    String context = ((HttpServletRequest) request).getContextPath();
    String pathInfo = ((HttpServletRequest) request).getRequestURI();
    String realPath = this.context.getRealPath(pathInfo);

    LOGGER.info("""
        Context 정보: {}
        URI 정보: {}
        물리적 경로: {}        
        """, context, pathInfo, realPath);

    chain.doFilter(request, response); // 다음 필터로 넘기는 작업
  }

  @Override
  public void destroy() {
    LOGGER.info("destroy 호출");
  }
}
