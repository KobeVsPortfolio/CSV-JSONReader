package com.realdolmen.hbo5.wasdapp.wasdappcore.service;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;

import java.util.List;

public interface WasdappService {

    List<WasdappEntry> findByNameContains(String nameShouldStartWith);

    WasdappEntry save(WasdappEntry entry);

    List<WasdappEntry> save(List<WasdappEntry> entry);

    List<WasdappEntryResponse> findAll();

    WasdappEntryResponse findById(Long id);

    WasdappEntry update(WasdappEntryResponse entryResponse);

    void deleteById(Long id);
}
