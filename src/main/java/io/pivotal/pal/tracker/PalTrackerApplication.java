package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication { 
    public static void main(String[] args){
        System.out.println(System.getProperty("java.version"));
        SpringApplication.run(PalTrackerApplication.class, args);
    }
    @Bean
    public TimeEntryRepository createTimeEntryRepository(){
        return new InMemoryTimeEntryRepository();
    }
}