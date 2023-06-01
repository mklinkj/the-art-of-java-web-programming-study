package org.mklinkj.taojwp.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/*
 * Spring 4.3 부터는 MultiActionController가 폐기됨. Spring 5에서 완전히 삭제됨.
 * 이제 부터는 어노테이션 기반 컨트롤러 쓰라고 한다.
 */
@SuppressWarnings("deprecation")
@Slf4j
public class UserController extends MultiActionController {

  public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ModelAndView mav = new ModelAndView();
    String userID = request.getParameter("userID");
    String passwd = request.getParameter("passwd");

    mav.addObject("userID", userID);
    mav.addObject("passwd", passwd);

    String viewName = getViewName(request);
    LOGGER.info("### viewName: {}", viewName);
    mav.setViewName(viewName);

    return mav;
  }

  private String getViewName(HttpServletRequest request) {

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

  public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView(getViewName(request));

    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    mav.addObject("id", id);
    mav.addObject("pwd", pwd);
    mav.addObject("name", name);
    mav.addObject("email", email);

    return mav;
  }
}
