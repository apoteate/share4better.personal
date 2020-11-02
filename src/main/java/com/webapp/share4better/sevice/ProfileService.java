package com.webapp.share4better.sevice;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements IProfileService {
    @Autowired
    private IUserRepository repository;

    @Override
    public Iterable<Profile> getUserNameAndPassword(String userName) {
        return repository.userAuthentication(userName);
    }

    @Override
    public Iterable<Profile> getUserProfile(String userName) {
        return repository.userProfile(userName);
    }
}
