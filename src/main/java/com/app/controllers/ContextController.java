package com.app.controllers;

import com.app.kamelntrial.AppContextLoader;
import com.app.repo.Quote;
import com.app.repo.QuoteRepository;
import com.app.repo.User;
import com.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class ContextController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/addall")
    public String addall() {
        AppContextLoader.createData(quoteRepository,userRepository);
        return "ok";
    }

    @GetMapping("/test")
    public String testLinking() {
        StringBuilder sb = new StringBuilder();
        Set<Quote> set = userRepository.findById("Emma")
                .orElseGet(User::new).getQuotes();
        if (!set.isEmpty()) {
            set.forEach(q -> sb.append(q.getText()).append("<br>"));
        }
        return sb.toString();
    }


}
