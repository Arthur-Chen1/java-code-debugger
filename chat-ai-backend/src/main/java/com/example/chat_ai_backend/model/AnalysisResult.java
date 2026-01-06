package com.example.chat_ai_backend.model;

import java.util.List;

public class AnalysisResult {
    private List<CodeError> errors;
    private String aiExplanation;

    public AnalysisResult(List<CodeError> errors) {
        this.errors = errors;
    }

    public List<CodeError> getErrors() {
        return errors;
    }

    public void setErrors(List<CodeError> errors) {
        this.errors = errors;
    }
    public String getAiExplanation() {
        return aiExplanation;
    }

    public void setAiExplanation(String aiExplanation) {
        this.aiExplanation = aiExplanation;
    }
}