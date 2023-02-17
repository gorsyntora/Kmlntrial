package com.app.kamelntrial;

import com.app.controllers.QuoteController;
import com.app.controllers.UserController;
import com.app.repo.QuoteRepository;
import com.app.repo.User;
import com.app.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserController controller = new UserController(userRepository);

    @BeforeEach
    public void setup() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void addUserTest() throws Exception {
        User user = new User("Emma", "ew@email.com", "12345", new Date());
        when(userRepository.findById("Emma")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        mockMvc.perform(post("/adduser")
                        .param("name", "Emma")
                        .param("email", "Heroku@newmail.jp")
                        .param("password", "4545")
                        .param("date", "Thu Feb 16 2023 22:50:32 GMT+0300 (Москва, стандартное время)")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void addUserValidateTest() throws Exception {
        User user = new User("Emma", "ew@email.com", "12345", new Date());
        when(userRepository.findById("Emma")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/adduser")
                        .param("name", "Emma")
                        .param("email", "Non valid Email")
                        .param("password", "4545")
                        .param("date", "Thu Feb 16 2023 22:50:32 GMT+0300 (Москва, стандартное время)")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                )
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}
