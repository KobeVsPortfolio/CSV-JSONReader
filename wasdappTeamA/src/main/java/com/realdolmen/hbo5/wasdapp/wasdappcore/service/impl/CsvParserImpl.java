package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.EmptyFileException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WasdappRuntimeException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WrongFileException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CsvParser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import com.realdolmen.hbo5.wasdapp.wasdappcore.util.Logger;
import java.io.BufferedReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Double.valueOf;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import org.apache.logging.log4j.LogManager;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class CsvParserImpl implements CsvParser {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    private WasdappService wasdappService;

    private BufferedReader bufferedReader;
    private List<String> stringList;

    @Autowired
    public CsvParserImpl(WasdappService wasdappService) {
        this.wasdappService = wasdappService;
    }

    @Override
    public void importCsv(InputStream is) throws IOException, WrongFileException {
        List<WasdappEntry> entries = new ArrayList<>();
        try {
            entries = readLines(is)
                    .stream()
                    .map(this::mapLineToEntry)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Faulty CSV file.");
            throw new WrongFileException();
        }
        if (!entries.contains(null)) {
            wasdappService.save(entries);
        } else {
            LOGGER.error("Faulty CSV file.");
            throw new WrongFileException();
        }
    }

    @Override
    public void importCsvFile(String fileName) {
        getLines(fileName)
                .stream()
                .map(this::mapLineToEntry)
                .forEach(entry -> wasdappService.save(entry));
    }

    protected List<String> readLines(InputStream is) throws UnsupportedEncodingException, IOException {
        try {
            stringList = new ArrayList<>();
            bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                stringList.add(line);
                line = bufferedReader.readLine();
            }
            if (!stringList.isEmpty()) {
                return stringList;
            } else {
                LOGGER.error("File is empty.");
                throw new EmptyFileException();
            }
        } catch (Exception e) {
            LOGGER.error("Can't read lines.");
            return null;
        }
    }

    protected List<String> getLines(String filename) {
        Path path;
        try {
            path = Paths.get(
                    getClass()
                            .getClassLoader()
                            .getResource(filename)
                            .toURI()
            );
        } catch (URISyntaxException e) {
            LOGGER.error("Error getting files.");
            throw new WasdappRuntimeException(e);
        }

        try {
            Stream<String> lines = Files.lines(path);
            List<String> stringList = lines.collect(toList());
            lines.close();
            return stringList;
        } catch (IOException e) {
            LOGGER.error("Error getting files.");
            throw new WasdappRuntimeException(e);
        }
    }

    protected WasdappEntry mapLineToEntry(String s) {

        try {
            WasdappEntry wasdappEntry = new WasdappEntry();
            String[] split = s.split(";", -1);
            if (split[0].length() < 8) {
                wasdappEntry.setId(getLongValue(split[0]));
            } else {
                LOGGER.error("ID has too many characters.");
                throw new WrongFileException();
            }
            if (!split[1].isEmpty() && split[1].length() < 64) {
                wasdappEntry.setName(getText(split[1]));
            } else {
                LOGGER.error("Empty title or title too long in csv-file.");
                throw new WrongFileException();
            }
            if (split[2].length() < 64) {
                wasdappEntry.setLocatie(getText(split[2]));
            } else {
                LOGGER.error("Location has too many characters.");
                throw new WrongFileException();
            }
            if (split[3].length() < 32) {
                wasdappEntry.setStraat(getText(split[3]));
            } else {
                LOGGER.error("Street has too many characters.");
                throw new WrongFileException();
            }
            if (split[4].length() < 8) {
                wasdappEntry.setNummer(getText(split[4]));
            } else {
                LOGGER.error("Number has too many characters.");
                throw new WrongFileException();
            }
            if (split[5].length() < 8) {
                wasdappEntry.setPostCode(getText(split[5]));
            } else {
                LOGGER.error("Postalcode has too many characters.");
                throw new WrongFileException();
            }
            if (split[6].length() < 32) {
                wasdappEntry.setGemeente(getText(split[6]));
            } else {
                LOGGER.error("City has too many characters.");
                throw new WrongFileException();
            }
            if (split[7].length() < 16) {
                wasdappEntry.setLand(getText(split[7]));
            } else {
                LOGGER.error("Country has too many characters.");
                throw new WrongFileException();
            }
            if (split[8].length() < 512) {
                wasdappEntry.setOmschrijving(getText(split[8]));
            } else {
                LOGGER.error("Description has too many characters.");
                throw new WrongFileException();
            }
            if (split[9].length() < 32) {
                wasdappEntry.setWikiLink(getText(split[9]));
            } else {
                LOGGER.error("Wikipedia link has too many characters.");
                throw new WrongFileException();
            }
            if (split[10].length() < 32) {
                wasdappEntry.setWebsite(getText(split[10]));
            } else {
                LOGGER.error("Website has too many characters.");
                throw new WrongFileException();
            }
            if (split[11].length() < 16) {
                wasdappEntry.setTelefoonNummer(getText(split[11]));
            } else {
                LOGGER.error("Telephone number has too many characters.");
                throw new WrongFileException();
            }
            if (split[12].length() < 64) {
                wasdappEntry.setEmail(getText(split[12]));
            } else {
                LOGGER.error("E-mail has too many characters.");
                throw new WrongFileException();
            }
            if (split[13].length() < 8) {
                wasdappEntry.setPrijs(getDoubleValue(split[13]));
            } else {
                LOGGER.error("Price has too many characters.");
                throw new WrongFileException();
            }
            if (split[14].length() < 32) {
                wasdappEntry.setPersoon(getText(split[14]));
            } else {
                LOGGER.error("Person has too many characters.");
                throw new WrongFileException();
            }
            if (split[15].length() < 16) {
                wasdappEntry.setLat(getDoubleValue(split[15]));
            } else {
                LOGGER.error("Latitude has too many characters.");
                throw new WrongFileException();
            }
            if (split[16].length() < 16) {
                wasdappEntry.setLon(getDoubleValue(split[16]));
            } else {
                LOGGER.error("Longitude has too many characters.");
                throw new WrongFileException();
            }
            wasdappEntry.setAanmaakDatum(Timestamp.valueOf(LocalDateTime.now()));
            wasdappEntry.setWijzigDatum(Timestamp.valueOf(LocalDateTime.now()));
            return wasdappEntry;
        } catch (WrongFileException e) {
            LOGGER.error("mapLineToEntry caught an error.");
            return null;
        }

    }

    private String getText(String s) {
        return isBlank(s) ? null : s;
    }

    private Double getDoubleValue(String s) {
        return isBlank(s) ? null : valueOf(s);
    }

    private Long getLongValue(String s) {
        return isBlank(s) ? null : Long.valueOf(s);
    }
}
