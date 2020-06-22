package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PalTrackerApplication { 
    public static void main(String[] args){
        System.out.println(System.getProperty("java.version"));
        SpringApplication.run(PalTrackerApplication.class, args);
    }

}