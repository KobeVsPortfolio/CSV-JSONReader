package com.realdolmen.hbo5.wasdapp.wasdappcore;

import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WasdappCoreApplication {
    
        @Autowired
        WasdappEntryRepository wasdappEntryRepository;
            
	public static void main(String[] args) {
		SpringApplication.run(WasdappCoreApplication.class, args);
	}

}

