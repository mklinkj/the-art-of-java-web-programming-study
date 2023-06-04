package org.mklinkj.taojwp.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.dto.SearchDTO;
import org.mklinkj.taojwp.member.dto.SearchType;
import org.mklinkj.taojwp.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/listMembers.do")
  public void listMembers(SearchDTO searchDTO, Model model) {
    List<MemberVO> memberList = memberService.listMembers(searchDTO);
    model.addAttribute("memberList", memberList);
    model.addAttribute("searchTypes", SearchType.values());
  }

  @GetMapping("/memberForm.do")
  public void memberForm(@ModelAttribute("command") MemberVO command) {}

  @GetMapping("/memberDetail.do")
  public void memberDetail(@RequestParam("id") String id, Model model) {
    MemberVO member = memberService.getMember(id);
    model.addAttribute("member", member);
  }

  @RequestMapping(
      value = "/addMember.do",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String addMember(
      @ModelAttribute("command") @Valid MemberVO memberVO,
      BindingResult bindingResult,
      HttpServletRequest request,
      HttpServletResponse response) {

    if (request.getMethod().equals(RequestMethod.GET.name())) {
      return "redirect:/member/memberForm.do";
    }

    if (bindingResult.hasErrors()) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      // 폼 내용 유지하려고, 등록 실패시는 forward 를 함.
      return "member/memberForm";
    }
    memberService.addMember(memberVO);
    return "redirect:/member/listMembers.do";
  }
}
