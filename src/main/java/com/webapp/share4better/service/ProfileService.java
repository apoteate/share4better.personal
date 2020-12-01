package com.webapp.share4better.service;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService implements IProfileService {
    @Autowired
    private IUserRepository repository;

    @Override
    public Optional<Profile> getUserProfile(String userEmail) {
        return repository.userAuthentication(userEmail);
    }

    @Override
    public void addUser(Profile profile) {
         repository.save(profile);
    }

    @Override
    public Optional<Profile> findUserById(Integer id) {
        return repository.findUser(id);
    }

    @Override
    public void removeUser(Integer id) {
        repository.deleteById(id);
    }

}
