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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // comentar si extendemos de TokenTesting
@AutoConfigureMockMvc // comentar si extendemos de TokenTesting
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // comentar si extendemos de TokenTesting
@Sql("classpath:data_testing.sql")
class AuthServiceTest /*extends TokenTesting*/ {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    private String email = "user@email.com";
    private String password = "upass";


    /*** Cuando no extendemos de TokenTesting ***/

    @Autowired
    PasswordEncoder passwordEncoder;

    private String accessToken = null;

    @BeforeAll
    public void SetUpUser() {
        // Create user
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    /*** FIN: Cuando no extendemos de TokenTesting ***/


    /*** Parte común ***/

    @Test
    void given_accesstoken_when_getproducts_then_success() {

        try {
            // given: existing token
            System.out.println("*********** accessToken:" + accessToken);
            // when
            mvc.perform(get("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer " + accessToken)
                    )
                    // then
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    void given_noAccesstoken_when_getproducts_then_UnAuth() {

        try {
            // given: NO existing token

            // when
            mvc.perform(get("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    // then
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isUnauthorized());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }


}