package com.example.spring_board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing // JPA Auditing 기능 활성화
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("saul"); // TODO : 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하기
    }

    // Article 의 생성자가 전부 saul 로 들어가게 된다.


}