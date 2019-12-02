package top.arron206.whisper.pojo;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import top.arron206.whisper.vo.AuthMessage;

public class HttpException extends AuthenticationException {
    private HttpStatus status;
    private AuthMessage authMessage;

    public HttpException(HttpStatus status, AuthMessage authMessage) {
        super();
        this.status = status;
        this.authMessage = authMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public AuthMessage getAuthMessage() {
        return authMessage;
    }

    public void setAuthMessage(AuthMessage authMessage) {
        this.authMessage = authMessage;
    }
}
