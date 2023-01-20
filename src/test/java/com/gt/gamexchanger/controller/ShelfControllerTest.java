package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import com.gt.gamexchanger.service.ShelfService;
import com.gt.gamexchanger.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShelfControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    ShelfService shelfService;
    @Test
    void getMyGames() throws Exception{

        when(shelfService.getAllMyGames(1L))
                .thenReturn(List.of(new GameDto(1L,"abc","abc", GameStatus.AVAILABLE,1L,1L,Visibility.PUBLIC)));

        mvc
                .perform(MockMvcRequestBuilders.get("/shelf/getMyGames/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("abc"));
    }
    @Test
    void getBorrowedGames() throws Exception{

        when(shelfService.getAllBorowedGame(1L))
                .thenReturn(List.of(new GameDto(1L,"Splendor","abc", GameStatus.AVAILABLE,2L,1L,Visibility.PUBLIC)));

        mvc
                .perform(MockMvcRequestBuilders.get("/shelf/getBorrowedGames/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Splendor"));

    }
}