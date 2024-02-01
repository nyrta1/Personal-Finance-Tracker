package com.nurik.userservice.security.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public static StringRedisTemplate stringRedisTemplateStatic;

    @PostConstruct
    public void initStringRedisTemplate() {
        stringRedisTemplateStatic = this.stringRedisTemplate;
    }

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void redisSaveTokenInfo(String token, String username) {
        LocalDateTime localDateTime = LocalDateTime.now();
        stringRedisTemplateStatic.opsForHash().put(username, "token", token);
        stringRedisTemplateStatic.opsForHash().put(username, "refleshtime",
                df.format(localDateTime.plus(7*24*60*60*1000, ChronoUnit.MILLIS)));
        stringRedisTemplateStatic.opsForHash().put(username, "expiration",
                df.format(localDateTime.plus(600*1000, ChronoUnit.MILLIS)));
        stringRedisTemplateStatic.expire(username, 7*24*60*60*1000, TimeUnit.SECONDS);
    }

    public static boolean hasToken(String username) {
        return stringRedisTemplateStatic
                .opsForHash()
                .getOperations()
                .hasKey(username);
    }
}