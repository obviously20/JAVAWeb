package com.obviously20.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 * 生成、解析方法共用【外部调用时传入的密钥】
 */
@Component
public class JwtUtil {

    /**
     * 令牌默认过期时间：24小时（毫秒）
     */
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;

    // todo 密钥的长度必须至少为32字节，否则会报错

    // ====================== 生成 JWT（密钥外部传入） ======================
    public static String generateToken(String secretKey, Map<String, Object> claims) {
        return Jwts.builder()// 构建 JWT 令牌
                .setClaims(claims)//设置立牌的载荷
                .setIssuedAt(new Date())//设置立牌的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//设置立牌的过期时间
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))//设置立牌的签名（HMAC-SHA256 算法）
                .compact();//压缩立牌为字符串
    }

    // ====================== 解析 JWT（密钥外部传入） ======================
    public static Claims parseToken(String secretKey, String token) {
        return Jwts.parserBuilder()// 构建 JWT 解析器构建器
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))//设置解析器的签名密钥（HMAC-SHA256 算法）
                .build()//构建 JWT 解析器
                .parseClaimsJws(token)//解析 JWT 令牌
                .getBody();//获取解析后的立牌（包含载荷）
    }
}