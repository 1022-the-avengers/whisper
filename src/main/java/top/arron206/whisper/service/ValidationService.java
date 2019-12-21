package top.arron206.whisper.service;

import top.arron206.whisper.entity.User;
import top.arron206.whisper.entity.Validation;
import top.arron206.whisper.vo.ValidationMessage;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ValidationService {
    boolean sendValidation(ValidationMessage validationMessage);

    void confirmValidation(ValidationMessage validationMessage);

    void readValidation();

    Map<String, List<ValidationMessage>> getValidations();

    int getUnprocessedValidationAmount();
}
