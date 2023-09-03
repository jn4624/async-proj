package com.app.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    /**
     * Bean을 주입 받아서 해당 메소드 호출시 비동기로 진행되는지 확인
     *
     * [asyncCall_1] :: http-nio-8080-exec-1
     * [sendMail] :: defaultTaskExecutor-1
     * [messagingTaskExecutor] :: messagingTaskExecutor-1
     *
     * 비동기 처리
     */
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    /**
     * 인스턴스 생성하여 해당 메소드 호출시 비동기로 진행되는지 확인
     *
     * [asyncCall_2] :: http-nio-8080-exec-2
     * [sendMail] :: http-nio-8080-exec-2
     * [messagingTaskExecutor] :: http-nio-8080-exec-2
     *
     * 동일 스레드가 처리한 것을 확인 - 비동기 미처리
     */
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    /**
     * 내부 메소드를 @Async 구현한 후 호출시 비동기로 진행되는지 확인
     *
     * [asyncCall_3] :: http-nio-8080-exec-3
     * [sendMail] :: http-nio-8080-exec-3
     *
     * 동일 스레드가 처리한 것을 확인 - 비동기 미처리
     */
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
