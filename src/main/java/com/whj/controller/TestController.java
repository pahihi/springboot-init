package com.whj.controller;

import com.whj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/test")
    public int test() throws ExecutionException, InterruptedException {
        testService.run();
        return 1;
    }

}
