package top.arron206.whisper.pojo;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;


@Component
public class TokenRealm extends AuthorizingRealm {
    public TokenRealm(){
        this.setCredentialsMatcher(new TokenCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        String token = (String)authenticationToken.getCredentials();
        Integer userId = TokenManager.getId(token);
        if(userId==null)
            userId=-1;
        AuthenticationInfo a = new SimpleAuthenticationInfo(userId, token, getName());
        return new SimpleAuthenticationInfo(userId, token, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("user");
        return simpleAuthorizationInfo;
    }
}
