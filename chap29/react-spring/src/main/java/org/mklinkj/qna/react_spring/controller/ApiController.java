package org.mklinkj.qna.react_spring.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  @GetMapping("/welcome")
  public Map<String, Object> welcome() {
    return Map.of("message", "welcome");
  }
}
