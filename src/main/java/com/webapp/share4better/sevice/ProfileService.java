package com.webapp.share4better.sevice;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileService implements IProfileService {

    @Autowired
    private IUserRepository repository;

    @Override
    public Iterable<Profile> getUserNameAndPassword(String userName) {
        return repository.userAuthentication(userName);
    }

    @Override
    public Iterable<Profile> getUserProfile(String userName) {
        repository.findById(userName);
        return repository.userProfile(userName);
    }
}
