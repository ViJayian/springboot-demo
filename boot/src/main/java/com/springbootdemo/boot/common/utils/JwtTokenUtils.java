package com.springbootdemo.boot.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.springbootdemo.boot.pojo.UserInfo;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: jwttoken
 * jwt构成： header头部分，payload有效载荷，signature签名
 * header部分 {"alg": "HS256","typ": "JWT"} alg表示签名算法， typ表示令牌类型,最后将json对象使用base64url转成字符串
 * payload部分    iss (issuer)：签发人
 * exp (expiration time)：过期时间
 * sub (subject)：主题
 * aud (audience)：受众
 * nbf (Not Before)：生效时间
 * iat (Issued At)：签发时间
 * jti (JWT ID)：编号
 * 私有字段定义：
 * {
 * "sub": "1234567890",
 * "name": "John Doe",
 * "admin": true
 * }
 * signature部分是对前两部分的签名，secret是存于服务器的秘钥
 * HMACSHA256(
 * base64UrlEncode(header) + "." +
 * base64UrlEncode(payload),
 * secret)
 * <p>
 * base64url算法：Base64 有三个字符+、/和=，在 URL 里面有特殊含义，
 * 所以要被替换掉：=被省略、+替换成-，/替换成_ 。这就是 Base64URL 算法。
 * @Author: wangwenjie
 * @CreateTime: 2019-12-05 10:47
 */
@Component
public class JwtTokenUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtils.class);

    //$获取配置文件中值 #获取配置文件中的值
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationTime}")
    private long expirationTime;

    /**
     * 生成jwttoken
     */
    private String generateToken(String subject) {
        return Jwts.builder().
                setSubject(subject)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public UserInfo parseToken(String token) {
        UserInfo userInfo = null;
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            String subject = claimsJws.getBody().getSubject();
            System.out.println(subject);
            userInfo = JSONObject.parseObject(subject, UserInfo.class);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            LOGGER.info("令牌过期{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("解析token出错{}", token);
            throw new SignatureException("解析token错误");
        }

        return userInfo;
    }

    public String generateToken(UserInfo userInfo) {
        String json = JSONObject.toJSONString(userInfo);
        return generateToken(json);
    }

    private Date generateExpirationDate() {
        return new Date(expirationTime + System.currentTimeMillis());
    }

}
