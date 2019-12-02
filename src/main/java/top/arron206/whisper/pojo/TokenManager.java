package top.arron206.whisper.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class TokenManager {
    private static final long EXPIRE_TIME = 60 * 6000 * 24 * 7;
    private static final String TOKEN_SECRET = "t+kad8Xolx98KRNfI/v7OvWuN8/PfycWK+mDkOf4kLN0744WjI0Q28TscMAlfS8J";

    public static String sign(Integer userId){
        try{
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static Integer getId(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        }catch (Exception e){
            return null;
        }
    }
}
