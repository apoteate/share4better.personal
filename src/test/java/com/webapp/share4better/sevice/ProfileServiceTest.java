package com.webapp.share4better.sevice;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.service.AddUserService;
import com.webapp.share4better.service.IProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProfileServiceTest {
    @Autowired
    private IProfileService service;

    @Mock
    private AddUserService userService;

    @Test
    public void testAddAndFindUser() {

        //Adding User
        Profile userProfile = new Profile();
        userProfile.setUserName("SUNNY");
        userProfile.setUserEmail("sunny123@hotmail.com");
        userProfile.setUserPassword("password");
        userProfile.setDonorStatus(true);
        userService.insertWithQuery(userProfile);

        Iterable<Profile> profiles =  service.getUserProfile("sunny123@hotmail.com");
       assertEquals("SUNNY", profiles.iterator().next().getUserName());

    }


}