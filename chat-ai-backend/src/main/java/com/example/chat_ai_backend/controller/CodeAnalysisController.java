package com.example.chat_ai_backend.controller;

import com.example.chat_ai_backend.service.CodeAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.chat_ai_backend.model.AnalysisResult;

@RestController
@RequestMapping("/api")
public class CodeAnalysisController {

    @Autowired
    private CodeAnalyzerService analyzerService;

    @PostMapping("/analyze")
    @CrossOrigin(origins = "http://localhost:3000")
    public AnalysisResult analyze(@RequestBody String code) {
        return analyzerService.analyzeCode(code);
    }
}