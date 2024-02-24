package org.mklinkj.taojwp.member.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
  public void memberForm(@ModelAttribute("command") MemberVO command) {
    // Spring Controller Void Method
  }

  /** 상세보기 폼과, 수정 폼은 동일한 로직 */
  @GetMapping({"/memberDetail.do", "/modMemberForm.do"})
  public String memberDetailOrModMemberForm(
      @RequestParam("id") String id,
      Model model,
      RedirectAttributes redirectAttributes,
      HttpServletRequest request) {
    MemberVO member = memberService.getMember(id);

    if (member == null) {
      redirectAttributes.addFlashAttribute("result", "error.notfound.member");
      return "redirect:/member/listMembers.do";
    }

    model.addAttribute("member", member);

    List<String> list =
        UriComponentsBuilder.fromUriString(request.getRequestURI()).build().getPathSegments();

    return String.format("member/%s", list.get(list.size() - 1).replace(".do", ""));
  }

  @RequestMapping(
      value = "/addMember.do",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String addMember(
      @ModelAttribute("command") @Valid MemberVO memberVO,
      BindingResult bindingResult,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes,
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
    redirectAttributes.addFlashAttribute("result", "register.success");
    return "redirect:/member/listMembers.do";
  }

  @RequestMapping(
      value = "/modMember.do",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String modMember(
      @ModelAttribute("member") @Valid MemberVO memberVO,
      BindingResult bindingResult,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes,
      HttpServletResponse response) {

    if (request.getMethod().equals(RequestMethod.GET.name())) {
      redirectAttributes.addAttribute("id", memberVO.getId());
      return "redirect:/member/modMemberForm.do";
    }

    if (bindingResult.hasErrors()) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      return "member/modMemberForm";
    }

    memberService.updateMember(memberVO);
    redirectAttributes.addAttribute("id", memberVO.getId());
    redirectAttributes.addFlashAttribute("result", "modify.success");
    return "redirect:/member/modMemberForm.do";
  }

  @PostMapping("/delMember.do")
  public String delMember(String id) {
    memberService.delMember(id);
    return "redirect:/member/listMembers.do";
  }
}
