
package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.UpdateEntryController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
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
public class UpdateEntryControllerTest {
    
    @Mock
    Model model;

    @Mock
    WasdappService wasdappService;

    @Mock
    CurrentUser currentUser;

    @InjectMocks
    UpdateEntryController updateEntryController;

    @Test
    public void updateFormSucces() {
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("edit.xhtml", updateEntryController.updateForm(1l, model));
    }

    @Test
    public void updateFormFailNoUser() {
        UserWassdapp user = null;
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/login", updateEntryController.updateForm(1l,model));
    }

    @Test
    public void updateFormFailNoAdmin() {
        UserWassdapp user = new UserWassdapp();
        user.setRole("user");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", updateEntryController.updateForm(1l,model));
    }
    
    @Test
    public void entrySubmitSucces() {
        assertEquals("redirect:/wasdapp", updateEntryController.entrySubmit(1l,null, null, "Gaston crommenlaan", "4", null, "Gent", null, "Kantoor gent in de refter", null, null, 51.037028, 3.735785, model));      
    }
}
