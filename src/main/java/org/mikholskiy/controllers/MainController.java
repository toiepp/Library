package org.mikholskiy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
  private final Dao dao;

  public MainController(Dao dao) {
    this.dao = dao;
  }

  @GetMapping
  public String mainPage() {
    return "index";
  }

  @GetMapping("/test")
  public String test() {
    System.out.println(dao.testBook());
    return "test-db";
  }
}
