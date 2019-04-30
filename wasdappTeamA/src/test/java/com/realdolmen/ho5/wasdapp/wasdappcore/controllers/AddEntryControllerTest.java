
package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.AddEntryController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import static org.junit.Assert.*;
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
public class AddEntryControllerTest {

    @Mock
    Model model;

    @Mock
    WasdappService wasdappService;

    @Mock
    CurrentUser currentUser;

    @InjectMocks
    AddEntryController addEntryController;

    @Test
    public void createFormSucces() {
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("add.xhtml", addEntryController.createForm(model));
    }

    @Test
    public void createFormFailNoUser() {
        UserWassdapp user = null;
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/login", addEntryController.createForm(model));
    }

    @Test
    public void createFormFailNoAdmin() {
        UserWassdapp user = new UserWassdapp();
        user.setRole("user");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", addEntryController.createForm(model));
    }

    @Test
    public void entrySubmitSucces() {
        WasdappEntryResponse entry = new WasdappEntryResponse();
        entry.setStraat("Gaston crommenlaan");
        entry.setGemeente("Gent");
        entry.setNummer("4");
        entry.setName("koffiemachien");
        entry.setLat(51.037028);
        entry.setLon(3.735785);
        entry.setOmschrijving("Kantoor gent in de refter");
        
        WasdappEntry entry2 = new WasdappEntry();
        entry2.setStraat("Gaston crommenlaan");
        entry2.setGemeente("Gent");
        entry2.setNummer("4");
        entry2.setName("koffiemachien");
        entry2.setLat(51.037028);
        entry2.setLon(3.735785);
        entry2.setOmschrijving("Kantoor gent in de refter");
 
        assertEquals("redirect:/wasdapp", addEntryController.entrySubmit(null, null, "Gaston crommenlaan", "4", null, "Gent", null, "Kantoor gent in de refter", null, null, 51.037028, 3.735785, model));
//        when(wasdappService.update(entry)).thenReturn(entry2);
//        verify(wasdappService, times(1)).update(entry); 
//        verify(model, times(1)).addAttribute("entry", entry);
    }
}
