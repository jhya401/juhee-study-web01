package com.study.juhee.stringboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @LoginUser라는 어노테이션을 만듦.
 */
@Target(ElementType.PARAMETER)      // 이 어노테이션이 생성될 수 있는 위치를 지정함 :: 파라미터로 지정했음.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {       // @interface :: 이 파일을 어노테이션 클래스로 지정함 /즉, @LoginUser 라는 어노테이션이 생성되었음.
}
