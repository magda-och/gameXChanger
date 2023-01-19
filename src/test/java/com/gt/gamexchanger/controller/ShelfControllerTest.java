package com.gt.gamexchanger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.model.Shelf;
import com.gt.gamexchanger.repository.GameRepository;
import com.gt.gamexchanger.service.ShelfService;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest
public class ShelfControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ShelfService shelfService;

    Game game1 = new Game(1L,"cos","cosDesc", GameStatus.AVAILABLE, 1L, 2L);
    Game game2 = new Game(2L,"cos2","cosDesc2", GameStatus.AVAILABLE, 1L, 2L);
    Game game3 = new Game(3L,"cos3","cosDesc3", GameStatus.AVAILABLE, 1L, 2L);

    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        List<Game> myGames = new ArrayList<>(Arrays.asList(game1, game2, game3));

        //Mockito.when(shelfService.getAllMyGames(1L))
        //        .thenReturn(myGames);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelf/getMyGames/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ShelfController shelfController;

    @MockBean
    ShelfService shelfService;

    @Test
    public void empty() {
    }

    @Test
    public void empty2() {
        assertEquals("12","21");
    }

    Game game1 = new Game(1L,"cos","cosDesc", GameStatus.AVAILABLE, 1L, 2L);
    Game game2 = new Game(2L,"cos2","cosDesc2", GameStatus.AVAILABLE, 1L, 2L);
    Game game3 = new Game(3L,"cos3","cosDesc3", GameStatus.AVAILABLE, 1L, 2L);

    @Test
    public void getMyGames() throws Exception {
        ShelfController shelfController = new ShelfController(shelfService);

        List<Game> myGames = new ArrayList<>(Arrays.asList(game1, game2, game3));

        Mockito.when(shelfService.getAllMyGames(1L))
                .thenReturn(myGames);

        shelfController.getMyGames(1L);
        //mockMvc .perform(MockMvcRequestBuilders
        //                .get("/shelf/getMyGames/1")
        //                .contentType(MediaType.APPLICATION_JSON))
        //        .andExpect(status().isOk());
                //.andExpect(jsonPath("", hasSize(3)))
                //.andExpect((ResultMatcher) jsonPath("$[3].name", is("cos3")));
    }

*/
    /*@Test
    public void getBorrowedGames() {
    }*/
}
