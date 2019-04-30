package com.realdolmen.hbo5.wasdapp.wasdappcore.controllers;

import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CurrentUser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.GeneratePdfReport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class PdfController {

    private GeneratePdfReport generatePdf = new GeneratePdfReport();

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private WasdappService wasdappService;

    @RequestMapping(value = "/downloadAll", method = RequestMethod.GET)
    public void printAll(HttpServletResponse response) throws IOException {
        List<WasdappEntryResponse> listtoprint = wasdappService.findAll();
        ByteArrayOutputStream bis = generatePdf.generatePdf(listtoprint);
        response.addHeader("Content-Type", "application/force-download");
        response.addHeader("Content-Disposition", "attachment; filename=\"yourFile.pdf\"");
        response.getOutputStream().write(bis.toByteArray());
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String entriesReport(HttpServletResponse response, Model model, @RequestParam("id") List<Long> ids) throws IOException {
        if (currentUser.getCurrentUser() != null) {
                List<WasdappEntryResponse> listtoprint = new ArrayList<>();
                for (Long id : ids) {
                        if (id > 0) {
                        listtoprint.add(wasdappService.findById(id));
                    }
                }
                ByteArrayOutputStream bis = generatePdf.generatePdf(listtoprint);
                response.addHeader("Content-Type", "application/force-download");
                response.addHeader("Content-Disposition", "attachment; filename=\"wasdapp.pdf\"");
                response.getOutputStream().write(bis.toByteArray());
                return null;
        } else {
            return "redirect:/wasdapp";
        }

    }

}
