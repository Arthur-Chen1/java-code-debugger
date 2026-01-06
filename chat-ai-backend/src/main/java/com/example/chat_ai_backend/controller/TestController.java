package com.example.chat_ai_backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/ping")
    public String ping() {
        return "Backend is running!";
    }

    @PostMapping("/echo")
    public ResponseEntity<Map<String, String>> echo(@RequestBody Map<String, String> payload) {
        Map<String, String> response = new HashMap<>();
        response.put("received", payload.get("message"));
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}