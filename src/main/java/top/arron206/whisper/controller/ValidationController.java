package top.arron206.whisper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.arron206.whisper.entity.Validation;
import top.arron206.whisper.pojo.ValidationException;
import top.arron206.whisper.service.ValidationService;
import top.arron206.whisper.service.impl.ValidationServiceImpl;
import top.arron206.whisper.vo.GeneralResponse;
import top.arron206.whisper.vo.RelationshipMessage;
import top.arron206.whisper.vo.ValidationMessage;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/verification/user")
public class ValidationController {
    @Autowired
    ValidationService validationService;

    @RequestMapping(value = "/validation", method = RequestMethod.POST)
    public GeneralResponse saveValidation(@RequestBody ValidationMessage validationMessage) throws ValidationException {
        if (this.validationService.sendValidation(validationMessage))
            return new GeneralResponse("发送验证请求成功", null,
                    "",
                    "",
                    "",
                    "");
        else
            throw new ValidationException();
    }

    @RequestMapping(value = "/validation-confirm", method = RequestMethod.POST)
    public GeneralResponse confirmValidation(@RequestBody ValidationMessage validationMessage) {
        this.validationService.confirmValidation(validationMessage);
        return new GeneralResponse("确认验证请求成功", null,
                "",
                "",
                "",
                "");
    }

    @RequestMapping(value = "/validation", method = RequestMethod.GET)
    public GeneralResponse getValidations() {
        Map<String, List<ValidationMessage>> validations = this.validationService.getValidations();
        return new GeneralResponse("验证请求列表", validations,
                "",
                "",
                "",
                "");
    }
}