package com.study.juhee.stringboot.config.auth;

import com.study.juhee.stringboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * 어노테이션 만들기2
 * HandlerMethodArgumentResolver 구현체 :: 한 가지 기능을 지원 :: 조건에 맞는 경우 메소드가 있다면 여기서 지정한 값으로 해당 메소드의 파라미터로 넘겨줄 수 있음!!
 * HandlerMethodArgumentResolver은 컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값을 바인딩해주는 인터페이스입니다.
 */
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    /* 얘는 조건검사하는 거 같고, */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {   // 컨트롤러 메서드에서 특정 파라미터를 조건 판단.
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;      // 조건1:파라미터에 @LoginUser 어노테이션이 붙어있는가?
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());       // 조건2:파라미터 클래스 타입이 SessionUser.class와 같은가?

        return isLoginUserAnnotation && isUserClass;
    }

    /* 여기서 지정한 값을 파라미터로 넘겨주겠구먼 */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
