package com.project.imdang.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/exchanges")
@RequiredArgsConstructor
@RestController
public class ExchangeController {

//    private final ExchangeCommandApplicationService exchangeCommandApplicationService;

    @PostMapping
    public ResponseEntity<?> request() {
        return new ResponseEntity<>(null);
    }

    @PostMapping
    public ResponseEntity<?> accept() {
        return new ResponseEntity<>(null);
    }

    @PostMapping
    public ResponseEntity<?> reject() {
        return new ResponseEntity<>(null);
    }
}
