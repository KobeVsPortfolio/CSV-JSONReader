package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WrongFileException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CsvParserImplTest {

    @InjectMocks
    private CsvParserImpl csvParser;

    @Mock
    private WasdappService wasdappService;

    @Captor
    private ArgumentCaptor<WasdappEntry> captor;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() {
        verifyNoMoreInteractions(wasdappService);
    }

    @Test
    public void shouldImportACsv() {
        WasdappEntry wasdappEntry0 = new WasdappEntry();
        wasdappEntry0.setStraat("Gaston crommenlaan");
        wasdappEntry0.setGemeente("Gent");
        wasdappEntry0.setNummer("4");
        wasdappEntry0.setName("koffiemachien");
        wasdappEntry0.setLat(51.037028);
        wasdappEntry0.setLon(3.735785);
        wasdappEntry0.setOmschrijving("Kantoor gent in de refter");

        WasdappEntry wasdappEntry1 = new WasdappEntry();
        wasdappEntry1.setStraat("Gaston crommenlaan");
        wasdappEntry1.setGemeente("Gent");
        wasdappEntry1.setNummer("4");
        wasdappEntry1.setName("koffiemachien");
        wasdappEntry1.setLat(51.037028);
        wasdappEntry1.setLon(3.735785);
        wasdappEntry1.setOmschrijving("Kantoor gent in de keuken");

        WasdappEntry wasdappEntry2 = new WasdappEntry();
        wasdappEntry2.setStraat("Prins Boudewijnlaan");
        wasdappEntry2.setGemeente("Kontich");
        wasdappEntry2.setNummer("26");
        wasdappEntry2.setName("theemachien");
        wasdappEntry2.setLat(51.037028);
        wasdappEntry2.setLon(3.735785);
        wasdappEntry2.setOmschrijving("Kantoor gent in de keuken");

        WasdappEntry wasdappEntry3 = new WasdappEntry();
        wasdappEntry3.setName("a");

        csvParser.importCsvFile("import.csv");

        verify(wasdappService, times(4)).save(captor.capture());
        wasdappEntry0.setAanmaakDatum(captor.getAllValues().get(0).getAanmaakDatum());
        wasdappEntry1.setAanmaakDatum(captor.getAllValues().get(1).getAanmaakDatum());
        wasdappEntry2.setAanmaakDatum(captor.getAllValues().get(2).getAanmaakDatum());
        wasdappEntry1.setWijzigDatum(captor.getAllValues().get(1).getWijzigDatum());
        wasdappEntry0.setWijzigDatum(captor.getAllValues().get(0).getWijzigDatum());
        wasdappEntry2.setWijzigDatum(captor.getAllValues().get(2).getWijzigDatum());
        assertThat(captor.getAllValues().size(), is(4));
        assertThat(captor.getAllValues().get(0), samePropertyValuesAs(wasdappEntry0));
        assertThat(captor.getAllValues().get(1), samePropertyValuesAs(wasdappEntry1));
        assertThat(captor.getAllValues().get(2), samePropertyValuesAs(wasdappEntry2));
    }

    @Test
    public void shouldThrowANullpointerExceptionIfTheFileDoesNotExist() {

        thrown.expect(NullPointerException.class);
        csvParser.importCsvFile("szq.csv");
    }

    @Test
    public void shouldThrowExceptionLangeGemeente() {
        String gemeente = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;GentfGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqqsdfdfqsfdq;;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(gemeente));
    }

    @Test
    public void shouldThrowExceptionLangeEmail() {
        String a = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;Gentfqssdfqsfdq;;Kantoor gent in de refter;;;;GentfqssdfqsfdqGGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqentfqssdfqsfdqGentfqssdfqsfdq;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(a));
    }

    @Test
    public void shouldThrowExceptionLangeID() {
        String s = "9999999999999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;Gentfqsddqsfqsdfqsfdq;;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeLand() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;Gentfqsdqsdfqsfdq;GentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdq;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeLat() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;Gentfqsddqsfqsdfqsfdq;;Kantoor gent in de refter;;;;;;;51.0375564465456456465465465456456456465465645645028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeLocatie() {
        String s = "999999;koffiemachien;hqfklhGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqsdhslkfklh;Gaston crommenlaan;4;;Gentfqsfdqsfqsdfqsfdq;;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeLon() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;Gentfqsdfdqsfdqsfqsdfqsfdq;;Kantoor gent in de refter;;;;;;;51.037028;3.76544564564564564564656454654656456456456453";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangePersoon() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;Gentfqsdfqsfsfdqsfdqsfqsdfqsfdq;;Kantoor gent in de refter;;;;;;GentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdq;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeNummer() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;446545456456456456654564564;;;;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeOmschrijving() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;;;Kantoor genGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqt in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangePostcode() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;645465456456456465465465456456465456645645465456645645;;;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangePrijs() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;;;Kantoor gent in de refter;;;;;45645645645645645645664545645645645645656445;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeStraat() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqan;4;;;;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeTelefoon() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;;;Kantoor gent in de refter;;;GentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdq;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeTitel() {
        String s = "999999;koffGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;;;Kantoor gent in de refter;;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeWebsite() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;;;Kantoor gent in de refter;GentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdq;;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldThrowExceptionLangeWikiLink() {
        String s = "999999;koffiemachien;hqfklhsdhslkfklh;Gaston crommenlaan;4;;;;Kantoor gent in de refter;;GentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdqGentfqssdfqsfdq;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldNotWorkWithoutTitleTest() {
        String s = "999999;;hqfklhsdhslkfklh;Gaston crommenlaan;4;;;;Kantoor gent in de refter;;Gentfqsfqsfdq;;;;;51.037028;3.73";
        assertNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void shouldWorkTest() {
        String s = "1;koffiemachien;refter;Gaston crommenlaan;4;1777;Gent;Nederland;Kantoor gent in de refter;wlink.wlink;link.link;0477;hup@mail.Com;20;Jan;51.037028;3.735785";
        assertNotNull(csvParser.mapLineToEntry(s));
    }

    @Test
    public void readLinesWorksTest() throws FileNotFoundException, IOException, WrongFileException {
        File file = new File("src/main/resources/import.csv");
        InputStream stream = new FileInputStream(file);
        List<String> strings = new ArrayList<>();
        String lineOne = ";koffiemachien;;Gaston crommenlaan;4;;Gent;;Kantoor gent in de refter;;;;;;;51.037028;3.735785";
        strings = csvParser.readLines(stream);
        assertEquals(lineOne, strings.get(0));
    }

    @Test
    public void readLinesDoesntWorkTest() throws FileNotFoundException, IOException {
        File file = new File("src/main/resources/testFiles/Leeg.csv");
        InputStream stream = new FileInputStream(file);
        assertNull(csvParser.readLines(stream));
    }
}
