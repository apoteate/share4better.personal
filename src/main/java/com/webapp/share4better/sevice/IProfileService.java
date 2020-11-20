package com.webapp.share4better.sevice;

import com.webapp.share4better.model.Profile;

public interface IProfileService {
    public Iterable<Profile> getUserProfile(String userEmail);

}
