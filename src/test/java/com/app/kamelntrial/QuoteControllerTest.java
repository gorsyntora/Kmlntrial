package com.app.kamelntrial;

import com.app.controllers.QuoteController;
import com.app.repo.Quote;
import com.app.repo.QuoteRepository;
import com.app.repo.User;
import com.app.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuoteControllerTest {

    private MockMvc mockMvc;
    private final QuoteRepository repository = Mockito.mock(QuoteRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final QuoteController controller = new QuoteController(repository,userRepository);
    List<Quote> quotes = new ArrayList<>();

    @BeforeEach
    public void setup() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        IntStream.range(1, 36).forEach(n ->
        {
            if (n <= 12) quotes.add(new Quote(0, "Sheldon", "It was cool", n * 10));
            if (12 < n && n <= 24) quotes.add(new Quote(0, "Emma", "I should agree, it was amassing", n * 5));
            if (n > 24) quotes.add(new Quote(0, "Leonardo", "Just the text to fill the content", n * 4));
        });


    }

    @Test
    void createTest() throws Exception {
        User user = new User("Wolowitz", "ew@email.com", "12345", new Date());
        when(userRepository.findById("Emma")).thenReturn(Optional.of(user));
        when(repository.save(quotes.get(1))).thenReturn( quotes.get(1));

        mockMvc.perform(post("/create")
                        .param("userName", "Emma")
                        .param("content", "Heroku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void updateTest() throws Exception {

        when(repository.findById(1)).thenReturn(Optional.ofNullable(quotes.get(1)));
        mockMvc.perform(post("/update")
                        .param("quoteId", "1")
                        .param("content", "Heroku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getTop () throws Exception {
        when(repository.findTop10ByOrderByVoteDesc()).thenReturn(quotes);
        mockMvc.perform(get("/top"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}



