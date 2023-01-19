package com.gt.gamexchanger.controller;


import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.mapper.UserDtoMapper;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.UserRepository;
import com.gt.gamexchanger.service.UserService;
import org.json.JSONString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

import  com.gt.gamexchanger.dto.UserDto.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = UserController.class)
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;


    @Test
    public void getAllUsers_beforeAddUser_status200() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/user/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

    }

    @Test
    public void getAllUsers_afterAddUser_shouldReturnProperly() throws Exception {
        when(userService.getAllUsers())
                .thenReturn(List.of(new UserDto("Magda", "Och", "magda@wp.pl", "123")));

        mvc
                .perform(MockMvcRequestBuilders.get("/user/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Magda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Och"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("magda@wp.pl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("123"));

    }
}
