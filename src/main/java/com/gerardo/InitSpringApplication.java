package com.gerardo;

import com.gerardo.models.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class InitSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitSpringApplication.class, args);
    }

    @Value(value = "${spring.redis.host}")
    private String server;
    @Value(value = "${spring.redis.port}")
    private Integer port;
    @Value(value = "${spring.redis.password}")
    private String password;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        System.out.println("Server "+server);
        System.out.println("Port "+port);
        System.out.println("Password "+password);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(server, port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Student> redisTemplate() {
        RedisTemplate<String, Student> template = new RedisTemplate<String, Student>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

}