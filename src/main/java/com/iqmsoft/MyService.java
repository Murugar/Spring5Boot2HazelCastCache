package com.iqmsoft;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.cache.annotation.CacheResult;


@RestController
public class MyService {

    @CacheResult(cacheName="testCache")
    public String getData(@RequestParam("param") String param) {
        System.out.println("@@@@> Cache miss! Getting new value");

        return param + "_" + System.currentTimeMillis();
    }

}
