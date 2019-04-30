
package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonParserImplTest {
    
    @InjectMocks
    private JsonParserImpl jsonParser;
    
    @Test
    public void shouldReadJsonTest() throws FileNotFoundException, IOException{
        File file = new File("src/main/resources/import.json");
        InputStream stream = new FileInputStream(file);
        List<WasdappEntry> entries = new ArrayList<>();
        entries = jsonParser.readJson(stream);
        assertEquals("name", entries.get(0).getName());
    }
    
    @Test
     public void shouldNotReadJsonTest() throws FileNotFoundException, IOException{
        File file = new File("src/main/resources/wrong.json");
        InputStream stream = new FileInputStream(file);
        assertNull(jsonParser.readJson(stream));
    }
}
