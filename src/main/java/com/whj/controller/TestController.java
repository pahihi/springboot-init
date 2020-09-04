package com.whj.controller;

import com.whj.interceptor.LogAnnotation;
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
    @LogAnnotation("test被调用")
    public String test() throws ExecutionException, InterruptedException {
        // testService.run();
        return "success";
    }

}
