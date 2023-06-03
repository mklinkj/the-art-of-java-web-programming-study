package org.mklinkj.taojwp.member.controller;

import static org.mklinkj.taojwp.common.util.ControllerUtil.getViewName;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.service.MemberService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@SuppressWarnings("deprecation")
public class MemberControllerImpl extends MultiActionController implements MemberController {

  @Setter private MemberService memberService;

  @Override
  public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) {
    String viewName = getViewName(request);
    List<MemberVO> memberList = memberService.listMembers();
    ModelAndView mav = new ModelAndView(viewName);
    mav.addObject("memberList", memberList);
    return mav;
  }

  // ✨: HttpServletRequest request, HttpServletResponse response 를 둘 다 메서드 인자로 받아야 제대로 동작한다.
  @Override
  public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) {
    return new ModelAndView(getViewName(request));
  }

  @Override
  public ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse response) {
    String id = request.getParameter("id");

    String viewName = getViewName(request);
    ModelAndView mav = new ModelAndView(viewName);

    MemberVO member = memberService.getMember(id);
    mav.addObject("member", member);

    return mav;
  }

  @Override
  public String addMember(
      HttpServletRequest request, HttpServletResponse response, MemberVO memberVO) {
    memberService.addMember(memberVO);
    return "redirect:/member/listMembers.do";
  }
}
