package com.webapp.share4better.controller;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.service.IProfileService;
import com.webapp.share4better.service.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
public class ProfileListControllerTest {

    @Autowired
    private ProfileListController profileListController;

    @Autowired
    private IProfileService service;

    @Autowired
    private TestUtil testUtil;

    @BeforeEach
    public void setUpStart() {
        testUtil.addProfileTestData();

    }

    @AfterEach
    public void setUpEnd() {
        testUtil.removeProfileTestData();
    }

    @Test
    public void getUserProfile_Valid() {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        String isValid = profileListController.getUserProfile("test@test.com", "123456", mockRequest);
        assertEquals("VALID USER", "redirect:/home.html", isValid);
    }

    @Test
    public void getUserProfile_Invalid_password() {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(99999999);
        String isValid = profileListController.getUserProfile("test@test.com", "123457", mockRequest);
        assertEquals("INVALID PASSWORD", "redirect:/index.html#id03", isValid);
    }

    @Test
    public void getUserProfile_Invalid_user() {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        String isValid = profileListController.getUserProfile("1233223", "123457", mockRequest);
        assertEquals("INVALID USER", "redirect:/index.html#id03", isValid);
    }

    @Test
    public void signupUser_AlreadyExist() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(99999999);
        String isValid = profileListController.signupUser("kartik suthar", "test@test.com", "123456", mockRequest);
        assertEquals("USER ALREADY EXIST", "redirect:/index.html#id04", isValid);
    }

    @Test
    public void signupUser() {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);


        String isValid = profileListController.signupUser("Test User", "testUser191919119@123.com", "123456", mockRequest);
        assertEquals("SIGN UP", "redirect:/home.html", isValid);

        Profile profile = service.getUserProfile("testUser191919119@123.com").get();
        service.removeUser(profile.getUser_id());
    }

    @Test
    void getUser() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        Optional<Profile> profile1 = service.getUserProfile("test@test.com");

        when(httpServletRequest.getSession().getAttribute("userID")).thenReturn(profile1.get().getUser_id());
        Profile profile = profileListController.getUser(httpServletRequest).getBody();
        assertEquals("UserName", "FirstName LastName", profile.getUser_name());
        assertEquals("UserEmail", "test@test.com", profile.getUser_email());
        assertEquals("Password", "123456", profile.getUser_password());
    }


    @Test
    public void destroySession() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        httpServletRequest.getSession().setAttribute("userID", 1111111111);
        profileListController.destroySession(httpServletRequest);
        assertNull("Session Destroyed expected null", httpServletRequest.getSession().getAttribute("userID"));

    }
}