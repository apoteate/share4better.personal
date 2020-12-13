package com.webapp.share4better.service;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.repository.IUserRepository;
import com.webapp.share4better.service.encruption.IEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class ProfileService implements IProfileService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    private IEncryption encryption;

    @Override
    public Optional<Profile> getUserProfile(String userEmail) {
        Optional<Profile> profile = repository.userAuthentication(userEmail);
        if (profile.isPresent()) {
            String password = null;
            try {
                password = encryption.decrypt(profile.get().getUser_password());
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
            profile.get().setUser_password(password);
        }
        return profile;
    }

    @Override
    public void addUser(Profile profile) {

        if (profile.getUser_password()!= null && !encryption.isEncrypted(profile.getUser_password())) {
         String password = encryption.encrypt(profile.getUser_password());
         profile.setUser_password(password);
        }
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
