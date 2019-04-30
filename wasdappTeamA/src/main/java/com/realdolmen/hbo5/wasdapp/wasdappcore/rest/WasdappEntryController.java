package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.SearchRequest;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController("/wasdappentry")
public class WasdappEntryController {

    private final WasdappService wasdappService;

    public WasdappEntryController(WasdappService wasdappService) {
        this.wasdappService = wasdappService;
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WasdappEntryResponse> searchByNameLike(@RequestBody SearchRequest searchRequest){
       List<WasdappEntry> results =  wasdappService.findByNameContains(searchRequest.getName());
        return results.stream()
                .map(WasdappEntryMapper::mapToDto)
                .collect(toList());
    }

}
