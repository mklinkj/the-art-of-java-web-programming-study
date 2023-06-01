package ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/*
 * Spring 4.3 부터는 MultiActionController가 폐기됨. Spring 5에서 완전히 삭제됨.
 * 이제 부터는 어노테이션 기반 컨트롤러 쓰라고 한다.
 */
@SuppressWarnings("deprecation")
public class UserController extends MultiActionController {

  public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ModelAndView mav = new ModelAndView();
    String userID = request.getParameter("userID");
    String passwd = request.getParameter("passwd");

    mav.addObject("userID", userID);
    mav.addObject("passwd", passwd);
    mav.setViewName("result");

    return mav;
  }
}
