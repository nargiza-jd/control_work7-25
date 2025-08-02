package kg.attractor.control_work725.service.impl;

import kg.attractor.control_work725.exceptions.ErrorResponseBody;
import kg.attractor.control_work725.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ErrorResponseBody makeResponse(BindingResult bindingResult) {
        Map<String, List<String>> reasons = new HashMap<>();

        bindingResult.getFieldErrors().stream()
                .filter(e -> e.getDefaultMessage() != null)
                .forEach(e -> {
                    reasons.computeIfAbsent(e.getField(), key -> new ArrayList<>())
                            .add(e.getDefaultMessage());
                });

        return ErrorResponseBody.builder()
                .title("Validation errors")
                .response(reasons)
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(Exception e) {
        return ErrorResponseBody.builder()
                .title(e.getMessage())
                .response(Map.of("errors", List.of(e.getMessage())))
                .build();
    }
}