package com.obviously20;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 生成与解析 测试类
 * 适配 jjwt 0.11.5 最新版本
 */
public class TestJWT {

    /**
     * 密钥：必须足够长（至少32位），保证安全
     * 注意：生成和解析必须使用同一个密钥
     */
    private static final String SECRET_KEY = "ilovejava123456789012345678901234";

    /**
     * 令牌过期时间：24小时（单位：毫秒）
     */
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;//h*m*s*ms

    /**
     * 【测试方法】生成 JWT 令牌
     */
    @Test
    public void testCreateJwt() {
        // 1. 自定义要存放的用户数据（负载 payload）
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 1001);
        claims.put("username", "zhangsan");
        claims.put("role", "admin");

        // 2. 生成 JWT 令牌
        String token = Jwts.builder()
                .setClaims(claims) // 设置自定义数据
                .setIssuedAt(new Date()) // 设置签发时间(当前时间)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间(当前时间 + 过期时间)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) // 设置签名密钥（Keys.hmacShaKeyFor(SECRET_KEY.getBytes())：将密钥转换为 HMAC-SHA256 签名密钥对象--即运用的签名算法为 HMAC-SHA256）
                .compact(); // 生成令牌

        // 3. 输出结果
        System.out.println("生成的 JWT 令牌：");
        System.out.println(token);
    }

    /**
     * 【测试方法】解析 JWT 令牌
     */
    @Test
    public void testParseJwt() {
        // 这里替换成上面生成的令牌
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJ1c2VySWQiOjEwMDEsInVzZXJuYW1lIjoiemhhbmdzYW4iLCJpYXQiOjE3Nzk3MTY0NjYsImV4cCI6MTc3OTgwMjg2Nn0.FXfgbm1Lh2pqN78ON1vSCBdbt1zcmuZLuKNZzSEKcCo";

        // 一步解析 JWT（适配最新版）
        Claims claims = Jwts.parserBuilder() // 创建解析器：造一个“解析器建造器”
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) // 设置签名密钥
                .build() // 构建解析器：建造完成，真正生成解析器
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取负载数据

        // 输出解析结果
        System.out.println("用户ID：" + claims.get("userId"));
        System.out.println("用户名：" + claims.get("username"));
        System.out.println("角色：" + claims.get("role"));
        System.out.println("令牌过期时间：" + claims.getExpiration());
    }
}
