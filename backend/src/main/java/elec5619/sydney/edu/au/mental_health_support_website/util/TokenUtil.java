package elec5619.sydney.edu.au.mental_health_support_website.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class TokenUtil {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long LIVE_DURATION_MILLIS = 3600000 * 2;

    public static String generateToken(String userName) {
        Date expireTime = new Date(System.currentTimeMillis() + LIVE_DURATION_MILLIS);
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(expireTime)
                .signWith(KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return username;
        }
        catch (Exception e) {
            return "";
        }
    }
}
