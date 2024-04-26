package com.google.workstreams.bqendpoint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @PostMapping("/storage")
    public String getStorageDescription(@RequestBody Request request) {

        System.out.println("test");
        return "done with storage api";
    }

    @PostMapping("/looker")
    public String getLookerDescription(@RequestBody Request request) {

        

        System.out.println("test");
        return "done with looker api";
    }

}
