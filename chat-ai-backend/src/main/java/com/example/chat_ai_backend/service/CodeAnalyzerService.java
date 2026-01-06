package com.example.chat_ai_backend.service;

import org.springframework.stereotype.Service;
import javax.tools.*;
import java.io.StringWriter;
import java.util.*;
import com.example.chat_ai_backend.model.AnalysisResult;
import com.example.chat_ai_backend.model.CodeError;
import com.example.chat_ai_backend.util.JavaSourceFromString;

@Service
public class CodeAnalyzerService {

    private final OpenAiService openAiService;
    public CodeAnalyzerService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }
    public AnalysisResult analyzeCode(String code) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        
        JavaFileObject file = new JavaSourceFromString("Main", code);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);

        JavaCompiler.CompilationTask task = compiler.getTask(
                null, null, diagnostics, null, null, compilationUnits);

        boolean success = task.call();  

        List<CodeError> errors = new ArrayList<>();
        for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                errors.add(new CodeError(
                        (int) diagnostic.getLineNumber(),
                        diagnostic.getMessage(null)
                ));
            }
        }

        String aiExplanation = openAiService.analyzeCodeWithAI(code);

        AnalysisResult result = new AnalysisResult(errors);
        result.setAiExplanation(aiExplanation);  

        return result;
    }
}