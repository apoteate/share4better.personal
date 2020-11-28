//package com.webapp.share4better.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.webapp.share4better.service.IProfileService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = ProfileListController.class)
//
//public class ProfileListControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
////
////    @MockBean
////    private IProfileService service;
////    @MockBean
////    private AddUserService userService;
////
////    @Test
////    void whenValidInput_thenReturns200() throws Exception {
////        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
////
////        params.add("userEmail","kartikr18@hotmail.com");
////        params.add("password","123456");
////
////        when(service.getUserProfile("kartikr18@hotmail.com")).thenReturn(any());
////        mockMvc.perform(MockMvcRequestBuilders.post("/validateUser").params(params));
////
////
////
////
////    }
//
//}