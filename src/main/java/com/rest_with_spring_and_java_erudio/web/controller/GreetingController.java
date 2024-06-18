package com.rest_with_spring_and_java_erudio.web.controller;

import com.rest_with_spring_and_java_erudio.domain.entity.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greetings")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "worls") String name) {
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }
}
