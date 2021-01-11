package com.study.juhee.stringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 전체 프로젝트의 메인 클래스 (항상 프로젝트의 최상단에 위치해야 함)
 * @SpringBootApplication을 선언함으로서 여기부터 설정을 읽고, 스프링부트, 빈 참조생성 설정 등 자동으로 됨.
 */

@EnableJpaAuditing  //JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 굳이 외장 WAS가 아닌 spring내부 WAS를 사용함(.jar파일로 실행 할 거임)
        SpringApplication.run(Application.class, args);
        // '내장 WAS사용을 권장' : 언제 어디서나 같은 환경에서 스프링부트를 배포할 수 있기 때문.
    }
}
