package com.realdolmen.hbo5.wasdapp.wasdappcore.repo;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WasdappEntryRepositoryValidatorTest {

    private Validator validator;
            
    @Before
    public void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void saveEntryValidatorTest() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        WasdappEntry entry = new WasdappEntry();
        entry.setId(8L);
        entry.setName("Zetel");
        entry.setAanmaakDatum(ts);
        entry.setWijzigDatum(ts);
        Set<ConstraintViolation<WasdappEntry>> validatorResponse = validator.validate(entry);
        assertEquals(validatorResponse.size(), 0);
    }

    @Test
    public void saveWrongEntryValidatorTest() {
        WasdappEntry entry = new WasdappEntry();
        Set<ConstraintViolation<WasdappEntry>> validatorResponse = validator.validate(entry);
        assertEquals(validatorResponse.size(), 4);
    }
}
