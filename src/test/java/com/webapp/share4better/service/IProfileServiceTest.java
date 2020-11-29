package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.model.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.*;
@SpringBootTest
public class IProfileServiceTest {

    @Autowired
    private IProfileService service;

    @Test
    public void testGetUpdateAndRemoveProfileInformation(){

        Profile profile = new Profile();
        profile.setUser_id(99999999);
        profile.setUser_name("FirstName LastName");
        profile.setUser_password("123456");
        profile.setDonor_status(true);
        profile.setUser_email("test@test.com");
        //Adding test profile to the table.
        service.addUser(profile);

        //getting data from table which was added.
        Optional<Profile> getProfile = service.getUserProfile("test@test.com");
        //asserting return data to the actual values.
        assertTrue(getProfile.isPresent());
        assertEquals(profile.getUser_email(),getProfile.get().getUser_email());
        assertEquals(profile.getUser_name(),getProfile.get().getUser_name());
        assertEquals(profile.getUser_id(),getProfile.get().getUser_id());
        assertEquals(profile.getUser_password(),getProfile.get().getUser_password());
        assertEquals(profile.isDonor_status(),getProfile.get().isDonor_status());


        //removing added test data from the table.
        service.removeUser(profile.getUser_id());
        //getting data from table which was added.
        Optional<Profile> getProfile1 = service.findUserById(99999999);
        //asserting return data to the actual values.
        assertFalse("Retrieved Address from address Table",getProfile1.isPresent());
    }

}