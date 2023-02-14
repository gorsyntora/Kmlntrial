package com.app.kamelntrial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.IntStream;


@RestController
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuoteRepository quoteRepository;

    @PostMapping("/adduser")
    public String addUser(User user) {

        boolean userExist = !userRepository
                .findById(user.getName())
                .orElseGet(() -> userRepository.save(user)).equals(user);
        if (userExist) return "User already exists";
        else return "User added successfully";

    }

    @GetMapping("/top")
    public List<Quote> getTop() {
        return quoteRepository.findTop10ByOrderByVoteDesc();
    }

    @GetMapping("/flop")
    public List<Quote> getFlop() {
        List<Quote> list = quoteRepository.findTop10ByOrderByVote();
        Collections.reverse(list);
        return list;
    }

    @GetMapping("/add")
    public String add() {

        userRepository.save(new User("Wolowitz", "ew@email.com", "12345", new Date()));
        userRepository.save(new User("Sheldon", "shelly@email.com", "12345", new Date()));
        userRepository.save(new User("Leonardo", "leo@email.com", "12345", new Date()));
        userRepository.save(new User("Emma", "emma@email.com", "12345", new Date()));

        IntStream.range(1, 36).forEach(n ->
        {
            if (n <= 12) quoteRepository.save(new Quote(n, "Sheldon", "It was cool", n * 10));
            if (12 < n && n <= 24) quoteRepository.save(new Quote(n, "Emma", "I should agree, it was amassing", n * 5));
            if (n > 24) quoteRepository.save(new Quote(n, "Leonardo", "Just the text to fill content", n * 4));

        });
        return "ok";
    }

    @PostMapping("/edit")
    public String editQuote(@RequestParam Integer quoteId, @RequestParam String content) {

        Quote quote = quoteRepository.findById(quoteId).orElseGet(Quote::new);
        quote.setText(content);
        quoteRepository.save(quote);
        return "OK";
    }

    @PostMapping("/delete")
    public String deleteQuote(@RequestParam Integer quoteId) {

        Quote quote = quoteRepository.findById(quoteId).orElseGet(Quote::new);
        quoteRepository.delete(quote);
        return "OK";
    }

    @GetMapping("/test")
    public String testLinking() {
        StringBuilder sb = new StringBuilder();
        Set<Quote> set = userRepository.findById("Emma")
                .orElseGet(User::new).getQuotes();
        if (!set.isEmpty()) {
            set.forEach(q -> sb.append(q.text).append("<br>"));
        }
        return sb.toString();
    }
}
