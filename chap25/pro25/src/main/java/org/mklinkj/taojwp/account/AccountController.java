package org.mklinkj.taojwp.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @RequestMapping("/sendMoney.do")
  public String sendMoney(Model model) {
    accountService.sendMoney();
    model.addAttribute("list", accountService.selectAll());
    return "account/result";
  }

  @RequestMapping("/resetAllBalance.do")
  public String resetAllBalance(Model model) {
    accountService.resetAllBalance();
    model.addAttribute("list", accountService.selectAll());
    return "account/result";
  }
}
