package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.SearchRequest;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WasdappEntryControllerTest {

    @Mock
    private WasdappService serviceMock;
    private WasdappEntryController wasdappEntryController;

    @Before
    public void setUp()  {
        wasdappEntryController = new WasdappEntryController(serviceMock);
    }

    @Test
    public void findByNameLike() {
        WasdappEntry entry1 = wasdappEntryFor("koffiemachien", "Koffiemachien met deca");
        WasdappEntry entry2 = wasdappEntryFor("koffiemachien", "Koffiemachien met goed gerief");


        when(serviceMock.findByNameContains("koffie")).thenReturn(Arrays.asList(entry1, entry2));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setName("koffie");

        List<WasdappEntryResponse> result = wasdappEntryController.searchByNameLike(searchRequest);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("koffiemachien");
        assertThat(result.get(0).getOmschrijving()).isEqualTo("Koffiemachien met deca");
        assertThat(result.get(1).getName()).isEqualTo("koffiemachien");
        assertThat(result.get(1).getOmschrijving()).isEqualTo("Koffiemachien met goed gerief");

    }

    private WasdappEntry wasdappEntryFor(String name, String description) {
        WasdappEntry wasdappEntry = new WasdappEntry();
        wasdappEntry.setId(1L);
        wasdappEntry.setName(name);
        wasdappEntry.setOmschrijving(description);
        wasdappEntry.setStraat("street");
        wasdappEntry.setNummer("number");
        wasdappEntry.setGemeente("city");
        wasdappEntry.setLat(2.02);
        wasdappEntry.setLon(3.03);
        return wasdappEntry;
    }
}