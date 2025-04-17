package ru.aminovniaz.mastercardproject.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import ru.aminovniaz.mastercardproject.config.SpringSecurityWebAuxTestConfig;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringSecurityWebAuxTestConfig.class)
@AutoConfigureMockMvc
@DisplayName("AccountController is working when")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getAccounts() is working")
    class GetAccountsTest {
        @WithUserDetails("test@mail.com")
        @Test
        public void return_all_accounts() throws Exception {
            mockMvc.perform(get("/admin/accounts"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("admin@mail.com")));
        }
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getAccount() is working")
    class GetAccountTest {
        @WithUserDetails("test@mail.com")
        @Test
        public void return_account() throws Exception {
            mockMvc.perform(get("/admin/account/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)));
        }
    }

    @WithUserDetails("test@mail.com")
    @Test
    @Order(1)
    public void create_account() throws Exception {
        mockMvc.perform(post("/admin/account").contentType(APPLICATION_JSON)
                        .content("{\"email\": \"2@mail.com\",\"password\": \"2\"}"))
                .andExpect(status().isOk());
    }

    @WithUserDetails("test@mail.com")
    @Test
    @Order(2)
    public void delete_account() throws Exception {
        mockMvc.perform(delete("/admin/account/14"))
                .andExpect(status().isOk());
    }
}
