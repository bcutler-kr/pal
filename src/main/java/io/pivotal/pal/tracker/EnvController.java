package io.pivotal.pal.tracker;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
    private HashMap<String, String> myEnv; 

    public EnvController(@Value("${PORT:NOT SET}") String port,
                        @Value("${MEMORY.LIMIT:NOT SET}") String memoryLimit,
                        @Value("${CF.INSTANCE.INDEX:NOT SET}") String cfInstanceIndex,
                        @Value("${CF.INSTANCE.ADDR:NOT SET}") String cfInstanceAddr){
        // 
        this.myEnv = new HashMap<>();
        this.myEnv.put("PORT", port);
        this.myEnv.put("MEMORY_LIMIT", memoryLimit);
        this.myEnv.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        this.myEnv.put("CF_INSTANCE_ADDR", cfInstanceAddr);
        
    }
    @GetMapping("/env")
    public HashMap<String,String> getEnv(){
        return this.myEnv;
    }

}