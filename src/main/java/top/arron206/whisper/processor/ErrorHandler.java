package top.arron206.whisper.processor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.arron206.whisper.pojo.HttpException;
import top.arron206.whisper.vo.AuthMessage;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<AuthMessage> defaultErrorHandler(HttpException e) {
        AuthMessage authMessage = e.getAuthMessage();
        return new ResponseEntity<>(authMessage, e.getStatus());
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<AuthMessage> authenticationExceptionHandler(AuthenticationException e) {
//
//        return new ResponseEntity<>(AuthMessage.getAuthMessage(
//                "XXXX", "", "login /auth/login",
//                "/auth/login", "login", "application/json"), HttpStatus.UNAUTHORIZED);
//    }
}
