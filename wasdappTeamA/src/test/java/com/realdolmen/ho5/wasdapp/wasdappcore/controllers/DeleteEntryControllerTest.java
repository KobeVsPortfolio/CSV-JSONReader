
package com.realdolmen.ho5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.controllers.DeleteEntryController;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import java.util.ArrayList;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeleteEntryControllerTest {

    @Mock
    Model model;

    @Mock
    WasdappService wasdappService;

    @Mock
    CurrentUser currentUser;

    @InjectMocks
    DeleteEntryController deleteEntryController;

    @Test
    public void handleDeleteNoUser() {
        List<Long> list = new ArrayList<>();
        list.add(-1l);
        UserWassdapp user = null;
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verifyZeroInteractions(wasdappService);
    }

    @Test
    public void handleDeleteNoAdmin() {
        List<Long> list = new ArrayList<>();
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("user");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verifyZeroInteractions(wasdappService);
    }

    @Test
    public void handleDeleteItemsNoIds() {
        List<Long> list = new ArrayList<>();
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verifyZeroInteractions(wasdappService);
    }

    @Test
    public void handleDeleteItemsOneId() {
        List<Long> list = new ArrayList<>();
        list.add(22l);
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verify(wasdappService, times(1)).deleteById(22l);
    }

    @Test
    public void handleDeleteItemsMultipleIds() {
        List<Long> list = new ArrayList<>();
        list.add(22l);
        list.add(123l);
        list.add(-1l);
        UserWassdapp user = new UserWassdapp();
        user.setRole("admin");
        when(currentUser.getCurrentUser()).thenReturn(user);
        assertEquals("redirect:/wasdapp", deleteEntryController.handleDeleteItems(list, model));
        verify(wasdappService, times(1)).deleteById(22l);
        verify(wasdappService, times(1)).deleteById(123l);

    }

}
