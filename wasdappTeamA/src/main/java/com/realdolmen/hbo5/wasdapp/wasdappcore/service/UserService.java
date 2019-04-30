
package com.realdolmen.hbo5.wasdapp.wasdappcore.service;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserWassdapp save(UserWassdapp entry) {
        return userRepo.save(entry);
    }

    public List<UserWassdapp> findAll() {
        List<UserWassdapp> entries = userRepo.findAll();
        return entries;
    }

    public UserWassdapp getUser(String uname) {
        List<UserWassdapp> list = userRepo.findAll();
        for (UserWassdapp u : list) {
            if (u.getEmail().equals(uname)) {
                return u;
            }
        }
        return null;
    }

    public boolean validUser(String uname, String upwd) {
        boolean isValidUser = false;
        try {
            List<UserWassdapp> list = userRepo.findAll();
            for (UserWassdapp u : list) {
                if (u.getEmail().equals(uname)) {
                    if (u.getPassword().equals(upwd)) {
                        isValidUser = true;
                    }
                }
            }
        } catch (Exception e) {
            isValidUser = false;
        }
        return isValidUser;
    }

}
