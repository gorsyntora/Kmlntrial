package com.app.kamelntrial;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository <Quote, Integer>{
    public List<Quote> findTop10ByOrderByVote();
    public List<Quote> findTop10ByOrderByVoteDesc();
}
