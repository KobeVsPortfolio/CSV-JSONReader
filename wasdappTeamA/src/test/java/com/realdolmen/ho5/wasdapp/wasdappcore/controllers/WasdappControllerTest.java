
package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.WasdappController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
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
public class WasdappControllerTest {
    
    @Mock
    Model model;
    
    @Mock
    WasdappService wasdappService;
    
    @Mock
    CurrentUser currentUser;
    
    @InjectMocks
    WasdappController wasdappController;
    
    @Test
    public void loggedInSucces(){
        UserWassdapp user = new UserWassdapp();
        user.setEmail("test@test.com");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("wasdapp.xhtml", wasdappController.showList(model));
        verify(wasdappService, times(1)).findAll();
        verify(model, times(1)).addAttribute("entries", wasdappService.findAll());

    }

    @Test
    public void notLoggedInFail(){
        when(currentUser.getCurrentUser()).thenReturn(null);
        assertEquals("redirect:/login", wasdappController.showList(model));
        verifyZeroInteractions(wasdappService);      
    }   
}
