package org.mklinkj.taojwp.member.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mklinkj.taojwp.member.domain.MemberVO;
import org.mklinkj.taojwp.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/listMembers.do")
  public void listMembers(Model model) {
    List<MemberVO> memberList = memberService.listMembers();
    model.addAttribute("memberList", memberList);
  }

  @GetMapping("/memberForm.do")
  public void memberForm() {}

  @GetMapping("/memberDetail.do")
  public void memberDetail(@RequestParam("id") String id, Model model) {
    MemberVO member = memberService.getMember(id);
    model.addAttribute("member", member);
  }

  @PostMapping("/addMember.do")
  public String addMember(MemberVO memberVO) {
    memberService.addMember(memberVO);
    return "redirect:/member/listMembers.do";
  }
}
