package com.webapp.share4better.service;

import com.webapp.share4better.model.Profile;

import java.util.Optional;

public interface IProfileService {
    public Optional<Profile> getUserProfile(String userEmail);
    public void addUser(Profile profile);
    public Optional<Profile> findUserById(Integer id);
    public void removeUser(Integer id);

}
