
package com.realdolmen.hbo5.wasdapp.wasdappcore.service;


import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CurrentUser {
    
    UserWassdapp currentUser;

    public CurrentUser() {
    }

    public UserWassdapp getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserWassdapp currentUser) {
        this.currentUser = currentUser;
    }
    
    
    
    
    
}
