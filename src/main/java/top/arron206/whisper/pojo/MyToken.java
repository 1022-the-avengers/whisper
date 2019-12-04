package top.arron206.whisper.pojo;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.AuthenticationToken;

public class MyToken implements AuthenticationToken {
    private String token;

    public MyToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        Integer userId = -1;
        if(TokenManager.verify(this.token)){
            userId = TokenManager.getId(token);
        }
        return userId;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
