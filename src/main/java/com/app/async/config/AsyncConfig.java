package com.app.async.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @EnableAsync - Async로 동작할 수 있도록 하는 설정
 * 위 어노테이션을 선언해줌으로써
 * Springframework에서는 async하게 동작할 수 있는
 * 어노테이션을 사용할 수 있구나를 알게 됨
 */
@Configuration
@EnableAsync
public class AsyncConfig {
}
