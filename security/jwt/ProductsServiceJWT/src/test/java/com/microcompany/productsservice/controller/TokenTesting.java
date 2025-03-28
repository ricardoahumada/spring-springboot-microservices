package com.microcompany.productsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcompany.productsservice.model.AuthRequest;
import com.microcompany.productsservice.model.AuthResponse;
import com.microcompany.productsservice.model.ERole;
import com.microcompany.productsservice.model.User;
import com.microcompany.productsservice.persistence.UserRepository;
import com.microcompany.productsservice.util.JsonUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TokenTesting {

    @Autowired
    public MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    private String email = "user@email.com";//"t@t.com";
    private String password = "upass";//""tpasswrd";
    public String accessToken = null;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeAll
    public void SetUpUser() {
        // Create user

        String enc_password = passwordEncoder.encode(password);

        User aUser = new User(null, email, enc_password, ERole.USER);
        userRepository.save(aUser);
    }

    @BeforeEach
    public void setUp() throws Exception {


        // Get Token
        AuthRequest authRequest = new AuthRequest(email, password);

        MvcResult result = mvc.perform(post("/auth/login")
                        .content(JsonUtil.asJsonString(authRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        AuthResponse response = new ObjectMapper().readValue(contentAsString, AuthResponse.class);

        System.out.println("JWT Tokem: " + response.toString());

        accessToken = response.getAccessToken();
    }



}
