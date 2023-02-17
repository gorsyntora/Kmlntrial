package com.app.controllers;

import com.app.repo.Quote;
import com.app.repo.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@Controller

public class ViewController {
    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/")
    public String start() {
          return "index.html";
    }


}
