package com.gt.gamexchanger.controller;
/*
import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.service.FriendRequestService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameRequestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    FriendRequestService gameRequestService;
    @Test
    void getReqests() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/requestgame/requests")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void addRequestGame() throws Exception {
        RequestGameDto requestGameDto= new RequestGameDto(1L, RequestStatus.WAITING, 1L, 2L, 1L, "abc");
        when(gameRequestService.addGameRequest(requestGameDto))
                .thenReturn(new RequestGameDto(1L, RequestStatus.WAITING, 1L, 2L, 1L, "abc"));

        mvc
                .perform(MockMvcRequestBuilders.get("/requestgame/addRequest"))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[1].message").value("cos"));
    }

    @Test
    void deleteRequest() {
    }

    @Test
    void updateRequest() {
    }

    @Test
    void getSendReqests() throws  Exception{

        when(gameRequestService.getMySendFriendRequest(1L))
                .thenReturn(List.of(new RequestFriendDto( RequestStatus.WAITING, 1L, 2L,  "abc")));

        mvc
                .perform(MockMvcRequestBuilders.get("/requestgame/sendReqests/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("abc"));
    }

    @Test
    void getReceivedReqests() throws Exception{

        when(gameRequestService.getReceivedFriendRequest(2L))
                .thenReturn(List.of(new RequestFriendDto( RequestStatus.WAITING, 1L, 2L, "abc")));

        mvc
                .perform(MockMvcRequestBuilders.get("/requestgame/receivedReqests/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("abc"));
    }
}
*/