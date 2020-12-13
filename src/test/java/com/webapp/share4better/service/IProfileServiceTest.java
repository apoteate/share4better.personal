package com.webapp.share4better.service;

import com.webapp.share4better.model.Profile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IProfileServiceTest {


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
    public void testGetUpdateAndRemoveProfileInformation() {

        //getting data from table which was added.
        Optional<Profile> getProfile = service.getUserProfile("test@test.com");
        //asserting return data to the actual values.
        assertTrue(getProfile.isPresent());
        assertEquals("test@test.com", getProfile.get().getUser_email());
        assertEquals("FirstName LastName", getProfile.get().getUser_name());
        assertEquals(99999999, getProfile.get().getUser_id());
        assertEquals("123456", getProfile.get().getUser_password());
    }

}