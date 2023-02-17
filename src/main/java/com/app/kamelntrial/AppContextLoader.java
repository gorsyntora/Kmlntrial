package com.app.kamelntrial;

//Generate data fo db testing manually, Postman, Selenium

import com.app.repo.Quote;
import com.app.repo.QuoteRepository;
import com.app.repo.User;
import com.app.repo.UserRepository;

import java.util.Date;
import java.util.stream.IntStream;

public class AppContextLoader {

   public static void createData(QuoteRepository quoteRepository, UserRepository userRepository) {
        userRepository.save(new User("Wolowitz", "ew@email.com", "12345", new Date()));
        userRepository.save(new User("Sheldon", "shelly@email.com", "12345", new Date()));
        userRepository.save(new User("Leonardo", "leo@email.com", "12345", new Date()));
        userRepository.save(new User("Emma", "emma@email.com", "12345", new Date()));

        IntStream.range(1, 36).forEach(n ->
        {
            if (n <= 12) quoteRepository.save(new Quote(0, "Sheldon", "It was cool", n * 10));
            if (12 < n && n <= 24) quoteRepository.save(new Quote(0, "Emma", "I should agree, it was amassing", n * 5));
            if (n > 24) quoteRepository.save(new Quote(0, "Leonardo", "Just the text to fill the content", n * 4));

        });


    }
}
