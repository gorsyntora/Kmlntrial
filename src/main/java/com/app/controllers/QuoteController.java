package com.app.controllers;

import com.app.repo.Quote;
import com.app.repo.QuoteRepository;
import com.app.repo.User;
import com.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class QuoteController {
    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    UserRepository userRepository;

    public QuoteController(QuoteRepository repository, UserRepository userRepository) {
        this.quoteRepository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public Quote createQuote(@RequestParam String userName, @RequestParam String content) {
        User user = userRepository.findById(userName).orElseGet(null);
        Quote quote = null;
        if (user != null)
            quote = quoteRepository.save(new Quote(0, user.getName(), content, 0));
        return quote;
    }

    @PostMapping("/update")
    public Quote updateQuote(@RequestParam Integer quoteId, @RequestParam String content) {
        Quote quote = quoteRepository.findById(quoteId).orElseGet(Quote::new);
        quote.setText(content);
        quote = quoteRepository.save(quote);
        return quote;
    }

    @PostMapping("/delete")
    public String deleteQuote(@RequestParam Integer quoteId) {
        quoteRepository.deleteById(quoteId);
        return "OK";
    }

    @PostMapping("/upvote")
    public String upvoteQuote(@RequestParam Integer quoteId) {
        quoteRepository.updateVote(1, quoteId);
        return "";
    }

    @PostMapping("/downvote")
    public String downvoteQuote(@RequestParam Integer quoteId) {
        quoteRepository.updateVote(-1, quoteId);
        return "";
    }


    @GetMapping("/top")
    public List<Quote> getTop() {

        List<Quote> list = quoteRepository.findTop10ByOrderByVoteDesc();
        if (list == null) return new ArrayList<>();
        else return list;
    }

    @GetMapping("/flop")
    public List<Quote> getFlop() {

        List<Quote> list = quoteRepository.findTop10ByOrderByVote();
        if (list == null) return new ArrayList<>();
        Collections.reverse(list);
        return list;
    }
}
