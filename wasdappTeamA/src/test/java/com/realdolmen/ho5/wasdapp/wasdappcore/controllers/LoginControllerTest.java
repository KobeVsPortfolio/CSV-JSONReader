
package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.LoginController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.UserService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Mock
    Model model;

    @Mock
    UserService userService;

    @Mock
    CurrentUser currentUser;

    @InjectMocks
    LoginController loginController;

    @Test
    public void loginPageLoaded1() {
        assertEquals("login.xhtml", loginController.login(model));
    }

    @Test
    public void loginPageLoaded2() {
        assertEquals("login.xhtml", loginController.login2(model));
    }

    @Test
    public void userLoggedOut() {
        assertEquals("redirect:/login", loginController.logout(model));
        verify(currentUser, times(1)).setCurrentUser(null);
    }

    @Test
    public void LoggedInFailWrongPassAndUser() {
        String uname = "fail";
        String upwd = "fail";
        assertEquals("login.xhtml", loginController.validateUser(model, uname, upwd));
        verify(userService, times(1)).validUser(uname, upwd);
        verify(model, times(1)).addAttribute("test", "Wrong username and password combination, please try again.");
    }

    @Test
    public void LoggedInFailWrongPassRightUser() {
        String uname = "test@gmail.com";
        String upwd = "fail";
        assertEquals("login.xhtml", loginController.validateUser(model, uname, upwd));
        verify(userService, times(1)).validUser(uname, upwd);
        verify(model, times(1)).addAttribute("test", "Wrong username and password combination, please try again.");

    }

    @Test
    public void LoggedInFailRightPassWrongUser() {
        String uname = "fail";
        String upwd = "password";
        assertEquals("login.xhtml", loginController.validateUser(model, uname, upwd));
        verify(userService, times(1)).validUser(uname, upwd);
        verify(model, times(1)).addAttribute("test", "Wrong username and password combination, please try again.");
    }

    @Test
    public void LoginSucces() {
        UserWassdapp user = new UserWassdapp();
        String uname = "pass@gmail.com";
        String upwd = "password";
        user.setEmail(uname);
        user.setPassword(upwd);
        when(userService.validUser(uname, upwd)).thenReturn(true);
        when(userService.getUser(uname)).thenReturn(null);
        assertEquals("redirect:/wasdapp", loginController.validateUser(model, uname, upwd));
        verify(userService, times(1)).validUser(uname, upwd);
        verify(currentUser, times(1)).setCurrentUser(userService.getUser(uname));
    }
}
