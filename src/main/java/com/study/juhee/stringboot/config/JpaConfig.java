package com.study.juhee.stringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // 여기서 JPA Auditing 활성화
public class JpaConfig {
}
