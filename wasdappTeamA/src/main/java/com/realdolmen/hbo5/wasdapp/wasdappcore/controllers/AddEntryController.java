package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddEntryController {

    @Autowired
    CurrentUser currentUser;

    @Autowired
    WasdappService wasdappService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createForm(Model model) {
        if (currentUser.getCurrentUser() != null) {
            if (currentUser.getCurrentUser().getRole().equals("admin")) {
                model.addAttribute(currentUser);
                return "add.xhtml";
            } else {
                return "redirect:/wasdapp";
            }
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String entrySubmit(
            @RequestParam String name, @RequestParam String locatie,
            @RequestParam String straat, @RequestParam String nummer,
            @RequestParam String postCode, @RequestParam String gemeente,
            @RequestParam String land, @RequestParam String omschrijving,
            @RequestParam String telefoon, @RequestParam String email,
            @RequestParam Double latitude, @RequestParam Double longitude,
            Model model) {
        WasdappEntryResponse entry = new WasdappEntryResponse();
        Long id = 0L;
        entry.setId(id);
        entry.setName(name);
        entry.setLocatie(locatie);
        entry.setStraat(straat);
        entry.setNummer(nummer);
        entry.setPostCode(postCode);
        entry.setGemeente(gemeente);
        entry.setLand(land);
        entry.setOmschrijving(omschrijving);
        entry.setTelefoonNummer(telefoon);
        entry.setEmail(email);
        entry.setLat(latitude);
        entry.setLon(longitude);
        wasdappService.update(entry);
        model.addAttribute("entry", entry);
        return "redirect:/wasdapp";
    }
}
