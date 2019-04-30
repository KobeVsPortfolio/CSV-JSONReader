package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteEntryController {

    @Autowired
    CurrentUser currentUser;

    @Autowired
    WasdappService wasdappService;

    @RequestMapping(value = "/deleteItems", method = RequestMethod.GET)
    public String handleDeleteItems(@RequestParam("id") List<Long> ids, Model model) {
        if (currentUser.getCurrentUser() != null) {
            if (currentUser.getCurrentUser().getRole().equals("admin")) {
                for (Long id : ids) {
                    if (id > 0) {
                        wasdappService.deleteById(id);
                    }
                }
            } else {
                return "redirect:/wasdapp";
            }
        } else {
            return "redirect:/wasdapp";
        }

        return "redirect:/wasdapp";
    }
}
