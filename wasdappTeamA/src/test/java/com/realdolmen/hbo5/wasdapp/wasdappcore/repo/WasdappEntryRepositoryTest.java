package com.realdolmen.hbo5.wasdapp.wasdappcore.repo;

import com.google.common.truth.Truth;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WasdappEntryRepositoryTest {

    @Autowired
    private WasdappEntryRepository wasdappEntryRepository;

    @Test
    public void getNameFindByIdTest() {
        System.out.println(wasdappEntryRepository.findById(1L).get().getName());
        List<WasdappEntry> test = new ArrayList<>();
        test = wasdappEntryRepository.findAll();

        for (WasdappEntry e : test) {
            System.out.println(e.getName());
        }
        Truth.assertThat(wasdappEntryRepository.findById(1L).isPresent());
    }

    @Test
    public void updateEntryTest() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        WasdappEntry entry = new WasdappEntry();
        entry.setId(1L);
        entry.setName("Theepot");
        entry.setWijzigDatum(ts);
        wasdappEntryRepository.save(entry);
        assertEquals(wasdappEntryRepository.findById(1L).get().getName(),"Theepot");
    }
    
    @Test
    public void deleteEntryTest(){
        WasdappEntry entryToDelete = wasdappEntryRepository.findById(4L).get();
        wasdappEntryRepository.delete(entryToDelete);
        assertFalse(wasdappEntryRepository.findById(4L).isPresent());
    }
}
