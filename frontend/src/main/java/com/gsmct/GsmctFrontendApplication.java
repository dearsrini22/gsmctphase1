package com.gsmct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class GsmctFrontendApplication {

  public static void main(String[] args) {
    SpringApplication.run(GsmctFrontendApplication.class, args);
  }

  @RequestMapping(value = "/")
  public String index(Model model) {
    return "index";
  }
}
