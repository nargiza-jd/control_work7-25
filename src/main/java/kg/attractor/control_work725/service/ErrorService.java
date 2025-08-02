package kg.attractor.control_work725.service;

import kg.attractor.control_work725.exceptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(BindingResult bindingResult);
    ErrorResponseBody makeResponse(Exception e);
}
