package org.mklinkj.taojwp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/main.do")
  public void home() {}
}
