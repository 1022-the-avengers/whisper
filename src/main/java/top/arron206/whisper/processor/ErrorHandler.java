package top.arron206.whisper.processor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.arron206.whisper.pojo.HttpException;
import top.arron206.whisper.pojo.ValidationException;
import top.arron206.whisper.vo.AuthMessage;
import top.arron206.whisper.vo.GeneralResponse;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<AuthMessage> defaultErrorHandler(HttpException e) {
        AuthMessage authMessage = e.getAuthMessage();
        return new ResponseEntity<>(authMessage, e.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GeneralResponse> validationException(ValidationException e) {
        GeneralResponse generalResponse = new GeneralResponse("不可重复添加/请求", null, "", "", "", "");
        return new ResponseEntity<>(generalResponse, HttpStatus.FORBIDDEN);
    }
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<AuthMessage> authenticationExceptionHandler(AuthenticationException e) {
//
//        return new ResponseEntity<>(AuthMessage.getAuthMessage(
//                "XXXX", "", "login /auth/login",
//                "/auth/login", "login", "application/json"), HttpStatus.UNAUTHORIZED);
//    }
}
