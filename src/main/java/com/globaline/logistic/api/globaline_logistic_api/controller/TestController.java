package com.globaline.logistic.api.globaline_logistic_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/api/test-cors")
    public Map<String, String> testCors() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "CORS está configurado correctamente");
        response.put("status", "success");
        return response;
    }
}