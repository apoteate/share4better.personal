package com.webapp.share4better.sevice;

import com.webapp.share4better.model.Profile;

public interface IProfileService {

    Iterable<Profile> getUserNameAndPassword(String userName);

    Iterable<Profile> getUserProfile(String userName);
}
