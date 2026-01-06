package com.example.chat_ai_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private final WebClient webClient;

    public OpenAiService() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        this.webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1/chat/completions")
            .defaultHeader("Authorization", "Bearer " + apiKey) 
            .build();
    }

    public String analyzeCodeWithAI(String code) {
        String prompt = "Analyze the following Java code for errors and suggest fixes:\n" + code;

        JsonNode response = webClient.post()
            .bodyValue(Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(Map.of("role", "user", "content", prompt))
            ))
            .retrieve()
            .bodyToMono(JsonNode.class)
            .block();

        return response.get("choices").get(0).get("message").get("content").asText();
    }
}