package com.realdolmen.hbo5.wasdapp.wasdappcore.service;

import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WrongFileException;
import java.io.IOException;
import java.io.InputStream;

public interface CsvParser {
    void importCsv(InputStream is) throws IOException, WrongFileException;
    void importCsvFile(String fileName);
}
