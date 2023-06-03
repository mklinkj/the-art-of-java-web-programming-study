package org.mklinkj.taojwp.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.springframework.web.servlet.ModelAndView;

public interface MemberController {
  ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response);
  // ✨: HttpServletRequest request, HttpServletResponse response 를 둘 다 메서드 인자로 받아야 제대로 동작한다.
  ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response);

  ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse response);

  String addMember(HttpServletRequest request, HttpServletResponse response, MemberVO memberVO);
}
