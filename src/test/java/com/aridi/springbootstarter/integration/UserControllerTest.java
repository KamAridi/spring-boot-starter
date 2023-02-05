package com.aridi.springbootstarter.integration;

import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void shouldFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("all-users"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("allUsers"))
                .andExpect(MockMvcResultMatchers.model().attribute("allUsers", IsCollectionWithSize.hasSize(31)));

    }

    @Test
    void shouldCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .param("firstname", "alex")
                        .param("lastname", "alexov"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void shouldGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/5"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("all-users"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void shouldUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1")
                        .param("firstname", "changed name")
                        .param("lastname", "changed lastname"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}