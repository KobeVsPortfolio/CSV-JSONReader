package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WrongFileException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import com.realdolmen.hbo5.wasdapp.wasdappcore.util.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonParserImpl {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    private WasdappService wasdappService;
    private ObjectMapper mapper;
    List<WasdappEntry> entries;

    @Autowired
    public JsonParserImpl(WasdappService wasdappService) {
        this.wasdappService = wasdappService;
    }
    
    public void importJson(InputStream is) throws IOException, WrongFileException{
        entries = readJson(is);
        if(entries != null){
            for(WasdappEntry w : entries){
                if(!w.getName().isEmpty()){
                    wasdappService.save(w);
                }else{
                    LOGGER.error("Empty name found in file, this entry has not been added.");
                }
            }
        }else{
            LOGGER.error("The list of entries to save is empty.");
            throw new WrongFileException();
        } 
    }
    
    protected List<WasdappEntry> readJson(InputStream is) throws IOException{
        try{
        mapper = new ObjectMapper();
        entries = mapper.readValue(is, new TypeReference<List<WasdappEntry>>(){});
        return entries;
        }catch(Exception e){
            LOGGER.error("Something went wrong when reading the JSON file.");
            return null;
        }
    } 
}
