package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuoteRepository extends JpaRepository <Quote, Integer>{

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query (value="UPDATE quotes SET quotes.vote = quotes.vote + :qvote WHERE quotes.id = :qid",  nativeQuery = true)

    public void updateVote(int qvote, int qid);
    public List<Quote> findTop10ByOrderByVote();
    public List<Quote> findTop10ByOrderByVoteDesc();
}
