package yingdg.exercise.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON Web Token
 */
public class HelloJWT {
    public static String salt = "MyJwt";

    public static String createToken() throws UnsupportedEncodingException {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        Date expire = calendar.getTime();

        Map<String, Object> head = new HashMap<>();
        head.put("alg", "HS256");
        head.put("typ", "JWT");

        String token = JWT.create().withHeader(head)
                .withClaim("a", "a").withClaim("b", "b")
                .withExpiresAt(expire).withIssuedAt(now)
                .sign(Algorithm.HMAC256(salt));

        return token;
    }

    public static Map<String, Claim> verify(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(salt)).build();

        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("token过期");
        }

        return decodedJWT.getClaims();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String token = createToken();
        System.out.println(token);

        Map<String, Claim> claimMap = verify(token);
        System.out.println(claimMap.get("a").asString());
        System.out.println(claimMap.get("b").asString());

        String oldToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhIjoiYSIsImIiOiJiIiwiZXhwIjoxNTIxNzk0OTM5LCJpYXQiOjE1MjE3OTQ4Nzl9.nkv0pfE5-cmy_vHb2v8AxBuB48pl6OyTYjJ7U5DsfZY";
        verify(oldToken);
    }
}
