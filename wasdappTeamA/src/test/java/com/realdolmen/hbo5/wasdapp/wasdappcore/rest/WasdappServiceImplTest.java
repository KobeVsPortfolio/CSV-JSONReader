package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import java.util.List;
import static com.google.common.truth.Truth.assertThat;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(WasdappEntryResponseMapper.class)
public class WasdappServiceImplTest {


    @Mock
    private WasdappEntryRepository repositoryMock;
    
    private WasdappServiceImpl service;

    @Before
    public void setUp()  {
        service = new WasdappServiceImpl(repositoryMock);
        PowerMockito.mockStatic(WasdappEntryResponseMapper.class);
    }

    @Test
    public void findByNameContains() {
        List repoResponse = mock(List.class);
        when(service.findByNameContains("koffie")).thenReturn(repoResponse);
        List<WasdappEntry> serviceResponse = service.findByNameContains("koffie");

        assertThat(serviceResponse).isEqualTo(repoResponse);

    }
    
    @Test
    public void updateEntryTest(){
        WasdappEntryResponse resp = new WasdappEntryResponse();
        WasdappEntry entry = new WasdappEntry();
        when(WasdappEntryResponseMapper.mapToEntry(resp)).thenReturn(entry);
        service.update(resp);
        verify(repositoryMock, times(1)).save(entry);
    }
    
    @Test
    public void findAllTest(){
        service.findAll();
        verify(repositoryMock, times(1)).findAll();  
    }
}