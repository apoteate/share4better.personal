package com.webapp.share4better.sevice;

import com.webapp.share4better.model.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfileServiceTest {
    @Autowired
    private IProfileService service;

    @Autowired
    private AddUserService userService;

    @Test
    public void testAddGetRemoveUser() {

        //Adding User
        Profile userProfile = new Profile();
        userProfile.setUserName("SUNNY");
        userProfile.setUserEmail("sunny123@hotmail.com");
        userProfile.setUserPassword("password");
        userProfile.setDonorStatus(true);
        userService.insertWithQuery(userProfile);

        // checking if the user is added to the DB
        Iterable<Profile> profiles =  service.getUserProfile("sunny123@hotmail.com");

        assertEquals("SUNNY", profiles.iterator().next().getUserName());



    }





}