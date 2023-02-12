package com.gt.gamexchanger.controller;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FriendRequestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    FriendRequestService friendRequestService;
    FriendRequestControllerTest.UnderTest underTest;
    RequestFriendDto requestFriendDto;

    @BeforeEach
    void setUp() {
        requestFriendDto = new RequestFriendDto();
        friendRequestService = mock(FriendRequestService.class);
        underTest = new FriendRequestControllerTest.UnderTest();
    }
    @Test
    void addRequest() {
        underTest.addRequest(requestFriendDto);
        verify(friendRequestService,times(1)).addFriendRequest(requestFriendDto);
    }
    @Test
    void getAllRequests() {
        underTest.getAllRequests();
        verify(friendRequestService, times(1)).getAllRequest();
    }
    @Test
    void deleteRequest() {
        underTest.deleteRequest(requestFriendDto.getRequestFriendId());
        verify(friendRequestService,times(1)).removeFriendRequestById(requestFriendDto.getRequestFriendId());
    }

    @Test
    void updateStatus() {
        underTest.updateStatus(requestFriendDto.getRequestFriendId(),RequestStatus.ACCEPTED);
        verify(friendRequestService,times(1)).updateStatus(requestFriendDto.getRequestFriendId(),RequestStatus.ACCEPTED);
    }

    @Test
    void getSendRequests() {
        underTest.getSendRequests(requestFriendDto.getRequestFriendId());
        verify(friendRequestService,times(1)).getMySendFriendRequest(requestFriendDto.getRequestFriendId());
    }
    @Test
    void getReceivedRequests() {
        underTest.getReceivedRequests(requestFriendDto.getRequestFriendId());
        verify(friendRequestService,times(1)).getReceivedFriendRequest(requestFriendDto.getRequestFriendId());
    }
    class UnderTest extends FriendRequestController{
        public UnderTest() {
            super(friendRequestService);
        }
    }
}

///////////////// testy integracyjne/////////////////////////////////
 /*  @Test
    void getReqests() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/requestgame/requests")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }*/

  /*  @Test
    void addRequestGame() throws Exception {
        RequestGameDto requestGameDto= new RequestGameDto(1L, RequestStatus.WAITING, 1L, 2L, 1L, "abc");
        when(gameRequestService.addGameRequest(requestGameDto))
                .thenReturn(new RequestGameDto(1L, RequestStatus.WAITING, 1L, 2L, 1L, "abc"));

        mvc
                .perform(MockMvcRequestBuilders.get("/requestgame/addRequest"))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[1].message").value("cos"));
    }*/
    /*    @Test
    void getSendReqests() throws  Exception{

        when(gameRequestService.getMySendFriendRequest(1L))
                .thenReturn(List.of(new RequestFriendDto( RequestStatus.WAITING, 1L, 2L,  "abc")));

        mvc
                .perform(MockMvcRequestBuilders.get("/requestgame/sendReqests/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("abc"));
    }*/

  /*  @Test
    void getReceivedReqests() throws Exception{

        when(gameRequestService.getReceivedFriendRequest(2L))
                .thenReturn(List.of(new RequestFriendDto( RequestStatus.WAITING, 1L, 2L, "abc")));

        mvc
                .perform(MockMvcRequestBuilders.get("/requestgame/receivedReqests/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("abc"));
    }*/
