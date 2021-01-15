package com.study.juhee.stringboot.config.auth;

import com.study.juhee.stringboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      // Spring Security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()        // h2-console화면을 사용하기 위해 해당 옵션들을 disable합니다(? p.181)
                .and()
                .authorizeRequests()    // URL별 권한 관리를 설정하는 옵션의 시작. 이걸 써야 antMatchers 옵션을 사용할 수 있음.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()      // '/'등 URL은 permitAll() 옵션으로 전체 열람 권한.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // POST메소드 '/api/v1/**'주소를 가진 API는 USER권한을 가진 사람들에게만 가능하게 함.
                .anyRequest().authenticated()       // anyRequest : 설정된 값 외 나머지 URL들을 나타냄.  // authenticated() : 나머지 URL들은 모두 인증된(로그인) 사용자들에게만 허용됨.
                .and()
                .logout()       // 로그아웃 기능 설정 시작점.
                .logoutSuccessUrl("/")  // 로그아웃 성공시 '/'주소로 이동.
                .and()
                .oauth2Login()  // OAuth2로그인 설정 시작점.
                .userInfoEndpoint() // OAuth2로그인 성공  후 사용자 정보를 가져올 때 설정들.
                .userService(customOAuth2UserService);  // 로그인 성공시 후속조치 진행할 userService인터페이스 구현체 등록
    }
}
