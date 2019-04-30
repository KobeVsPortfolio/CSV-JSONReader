
package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.CreateUserController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.UserService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CreateUserControllerTest {

    @Mock
    Model model;

    @Mock
    UserService userService;

    @Mock
    CurrentUser currentUser;

    @InjectMocks
    CreateUserController createUserController;

    @Test
    public void createUserSucces() {
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("createUser.xhtml", createUserController.createForm(model));
    }

    @Test
    public void createUserFailNoUser() {
        UserWassdapp user = null;
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", createUserController.createForm(model));
    }

    @Test
    public void createUserFailNoAdmin() {
        UserWassdapp user = new UserWassdapp();
        user.setRole("user");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", createUserController.createForm(model));
    }
    
    @Test
    public void entrySubmitSucces() {
        assertEquals("redirect:/createUser", createUserController.entrySubmit("test", "test@test.com", "test", "test", "user", model));      
    }
}
